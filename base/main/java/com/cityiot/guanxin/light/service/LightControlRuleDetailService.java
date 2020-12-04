package com.cityiot.guanxin.light.service;

import com.cityiot.guanxin.light.entity.LightControlRuleDetail;
import com.cityiot.guanxin.light.entity.QLightControlRuleDetail;
import com.cityiot.guanxin.light.repository.LightControlRuleDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.List;

@Service
public class LightControlRuleDetailService extends BaseService<LightControlRuleDetailRepository, LightControlRuleDetail> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightControlRuleDetailService.class);

    /** 根据路灯控制规则Id获取规则详情
     * @param lightControlRuleId
     * @return
     */
    public List<LightControlRuleDetail> getAllLightControlRuleDetailOfLight(long lightControlRuleId) {
        return getAllItems(tupleJPAQuery -> tupleJPAQuery.where(QLightControlRuleDetail.lightControlRuleDetail.lightControlRuleId.eq(lightControlRuleId)));
    }
}
