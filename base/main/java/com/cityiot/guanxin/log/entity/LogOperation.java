package com.cityiot.guanxin.log.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.BaseEntity;

import swallow.framework.jpaquery.repository.annotations.CnName;

@ApiModel(value="操作日志")
@CnName("操作日志")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LogOperation  extends BaseEntity {
	    private Long   id;
		@ApiModelProperty(value="操作人账号",name="identity",example="")
	    @CnName("操作人账号")
	    private String identity ; // 操作人账号
		@ApiModelProperty(value="客户端ip",name="clientIp",example="")
	    @CnName("客户端ip")
	    private String clientIp ; // 客户端ip
		@ApiModelProperty(value="操作人姓名",name="username",example="")
		@CnName("操作人姓名")
	    private String username ; // 操作人姓名
		@ApiModelProperty(value="日志类型",name="operType",example="")
	    @CnName("日志类型 ")
	    private Long   operType ; // 日志类型，不准确，不建议使用 (1:新增操作;2:修改操作；3:删除操作;)
		@ApiModelProperty(value="操作的url",name="operUrl",example="")
	    @CnName("操作的url")
	    private String operUrl ; // 操作的url
		@ApiModelProperty(value="操作事件",name="operEvent",example="")
	    @CnName("操作事件")
	    private String operEvent ; // 操作事件
		@ApiModelProperty(value="请求方式：POST或者GET",name="reqType",example="")
	    @CnName("请求方式：POST或者GET")
	    private String reqType ; // 请求方式：POST或PUT或DELETE或GET
		@ApiModelProperty(value="操作时间",name="operTime",example="")
	    @CnName("操作时间 ")
	    private Date   operTime ; // 操作时间
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getIdentity() {
			return identity;
		}
		public void setIdentity(String identity) {
			this.identity = identity;
		}
		public String getClientIp() {
			return clientIp;
		}
		public void setClientIp(String clientIp) {
			this.clientIp = clientIp;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Long getOperType() {
			return operType;
		}
		public void setOperType(Long operType) {
			this.operType = operType;
		}
		public String getOperUrl() {
			return operUrl;
		}
		public void setOperUrl(String operUrl) {
			this.operUrl = operUrl;
		}
		public String getOperEvent() {
			return operEvent;
		}
		public void setOperEvent(String operEvent) {
			this.operEvent = operEvent;
		}
		
		public String getReqType() {
			return reqType;
		}
		public void setReqType(String reqType) {
			this.reqType = reqType;
		}
		public Date getOperTime() {
			return operTime;
		}
		public void setOperTime(Date operTime) {
			this.operTime = operTime;
		}
	    
	    
	    
	    
	    
	    
}
