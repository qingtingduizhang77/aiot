package com.cityiot.guanxin.monitor.statistics.repository;

import com.cityiot.guanxin.monitor.statistics.entity.LajitongStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LajitongRepository extends BaseStatisticsRepository<LajitongStatistics> {
    private static final Logger log = LoggerFactory.getLogger(LajitongRepository.class);
}
