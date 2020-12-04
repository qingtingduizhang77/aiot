package com.cityiot.guanxin.monitor.statistics.service;

import com.cityiot.guanxin.monitor.statistics.repository.JinggaiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JinggaiService extends BaseStatisticsService<JinggaiRepository> {
    private static final Logger log = LoggerFactory.getLogger(JinggaiService.class);
}
