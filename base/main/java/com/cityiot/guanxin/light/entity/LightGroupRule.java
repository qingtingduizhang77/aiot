package com.cityiot.guanxin.light.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huangjinyong
 * 路灯规则实体
 */
@ApiModel(value="路灯规则")
@CnName("路灯规则实体")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LightGroupRule extends Rule {
    @ApiModelProperty(value="分组Id",name="groupId",example="")
    @CnName("分组Id")
    private long groupId;


    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
