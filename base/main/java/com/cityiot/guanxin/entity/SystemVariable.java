package com.cityiot.guanxin.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.*;

/**
 * @author huangjinyong
 * 系统变量表
 */
@ApiModel(value="系统变量配置")
@Entity
@CnName("系统变量配置")
@Table(uniqueConstraints= {@UniqueConstraint(name="vartypeOnly",columnNames= {"vartype","variable"})})
public class SystemVariable extends BaseEntity {
    static final String T_SystemVaribale_varType = "T_SystemVaribale_varType";
    @ApiModelProperty(value="变量值",name="variable",example="")
    @CnName("变量值")
    @Column(nullable=false)
    private int variable;
    @ApiModelProperty(value="变量名称",name="name",example="")
    @CnName("变量名称")
    @Column(length=50,nullable=false)
    private String name;
    @ApiModelProperty(value="变量类型",name="vartype",example="")
    @CnName("变量类型")
    @Column(nullable=false)
    @JoinEntity(name="id",joinEntityClass = SystemVariableType.class, joinEntityAlias = T_SystemVaribale_varType)
    private long vartype;
    @ApiModelProperty(value="变量类型名",name="vartypeName",example="")
    @Transient
    @CnName("变量类型名")
    @FieldPath(name="name",joinEntityClass = SystemVariableType.class, joinEntityAlias = T_SystemVaribale_varType)
    private String vartypeName;


    public int getVariable() {
        return variable;
    }
    public void setVariable(int variable) {
        this.variable = variable;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getVartype() {
        return vartype;
    }

    public void setVartype(long vartype) {
        this.vartype = vartype;
    }

    public String getVartypeName() {
        return vartypeName;
    }
    public void setVartypeName(String vartypeName) {
        this.vartypeName = vartypeName;
    }
}
