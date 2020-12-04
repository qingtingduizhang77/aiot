package com.cityiot.guanxin.alarmRule.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author Guoyz
 * 告警规则与角色中间表
 */
@ApiModel(value="告警规则与角色中间表")
@CnName("告警规则与角色中间表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AlarmRuleToRole extends BaseEntity {
    @CnName("告警规则id")
    @ApiModelProperty
    private long alarmRuleId;
    @CnName("角色id")
    @ApiModelProperty
    private long roleId;

    public long getAlarmRuleId() {
        return alarmRuleId;
    }

    public void setAlarmRuleId(long alarmRuleId) {
        this.alarmRuleId = alarmRuleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
