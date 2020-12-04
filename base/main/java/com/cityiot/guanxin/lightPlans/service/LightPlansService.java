package com.cityiot.guanxin.lightPlans.service;

import com.cityiot.guanxin.lightPlans.entity.LightPlans;
import com.cityiot.guanxin.lightPlans.repository.LightPlansRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.io.Serializable;


@Service
public class LightPlansService extends BaseService<LightPlansRepository, LightPlans> {
    private static final Logger log = LoggerFactory.getLogger(LightPlansService.class);

    @Autowired
    private LightPlansTimeService lightPlansTimeService;

    //新增
    @Override
    @Transactional
    public LightPlans insertItem(LightPlans entity) {
        //新增主表
        LightPlans lightPlans = super.insertItem(entity);
        //新增计划安排表
        lightPlansTimeService.insertItemByLightPlans(lightPlans);
        return lightPlans;
    }

    //删除
    @Override
    public long deleteItemById(Serializable id) {
        //删除主表
        long ls = super.deleteItemById(id);
        if(ls!=0L){
            //删除计划安排表
            lightPlansTimeService.deleteItemBylightPlansId((long)id);
        }
        return ls;
    }

    //更新
    @Override
    @Transactional
    public LightPlans updateItem(LightPlans enity) throws SwallowException {
        //更新主表
        LightPlans lightPlans = super.updateItem(enity);
        //更新计划安排表
        lightPlansTimeService.deleteItemBylightPlansId(enity.getId());
        lightPlansTimeService.insertItemByLightPlans(lightPlans);
        return lightPlans;
    }
}
