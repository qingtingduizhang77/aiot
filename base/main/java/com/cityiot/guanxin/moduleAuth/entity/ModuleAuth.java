package com.cityiot.guanxin.moduleAuth.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.OnlyIdEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

@ApiModel(value="模块授权")
@CnName("模块授权")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ModuleAuth  extends OnlyIdEntity {
	
	 @ApiModelProperty(value="模块编码",name="moCode",example="")
	 @CnName("模块编码")
	 private String moCode;//模块;10001 告警信息管理员
	 
	 @ApiModelProperty(value="模块名称",name="name",example="")
	 @CnName("模块名称")
	 private String name;//模块名称
	 
	 @ApiModelProperty(value="机构id",name="orgId",example="")
	 @CnName("机构id")
	 private Long orgId;//机构id
	 
	 @ApiModelProperty(value="角色id",name="角色id",example="1,2,3")
	 @CnName("角色id")
	 @Transient
	 private String roleId;//角色id
	 
	 @ApiModelProperty(value="角色名称",name="角色名称",example="cc,bb,33")
	 @CnName("角色名称")
	 @Transient
	 private String roleName;//角色名称
	 
	 @ApiModelProperty(value="用户id",name="用户id_",example="1,2,3")
	 @CnName("用户id")
	 @Transient
	 private String userId;//用户id
	 
	 @ApiModelProperty(value="用户名称",name="用户名称",example="xxx,aaa,bbb")
	 @CnName("用户名称")
	 @Transient
	 private String userName;//用户名称
	 
	 

	public String getMoCode() {
		return moCode;
	}

	public void setMoCode(String moCode) {
		this.moCode = moCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	  
	 
	 
	 

}
