package com.cityiot.guanxin.branchLeader.entity.vo;


import com.cityiot.guanxin.branchLeader.entity.BranchLeader;

import javax.persistence.Transient;

/**
 * @author yingzhao
 * 用于封装接收参数
 */
public class BranchLeaderVo extends BranchLeader {

    //private BranchLeader branchLeader;
    //区域ids
    @Transient
    private Long[] areaIds;
    //设备型号ids
    @Transient
    private Long[] deviceModelIds;

    //设备分组ids
    @Transient
    private Long[] deviceGroupIds;


    public Long[] getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(Long[] areaIds) {
        this.areaIds = areaIds;
    }

    public Long[] getDeviceModelIds() {
        return deviceModelIds;
    }

    public void setDeviceModelIds(Long[] deviceModelIds) {
        this.deviceModelIds = deviceModelIds;
    }
    //重写父类方法
    @Transient
    public Long getId() {
        return super.getId();
    }

    public Long[] getDeviceGroupIds() {
        return deviceGroupIds;
    }

    public void setDeviceGroupIds(Long[] deviceGroupIds) {
        this.deviceGroupIds = deviceGroupIds;
    }
}
