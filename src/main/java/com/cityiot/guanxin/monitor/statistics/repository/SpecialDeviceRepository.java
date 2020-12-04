package com.cityiot.guanxin.monitor.statistics.repository;

import com.cityiot.guanxin.monitor.statistics.entity.SpecialStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpecialDeviceRepository extends BaseStatisticsRepository<SpecialStatistics> {
    private static final Logger log = LoggerFactory.getLogger(SpecialDeviceRepository.class);
}
