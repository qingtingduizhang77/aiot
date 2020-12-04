package com.cityiot.guanxin.deviceGroup.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author zhengjc
 * 设备与分组关联表
 */
@ApiModel(value="设备与分组")
@CnName("设备与分组关联表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DeviceGroupRelation extends BaseEntity {

    @ApiModelProperty(value="设备分组ID",name="deviceGroupId",example="")
    @CnName("设备分组ID")
    private long deviceGroupId;

    @ApiModelProperty(value="设备ID",name="deviceId",example="")
    @CnName("设备ID")
    private long deviceId;

    public long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }
}
