package com.cityiot.guanxin.plan.service;

import com.cityiot.guanxin.plan.entity.Plan;
import com.cityiot.guanxin.plan.repository.PlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class PlanService extends BaseService<PlanRepository, Plan> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(PlanService.class);


    public Plan getItemById01(long id) {
        Plan item = this.getItemById(id);
        item.setLayerOrder(0);
        item.setLayerType("1-1");
        item.setPlanLock(1);
        item.setX(0);
        item.setY(0);
        return item;
    }
}
