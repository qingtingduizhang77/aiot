package com.cityiot.guanxin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

@ApiModel(value="用户视图")
@CnName("用户视图")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Userview  extends BaseEntity{
	 //帐号
	@ApiModelProperty(value="帐号",name="account",example="")
    @CnName("帐号")
    @Column(unique = true)
    private String account;
    
    //1:系统2运维
	@ApiModelProperty(value="类型",name="type",example="1:系统2运维")
    @CnName("类型")
    private int type;

    //用户id
	@ApiModelProperty(value="用户id",name="did",example="")
    @CnName("用户id")
    private int did;
    
    //用户名
	@ApiModelProperty(value="用户名",name="username",example="")
    @CnName("用户名")
    @Column
    private String username;
    
	@ApiModelProperty(value="头像",name="headPortrait",example="")
    @CnName("头像")
    private String headPortrait;
    
    // 是否禁用
	@ApiModelProperty(value="是否禁用",name="disable",example="")
    @CnName("是否禁用")
    private int disable;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public int getDisable() {
		return disable;
	}

	public void setDisable(int disable) {
		this.disable = disable;
	}
    
    
    
    
}
