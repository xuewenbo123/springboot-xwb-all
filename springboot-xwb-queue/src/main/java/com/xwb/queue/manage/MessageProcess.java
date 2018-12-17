package com.xwb.queue.manage;

import com.xwb.queue.common.DetailResponse;

/**
 * @Description queue发送执行
 * @Author Mr.Xue
 */
public interface MessageProcess<T> {

    /**
     * @Description
     * @param message
     * @return DetailResponse
     */
    DetailResponse process(T message);


}
