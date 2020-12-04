package com.cityiot.guanxin.monitor.statistics.service;

import com.cityiot.guanxin.monitor.statistics.repository.LajitongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LajitongService extends BaseStatisticsService<LajitongRepository> {
    private static final Logger log = LoggerFactory.getLogger(LajitongService.class);
}
