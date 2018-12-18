package com.xwb.queue.consumer;

import com.xwb.queue.common.DetailResponse;
import com.xwb.queue.common.RabbitConstants;
import com.xwb.queue.manage.MessageProcess;
import com.xwb.queue.manage.RabbitmqBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolConsumer<T> {

    protected Logger log = Logger.getLogger(RabbitmqBuilder.class);

    private ExecutorService executor;
    private volatile boolean stop = false;
    private final ThreadPoolConsumerBuilder<T> infoHolder;


    public static class ThreadPoolConsumerBuilder<T> {
        int threadCount;
        long intervalMils;
        RabbitmqBuilder mqAccessBuilder;
        String exchange;
        String routingKey;
        String queue;
        String type = "direct";
        MessageProcess<T> messageProcess;

        public ThreadPoolConsumerBuilder<T> setThreadCount(int threadCount) {
            this.threadCount = threadCount;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setIntervalMils(long intervalMils) {
            this.intervalMils = intervalMils;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setMQAccessBuilder(RabbitmqBuilder mqAccessBuilder) {
            this.mqAccessBuilder = mqAccessBuilder;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setExchange(String exchange) {
            this.exchange = exchange;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setRoutingKey(String routingKey) {
            this.routingKey = routingKey;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setQueue(String queue) {
            this.queue = queue;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setType(String type) {
            this.type = type;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setMessageProcess(MessageProcess<T> messageProcess) {
            this.messageProcess = messageProcess;

            return this;
        }

        public ThreadPoolConsumer<T> build() {
            return new ThreadPoolConsumer<T>(this);
        }
    }

    private ThreadPoolConsumer(ThreadPoolConsumerBuilder<T> threadPoolConsumerBuilder) {
        this.infoHolder = threadPoolConsumerBuilder;
        //手动创建Executors更好？
        executor = Executors.newFixedThreadPool(threadPoolConsumerBuilder.threadCount);
    }

    //1 构造messageConsumer
    //2 执行consume
    public void start() throws IOException {
        for (int i = 0; i < infoHolder.threadCount; i++) {
            //1
            final MessageConsumer messageConsumer = infoHolder.mqAccessBuilder.buildMessageConsumer(infoHolder.exchange,
                    infoHolder.routingKey, infoHolder.queue, infoHolder.messageProcess, infoHolder.type);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (!stop) {
                        try {
                            //2
                            DetailResponse detailRes = messageConsumer.consume();

                            if (infoHolder.intervalMils > 0) {
                                try {
                                    Thread.sleep(infoHolder.intervalMils);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    log.info("interrupt ", e);
                                }
                            }

                            if (!detailRes.getSuccess()) {
                                log.info("run error " + detailRes.getErrMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.info("run exception ", e);
                        }
                    }
                }
            });
        }
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }


    public void stop() {
        this.stop = true;
        try {
            Thread.sleep(RabbitConstants.ONE_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
