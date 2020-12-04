package com.cityiot.guanxin.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;

@ApiModel(value="系统变量类型")
@Entity
@CnName("系统变量类型")
public class SystemVariableType extends BaseEntity {
    @ApiModelProperty(value="类型名",name="name",example="")
    @CnName("类型名")
    @Column(length=30,nullable=false,unique=true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
