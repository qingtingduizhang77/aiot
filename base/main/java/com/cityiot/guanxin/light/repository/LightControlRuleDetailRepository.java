package com.cityiot.guanxin.light.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.light.entity.LightControlRuleDetail;

import com.querydsl.core.types.EntityPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LightControlRuleDetailRepository extends BaseRepository<LightControlRuleDetail> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightControlRuleDetailRepository.class);
   
}
