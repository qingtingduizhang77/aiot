package com.cityiot.guanxin.lightPlans.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.lightPlans.entity.LightPlansGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LightPlansGroupRepository extends BaseRepository<LightPlansGroup> {
    private static final Logger log = LoggerFactory.getLogger(LightPlansGroupRepository.class);


}
