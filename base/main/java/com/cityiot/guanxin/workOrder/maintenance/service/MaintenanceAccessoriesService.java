package com.cityiot.guanxin.workOrder.maintenance.service;


import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceAccessories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.repository.MaintenanceAccessoriesRepository;

/**
 * MaintenanceAccessories 数据Service
 * @author aohanhe
 *
 */
@Service
public class MaintenanceAccessoriesService extends BaseService<MaintenanceAccessoriesRepository, MaintenanceAccessories>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(MaintenanceAccessoriesService.class);

	
}
