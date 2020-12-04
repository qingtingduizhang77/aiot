package com.cityiot.guanxin.service;


import com.cityiot.guanxin.entity.SystemVariableType;
import com.cityiot.guanxin.repository.SystemVariableTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

/**
 * ESystemVariableType 数据Service
 * @author aohanhe
 *
 */
@Service
public class SystemVariableTypeService extends BaseService<SystemVariableTypeRepository, SystemVariableType> {
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(SystemVariableTypeRepository.class);
	
}
