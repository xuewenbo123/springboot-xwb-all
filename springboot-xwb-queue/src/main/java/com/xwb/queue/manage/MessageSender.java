package com.xwb.queue.manage;

import com.xwb.queue.common.DetailResponse;
import com.xwb.queue.common.RabbitmqMessage;

public interface MessageSender {

    DetailResponse send(Object message);

    DetailResponse send(RabbitmqMessage message);

}
