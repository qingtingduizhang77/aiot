package com.cityiot.guanxin.workOrder.deviceLog.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.workOrder.deviceLog.entity.DeviceLog;
import com.cityiot.guanxin.workOrder.deviceLog.repository.DeviceLogRepository;

import java.util.Date;

/**
 * DeviceLog 数据Service
 * @author aohanhe
 *
 */
@Service
public class DeviceLogService extends BaseService<DeviceLogRepository, DeviceLog>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(DeviceLogService.class);


	public void insertDeviceLog(int type,long deviceId, int brightness, Long userId){
		DeviceLog deviceLog = new DeviceLog();
		deviceLog.setOperationType(this.getOperationType(type));
		deviceLog.setDescription(this.getDesc(deviceLog.getOperationType(), brightness));
		deviceLog.setDeviceId(deviceId);
		deviceLog.setMarkTime(new Date());
		if (userId != null && userId > 0)
			deviceLog.setOperatorId(userId);

		this.insertItem(deviceLog);

	}

	private int getOperationType(int type){
		switch (type){
			case 1: return 2001;//开灯
			case 2: return 2002;//关灯
			case 3: return 2004;//开启智能分流调光
			case 4: return 2005;//关闭智能分流调光
			case 5: return 2003;//调光
			default: return 1001;//系统同步状态
		}
	}

	private String getDesc(int operationType, int brightness){
		switch (operationType){
			case 2001: return "触发开灯操作";
			case 2002: return "触发关灯操作";
			case 2004: return "开启智能分流调光操作";
			case 2005: return "关闭智能分流调光操作";
			case 2003: return "触发调光操作，亮度为" + brightness;
			default: return "系统同步设备状态";
		}
	}
}
