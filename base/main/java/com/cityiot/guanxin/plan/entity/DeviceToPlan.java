package com.cityiot.guanxin.plan.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * @author Guoyz
 * 平面图设备关联表
 */
@ApiModel(value="平面图设备")
@CnName("平面图设备关联表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DeviceToPlan extends BaseEntity {
    public static final String T_Plan_DeviceToPlan = "T_Plan_DeviceToPlan";
    public static final String T_Deviceinformation_DeviceToPlan = "T_Deviceinformation_DeviceToPlan";

    @ApiModelProperty(value="平面图id",name="planId",example="")
    @CnName("平面图id")
    @JoinEntity(name = "id",joinEntityClass = Plan.class,joinEntityAlias = T_Plan_DeviceToPlan)
    private long planId;

    @ApiModelProperty(value="设备id",name="deviceId",example="")
    @CnName("设备id")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class,joinEntityAlias = T_Deviceinformation_DeviceToPlan)
    private long deviceId;

    @ApiModelProperty(value="x轴",name="x",example="")
    @CnName("x轴")
    private long x;
    @ApiModelProperty(value="y轴",name="y",example="")
    @CnName("y轴")
    private long y;
    @ApiModelProperty(value="设备显示宽度",name="devWidth",example="")
    @CnName("设备显示宽度")
    private long devWidth;
    @ApiModelProperty(value="设备显示高度",name="devHeight",example="")
    @CnName("设备显示高度")
    private long devHeight;
    @ApiModelProperty(value="图层次序",name="layerOrder",example="")
    @CnName("图层次序")
    private long layerOrder;
    @ApiModelProperty(value="图层类型",name="layerType",example="")
    @CnName("图层类型")
    private String layerType;
    @ApiModelProperty(value="是否上锁",name="planLock",example="")
    @CnName("是否上锁")
    private long planLock;//1为上锁，0为不上锁
    @ApiModelProperty(value="旋转角度",name="planAngle",example="")
    @CnName("旋转角度")
    private long planAngle;


    //@CnName("平面图名称")
    //@Transient
    //@FieldPath(name = "name",joinEntityClass = Plan.class)
    //private String planName;
    //@CnName("平面图备注")
    //@Transient
    //@FieldPath(name = "remark",joinEntityClass = Plan.class)
    //private String planRemark;
    @ApiModelProperty(value="设备类型Id",name="deviceTypeId",example="")
    @CnName("设备类型Id")
    @Transient
    @FieldPath(name = "deviceTypeId",joinEntityClass = Deviceinformation.class,joinEntityAlias = T_Deviceinformation_DeviceToPlan)
    private long deviceTypeId;
    @ApiModelProperty(value="设备名称",name="deviceName",example="")
    @CnName("设备名称")
    @Transient
    @FieldPath(name = "deviceName",joinEntityClass = Deviceinformation.class,joinEntityAlias = T_Deviceinformation_DeviceToPlan)
    private String deviceName;

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getDevWidth() {
        return devWidth;
    }

    public void setDevWidth(long devWidth) {
        this.devWidth = devWidth;
    }

    public long getDevHeight() {
        return devHeight;
    }

    public void setDevHeight(long devHeight) {
        this.devHeight = devHeight;
    }

    public long getLayerOrder() {
        return layerOrder;
    }

    public void setLayerOrder(long layerOrder) {
        this.layerOrder = layerOrder;
    }

    public String getLayerType() {
        return layerType;
    }

    public void setLayerType(String layerType) {
        this.layerType = layerType;
    }

    public long getPlanLock() {
        return planLock;
    }

    public void setPlanLock(long planLock) {
        this.planLock = planLock;
    }

    public long getPlanAngle() {
        return planAngle;
    }

    public void setPlanAngle(long planAngle) {
        this.planAngle = planAngle;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
