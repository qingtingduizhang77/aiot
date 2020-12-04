package com.cityiot.guanxin.light.entity.vo;

import com.cityiot.guanxin.light.entity.LightControlRuleDetail;

import java.util.List;

public class LightControlRuleDetailVo {
    private String ruleName;
    private String ruleCode;
    private List<LightControlRuleDetail> ruleDetails;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public List<LightControlRuleDetail> getRuleDetails() {
        return ruleDetails;
    }

    public void setRuleDetails(List<LightControlRuleDetail> ruleDetails) {
        this.ruleDetails = ruleDetails;
    }
}
