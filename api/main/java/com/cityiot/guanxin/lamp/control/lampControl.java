package com.cityiot.guanxin.lamp.control;

import java.util.Date;

import com.cityiot.guanxin.log.annotation.ViLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.light.entity.LightGroup;
import com.cityiot.guanxin.light.entity.QLightGroupRule;
import com.cityiot.guanxin.light.service.LightControlRuleService;
import com.cityiot.guanxin.light.service.LightGroupRuleService;
import com.cityiot.guanxin.light.service.LightGroupService;
import com.cityiot.guanxin.response.BaseResponse;
import com.cityiot.guanxin.response.RespCode;
import com.cityiot.guanxin.response.RespData;

import io.swagger.annotations.Api;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.BasePageQueryBean;

@RestController
@Api(value = "/guanxin-api/lamp",tags = "app路灯接口")
@RequestMapping("/guanxin-api/lamp")
public class lampControl {

    private static final Logger log = LoggerFactory.getLogger(lampControl.class);

    @Autowired
    private LightGroupService service;
    @Autowired
    private LightGroupRuleService lightGroupRuleService;
    @Autowired
    private LightControlRuleService lightControlRuleService;

    static class LampGroupQueryBean extends BasePageQueryBean {
        // 组名
        @Like
        private String groupName;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }

    /**
     * 查询路灯分组
     * @param page
     * @param pageSize
     * @param groupName
     * @return
     */
    @RequestMapping("/queryLampGroups")
    public BaseResponse query(int page,int pageSize, String keyword){
        RespData data = new RespData();
        try {
            LampGroupQueryBean query = new LampGroupQueryBean();
            query.setGroupName(keyword);
            query.setPage(page);
            query.setPageSize(pageSize);
            data.putData("lampGroups", service.getAllItemPageByQuerybean(query).getItems());
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询路灯分组对象出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询路灯分组对象出错");
        }
    }

    /**
     * 分组详情
     * @param groupId
     * @return
     */
    @RequestMapping("/groupDetail")
    public BaseResponse groupDetail(long groupId){
        RespData data = new RespData();
        try {
            LightGroup lightGroup = service.getItemById(groupId);
            data.putData("id", lightGroup.getId());
            data.putData("groupName", lightGroup.getGroupName());
            data.putData("lampCount", lightGroup.getLampCount());
            data.putData("timeSwitchStatus", lightGroup.getTimeSwitchStatus());
            data.putData("address", lightGroup.getAddress());
            // 在线数量
            data.putData("onlineCount", lightGroup.getLampCount());
            // 亮灯时长
            data.putData("lightsTime", 6);
            QLightGroupRule lightGroupRule = QLightGroupRule.lightGroupRule;
            data.putData("rules", lightGroupRuleService
                    .getAllItems(query -> query.where(lightGroupRule.groupId.eq(groupId))));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询路灯分组对象出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询路灯分组对象出错");
        }
    }


    /**
     * 查询定时开关规则列表
     * @return
     */
    @RequestMapping("/queryRules")
    public BaseResponse queryRules(){
        RespData data = new RespData();
        try {
            data.putData("rules", lightControlRuleService.queryRules());
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询定时开关规则列表出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询定时开关规则列表出错");
        }
    }


    /**
     * 新增/修改分组开关规则出错
     * @param groupId
     * @param startTime
     * @param endTime
     * @param ruleId
     * @param brightness
     * @return
     */
    @ViLog(operEvent = "新增/修改分组开关规则出错", operType =2)//日志记录
    @RequestMapping("/saveGroupRules")
    public BaseResponse saveGroupRules(long groupId, Date startTime, Date endTime, Long ruleId, Integer brightness){
        RespData data = new RespData();
        try {
            service.saveGroupRules(groupId, startTime, endTime, ruleId, brightness);
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("新增/修改分组开关规则出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "新增/修改分组开关规则出错");
        }
    }

    /**
     * 路灯分组控制
     * @param groupId
     * @param timeSwitchStatus
     * @return
     */
    @ViLog(operEvent = "路灯分组控制", operType =2)//日志记录
    @RequestMapping("/lampGroupControl")
    public BaseResponse lampGroupControl(long groupId, int timeSwitchStatus){
        RespData data = new RespData();
        try {
            service.updateStatus(groupId, timeSwitchStatus);
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("路灯分组控制出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "路灯分组控制出错");
        }
    }


    /**
     * 删除分组开关规则出错
     * @param ruleId
     * @return
     */
    @ViLog(operEvent = "删除分组开关规则出错", operType =3)//日志记录
    @RequestMapping("/delGroupRule")
    public BaseResponse delGroupRule(long ruleId){
        RespData data = new RespData();
        try {
            service.delGroupRule(ruleId);
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("删除分组开关规则出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "删除分组开关规则出错");
        }
    }
}
