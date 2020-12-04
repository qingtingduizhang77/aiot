package com.cityiot.guanxin.light.entity.vo;

import com.cityiot.guanxin.light.entity.LightControlRule;
import com.cityiot.guanxin.light.entity.LightControlRuleDetail;

import java.util.List;

public class LightControlRuleAndDetailVo {
    // 路灯控制规则
    private LightControlRule lightControlRule;
    // 旧的要编辑的路灯控制规则子表
    private List<LightControlRuleDetail> oldLightControlRuleDetailList;
    // 新路灯控制规则子表
    private List<LightControlRuleDetail> newLightControlRuleDetailList;
    // 将要删除的路灯控制规则子表
    private List<LightControlRuleDetail> readyDeleteLightControlRuleDetailList;

    public LightControlRule getLightControlRule() {
        return lightControlRule;
    }

    public void setLightControlRule(LightControlRule lightControlRule) {
        this.lightControlRule = lightControlRule;
    }

    public List<LightControlRuleDetail> getNewLightControlRuleDetailList() {
        return newLightControlRuleDetailList;
    }

    public void setNewLightControlRuleDetailList(List<LightControlRuleDetail> newLightControlRuleDetailList) {
        this.newLightControlRuleDetailList = newLightControlRuleDetailList;
    }

    public List<LightControlRuleDetail> getReadyDeleteLightControlRuleDetailList() {
        return readyDeleteLightControlRuleDetailList;
    }

    public void setReadyDeleteLightControlRuleDetailList(List<LightControlRuleDetail> readyDeleteLightControlRuleDetailList) {
        this.readyDeleteLightControlRuleDetailList = readyDeleteLightControlRuleDetailList;
    }

    public List<LightControlRuleDetail> getOldLightControlRuleDetailList() {
        return oldLightControlRuleDetailList;
    }

    public void setOldLightControlRuleDetailList(List<LightControlRuleDetail> oldLightControlRuleDetailList) {
        this.oldLightControlRuleDetailList = oldLightControlRuleDetailList;
    }
}
