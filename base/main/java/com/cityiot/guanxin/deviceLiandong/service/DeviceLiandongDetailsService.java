package com.cityiot.guanxin.deviceLiandong.service;

import com.cityiot.guanxin.deviceLiandong.entity.DeviceLiandongDetails;
import com.cityiot.guanxin.deviceLiandong.repository.DeviceLiandongDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class DeviceLiandongDetailsService extends BaseService<DeviceLiandongDetailsRepository, DeviceLiandongDetails> {
    private static final Logger log = LoggerFactory.getLogger(DeviceLiandongDetailsService.class);

}
