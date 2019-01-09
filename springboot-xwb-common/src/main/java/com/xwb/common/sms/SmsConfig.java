package com.xwb.common.sms;

import java.io.Serializable;

/**
 * @Description 短信服务配置类模型
 * @Author Mr.Xue
 */
public class SmsConfig implements Serializable{

    String appid;

    String signName;

    String region;

    String accessKeyId;

    String accessKeySecret;

    SmsProvider provider;

    SmsConfigStatus status;

    public SmsConfigStatus getStatus() {
        return status;
    }

    public void setStatus(SmsConfigStatus status) {
        this.status = status;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public SmsProvider getProvider() {
        return provider;
    }

    public void setProvider(SmsProvider provider) {
        this.provider = provider;
    }
}
