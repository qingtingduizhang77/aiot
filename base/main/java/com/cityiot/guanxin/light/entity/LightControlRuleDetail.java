package com.cityiot.guanxin.light.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huangjinyong
 */
@ApiModel(value="路灯规则详情控制")
@CnName("路灯规则详情控制实体")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LightControlRuleDetail extends Rule {
    @ApiModelProperty(value="规则编号",name="name",example="")
    @CnName("规则编号")
    private String code;
    @ApiModelProperty(value="路灯控制规则ID",name="lightControlRuleId",example="")
    private long lightControlRuleId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getLightControlRuleId() {
        return lightControlRuleId;
    }

    public void setLightControlRuleId(long lightControlRuleId) {
        this.lightControlRuleId = lightControlRuleId;
    }
}
