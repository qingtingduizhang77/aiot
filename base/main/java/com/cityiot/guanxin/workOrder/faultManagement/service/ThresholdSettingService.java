package com.cityiot.guanxin.workOrder.faultManagement.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.workOrder.faultManagement.entity.ThresholdSetting;
import com.cityiot.guanxin.workOrder.faultManagement.repository.ThresholdSettingRepository;

/**
 * ThresholdSetting 数据Service
 * @author aohanhe
 *
 */
@Service
public class ThresholdSettingService extends BaseService<ThresholdSettingRepository, ThresholdSetting>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(ThresholdSettingService.class);
	
}
