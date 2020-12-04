package com.cityiot.guanxin.branchLeader.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.user.entity.Userview;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.*;

/**
 * @author guoyingzhao
 * 型号区域负责人配置表
 * (前端称它为：设备运维配置)
 */
@ApiModel(value="区域负责人")
@CnName("区域负责人配置表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BranchLeader extends BaseEntity {
    //编号id，修改人modifier，最后修改时间lastmodi在父类中

    //运维人员对象
    public static final String Leader = "T_Leader";

    @ApiModelProperty(value="运维人员id",name="operatorOrCompanyManageId",example="")
    @CnName("运维人员id")
    @JoinEntity(name = "id", joinEntityClass = Userview.class,joinEntityAlias = Leader)
    private long operatorOrCompanyManageId;

    @ApiModelProperty(value="运维人员姓名",name="leaderName",example="")
    @CnName("运维人员姓名")
    @FieldPath(name = "username", joinEntityClass = Userview.class,joinEntityAlias = Leader)
    @Transient
    private String leaderName;

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    @ApiModelProperty(value="负责区域名称",name="areaName",example="")
    //负责区域
    @CnName("负责区域名称")
    private String areaName;

    @ApiModelProperty(value="负责型号配置名称",name="deviceModelName",example="")
    //负责型号配置
    @CnName("负责型号配置名称")
    private String deviceModelName;

    @ApiModelProperty(value="设备分组名称",name="deviceGroupName",example="")
    @CnName("设备分组名称")
    private String deviceGroupName;


    public long getOperatorOrCompanyManageId() {
        return operatorOrCompanyManageId;
    }

    public void setOperatorOrCompanyManageId(long operatorOrCompanyManageId) {
        this.operatorOrCompanyManageId = operatorOrCompanyManageId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(String deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }
}
