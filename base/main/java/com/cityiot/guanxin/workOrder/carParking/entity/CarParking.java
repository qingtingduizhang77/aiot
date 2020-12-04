package com.cityiot.guanxin.workOrder.carParking.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="停车场信息")
@Entity
@CnName("停车场信息")
@EntityListeners(AuditingEntityListener.class)
public class CarParking extends BaseEntity {

    @ApiModelProperty(value="停车场类型",name="parkingType",example="")
    @CnName("停车场类型")
    private Integer parkingType;

    @ApiModelProperty(value="停车场名称",name="parkingName",example="")
    @CnName("停车场名称")
    private String parkingName;

    @ApiModelProperty(value="地址",name="parkingAddress",example="")
    @CnName("地址")
    private String parkingAddress;
    @ApiModelProperty(value="区域",name="areaCode",example="")
    @CnName("区域")
    private String areaCode;
    @ApiModelProperty(value="车位数",name="totalNumber",example="")
    @CnName("车位数")
    private Integer totalNumber;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("remark")
    private String remark;
    @ApiModelProperty(value="纬度",name="lat",example="")
    @CnName("纬度")
    private Double lat;
    @ApiModelProperty(value="经度",name="lng",example="")
    @CnName("经度")
    private Double lng;

    public Integer getParkingType() {
        return parkingType;
    }

    public void setParkingType(Integer parkingType) {
        this.parkingType = parkingType;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
