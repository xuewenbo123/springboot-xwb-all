package com.xwb.api.model;

import java.io.Serializable;

public class JobTask implements Serializable{

    private Long id;

    private String content;
    /**
     * 0-未执行
     * 1-已执行
     */

    private Integer status;

    private Long sendTime;

    public JobTask() {
    }

    public JobTask(String content, Integer status, Long sendTime) {
        this.content = content;
        this.status = status;
        this.sendTime = sendTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }
}
