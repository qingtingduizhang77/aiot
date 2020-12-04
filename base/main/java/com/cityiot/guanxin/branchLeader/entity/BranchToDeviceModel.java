package com.cityiot.guanxin.branchLeader.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.common.entity.OnlyIdEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
/**
 * @author yingzhao
 *
 */

@ApiModel(value="设备型号与负责人")
@Entity
@CnName("deviceModel与branch中间表")
public class BranchToDeviceModel extends BaseEntity {
    public static final String T_DeviceModel_BranchToDeviceModel = "T_DeviceModel_BranchToDeviceModel";

    @ApiModelProperty(value="负责人id",name="branchId",example="")
    @CnName("负责人id")
    private Long branchId;

    @ApiModelProperty(value="负责型号配置id",name="deviceModelId",example="")
    @CnName("负责型号配置id")
    @JoinEntity(name = "id",joinEntityClass = DeviceModel.class,joinEntityAlias = T_DeviceModel_BranchToDeviceModel)
    private Long deviceModelId;

    @ApiModelProperty(value="负责型号配置名称",name="deviceModel",example="")
    @Transient
    @CnName("负责型号配置名称")
    @FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class,joinEntityAlias = T_DeviceModel_BranchToDeviceModel)
    private String deviceModel;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
