package com.xwb.common.sms;

public interface SmsManager {

    /**
     * 发送短信消息
     * @return
     */
    Boolean sendMsg();


    /**
     * 校验验证码
     * @return
     */
    Boolean verifyCode();



}
