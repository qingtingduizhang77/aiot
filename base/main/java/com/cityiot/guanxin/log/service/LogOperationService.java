package com.cityiot.guanxin.log.service;

import org.springframework.stereotype.Service;

import com.cityiot.guanxin.log.entity.LogOperation;
import com.cityiot.guanxin.log.repository.LogOperationRepository;

import swallow.framework.service.BaseService;

@Service
public class LogOperationService  extends BaseService<LogOperationRepository, LogOperation>{

}
