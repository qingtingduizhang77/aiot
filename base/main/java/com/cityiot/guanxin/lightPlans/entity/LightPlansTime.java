package com.cityiot.guanxin.lightPlans.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 路灯计划安排
 * 路灯计划策略管理表的子表，用于方便计划查询
 * @author Guoyz
 * createTime   2020/5/12 14:20
 */
@ApiModel(value="路灯计划安排")
@CnName("路灯计划安排")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LightPlansTime extends BaseEntity {
    public static final String T_LightPlans_LightPlansTime = "T_LightPlans_LightPlansTime";
    @ApiModelProperty(value="路灯计划策略管理id",name="lightPlansId",example="")
    @CnName("路灯计划策略管理id")
    @JoinEntity(name = "id",joinEntityClass = LightPlans.class,joinEntityAlias = T_LightPlans_LightPlansTime)
    @Column(nullable = false)
    private Long lightPlansId;
    @ApiModelProperty(value="执行时间",name="time",example="日期类型HH:mm")
    @CnName("执行时间")
    @JsonFormat(pattern="HH:mm")
    @Temporal(TemporalType.TIME)
    private Date time;
    @ApiModelProperty(value="亮度",name="luminance",example="0~100;0是关灯")
    @CnName("亮度")
    private Integer luminance;
    @ApiModelProperty(value="操作类型",name="type",example="开灯:open;关灯:off;调光:adjust;")
    @CnName("操作类型")
    private String type;

    @CnName("周设置")
    @Transient
    @ApiModelProperty(value="周设置",name="zhou",example="一,二,三,四,五,六,日(多选，多个用逗号分隔)")
    @FieldPath(name = "zhou",joinEntityClass = LightPlans.class,joinEntityAlias = T_LightPlans_LightPlansTime)
    private String zhou;
    @CnName("节假日开始日期")
    @Transient
    @Temporal(TemporalType.DATE)//定义类型为YYYY-MM-DD
    @FieldPath(name = "startDate",joinEntityClass = LightPlans.class,joinEntityAlias = T_LightPlans_LightPlansTime)
    @ApiModelProperty(value="节假日开始日期",name="startDate",example="YYYY-MM-DD")
    private Date startDate;
    @CnName("节假日结束日期")
    @Transient
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(value="节假日结束日期",name="endDate",example="YYYY-MM-DD")
    @FieldPath(name = "endDate",joinEntityClass = LightPlans.class,joinEntityAlias = T_LightPlans_LightPlansTime)
    private Date endDate;

    public String getZhou() {
        return zhou;
    }

    public void setZhou(String zhou) {
        this.zhou = zhou;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getLightPlansId() {
        return lightPlansId;
    }

    public void setLightPlansId(Long lightPlansId) {
        this.lightPlansId = lightPlansId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getLuminance() {
        return luminance;
    }

    public void setLuminance(Integer luminance) {
        this.luminance = luminance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
