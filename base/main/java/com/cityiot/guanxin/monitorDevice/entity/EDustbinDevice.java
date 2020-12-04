package com.cityiot.guanxin.monitorDevice.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
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
public class EDustbinDevice extends OnlyIdEntity {
    @ApiModelProperty(value="设备信息ID",name="deviceInfoId",example="")
    @CnName("设备信息ID")
    private long deviceInfoId;
    @ApiModelProperty(value="设备状态",name="DustbinStatus",example="")
    @CnName("设备状态") //1 正常 2 离线
    private int DustbinStatus;
    @ApiModelProperty(value="使用状态",name="useStatus",example="")
    @CnName("使用状态") //1 未满 2 已满
    private int useStatus;

    public long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }

    public int getDustbinStatus() {
        return DustbinStatus;
    }

    public void setDustbinStatus(int dustbinStatus) {
        DustbinStatus = dustbinStatus;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }
}
