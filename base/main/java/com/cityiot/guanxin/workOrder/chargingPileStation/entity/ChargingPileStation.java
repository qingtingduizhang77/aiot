package com.cityiot.guanxin.workOrder.chargingPileStation.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="充电站信息")
@Entity
@CnName("充电站信息")
@EntityListeners(AuditingEntityListener.class)
public class ChargingPileStation extends BaseEntity {

    @ApiModelProperty(value="名称",name="stationName",example="")
    @CnName("名称")
    private String stationName;

    @ApiModelProperty(value="地址",name="stationAddress",example="")
    @CnName("地址")
    private String stationAddress;
    @ApiModelProperty(value="电桩总数",name="totalPile",example="")
    @CnName("电桩总数")
    private Integer totalPile;
    @ApiModelProperty(value="区域",name="areaCode",example="")
    @CnName("区域")
    private String areaCode;

    @ApiModelProperty(value="纬度",name="lat",example="")
    @CnName("纬度")
    private Double lat;
    @ApiModelProperty(value="经度",name="lng",example="")
    @CnName("经度")
    private Double lng;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("remark")
    private String remark;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public Integer getTotalPile() {
        return totalPile;
    }

    public void setTotalPile(Integer totalPile) {
        this.totalPile = totalPile;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
