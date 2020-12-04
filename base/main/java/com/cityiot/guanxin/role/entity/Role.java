package com.cityiot.guanxin.role.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

/**
 * 角色
 * @author huangjinyong
 */
@ApiModel(value="角色对象")
@CnName("角色")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {
    // 角色名称
	@ApiModelProperty(value="角色名称",name="name",example="")
    @CnName("角色名称")
    private String name;
	@ApiModelProperty(value="角色中文名称",name="cnName",example="")
    @CnName("角色中文名称")
    private String cnName;
    // 角色唯一标识
	@ApiModelProperty(value="角色唯一标识",name="code",example="")
    @CnName("角色唯一标识")
    private String code;
	@ApiModelProperty(value="角色描述",name="description",example="")
    @CnName("角色描述")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
