package com.cityiot.guanxin.common.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SmsBuilder implements Serializable {

    public String title;//使用系统签名
    public String messageCode;//消息模板编号
    public String phone;//发送消息的手机号码
    public Map<String, String> params = new HashMap<>();//模板对应的参数
    public SmsBuilder() {
    }
    public SmsBuilder(String title, String messageCode) {
        this.title =title;
        this.messageCode = messageCode;
        this.params = new HashMap<>();
    }

    public SmsBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public SmsBuilder addParam(String key, String value) {
        this.params.put(key, value);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getPhone() {
        return phone;
    }
}
