package com.cityiot.guanxin.workOrder.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.BaseEntity;

import swallow.framework.jpaquery.repository.annotations.CnName;

@ApiModel(value="工单")
@CnName("工单")
@Entity
@Table(name = "ework_order")
@EntityListeners(AuditingEntityListener.class)
public class EWorkOrder extends BaseEntity {

    @ApiModelProperty(value="主题",name="title",example="")
	@CnName("主题")
	private String title;
    @ApiModelProperty(value="记录转工单ID",name="recordId",example="")
	@CnName("记录转工单ID")
	private Long recordId;//工单id_
    @ApiModelProperty(value="工单类型",name="workOrderType",example="")
	@CnName("工单类型") //1巡检工单 2保养工单 3维修工单
    private int workOrderType;
    @ApiModelProperty(value="处理人ID",name="handlerId",example="")
	@CnName("处理人ID")
	private Long handlerId;
    @ApiModelProperty(value="处理人名称",name="handlerName",example="")
	@CnName("处理人名称")
	private String handlerName;
    @ApiModelProperty(value="工单状态",name="handleStatus",example="")
	@CnName("工单状态") //1待处理 2处理中 3已完成 4已退单
	private int handleStatus;
	
	
	
	//下面属性暂时不用
    @CnName("工单编号")
    private String code;
    @CnName("处理时间")
    private Date handleTime;
    @CnName("备注")
    private String remark;
    @CnName("描述")
    private String desContent;
    @CnName("设备编号")
    private String deviceCode;
    @CnName("设备信息名称")
    private String deviceName;
    @CnName("设备型号")
    private String deviceModel;
    @CnName("设备类型")
    private String deviceType;
    @CnName("所属区域")
    private String area;
    @CnName("设备经度")
    private String longitude;
    @CnName("设备纬度")
    private String latitude;
    @CnName("设备距离")
    private String distance;
    @CnName("设备id")
    private long deviceInfoId;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public int getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(int handleStatus) {
        this.handleStatus = handleStatus;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(int workOrderType) {
        this.workOrderType = workOrderType;
    }

    public String getDesContent() {
        return desContent;
    }

    public void setDesContent(String desContent) {
        this.desContent = desContent;
    }

    public long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
    
    
    
}
