package com.cityiot.guanxin.light.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.light.entity.LightControlRule;
import com.cityiot.guanxin.light.entity.QLightControlRule;
import com.querydsl.core.types.EntityPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LightControlRuleRepository extends BaseRepository<LightControlRule> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightControlRuleRepository.class);
  
}
