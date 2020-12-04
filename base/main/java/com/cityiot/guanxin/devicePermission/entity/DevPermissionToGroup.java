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
 * 设备权限与分组关联权限表
 */
@ApiModel(value="设备权限与分组")
@CnName("设备权限与分组关联权限表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DevPermissionToGroup extends BaseEntity {

    @ApiModelProperty(value="设备权限ID",name="devicePermissionId",example="")
    @CnName("设备权限ID")
    private long devicePermissionId;

    @ApiModelProperty(value="设备分组id",name="deviceGroupId",example="")
    @CnName("设备分组id")
    private long deviceGroupId;

    public long getDevicePermissionId() {
        return devicePermissionId;
    }

    public void setDevicePermissionId(long devicePermissionId) {
        this.devicePermissionId = devicePermissionId;
    }

    public long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }
}
