package com.cityiot.guanxin.monitorDevice.entity;

import com.cityiot.guanxin.common.entity.OnlyIdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="充电桩设备")
@CnName("充电桩设备")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EChargingPileDevice extends OnlyIdEntity {
    @ApiModelProperty(value="设备信息ID",name="deviceInfoId",example="")
    @CnName("设备信息ID")
    private long deviceInfoId;
    @ApiModelProperty(value="状态",name="ChargingStatus",example="")
    @CnName("状态") //1 正常 2 离线
    private int ChargingStatus;
    @ApiModelProperty(value="使用状态",name="useStatus",example="")
    @CnName("使用状态") //1 空闲 2 充电中
    private int useStatus;

    public long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }

    public int getChargingStatus() {
        return ChargingStatus;
    }

    public void setChargingStatus(int chargingStatus) {
        ChargingStatus = chargingStatus;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }
}
