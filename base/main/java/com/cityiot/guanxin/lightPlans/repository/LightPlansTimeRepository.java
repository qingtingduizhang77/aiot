package com.cityiot.guanxin.lightPlans.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.lightPlans.entity.LightPlansTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LightPlansTimeRepository extends BaseRepository<LightPlansTime> {
    private static final Logger log = LoggerFactory.getLogger(LightPlansTimeRepository.class);

}
