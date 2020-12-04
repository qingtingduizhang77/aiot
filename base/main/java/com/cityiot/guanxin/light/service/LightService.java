package com.cityiot.guanxin.light.service;

import com.cityiot.guanxin.light.entity.Light;
import com.cityiot.guanxin.light.repository.LightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class LightService extends BaseService<LightRepository, Light> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightService.class);
}
