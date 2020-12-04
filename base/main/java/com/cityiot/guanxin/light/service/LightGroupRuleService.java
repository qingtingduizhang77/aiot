package com.cityiot.guanxin.light.service;

import com.cityiot.guanxin.light.entity.LightGroupRule;
import com.cityiot.guanxin.light.repository.LightGroupRuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class LightGroupRuleService extends BaseService<LightGroupRuleRepository, LightGroupRule> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightGroupRuleService.class);
}
