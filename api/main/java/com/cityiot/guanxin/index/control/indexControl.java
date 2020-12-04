package com.cityiot.guanxin.index.control;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.emergency.service.EmergencyService;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.monitor.statistics.control.StatisticsControl.QueryBean;
import com.cityiot.guanxin.response.BaseResponse;
import com.cityiot.guanxin.response.RespCode;
import com.cityiot.guanxin.response.RespData;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.chargingPileStation.entity.ChargingPileStation;
import com.cityiot.guanxin.workOrder.entity.QEWorkOrder;
import com.cityiot.guanxin.workOrder.service.EWorkOrderService;

import io.swagger.annotations.Api;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;


@RestController
@Api(value = "/guanxin-api/index",tags = "app首页接口")
@RequestMapping("/guanxin-api/index")
public class indexControl {

    private static final Logger log = LoggerFactory.getLogger(indexControl.class);

    @Autowired
    private EWorkOrderService workOrderService;
    @Autowired
    private DeviceinformationService deviceinformationService;
    @Autowired
    private EmergencyService emergencyService;


    // 工单
    static class EWorkOrderQueryBean extends QueryBean {
        // 工单状态
        private Integer handleStatus;
        private Long handlerId;

        public Integer getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(Integer handleStatus) {
            this.handleStatus = handleStatus;
        }

        public Long getHandlerId() {
            return handlerId;
        }

        public void setHandlerId(Long handlerId) {
            this.handlerId = handlerId;
        }


    }


    /**
     * 查询我的待办事项列表
     *
     * @param handleStatus 工单状态2处理中 3已完成 4已退单
     * @param page         页码由1开始
     * @param pageSize     每页条数
     * @return
     */
    @RequestMapping("/queryMyWork")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<ChargingPileStation>> queryMyWork(@RequestBody EWorkOrderQueryBean query) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        try {
            query.setHandlerId(loginUser.getId());
            QEWorkOrder qeWorkOrder = QEWorkOrder.eWorkOrder;
            return ApiResult.success(workOrderService.getAllItemPageByQuerybean(query, qu ->
                    qu.orderBy(qeWorkOrder.id.desc())));

        } catch (Exception e) {
            log.error("查询我的待办事项列表:" + e.getMessage(), e);

            return ApiResult.fail("查询我的待办事项列表:" + e.getMessage());
        }
    }


    /**
     * 查询我的事项数量
     *
     * @return
     */
    @RequestMapping("/queryMyWorkNum")
    public BaseResponse queryMyWorkNum() {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
        try {
            data.putData("workNumInfo", workOrderService.queryMyWorkNum(loginUser.getId()));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询我的事项数量出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "查询我的事项数量出错");
        }
    }


    /**
     * 查询我的日常统计
     *
     * @param scopeType
     * @return
     */
    @RequestMapping("/queryMyStatistic/{scopeType}")
    public ApiResult<Map<String, Object>> queryMyStatistic(@PathVariable Integer scopeType) {
    	 Subject subject = SecurityUtils.getSubject();
         Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //设备数量统计
//            data.getData().putAll(deviceinformationService.selectCount());
            //工单数量统计
            result.put("workOrderStatistic", workOrderService.workOrderStatistic(scopeType,loginUser.getId()));
//            data.putData("workOrderStatistic", workOrderService.workOrderStatistic(scopeType));
//            BaseResponse baseResponse = new BaseResponse(data);
//            baseResponse.setState(RespCode.SUCCESS);
            return ApiResult.success(result);
        } catch (Exception e) {
            log.error("查询我的日常统计出错:" + e.getMessage(), e);
//            return new BaseResponse(data, RespCode.ERROR, "查询我的日常统计出错");
            return ApiResult.fail("查询我的日常统计出错:" + e.getMessage());
        }
    }

    static class EmergencyQueryBean extends BasePageQueryBean {
        @FieldPath(name = "deviceTypeName", joinEntityClass = DeviceType.class)
        private String deviceTypeName;

        @FieldPath(name = "deviceCode", joinEntityClass = Deviceinformation.class)
        @Like
        private String deviceCode;

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }
    }

    /**
     * 查询首页告警信息
     *
     * @return
     */
   // @ViLog(operEvent = "查询首页告警信息", operType =1)  //接口日志记录的AOP自定义注解-operEvent：操作事件；operType=日志类型
    @RequestMapping("/queryEmergencies")
    public BaseResponse queryEmergencies(int page, int pageSize, String keyword, Integer deviceType) {
        RespData data = new RespData();
        try {
            EmergencyQueryBean queryBean = new EmergencyQueryBean();
            queryBean.setPage(page);
            queryBean.setPageSize(pageSize);
            queryBean.setDeviceCode(keyword);
            if (deviceType != null) {
                queryBean.setDeviceTypeName(DeviceType.getDeviceType(deviceType));
            }
            data.putData("totalNum", emergencyService.selectTotalNum());
            data.putData("emergencies", emergencyService.getAllItemByQuerybean(queryBean));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询首页告警信息出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "查询首页告警信息出错");
        }
    }


    /**
     * 告警已读
     *
     * @param
     * @return
     */
    @RequestMapping("/readEmergency")
    public BaseResponse readEmergency() {
        RespData data = new RespData();
        try {
            emergencyService.readEmergency();
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("告警已读出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "告警已读出错");
        }
    }

}
