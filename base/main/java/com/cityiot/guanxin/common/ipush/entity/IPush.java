package com.cityiot.guanxin.common.ipush.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.user.entity.Accountinfo;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.*;
import java.util.Date;


@CnName("组织")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class IPush extends BaseEntity {
    @CnName("用户id")
    @JoinEntity(name = "id", joinEntityClass = Accountinfo.class)
    private long AccountId;
    @CnName("客户端标识id")
    private String clientId;
    @CnName("消息内容")
    private String message;
    @CnName("发送时间")
    private Date pushTime;
    @CnName("状态")
    private long status;//0为成功，1为失败

    public long getAccountId() {
        return AccountId;
    }

    public void setAccountId(long accountId) {
        AccountId = accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

}
