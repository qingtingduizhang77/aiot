package com.cityiot.guanxin.deviceInformation.service;


import com.cityiot.guanxin.deviceInformation.entity.QDeviceBrand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.repository.DeviceBrandRepository;

import java.util.List;

/**
 * DeviceBrand 数据Service
 * @author aohanhe
 *
 */
@Service
public class DeviceBrandService extends BaseService<DeviceBrandRepository, DeviceBrand>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(DeviceBrandService.class);

	public boolean checkForPresenceInfo(DeviceBrand deviceBrand){
		QDeviceBrand qDeviceBrand = QDeviceBrand.deviceBrand;
		List<DeviceBrand> list =this.getAllItems(query ->{
					query.where(qDeviceBrand.deviceBrandName.eq(deviceBrand.getDeviceBrandName())
							.and(qDeviceBrand.deviceManufacturer.eq(deviceBrand.getDeviceManufacturer())));
					if (deviceBrand.getId() != null) {// 更新时校验
						query.where(qDeviceBrand.id.ne(deviceBrand.getId()));
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
