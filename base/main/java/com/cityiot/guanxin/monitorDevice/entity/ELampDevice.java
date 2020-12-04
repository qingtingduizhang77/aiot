package com.cityiot.guanxin.monitorDevice.entity;

import com.cityiot.guanxin.common.entity.OnlyIdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="路灯设备")
@CnName("路灯设备")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ELampDevice extends OnlyIdEntity {
    @ApiModelProperty(value="设备信息ID",name="deviceInfoId",example="")
    @CnName("设备信息ID")
    private long deviceInfoId;
    @ApiModelProperty(value="开关状态",name="switchStatus",example="")
    @CnName("开关状态") //1正常 2离线
    private int switchStatus;
    @ApiModelProperty(value="亮度",name="brightness",example="")
    @CnName("亮度")
    private Integer brightness;

    public long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }

    public int getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(int switchStatus) {
        this.switchStatus = switchStatus;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }
}
