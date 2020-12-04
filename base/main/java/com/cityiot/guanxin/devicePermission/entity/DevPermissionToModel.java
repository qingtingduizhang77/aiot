package com.cityiot.guanxin.devicePermission.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author zhengjc
 * 设备权限与型号关联权限表
 */
@ApiModel(value="设备权限与型号")
@CnName("设备权限与型号关联权限表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DevPermissionToModel extends BaseEntity {

    @ApiModelProperty(value="设备权限ID",name="devicePermissionId",example="")
    @CnName("设备权限ID")
    private long devicePermissionId;

    @ApiModelProperty(value="设备型号id",name="deviceModelId",example="")
    @CnName("设备型号id")
    private long deviceModelId;

    public long getDevicePermissionId() {
        return devicePermissionId;
    }

    public void setDevicePermissionId(long devicePermissionId) {
        this.devicePermissionId = devicePermissionId;
    }

    public long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }
}
