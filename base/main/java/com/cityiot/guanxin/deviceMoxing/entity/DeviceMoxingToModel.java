package com.cityiot.guanxin.deviceMoxing.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="设备模型与设备型号关联表")
@Entity
@CnName("设备模型与设备型号关联表")
@EntityListeners(AuditingEntityListener.class)
public class DeviceMoxingToModel extends BaseEntity {

    @ApiModelProperty(value="设备模型ID",name="deviceMoxingId",example="")
    @CnName("设备模型ID")
    private long deviceMoxingId;

    @ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
    @CnName("设备型号ID")
    private long deviceModelId;

    @ApiModelProperty(value="设备型号",name="deviceModel",example="")
    @CnName("设备型号")
    private String deviceModel;

    public long getDeviceMoxingId() {
        return deviceMoxingId;
    }

    public void setDeviceMoxingId(long deviceMoxingId) {
        this.deviceMoxingId = deviceMoxingId;
    }

    public long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
