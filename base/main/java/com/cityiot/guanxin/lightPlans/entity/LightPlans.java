package com.cityiot.guanxin.lightPlans.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.*;
import java.util.Date;

/**
 * 路灯计划策略管理
 * @author Guoyz
 * createTime   2020/5/11 16:37
 */
@ApiModel(value="路灯计划策略管理")
@CnName("路灯计划策略管理")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LightPlans extends BaseEntity {
    @ApiModelProperty(value="名称",name="name",example="")
    @CnName("名称")
    private String name;
    @CnName("类型")
    @ApiModelProperty(value="类型",name="dateType",example="1-日常;2-节假日")
    private Integer dateType;
    @CnName("周设置")
    @ApiModelProperty(value="周设置",name="zhou",example="一,二,三,四,五,六,日(多选，多个用逗号分隔)")
    private String zhou;
    @CnName("节假日开始日期")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value="节假日开始日期",name="startDate",example="yyyy-MM-dd")
    private Date startDate;
    @CnName("节假日结束日期")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value="节假日结束日期",name="endDate",example="yyyy-MM-dd")
    private Date endDate;
    @CnName("计划安排")
    @Column(columnDefinition="TEXT")
    @ApiModelProperty(value="名称",name="name",example="[{\"time\":\"00:01~23:59\",\"type\":\"open,off,adjust,separation-on,separation-off\",\"luminance\":0~100},{...}]")
    private String plansTime;//JSON传输，后台保存在lightPlansTime表中,方便查询。[{"time":"00:01~23:59","type":"open,off,adjust,separation-on,separation-off","luminance":0~100},{...}]
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

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

    public String getPlansTime() {
        return plansTime;
    }

    public void setPlansTime(String plansTime) {
        this.plansTime = plansTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
