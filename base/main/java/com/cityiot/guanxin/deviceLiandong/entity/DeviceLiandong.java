package com.cityiot.guanxin.deviceLiandong.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxing;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.*;

/**
 * 设备联动控制
 * @author Guoyz
 * createTime   2020/7/2 10:49
 */
@Entity
@CnName("设备联动控制")
@ApiModel(value="设备联动控制")
@EntityListeners(AuditingEntityListener.class)
public class DeviceLiandong extends BaseEntity {
    //关联设备模型表
    public static final String T_DeviceMoxing_DeviceLiandong_Source = "T_DeviceMoxing_DeviceLiandong_Source";
    public static final String T_DeviceMoxing_DeviceLiandong_Target = "T_DeviceMoxing_DeviceLiandong_Target";

    @CnName("名称")
    @ApiModelProperty(value="名称",name="name",example="")
    private String name;

    @CnName("源设备模型id")
    @ApiModelProperty(value="源设备模型id",name="sourceDeviceMoxingId",example="")
    @JoinEntity(name = "id",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_DeviceLiandong_Source)
    private Integer sourceDeviceMoxingId;

    @Transient
    @ApiModelProperty(value="源设备模型名称",name="sourceDeviceMoxingName",example="")
    @FieldPath(name = "name",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_DeviceLiandong_Source)
    private String sourceDeviceMoxingName;

    @Transient
    @ApiModelProperty(value="源设备型号ID",name="sourceDeviceModelIds",example="")
    @FieldPath(name = "deviceModelIds",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_DeviceLiandong_Source)
    private String sourceDeviceModelIds;

    @CnName("目标设备模型id")
    @ApiModelProperty(value="目标设备模型id",name="targetDeviceMoxingId",example="")
    @JoinEntity(name = "id",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_DeviceLiandong_Target)
    private Integer targetDeviceMoxingId;

    @Transient
    @ApiModelProperty(value="目标设备模型名称",name="targetDeviceMoxingName",example="")
    @FieldPath(name = "name",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_DeviceLiandong_Target)
    private String targetDeviceMoxingName;

    @Transient
    @ApiModelProperty(value="目标设备型号ID",name="tagetDeviceModelIds",example="")
    @FieldPath(name = "deviceModelIds",joinEntityClass = DeviceMoxing.class,joinEntityAlias = T_DeviceMoxing_DeviceLiandong_Target)
    private String tagetDeviceModelIds;

    @CnName("触发条件")
    @Column(columnDefinition="TEXT")
    @ApiModelProperty(value="触发条件",name="math",example="")
    private String math;

    @CnName("触发动作")
    @ApiModelProperty(value="code",name="code",example="触发动作")
    private String code;

    @CnName("状态")
    @ApiModelProperty(value="状态",name="status",example="1-启用，0-禁用")
    private Integer status;

    @CnName("备注")
    @ApiModelProperty(value="备注",name="remark",example="")
    private String remark;

    public String getSourceDeviceModelIds() {
        return sourceDeviceModelIds;
    }

    public void setSourceDeviceModelIds(String sourceDeviceModelIds) {
        this.sourceDeviceModelIds = sourceDeviceModelIds;
    }

    public String getTagetDeviceModelIds() {
        return tagetDeviceModelIds;
    }

    public void setTagetDeviceModelIds(String tagetDeviceModelIds) {
        this.tagetDeviceModelIds = tagetDeviceModelIds;
    }

    public String getSourceDeviceMoxingName() {
        return sourceDeviceMoxingName;
    }

    public void setSourceDeviceMoxingName(String sourceDeviceMoxingName) {
        this.sourceDeviceMoxingName = sourceDeviceMoxingName;
    }

    public String getTargetDeviceMoxingName() {
        return targetDeviceMoxingName;
    }

    public void setTargetDeviceMoxingName(String targetDeviceMoxingName) {
        this.targetDeviceMoxingName = targetDeviceMoxingName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSourceDeviceMoxingId() {
        return sourceDeviceMoxingId;
    }

    public void setSourceDeviceMoxingId(Integer sourceDeviceMoxingId) {
        this.sourceDeviceMoxingId = sourceDeviceMoxingId;
    }

    public Integer getTargetDeviceMoxingId() {
        return targetDeviceMoxingId;
    }

    public void setTargetDeviceMoxingId(Integer targetDeviceMoxingId) {
        this.targetDeviceMoxingId = targetDeviceMoxingId;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
