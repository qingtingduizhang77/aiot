package com.cityiot.guanxin.light.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huangjinyong
 * 路灯控制规则实体
 */
@ApiModel(value="路灯控制规则")
@Entity
@CnName("路灯控制规则实体")
@EntityListeners(AuditingEntityListener.class)
public class LightControlRule extends BaseEntity {
    @ApiModelProperty(value="规则名称",name="name",example="")
    @CnName("规则名称")
    private String name;
    @ApiModelProperty(value="规则编号",name="code",example="")
    @CnName("规则编号")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
