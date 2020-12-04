package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="运维人员/公司管理")
@Entity
@CnName("运维人员/公司管理")
@EntityListeners(AuditingEntityListener.class)
public class OperatorOrCompanyManage extends BaseEntity {

    @ApiModelProperty(value="姓名/公司",name="name",example="")
    @CnName("姓名/公司")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(value="公司",name="name",example="")
    @CnName("公司")
    @Column(nullable = false)
    private String companyname;

    @ApiModelProperty(value="性别",name="gender",example="")
    @CnName("性别")//1男2女
    private Integer gender;

    @ApiModelProperty(value="联系电话",name="phone",example="")
    @CnName("联系电话")
    @Column(unique = true)
    private String phone;

    @ApiModelProperty(value="在岗状态",name="status",example="")
    @CnName("在岗状态")//1在职2离职
    private Integer status;

    @ApiModelProperty(value="类型",name="operatorType",example="")
    @CnName("类型")
    @Column(nullable = false)//1员工2公司
    private Integer operatorType;

    @ApiModelProperty(value="禁用状态",name="disable",example="")
    @CnName("禁用状态")//1启动2禁用
    @Column(nullable = false)
    private  Integer disable;

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    @ApiModelProperty(value="账户",name="account",example="")
    @CnName("账户")
    @Column(nullable = false)
    private String account;

    @ApiModelProperty(value="密码",name="password",example="")
    @CnName("密码")
    @Column(nullable = false)
    private String password;

    @ApiModelProperty(value="token",name="token",example="")
    @CnName("token")
    private String token;

    @ApiModelProperty(value="头像",name="headPortrait",example="")
    @CnName("头像")
    private String headPortrait;

    @ApiModelProperty(value="帐号ID",name="accountid",example="")
    @CnName("帐号ID")
    private Long accountid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
