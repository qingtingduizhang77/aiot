package com.cityiot.guanxin.monitor.statistics.service;

import com.cityiot.guanxin.monitor.statistics.repository.SpecialDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpecialDeviceService extends BaseStatisticsService<SpecialDeviceRepository> {
    private static final Logger log = LoggerFactory.getLogger(SpecialDeviceService.class);
}
