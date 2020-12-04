package com.cityiot.guanxin.monitor.statistics.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Statistics extends BaseEntity {
    private long areaId;
    private String areaName;
    private long streetId;
    private String streetName;
    // 统计时间范围开始
    private Date timeScopeStart;
    // 统计时间范围结束
    private Date timeScopeEnd;
    // 统计目标总数
    private int targetCount;

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public long getStreetId() {
        return streetId;
    }

    public void setStreetId(long streetId) {
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Date getTimeScopeStart() {
        return timeScopeStart;
    }

    public void setTimeScopeStart(Date timeScopeStart) {
        this.timeScopeStart = timeScopeStart;
    }

    public Date getTimeScopeEnd() {
        return timeScopeEnd;
    }

    public void setTimeScopeEnd(Date timeScopeEnd) {
        this.timeScopeEnd = timeScopeEnd;
    }

    public int getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(int targetCount) {
        this.targetCount = targetCount;
    }
}
