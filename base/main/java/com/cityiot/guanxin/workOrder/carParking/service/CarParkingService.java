package com.cityiot.guanxin.workOrder.carParking.service;


import com.cityiot.guanxin.workOrder.carParking.entity.CarParking;
import com.cityiot.guanxin.workOrder.carParking.repository.CarParkingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;


/**
 * InspectionRecord 数据Service
 * @author aohanhe
 *
 */
@Service
public class CarParkingService extends BaseService<CarParkingRepository, CarParking>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(CarParkingService.class);
}
