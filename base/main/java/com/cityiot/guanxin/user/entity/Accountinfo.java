package com.cityiot.guanxin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

@ApiModel(value="帐户对象")
@CnName("帐户")
@Entity
public class Accountinfo  extends BaseEntity {
	
	 // 帐号
	@ApiModelProperty(value="帐号",name="account",example="")
    @CnName("帐号")
    @Column(unique = true)
    private String account;
    
    // 密码
	@ApiModelProperty(value="密码",name="password",example="")
    @CnName("密码")
    @Column(nullable = false)
    private String password;
    
    //1:系统2运维
	@ApiModelProperty(value="类型",name="type",example="1:系统2运维")
    @CnName("类型")
    private int type;

    //用户id
	@ApiModelProperty(value="用户id",name="did",example="")
    @CnName("用户id")
    private int did;
    
    
    //pctoken
	@ApiModelProperty(value="pctoken",name="pctoken",example="")
    @CnName("pctoken")
    @Column
    private String pctoken;
    
    //apptoken
	@ApiModelProperty(value="apptoken",name="apptoken",example="")
    @CnName("apptoken")
    @Column
    private String apptoken;
    
    
    // 是否禁用
	@ApiModelProperty(value="是否禁用",name="disable",example="")
    @CnName("是否禁用")
    private int disable;
    
	@ApiModelProperty(value="联系电话",name="phone",example="")
    @CnName("联系电话")
    @Column(unique = true)
    private String phone;

	@ApiModelProperty(value="客户端推送标识",name="clientId",example="")
	@CnName("客户端推送标识")
	private String clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPctoken() {
		return pctoken;
	}

	public void setPctoken(String pctoken) {
		this.pctoken = pctoken;
	}

	public String getApptoken() {
		return apptoken;
	}

	public void setApptoken(String apptoken) {
		this.apptoken = apptoken;
	}

	public int getDisable() {
		return disable;
	}

	public void setDisable(int disable) {
		this.disable = disable;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    
    

}
