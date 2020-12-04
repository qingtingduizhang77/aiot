package com.cityiot.guanxin.emergency.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.emergency.entity.Emergency;
import com.cityiot.guanxin.emergency.repository.EmergencyRepository;

/**
 * Emergency 数据Service
 * @author aohanhe
 *
 */
@Service
public class EmergencyService extends BaseService<EmergencyRepository, Emergency>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(EmergencyService.class);

	@Autowired
	private EmergencyRepository repository;

	public Long selectTotalNum() {
		return this.repository.selectTotalNum();
	}

	@Transactional(rollbackFor = Exception.class)
	public void readEmergency() {
		this.repository.readEmergency();
	}
}
