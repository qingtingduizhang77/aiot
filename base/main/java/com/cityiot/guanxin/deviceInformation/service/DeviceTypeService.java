package com.cityiot.guanxin.deviceInformation.service;


import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.repository.DeviceTypeRepository;

import java.util.List;

/**
 * DeviceType 数据Service
 * @author aohanhe
 *
 */
@Service
public class DeviceTypeService extends BaseService<DeviceTypeRepository, DeviceType>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(DeviceTypeService.class);

	public boolean checkForPresenceInfo(DeviceType deviceType){
		QDeviceType qDeviceType = QDeviceType.deviceType;
		List<DeviceType> list =this.getAllItems(query ->{
					query.where(qDeviceType.deviceTypeName.eq(deviceType.getDeviceTypeName()));
					if (deviceType.getId() != null) {// 更新时校验
						query.where(qDeviceType.id.ne(deviceType.getId()));
					}
					return query;
				}
		);
		if (list != null && list.size() > 0){
			return true;
		}
		return false;
	}
}
