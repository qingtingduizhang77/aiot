package com.cityiot.guanxin.deviceInformation.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="设备品牌")
@Entity
@CnName("设备品牌")
@EntityListeners(AuditingEntityListener.class)
public class DeviceBrand extends BaseEntity {

    @ApiModelProperty(value="设备厂家",name="deviceManufacturer",example="")
    @CnName("设备厂家")
    @Column(nullable = false)
    private String deviceManufacturer;

    @ApiModelProperty(value="设备品牌",name="deviceBrandName",example="")
    @CnName("设备品牌")
    @Column(nullable = false)
    private String deviceBrandName;

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
}
