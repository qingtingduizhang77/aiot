package com.cityiot.guanxin.light.service;

import com.cityiot.guanxin.light.entity.Light;
import com.cityiot.guanxin.light.entity.LightGroup;
import com.cityiot.guanxin.light.entity.LightGroupRule;
import com.cityiot.guanxin.light.entity.vo.LightGroupAndGroupRuleVo;
import com.cityiot.guanxin.light.entity.vo.LightGroupAndLightDetailVo;
import com.cityiot.guanxin.light.repository.LightGroupRepository;
import com.cityiot.guanxin.light.repository.LightGroupRuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.Date;
import java.util.List;

@Service
public class LightGroupService extends BaseService<LightGroupRepository, LightGroup> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightGroupService.class);
    @Autowired
    private LightGroupRuleService lightGroupRuleService;
    @Autowired
    private LightGroupRepository repository;
    @Autowired
    private LightService lightService;
    @Autowired
    private LightGroupRuleRepository lightGroupRuleRepository;

    public LightGroup addNewLightGroup(LightGroupAndLightDetailVo lightGroupAndLightDetailVo) {
        LightGroup lightGroup = lightGroupAndLightDetailVo.getLightGroup();
        lightGroup.setLampCount(lightGroupAndLightDetailVo.getNewLightList().size());
        if(null==lightGroup) {
            throw new SwallowException("路灯分组实体不能为空，无法插入");
        }
        lightGroup = insertItem(lightGroup);
        List<Light> newLightList = lightGroupAndLightDetailVo.getNewLightList();
        for (Light light:newLightList) {
            light.setGroupId((Long) lightGroup.getId());
            lightService.insertItem(light);
        }
        return lightGroup;
    }

    public LightGroup addNewLightGroup(LightGroupAndGroupRuleVo lighGroupAndGroupRuleVo) {
        LightGroup lightGroup = lighGroupAndGroupRuleVo.getLightGroup();
        if(null==lightGroup) {
            throw new SwallowException("路灯分组实体不能为空，无法插入");
        }
        lightGroup = insertItem(lightGroup);
//        List<LightGroupRule> newLightList = lighGroupAndGroupRuleVo.getNewLightGroupRuleList();
//        for (LightGroupRule lightGroupRule:newLightGroupRuleList) {
//            lightGroupRule.setGroupId(lightGroup.getId());
//            lightGroupRuleService.insertItem(lightGroupRule);
//        }
        return lightGroup;
    }

    public LightGroup updateLightGroupAndLights(LightGroupAndLightDetailVo lightGroupAndLightDetailVo) {
        // 更新旧路灯控制规则实体
        LightGroup lightGroup = lightGroupAndLightDetailVo.getLightGroup();
        if(null==lightGroup) {
            throw new SwallowException("路灯分组实体不能为空，无法更新");
        }
        lightGroup = updateItem(lightGroup);

        // 删除旧路灯控制规则详情实体
        List<Light> readyDeleteLightList = lightGroupAndLightDetailVo.getReadyDeleteLightList();
        for (Light Light:readyDeleteLightList) {
            lightService.deleteItemById(Light.getId());
        }
        // 新增旧路灯控制规则详情实体
        List<Light> newLightList = lightGroupAndLightDetailVo.getNewLightList();
        for (Light Light:newLightList) {
            Light.setGroupId((Long)lightGroup.getId());
            lightService.insertItem(Light);
        }
        return lightGroup;
    }

    public LightGroup updateLightGroup(LightGroupAndGroupRuleVo lighGroupAndGroupRuleVo) {
        // 更新旧路灯控制规则实体
        LightGroup lightGroup = lighGroupAndGroupRuleVo.getLightGroup();
        if(null==lightGroup) {
            throw new SwallowException("路灯分组实体不能为空，无法更新");
        }
        lightGroup = updateItem(lightGroup);
        // 更新旧路灯控制规则详情实体
        List<LightGroupRule> oldLightGroupRuleList = lighGroupAndGroupRuleVo.getOldLightGroupRuleList();
        for (LightGroupRule lightGroupRule:oldLightGroupRuleList) {
            lightGroupRule.setGroupId((Long)lightGroup.getId());
            lightGroupRuleService.updateItem(lightGroupRule);
        }
        // 删除旧路灯控制规则详情实体
        List<LightGroupRule> readyDeleteLightGroupRuleList = lighGroupAndGroupRuleVo.getReadyDeleteLightGroupRuleList();
        for (LightGroupRule lightGroupRule:readyDeleteLightGroupRuleList) {
            lightGroupRuleService.deleteItemById(lightGroupRule.getId());
        }
        // 新增旧路灯控制规则详情实体
        List<LightGroupRule> newLightGroupRuleList = lighGroupAndGroupRuleVo.getNewLightGroupRuleList();
        for (LightGroupRule lightGroupRule:newLightGroupRuleList) {
            lightGroupRule.setGroupId((Long)lightGroup.getId());
            lightGroupRuleService.insertItem(lightGroupRule);
        }
        return lightGroup;
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(long groupId, int timeSwitchStatus) {
        if(timeSwitchStatus == 0 || timeSwitchStatus == 1){
            repository.updateStatus(groupId, timeSwitchStatus);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void saveGroupRules(long groupId, Date startTime, Date endTime, Long ruleId, Integer brightness) {
        LightGroupRule lightGroupRule = new LightGroupRule();
        lightGroupRule.setGroupId(groupId);
        lightGroupRule.setStartTime(startTime);
        lightGroupRule.setEndTime(endTime);
        lightGroupRule.setBrightness(brightness);
        if(ruleId == null){
            this.lightGroupRuleService.insertItem(lightGroupRule);
        }else{
            LightGroupRule updateItem = this.lightGroupRuleService.getItemById(ruleId);
            updateItem.setStartTime(startTime);
            updateItem.setEndTime(endTime);
            updateItem.setBrightness(brightness);
            this.lightGroupRuleService.updateItem(updateItem);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delGroupRule(long ruleId) {
        this.lightGroupRuleRepository.deleteItemById(ruleId);
    }
}
