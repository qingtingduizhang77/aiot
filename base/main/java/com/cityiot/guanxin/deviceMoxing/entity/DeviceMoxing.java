package com.cityiot.guanxin.deviceMoxing.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="设备模型")
@Entity
@CnName("设备模型")
@EntityListeners(AuditingEntityListener.class)
public class DeviceMoxing extends BaseEntity {

    @ApiModelProperty(value="设备模型名称",name="name",example="")
    @CnName("设备模型名称")
    private String name;

    @ApiModelProperty(value="设备型号ID",name="deviceModelIds",example="")
    @CnName("设备型号ID")// 多个用逗号，隔开
    private String deviceModelIds;

    @ApiModelProperty(value="设备型号",name="deviceModels",example="")
    @CnName("设备型号")// 多个用逗号，隔开
    private String deviceModels;

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceModelIds() {
        return deviceModelIds;
    }

    public void setDeviceModelIds(String deviceModelIds) {
        this.deviceModelIds = deviceModelIds;
    }

    public String getDeviceModels() {
        return deviceModels;
    }

    public void setDeviceModels(String deviceModels) {
        this.deviceModels = deviceModels;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
