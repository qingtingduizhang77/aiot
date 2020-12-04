package com.cityiot.guanxin.deviceInformation.repository;


import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;

import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 设备型号 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class DeviceModelRepository extends SwallowRepository<DeviceModel>{

	
}
