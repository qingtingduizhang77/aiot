package com.cityiot.guanxin.monitor.control;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="设备统计")
public class DeviceCountVo {
	@ApiModelProperty(value="设备类型id",name="deviceTypeId",example="")
    private long deviceTypeId;
	@ApiModelProperty(value="统计数",name="deviceCount",example="")
    private long deviceCount;
	@ApiModelProperty(value="设备类型名称",name="deviceTypeName",example="")
    private String deviceTypeName;

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }
}
