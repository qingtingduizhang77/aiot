package com.cityiot.guanxin.deviceInformation.repository;


import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceType;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 设备类型 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class DeviceTypeRepository extends SwallowRepository<DeviceType>{



}
