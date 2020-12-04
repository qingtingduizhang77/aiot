package com.cityiot.guanxin.message.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

@ApiModel(value="消息中心")
@CnName("消息中心")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EMessageCenter extends BaseEntity {
    @ApiModelProperty(value="消息内容",name="messageContent",example="")
    @CnName("消息内容")
    private String messageContent;
    @ApiModelProperty(value="消息类型",name="messageType",example="")
    @CnName("消息类型") //1创建工单 2指派工单
    private int messageType;
    @ApiModelProperty(value="消息状态",name="messageStatus",example="")
    @CnName("消息状态") //1未读 2已读 3删除
    private int messageStatus;
    @ApiModelProperty(value="消息时间",name="messageTime",example="")
    @CnName("消息时间")
    private Date messageTime;
    @ApiModelProperty(value="目标ID",name="targetId",example="")
    @CnName("目标ID")
    private long targetId;
    @ApiModelProperty(value="目标url",name="url",example="")
    @CnName("目标url")
    private String url;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
