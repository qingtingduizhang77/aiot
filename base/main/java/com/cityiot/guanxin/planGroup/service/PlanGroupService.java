package com.cityiot.guanxin.planGroup.service;

import com.cityiot.guanxin.planGroup.entity.PlanGroup;
import com.cityiot.guanxin.planGroup.entity.QPlanGroup;
import com.cityiot.guanxin.planGroup.repository.PlanGroupRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;

@Service
public class PlanGroupService extends BaseService<PlanGroupRepository, PlanGroup> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(PlanGroupService.class);

    @Autowired
    private PlanGroupToPlanService PToPService;

    @Transactional
    public void deleteItemById(long []ids) throws Exception{
        for(var id:ids){
            if (this.getPlanGroupListByParentId(id).size() > 0) {
                throw new Exception("有子级分组，不予以删除！");
            }
            this.deleteItemById(id); //删除主表
            PToPService.deleteItemByGroupId(id);// 删除附表
        }
    }

    //查询分组对应上级所有分组
    public List<PlanGroup> getPlanGroupListByParentId(long id){
        QPlanGroup qPlanGroup = QPlanGroup.planGroup;
        return this.getAllItems(
                query -> query.where(qPlanGroup.parentId.eq(id).or(qPlanGroup.parentIdArr.like(id+",")))
        );
    }

    public void checkIsGtLevel(PlanGroup item) throws Exception {
        // 设备分组属于第几级别
        if (item.getParentId() > 0) {
            PlanGroup group = this.getItemById(item.getParentId());
            if (group != null){
                item.setLevel(group.getLevel() + 1);
                if (StringUtils.isNotEmpty(group.getParentIdArr())) {
                    item.setParentIdArr("" + group.getParentIdArr() + ",'" + group.getId() + "'");
                }else{
                    item.setParentIdArr("'" + group.getId()+ "'");
                }
            }
        }else {
            item.setLevel(1);
            item.setParentIdArr(null);
        }
        if (item.getLevel() > 9) {
            throw new Exception("分组层级最多为9！");
        }
    }
}
