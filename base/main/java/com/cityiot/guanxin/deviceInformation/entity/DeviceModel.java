package com.cityiot.guanxin.deviceInformation.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
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

@ApiModel(value="设备型号")
@Entity
@CnName("设备型号")
@EntityListeners(AuditingEntityListener.class)
public class DeviceModel extends BaseEntity {

    @ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
    @CnName("设备类型ID")
    @Column(nullable = false)
    @JoinEntity(name = "id",joinEntityClass = DeviceType.class)
    private Long deviceTypeId;

    @ApiModelProperty(value="设备类型",name="deviceTypeName",example="")
    @CnName("设备类型")
    @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
    @Transient
    private String deviceTypeName;

    @ApiModelProperty(value="设备品牌ID",name="deviceBrandId",example="")
    @CnName("设备品牌ID")
    @Column(nullable = false)
    @JoinEntity(name = "id",joinEntityClass = DeviceBrand.class)
    private Long deviceBrandId;

    @ApiModelProperty(value="设备厂家",name="deviceManufacturer",example="")
    @CnName("设备厂家")
    @Transient
    @FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
    private String deviceManufacturer;

    @ApiModelProperty(value="设备品牌",name="deviceBrandName",example="")
    @CnName("设备品牌")
    @Transient
    @FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
    private String deviceBrandName;

    @ApiModelProperty(value="设备型号",name="deviceModel",example="")
    @CnName("设备型号")
    @Column(nullable = false)
    private String deviceModel;

    @ApiModelProperty(value="维保周期",name="maintenanceCycle",example="")
    @CnName("维保周期")
    private Integer maintenanceCycle;

    @ApiModelProperty(value="巡检周期",name="checkUpCycle",example="")
    @CnName("巡检周期")
    private Integer checkUpCycle;
    

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getDeviceBrandName() {
        return deviceBrandName;
    }

    public void setDeviceBrandName(String deviceBrandName) {
        this.deviceBrandName = deviceBrandName;
    }

    public Long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Long getDeviceBrandId() {
        return deviceBrandId;
    }

    public void setDeviceBrandId(Long deviceBrandId) {
        this.deviceBrandId = deviceBrandId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Integer getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(Integer maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

	public Integer getCheckUpCycle() {
		return checkUpCycle;
	}

	public void setCheckUpCycle(Integer checkUpCycle) {
		this.checkUpCycle = checkUpCycle;
	}
    
    
}
