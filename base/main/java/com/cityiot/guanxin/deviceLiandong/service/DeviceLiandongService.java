package com.cityiot.guanxin.deviceLiandong.service;

import com.cityiot.guanxin.deviceLiandong.entity.DeviceLiandong;
import com.cityiot.guanxin.deviceLiandong.repository.DeviceLiandongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;
@Service
public class DeviceLiandongService extends BaseService<DeviceLiandongRepository, DeviceLiandong> {
    private static final Logger log = LoggerFactory.getLogger(DeviceLiandongService.class);

}
