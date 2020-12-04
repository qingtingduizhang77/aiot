package com.cityiot.guanxin.branchLeader.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author zhengjc
 * 设备分组与负责人配置表
 */
@ApiModel(value="设备分组与负责人")
@Entity
@CnName("deviceGroup与branch中间表")
@EntityListeners(AuditingEntityListener.class)
public class BranchToDeviceGroup extends BaseEntity {

    @ApiModelProperty(value="负责人id",name="branchId",example="")
    @CnName("负责人id")
    private Long branchId;
    @ApiModelProperty(value="设备分组id",name="deviceGroupId",example="")
    @CnName("设备分组id")
    private Long deviceGroupId;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(Long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }
}
