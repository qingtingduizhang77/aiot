package com.cityiot.guanxin.light.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@ApiModel(value="规则基础类")
@MappedSuperclass
public class Rule extends BaseEntity {
    @ApiModelProperty(value="开始时间",name="startTime",example="")
    @CnName("开始时间")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @ApiModelProperty(value="结束时间",name="endTime",example="")
    @CnName("结束时间")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @ApiModelProperty(value="亮度",name="brightness",example="")
    @CnName("亮度")
    private int brightness;
    @ApiModelProperty(value="开启状态",name="openStatus",example="")
    @CnName("开启状态")
    // 0不开启 1开启
    private int openStatus;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(int openStatus) {
        this.openStatus = openStatus;
    }
}
