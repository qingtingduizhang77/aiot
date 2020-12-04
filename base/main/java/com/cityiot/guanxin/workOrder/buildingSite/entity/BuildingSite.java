package com.cityiot.guanxin.workOrder.buildingSite.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="工地信息")
@Entity
@CnName("工地信息")
@EntityListeners(AuditingEntityListener.class)
public class BuildingSite extends BaseEntity {

    @ApiModelProperty(value="工地名称",name="buildingSiteName",example="")
    @CnName("工地名称")
    private String buildingSiteName;
    @ApiModelProperty(value="地址",name="buildingSiteAddress",example="")
    @CnName("地址")
    private String buildingSiteAddress;
    @ApiModelProperty(value="区域",name="areaCode",example="")
    @CnName("区域")
    private String areaCode;
    @ApiModelProperty(value="纬度",name="lat",example="")
    @CnName("纬度")
    private Double lat;
    @ApiModelProperty(value="经度",name="lng",example="")
    @CnName("经度")
    private Double lng;
    @ApiModelProperty(value="作业人数",name="totalWorkerNumber",example="")
    @CnName("作业人数")
    private Integer totalWorkerNumber;
    @ApiModelProperty(value="pm2.5设备数",name="totalPm25Device",example="")
    @CnName("pm2.5设备数")
    @Column(name = "total_pm25_device")
    private Integer totalPm25Device;
    @ApiModelProperty(value="噪音监测设备数",name="totalNoiseDevice",example="")
    @CnName("噪音监测设备数")
    private Integer totalNoiseDevice;
    @ApiModelProperty(value="吊车数",name="totalCrane",example="")
    @CnName("吊车数")
    private Integer totalCrane;
    @ApiModelProperty(value="起重机数",name="totalConstructionLift",example="")
    @CnName("起重机数")
    private Integer totalConstructionLift;
    @ApiModelProperty(value="吊塔数",name="totalConstructionTower",example="")
    @CnName("吊塔数")
    private Integer totalConstructionTower;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("remark")
    private Integer remark;

    public String getBuildingSiteName() {
        return buildingSiteName;
    }

    public void setBuildingSiteName(String buildingSiteName) {
        this.buildingSiteName = buildingSiteName;
    }

    public String getBuildingSiteAddress() {
        return buildingSiteAddress;
    }

    public void setBuildingSiteAddress(String buildingSiteAddress) {
        this.buildingSiteAddress = buildingSiteAddress;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getTotalWorkerNumber() {
        return totalWorkerNumber;
    }

    public void setTotalWorkerNumber(Integer totalWorkerNumber) {
        this.totalWorkerNumber = totalWorkerNumber;
    }

    public Integer getTotalPm25Device() {
        return totalPm25Device;
    }

    public void setTotalPm25Device(Integer totalPm25Device) {
        this.totalPm25Device = totalPm25Device;
    }

    public Integer getTotalNoiseDevice() {
        return totalNoiseDevice;
    }

    public void setTotalNoiseDevice(Integer totalNoiseDevice) {
        this.totalNoiseDevice = totalNoiseDevice;
    }

    public Integer getTotalCrane() {
        return totalCrane;
    }

    public void setTotalCrane(Integer totalCrane) {
        this.totalCrane = totalCrane;
    }

    public Integer getTotalConstructionLift() {
        return totalConstructionLift;
    }

    public void setTotalConstructionLift(Integer totalConstructionLift) {
        this.totalConstructionLift = totalConstructionLift;
    }

    public Integer getTotalConstructionTower() {
        return totalConstructionTower;
    }

    public void setTotalConstructionTower(Integer totalConstructionTower) {
        this.totalConstructionTower = totalConstructionTower;
    }

    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
        this.remark = remark;
    }
}
