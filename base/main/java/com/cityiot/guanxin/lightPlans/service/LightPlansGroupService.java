package com.cityiot.guanxin.lightPlans.service;

import com.cityiot.guanxin.lightPlans.entity.LightPlansGroup;
import com.cityiot.guanxin.lightPlans.repository.LightPlansGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

/**
 * @author Guoyz
 * createTime   2020/5/15 14:17
 */
@Service
public class LightPlansGroupService extends BaseService<LightPlansGroupRepository, LightPlansGroup> {
    private static final Logger log = LoggerFactory.getLogger(LightPlansGroupService.class);


}
