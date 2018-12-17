package com.xwb.queue.manage;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.xwb.queue.common.DetailResponse;
import com.xwb.queue.common.RabbitConstants;
import com.xwb.queue.common.RabbitmqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description rabbitmq的builder类
 * @Author Mr.Xue
 */
@Slf4j
public class RabbitmqBuilder {

    protected Logger log = Logger.getLogger(RabbitmqBuilder.class);

    private ConnectionFactory connectionFactory;

    public RabbitmqBuilder(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private void buildQueue(String exchange, String routingKey, final String queue, Connection connection, String type) throws IOException {
        Channel channel = connection.createChannel(false);
        if (type.equals("direct")) {
            channel.exchangeDeclare(exchange, "direct", true, false, null);
        } else if (type.equals("topic")) {
            channel.exchangeDeclare(exchange, "topic", true, false, null);
        }
        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, routingKey);
        try {
            channel.close();
        } catch (TimeoutException e) {
            log.info("close channel time out ", e);
        }
    }

    private void buildTopic(String exchange, Connection connection) throws IOException {
        Channel channel = connection.createChannel(false);
        channel.exchangeDeclare(exchange, "topic", true, false, null);
    }

    public MessageSender buildDirectMessageSender(final String exchange, final String routingKey, final String queue) throws IOException {
        return buildMessageSender(exchange, routingKey, queue, "direct");
    }

    public MessageSender buildTopicMessageSender(final String exchange, final String routingKey) throws IOException {
        return buildMessageSender(exchange, routingKey, null, "topic");
    }



    /**
     * 1 构造template, exchange, routingkey等
     * 2 设置message序列化方法
     * 3 设置发送确认
     * 4 构造sender方法
     */
    public MessageSender buildMessageSender(final String exchange, final String routingKey,
                                            final String queue, final String type) throws IOException {
        Connection connection = connectionFactory.createConnection();
        //1
        if (type.equals("direct")) {
            buildQueue(exchange, routingKey, queue, connection, "direct");
        } else if (type.equals("topic")) {
            buildTopic(exchange, connection);
        }
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(routingKey);
        //2
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        RetryCache retryCache = new RetryCache();
        //3
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.info("send message failed: " + cause + correlationData.toString());
            } else {
                retryCache.del(Long.valueOf(correlationData.getId()));
            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, tmpExchange, tmpRoutingKey) -> {
            try {
                Thread.sleep(RabbitConstants.ONE_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("send message failed: " + replyCode + " " + replyText);
            rabbitTemplate.send(message);
        });
        //4
        return new MessageSender() {
            {
                retryCache.setSender(this);
            }
            @Override
            public DetailResponse send(Object message) {
                long id = retryCache.generateId();
                long time = System.currentTimeMillis();

                return send(new RabbitmqMessage(id, time, message));
            }

            @Override
            public DetailResponse send(RabbitmqMessage messageWithTime) {
                try {
                    retryCache.add(messageWithTime);
                    rabbitTemplate.correlationConvertAndSend(messageWithTime.getMessage(),
                            new CorrelationData(String.valueOf(messageWithTime.getId())));
                } catch (Exception e) {
                    return new DetailResponse(false, "");
                }

                return new DetailResponse(true, "");
            }
        };
    }

















    /**
     * for test
     * @param queue
     * @return
     * @throws IOException
     */
    public int getMessageCount(final String queue) throws IOException {
        Connection connection = connectionFactory.createConnection();
        final Channel channel = connection.createChannel(false);
        final AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive(queue);
        return declareOk.getMessageCount();
    }

}
