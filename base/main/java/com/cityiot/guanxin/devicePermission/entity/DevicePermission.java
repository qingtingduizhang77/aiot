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
 * 设备权限管理
 */
@ApiModel(value="设备权限管理")
@CnName("设备权限管理")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DevicePermission extends BaseEntity {

    @ApiModelProperty(value="设备型号id",name="deviceModelId",example="")
    @CnName("设备型号id")
    private String deviceModelId;

    @ApiModelProperty(value="设备型号名称",name="deviceModelName",example="")
    @CnName("设备型号名称")
    private String deviceModelName;

    @ApiModelProperty(value="区域ID",name="areaId",example="")
    @CnName("区域ID")
    private String areaId;

    @ApiModelProperty(value="区域编码",name="areaCode",example="")
    @CnName("区域编码")
    private String areaCode;

    @ApiModelProperty(value="区域名称",name="areaName",example="")
    @CnName("区域名称")
    private String areaName;

    @ApiModelProperty(value="设备分组ID",name="deviceGroupId",example="")
    @CnName("设备分组ID")
    private String deviceGroupId;

    @ApiModelProperty(value="设备分组名称",name="deviceGroupName",example="")
    @CnName("设备分组名称")
    private String deviceGroupName;

    @ApiModelProperty(value="查看角色ID",name="viewRoleId",example="")
    @CnName("查看角色ID")
    private String viewRoleId;

    @ApiModelProperty(value="查看角色名称",name="viewRoleName",example="")
    @CnName("查看角色名称")
    private String viewRoleName;

    @ApiModelProperty(value="操作角色ID",name="operRoleId",example="")
    @CnName("操作角色ID")
    private String operRoleId;

    @ApiModelProperty(value="操作角色名称",name="operRoleName",example="")
    @CnName("操作角色名称")
    private String operRoleName;

    public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(String deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public String getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(String deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }

    public String getViewRoleId() {
        return viewRoleId;
    }

    public void setViewRoleId(String viewRoleId) {
        this.viewRoleId = viewRoleId;
    }

    public String getViewRoleName() {
        return viewRoleName;
    }

    public void setViewRoleName(String viewRoleName) {
        this.viewRoleName = viewRoleName;
    }

    public String getOperRoleId() {
        return operRoleId;
    }

    public void setOperRoleId(String operRoleId) {
        this.operRoleId = operRoleId;
    }

    public String getOperRoleName() {
        return operRoleName;
    }

    public void setOperRoleName(String operRoleName) {
        this.operRoleName = operRoleName;
    }
}
