package com.cityiot.guanxin.deviceLiandong.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.deviceLiandong.entity.DeviceLiandongDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Guoyz
 * createTime   2020/7/2 13:51
 */
@Service
public class DeviceLiandongDetailsRepository extends BaseRepository<DeviceLiandongDetails> {
    private static final Logger log = LoggerFactory.getLogger(DeviceLiandongDetailsRepository.class);
}
