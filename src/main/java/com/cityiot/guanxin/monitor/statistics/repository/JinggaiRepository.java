package com.cityiot.guanxin.monitor.statistics.repository;

import com.cityiot.guanxin.monitor.statistics.entity.JinggaiStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JinggaiRepository extends BaseStatisticsRepository<JinggaiStatistics> {
    private static final Logger log = LoggerFactory.getLogger(JinggaiRepository.class);
}
