package com.cityiot.guanxin.user.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 用户
 * @author huangjinyong
 */
@ApiModel(value="系统用户对象")
@CnName("用户")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    // 姓名
	@ApiModelProperty(value="姓名",name="name",example="")
    @CnName("姓名")
    private String name;
    // 用户名
	@ApiModelProperty(value="用户名",name="username",example="")
    @CnName("用户名")
    @Column(unique = true)
    private String username;
	@ApiModelProperty(value="性别",name="sex",example="0为女 1为男")
    @CnName("性别")
    // 0为女 1为男
    private int sex;
    // 手机号码
	@ApiModelProperty(value="手机号码",name="phone",example="")
    @CnName("手机号")
    @Column(unique = true)
    private String phone;
    // 密码
	@ApiModelProperty(value="密码",name="password",example="")
    @CnName("密码")
    @Column(nullable = false)
    private String password;
    // 是否禁用
	@ApiModelProperty(value="是否禁用",name="disable",example="")
    @CnName("是否禁用")
    private int disable;
    @CnName("app token")
    private String token;
    @ApiModelProperty(value="用户头像",name="headPortrait",example="")
    @CnName("用户头像")
    private String headPortrait;
    
    @ApiModelProperty(value="帐号ID",name="accountid",example="")
    @CnName("帐号ID")
    private Long accountid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}
    
    
    
    
}
