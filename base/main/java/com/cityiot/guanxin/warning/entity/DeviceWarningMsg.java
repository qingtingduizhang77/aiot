package com.cityiot.guanxin.warning.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.BaseSimpleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

@ApiModel(value="设备告警信息")
@CnName("设备告警信息")
@Entity
@Table(name = "device_warning_msg")
@EntityListeners(AuditingEntityListener.class)
public class DeviceWarningMsg  extends BaseSimpleEntity {
	
	 @ApiModelProperty(value="告警规则ID",name="ruleId",example="")
	 @CnName("告警规则ID")
	 private Integer ruleId;
	 
	 @ApiModelProperty(value="告警规则名称",name="ruleName",example="")
	 @CnName("告警规则名称")
	 private String ruleName;
	 
	 @ApiModelProperty(value="告警类型",name="warningType",example="")
	 @CnName("告警类型")
	 private String warningType;
	 
	 @ApiModelProperty(value="告警级别",name="warningEvel",example="")
	 @CnName("告警级别")
	 private String warningEvel;
	 
	 @ApiModelProperty(value="告警时间",name="warningTime",example="")
	 @CnName("告警时间")
	 private Date warningTime;
	 
	 @ApiModelProperty(value="通知角色ID",name="noticeRoleId",example="")
	 @CnName("通知角色ID")
	 private String noticeRoleId;
	 
	 @ApiModelProperty(value="通知人ID",name="noticeUserId",example="")
	 @CnName("通知人ID")
	 private String noticeUserId;
	 
	 @ApiModelProperty(value="通知人姓名",name="noticeUserName",example="")
	 @CnName("通知人姓名")
	 private String noticeUserName;
	 
	 @ApiModelProperty(value="设备ID",name="deviceId",example="")
	 @CnName("设备ID")
	 private Long deviceId;
	 
	 @ApiModelProperty(value="设备名称",name="deviceName",example="")
	 @CnName("设备名称")
	 private String deviceName;
	 
	 @ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
	 @CnName("设备类型ID")
	 private Integer deviceTypeId;
	 
	 @ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
	 @CnName("设备型号ID")
	 private Integer deviceModelId;
	 
	 @ApiModelProperty(value="设备编号",name="deviceCode",example="")
	 @CnName("设备编号")
	 private String deviceCode;
	 
	 @ApiModelProperty(value="设备经纬度",name="coordinates",example="")
	 @CnName("设备经纬度")
	 private String coordinates;
	 
	 @ApiModelProperty(value="设备地址",name="area",example="")
	 @CnName("设备地址")
	 private String area;
	 
	 @ApiModelProperty(value="状态",name="state",example="状态：1未处理，2已处理")
	 @CnName("状态")
	 private Integer state;
	 
	 @ApiModelProperty(value="处理人ID",name="state",example="")
	 @CnName("处理人ID")
	 private Integer handleUserId;
	 
	 @ApiModelProperty(value="处理人姓名",name="handleUserName",example="")
	 @CnName("处理人姓名")
	 private String handleUserName;
	 
	 @ApiModelProperty(value="处理时间",name="handleTime",example="")
	 @CnName("处理时间")
	 private Date handleTime;
	 
	 @ApiModelProperty(value="处理过程",name="handleText",example="")
	 @CnName("处理过程")
	 private String handleText;

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getWarningEvel() {
		return warningEvel;
	}

	public void setWarningEvel(String warningEvel) {
		this.warningEvel = warningEvel;
	}

	public Date getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(Date warningTime) {
		this.warningTime = warningTime;
	}

	public String getNoticeRoleId() {
		return noticeRoleId;
	}

	public void setNoticeRoleId(String noticeRoleId) {
		this.noticeRoleId = noticeRoleId;
	}

	public String getNoticeUserId() {
		return noticeUserId;
	}

	public void setNoticeUserId(String noticeUserId) {
		this.noticeUserId = noticeUserId;
	}

	public String getNoticeUserName() {
		return noticeUserName;
	}

	public void setNoticeUserName(String noticeUserName) {
		this.noticeUserName = noticeUserName;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public Integer getDeviceModelId() {
		return deviceModelId;
	}

	public void setDeviceModelId(Integer deviceModelId) {
		this.deviceModelId = deviceModelId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getHandleUserId() {
		return handleUserId;
	}

	public void setHandleUserId(Integer handleUserId) {
		this.handleUserId = handleUserId;
	}

	public String getHandleUserName() {
		return handleUserName;
	}

	public void setHandleUserName(String handleUserName) {
		this.handleUserName = handleUserName;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandleText() {
		return handleText;
	}

	public void setHandleText(String handleText) {
		this.handleText = handleText;
	}








}
