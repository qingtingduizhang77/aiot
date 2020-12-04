package com.cityiot.guanxin.light.entity.vo;

import com.cityiot.guanxin.light.entity.LightGroup;
import com.cityiot.guanxin.light.entity.LightGroupRule;

import java.util.List;

public class LightGroupAndGroupRuleVo {
    // 路灯分组
    private LightGroup lightGroup;
    // 旧的要编辑的路灯控制规则子表
    private List<LightGroupRule> oldLightGroupRuleList;
    // 新路灯控制规则子表
    private List<LightGroupRule> newLightGroupRuleList;
    // 将要删除的路灯控制规则子表
    private List<LightGroupRule> readyDeleteLightGroupRuleList;

    public LightGroup getLightGroup() {
        return lightGroup;
    }

    public void setLightGroup(LightGroup lightGroup) {
        this.lightGroup = lightGroup;
    }

    public List<LightGroupRule> getOldLightGroupRuleList() {
        return oldLightGroupRuleList;
    }

    public void setOldLightGroupRuleList(List<LightGroupRule> oldLightGroupRuleList) {
        this.oldLightGroupRuleList = oldLightGroupRuleList;
    }

    public List<LightGroupRule> getNewLightGroupRuleList() {
        return newLightGroupRuleList;
    }

    public void setNewLightGroupRuleList(List<LightGroupRule> newLightGroupRuleList) {
        this.newLightGroupRuleList = newLightGroupRuleList;
    }

    public List<LightGroupRule> getReadyDeleteLightGroupRuleList() {
        return readyDeleteLightGroupRuleList;
    }

    public void setReadyDeleteLightGroupRuleList(List<LightGroupRule> readyDeleteLightGroupRuleList) {
        this.readyDeleteLightGroupRuleList = readyDeleteLightGroupRuleList;
    }
}
