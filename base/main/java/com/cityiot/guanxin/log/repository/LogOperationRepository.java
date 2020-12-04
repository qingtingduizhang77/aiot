package com.cityiot.guanxin.log.repository;

import org.springframework.stereotype.Service;

import com.cityiot.guanxin.log.entity.LogOperation;

import swallow.framework.jpaquery.repository.SwallowRepository;

@Service
public class LogOperationRepository extends SwallowRepository<LogOperation>{

}
