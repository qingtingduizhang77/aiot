package com.cityiot.guanxin.light.service;

import com.cityiot.guanxin.light.entity.LightControlRule;
import com.cityiot.guanxin.light.entity.LightControlRuleDetail;
import com.cityiot.guanxin.light.entity.QLightControlRuleDetail;
import com.cityiot.guanxin.light.entity.vo.LightControlRuleAndDetailVo;
import com.cityiot.guanxin.light.entity.vo.LightControlRuleDetailVo;
import com.cityiot.guanxin.light.entity.vo.dto.LightControlRuleAndDetailsDto;
import com.cityiot.guanxin.light.repository.LightControlRuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.ArrayList;
import java.util.List;

@Service
public class LightControlRuleService extends BaseService<LightControlRuleRepository, LightControlRule> {
    @Autowired
    private LightControlRuleDetailService lightControlRuleDetailService;
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightControlRuleService.class);
    public LightControlRule addNewLightControlRule(LightControlRuleAndDetailVo lightControlRuleAndDetailVo) {
        LightControlRule lightControlRule = lightControlRuleAndDetailVo.getLightControlRule();
        if(null==lightControlRule) {
            throw new SwallowException("路灯规则实体不能为空，无法插入");
        }
        lightControlRule = insertItem(lightControlRule);
        List<LightControlRuleDetail> newLightControlRuleDetailList = lightControlRuleAndDetailVo.getNewLightControlRuleDetailList();
        for (LightControlRuleDetail lightControlRuleDetail:newLightControlRuleDetailList) {
            lightControlRuleDetail.setLightControlRuleId((Long) lightControlRule.getId());
            lightControlRuleDetail.setCode(lightControlRule.getCode());
            lightControlRuleDetailService.insertItem(lightControlRuleDetail);
        }
        return lightControlRule;
    }
    public LightControlRule updateLightControlRule(LightControlRuleAndDetailVo lightControlRuleAndDetailVo) {
        // 更新旧路灯控制规则实体
        LightControlRule lightControlRule = lightControlRuleAndDetailVo.getLightControlRule();
        if(null==lightControlRule) {
            throw new SwallowException("路灯规则实体不能为空，无法更新");
        }
        lightControlRule = updateItem(lightControlRule);
        // 更新旧路灯控制规则详情实体
        List<LightControlRuleDetail> oldLightControlRuleDetailList = lightControlRuleAndDetailVo.getOldLightControlRuleDetailList();
        for (LightControlRuleDetail lightControlRuleDetail:oldLightControlRuleDetailList) {
            lightControlRuleDetail.setLightControlRuleId((Long) lightControlRule.getId());
            lightControlRuleDetail.setCode(lightControlRule.getCode());
            lightControlRuleDetailService.updateItem(lightControlRuleDetail);
        }
        // 删除旧路灯控制规则详情实体
        List<LightControlRuleDetail> readyDeleteLightControlRuleDetailList = lightControlRuleAndDetailVo.getReadyDeleteLightControlRuleDetailList();
        for (LightControlRuleDetail lightControlRuleDetail:readyDeleteLightControlRuleDetailList) {
            lightControlRuleDetailService.deleteItemById(lightControlRuleDetail.getId());
        }
        // 新增旧路灯控制规则详情实体
        List<LightControlRuleDetail> newLightControlRuleDetailList = lightControlRuleAndDetailVo.getNewLightControlRuleDetailList();
        for (LightControlRuleDetail lightControlRuleDetail:newLightControlRuleDetailList) {
            lightControlRuleDetail.setLightControlRuleId((Long) lightControlRule.getId());
            lightControlRuleDetail.setCode(lightControlRule.getCode());
            lightControlRuleDetailService.insertItem(lightControlRuleDetail);
        }
        return lightControlRule;
    }

    /** 获取所有的路灯规则和详情
     * @param queryBean
     * @return
     */
    public PageListData<LightControlRuleAndDetailsDto> getAllLightControlRuleAndDetails(BasePageQueryBean queryBean) {
        PageListData<LightControlRule> lightControlRuleAndDetailsDtoPageListData = getAllItemPageByQuerybean(queryBean);
        List<LightControlRuleAndDetailsDto> lightControlRuleAndDetailsDtoList = new ArrayList<>();
        // 策略1，N+1查询
        for(LightControlRule lightControlRule:lightControlRuleAndDetailsDtoPageListData.getItems()) {
            List<LightControlRuleDetail> lightControlRuleDetailList = lightControlRuleDetailService.getAllLightControlRuleDetailOfLight((Long) lightControlRule.getId());
            LightControlRuleAndDetailsDto lightControlRuleAndDetailsDto = new LightControlRuleAndDetailsDto();
            lightControlRuleAndDetailsDto.setLightControlRule(lightControlRule);
            lightControlRuleAndDetailsDto.setLightControlRuleDetailList(lightControlRuleDetailList);
            lightControlRuleAndDetailsDtoList.add(lightControlRuleAndDetailsDto);
        }
        // 强制转换类型
        PageListData<LightControlRuleAndDetailsDto> lightControlRuleAndDetailsDtoPageListData2 = (PageListData)lightControlRuleAndDetailsDtoPageListData;
        lightControlRuleAndDetailsDtoPageListData2.setItems(lightControlRuleAndDetailsDtoList);
        return lightControlRuleAndDetailsDtoPageListData2;
    }

    public List<LightControlRuleDetailVo> queryRules() {
        List<LightControlRule> lightControlRules = this.getAllItems(query -> query);
        QLightControlRuleDetail qLightControlRuleDetail = QLightControlRuleDetail.lightControlRuleDetail;
        List<LightControlRuleDetailVo> voList = new ArrayList<>();
        for (LightControlRule lightControlRule : lightControlRules) {
            LightControlRuleDetailVo vo = new LightControlRuleDetailVo();
            List<LightControlRuleDetail> ruleDetails = lightControlRuleDetailService.getAllItems(query ->
                    query.where(qLightControlRuleDetail.lightControlRuleId.eq((Long) lightControlRule.getId())));
            vo.setRuleName(lightControlRule.getName());
            vo.setRuleCode(lightControlRule.getCode());
            vo.setRuleDetails(ruleDetails);
            voList.add(vo);
        }
        return voList;
    }
}
