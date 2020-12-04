package com.cityiot.guanxin.light.entity.vo.dto;

import com.cityiot.guanxin.light.entity.LightControlRule;
import com.cityiot.guanxin.light.entity.LightControlRuleDetail;

import java.util.List;

public class LightControlRuleAndDetailsDto {
    private LightControlRule lightControlRule;
    private List<LightControlRuleDetail> lightControlRuleDetailList;

    public LightControlRule getLightControlRule() {
        return lightControlRule;
    }

    public void setLightControlRule(LightControlRule lightControlRule) {
        this.lightControlRule = lightControlRule;
    }

    public List<LightControlRuleDetail> getLightControlRuleDetailList() {
        return lightControlRuleDetailList;
    }

    public void setLightControlRuleDetailList(List<LightControlRuleDetail> lightControlRuleDetailList) {
        this.lightControlRuleDetailList = lightControlRuleDetailList;
    }
}
