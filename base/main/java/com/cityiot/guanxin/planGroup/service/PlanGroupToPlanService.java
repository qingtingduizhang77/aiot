package com.cityiot.guanxin.planGroup.service;


import com.cityiot.guanxin.planGroup.entity.PlanGroupToPlan;
import com.cityiot.guanxin.planGroup.entity.QPlanGroupToPlan;
import com.cityiot.guanxin.planGroup.repository.PlanGroupToPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;
import java.util.List;

@Service
public class PlanGroupToPlanService extends BaseService<PlanGroupToPlanRepository, PlanGroupToPlan> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(PlanGroupToPlanService.class);


    public PlanGroupToPlan insertItem(long groupId, long planId){
        PlanGroupToPlan planGroupToPlan = new PlanGroupToPlan();
        planGroupToPlan.setPlanGroupId(groupId);
        planGroupToPlan.setPlanId(planId);
        return this.insertItem(planGroupToPlan);
    }
    //删除指定平面图分组的中间表
    public void deleteItemByPlanGroupId(long groupId, long planId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QPlanGroupToPlan.PlanGroupToPlan.planGroupId.eq(groupId)
                        .and(QPlanGroupToPlan.PlanGroupToPlan.planId.eq(planId))));
    }
    //删除指定平面图分组的所有中间表
    public void deleteItemByGroupId(Long groupId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QPlanGroupToPlan.PlanGroupToPlan.planGroupId.eq(groupId)));
    }
    //查询平面图列表（按ordering排序）
    public List<PlanGroupToPlan> getPlanGroupToPlanListByGroupId(long groupId){
        //条件查询
        List<PlanGroupToPlan> entitys = this.getAllItems(query -> {
            return query.where(QPlanGroupToPlan.PlanGroupToPlan.planGroupId.eq(groupId))
                    .orderBy(QPlanGroupToPlan.PlanGroupToPlan.ordering.asc());
        });
        return entitys;
    }

}
