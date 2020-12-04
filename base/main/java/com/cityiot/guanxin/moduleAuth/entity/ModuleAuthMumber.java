package com.cityiot.guanxin.moduleAuth.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.OnlyIdEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

@ApiModel(value="模块授权成员")
@CnName("模块授权成员")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ModuleAuthMumber  extends OnlyIdEntity {
	
	
	 @ApiModelProperty(value="成员ID",name="memberId",example="")
	 @CnName("模块编码")
	 private Long memberId;//MEMBER_ID;//成员ID
	 
	 @ApiModelProperty(value="成员名称",name="memberName",example="")
	 @CnName("成员名称")
	 private String memberName;// 成员名称
	 
	 
	 @ApiModelProperty(value="模块授权ID",name="moAuthId",example="")
	 @CnName("模块授权ID")
	 private Long moAuthId;//`MO_AUTH_ID`;//模块授权ID
	 
	 @ApiModelProperty(value="成员类型",name="memberType",example="")
	 @CnName("成员类型")
	 private int memberType;// `MEMBER_TYPE`;//成员类型 1：角色 2：人员；
	 
	 
	 @ApiModelProperty(value="状态",name="state",example=" 0:普通;1：编辑时不可见，不可删除")
	 @CnName("状态")
	 private int state;// 状态0:普通;1：编辑时不可见，不可删除
	 
	 @ApiModelProperty(value="机构id_",name="orgId",example="")
	 @CnName("机构id_")
	 private Long orgId;//机构id_

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMoAuthId() {
		return moAuthId;
	}

	public void setMoAuthId(Long moAuthId) {
		this.moAuthId = moAuthId;
	}

	public int getMemberType() {
		return memberType;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	 
	 
	 
	 
	 

}
