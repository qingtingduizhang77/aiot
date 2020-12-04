package com.cityiot.guanxin.alarmRule.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxing;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;


/**
 * 当指定模型的其中一个设备满足公式要求时，发出通知给指定的角色人员。
 * @author Guoyz
 * 告警规则
 */
@ApiModel(value="告警规则")
@CnName("告警规则")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AlarmRule extends BaseEntity {
    public static final String T_DeviceMoxing_AlarmRule = "T_DeviceMoxing_AlarmRule";
    @ApiModelProperty
    @CnName("告警规则名称")
    private String name;
    @ApiModelProperty
    @CnName("设备模型id")
    @JoinEntity(name = "id",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_AlarmRule)
    @Column(nullable = false)
    private Long deviceParameterId;
    @ApiModelProperty
    @CnName("设备模型名称")
    @Transient
    @FieldPath(name = "name",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_AlarmRule)
    private String deviceParameterName;
    @ApiModelProperty
    @CnName("公式组合")     //JSON数组：[{"name":"属性名称","value":"大气压"},{"name":"运算","value":">"},...]
    private String math;
    @ApiModelProperty
    @Column(columnDefinition="TEXT")
    @CnName("公式预览")     //(Atmos>60)&&(Atom!=70)
    private String preview;
    @ApiModelProperty
    @CnName("告警类型")     //10:故障,20:警报,30:其他
    @Column(nullable = false)
    private Long alarmType;
    @ApiModelProperty
    @CnName("告警级别")     //10:紧急,20:重要,30:一般
    @Column(nullable = false)
    private Long alarmLevel;
    @ApiModelProperty
    @CnName("备注")
    private String remark;
    @ApiModelProperty
    @CnName("通知人员角色ids")
    private String roleIds; //用逗号分隔
    @ApiModelProperty
    @CnName("通知人员角色名称")
    private String roleNames;//用#&#分隔
    @ApiModelProperty
    @CnName("告警状态")
    private int status;     //1-有效，0-禁用。默认1有效
    @ApiModelProperty
    @CnName("Code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeviceParameterId() {
        return deviceParameterId;
    }

    public void setDeviceParameterId(Long deviceParameterId) {
        this.deviceParameterId = deviceParameterId;
    }

    public String getDeviceParameterName() {
        return deviceParameterName;
    }

    public void setDeviceParameterName(String deviceParameterName) {
        this.deviceParameterName = deviceParameterName;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Long getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Long alarmType) {
        this.alarmType = alarmType;
    }

    public Long getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Long alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
