package com.cityiot.guanxin.alarmRule.repository;

import com.cityiot.guanxin.alarmRule.entity.AlarmRule;
import com.cityiot.guanxin.common.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlarmRuleRepository extends BaseRepository<AlarmRule> {
    private static final Logger log = LoggerFactory.getLogger(AlarmRuleRepository.class);

}
