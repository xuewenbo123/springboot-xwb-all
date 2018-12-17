package com.xwb.queue.manage;

import com.xwb.queue.common.DetailResponse;
import com.xwb.queue.common.RabbitConstants;
import com.xwb.queue.common.RabbitmqMessage;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

public class RetryCache {

    protected Logger log = Logger.getLogger(RabbitmqBuilder.class);

    private MessageSender sender;
    private boolean stop = false;
    private Map<Long, RabbitmqMessage> map = new ConcurrentSkipListMap<>();
    private AtomicLong id = new AtomicLong();

    public void setSender(MessageSender sender) {
        this.sender = sender;
        startRetry();
    }

    public long generateId() {
        return id.incrementAndGet();
    }

    public void add(RabbitmqMessage messageWithTime) {
        map.putIfAbsent(messageWithTime.getId(), messageWithTime);
    }

    public void del(long id) {
        map.remove(id);
    }

    private void startRetry() {
        new Thread(() ->{
            while (!stop) {
                try {
                    Thread.sleep(RabbitConstants.RETRY_TIME_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long now = System.currentTimeMillis();

                for (Map.Entry<Long, RabbitmqMessage> entry : map.entrySet()) {
                    RabbitmqMessage messageWithTime = entry.getValue();

                    if (null != messageWithTime) {
                        if (messageWithTime.getTime() + 3 * RabbitConstants.VALID_TIME < now) {
                            log.info("send message {} failed after 3 min "+messageWithTime);
                            del(entry.getKey());
                        } else if (messageWithTime.getTime() + RabbitConstants.VALID_TIME < now) {
                            DetailResponse res = sender.send(messageWithTime);

                            if (!res.getSuccess()) {
                                log.info("retry send message failed {} errMsg {}"+messageWithTime+"========="+res.getErrMsg());
                            }
                        }
                    }
                }
            }
        }).start();
    }

}
