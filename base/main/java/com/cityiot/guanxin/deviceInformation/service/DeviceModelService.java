package com.cityiot.guanxin.deviceInformation.service;


import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.repository.DeviceModelRepository;

import java.util.List;

/**
 * DeviceModel 数据Service
 * @author aohanhe
 *
 */
@Service
public class DeviceModelService extends BaseService<DeviceModelRepository, DeviceModel>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(DeviceModelService.class);

	public boolean checkForPresenceInfo(DeviceModel deviceModel){
		QDeviceModel qDeviceModel = QDeviceModel.deviceModel1;
		List<DeviceModel> list =this.getAllItems(query ->{
				query.where(qDeviceModel.deviceModel.eq(deviceModel.getDeviceModel())
						.and(qDeviceModel.deviceTypeId.eq(deviceModel.getDeviceTypeId()))
						.and(qDeviceModel.deviceBrandId.eq(deviceModel.getDeviceBrandId())));
				if (deviceModel.getId() != null) {// 更新时校验
					query.where(qDeviceModel.id.ne(deviceModel.getId()));
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
