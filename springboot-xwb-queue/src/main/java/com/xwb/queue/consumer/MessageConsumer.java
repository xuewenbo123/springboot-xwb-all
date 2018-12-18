package com.xwb.queue.consumer;

import com.xwb.queue.common.DetailResponse;

/**
 * @Description queue的消费者
 * @Author Mr.Xue
 */
public interface MessageConsumer {

    /**
     * 消息消费
     * @return DetailResponse
     */
    DetailResponse consume();


}
