package com.xwb.queue.consumer;

import com.xwb.queue.common.DetailResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description queue的消费者
 * @Author Mr.Xue
 */
@Slf4j
public interface MessageConsumer {

    /**
     * 消息消费
     * @return DetailResponse
     */
    DetailResponse consume();


}
