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
 * 设备权限与操作角色权限关联表
 */
@ApiModel(value="设备权限与操作角色")
@CnName("设备权限与操作角色权限关联表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DevPermissionToOperRole extends BaseEntity {

    @ApiModelProperty(value="设备权限ID",name="devicePermissionId",example="")
    @CnName("设备权限ID")
    private long devicePermissionId;

    @ApiModelProperty(value="操作角色ID",name="operRoleId",example="")
    @CnName("操作角色ID")
    private long operRoleId;

    public long getDevicePermissionId() {
        return devicePermissionId;
    }

    public void setDevicePermissionId(long devicePermissionId) {
        this.devicePermissionId = devicePermissionId;
    }

    public long getOperRoleId() {
        return operRoleId;
    }

    public void setOperRoleId(long operRoleId) {
        this.operRoleId = operRoleId;
    }
}
