package com.cityiot.guanxin.common.ipush.entity;

//个推的发送消息实体类(推送消息模板)
public class GeTuiBuilder {
    //标题
    //public final String MESSAGE_TITLE ="messageTitle";
    //内容
    //public final String MESSAGE_CONTENT ="messageContent";

    
    private long accountId;
    //发送的目标账户
    private String clientId;
    //发送的内容(透传内容)(json格式，需要有"messageTitle"，"messageContent"字段)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

  
}
