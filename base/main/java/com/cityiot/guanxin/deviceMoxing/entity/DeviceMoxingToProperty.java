package com.cityiot.guanxin.deviceMoxing.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="设备模型与属性关联表")
@Entity
@CnName("设备模型与属性关联表")
@EntityListeners(AuditingEntityListener.class)
public class DeviceMoxingToProperty extends BaseEntity {

    @ApiModelProperty(value="设备模型ID",name="deviceMoxingId",example="")
    @CnName("设备模型ID")
    private long deviceMoxingId;

    @ApiModelProperty(value="设备模型属性ID",name="propertyId",example="")
    @CnName("设备模型属性ID")
    private long propertyId;

    public long getDeviceMoxingId() {
        return deviceMoxingId;
    }

    public void setDeviceMoxingId(long deviceMoxingId) {
        this.deviceMoxingId = deviceMoxingId;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }
}
