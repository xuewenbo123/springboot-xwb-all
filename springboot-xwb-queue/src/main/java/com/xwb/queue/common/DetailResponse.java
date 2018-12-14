package com.xwb.queue.common;

/**
 * @Description 消息发送返回结果类
 * @Author Mr.Xue
 */
public class DetailResponse {
    /**
     * 是否成功
     */
    Boolean isSuccess;

    /**
     * 错误信息
     */
    String errMsg;

    public DetailResponse() {
    }

    public DetailResponse(Boolean isSuccess, String errMsg) {
        this.isSuccess = isSuccess;
        this.errMsg = errMsg;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
