package com.cityiot.guanxin.workOrder.control;

import java.util.*;
import java.util.function.Function;

import com.cityiot.guanxin.common.utils.DateUtil;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.entity.*;
import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;
import com.cityiot.guanxin.workOrder.inspection.entity.QInspectionRecord;
import com.cityiot.guanxin.workOrder.inspection.service.InspectionRecordService;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceRecord;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceRecord;
import com.cityiot.guanxin.workOrder.maintenance.service.MaintenanceAccessoriesService;
import com.cityiot.guanxin.workOrder.maintenance.service.MaintenanceRecordService;
import com.cityiot.guanxin.workOrder.vo.MaintenanceWorkOrderVo;
import com.cityiot.guanxin.workOrder.vo.RepairWorkOrderVo;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import io.swagger.annotations.Api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.message.service.EMessageCenterService;
import com.cityiot.guanxin.response.BaseResponse;
import com.cityiot.guanxin.response.RespCode;
import com.cityiot.guanxin.response.RespData;
import com.cityiot.guanxin.service.SystemVariableService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;
import com.cityiot.guanxin.workOrder.repository.EWorkOrderRepository;
import com.cityiot.guanxin.workOrder.service.EInspectionWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderProgressService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderService;
import com.querydsl.core.types.Predicate;

import swallow.framework.jpaquery.repository.annotations.IgnorePredicate;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.jpaquery.repository.annotations.PredicateMethod;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;
import com.cityiot.guanxin.workOrder.vo.InspectionWorkOrderVo;
import swallow.framework.web.PageListData;

@RestController
@Api(value = "/guanxin-api/workOrder",tags = "工单数据访问API接口")
@RequestMapping("/guanxin-api/workOrder")
public class WorkOrderControl {

    private static final Logger log = LoggerFactory.getLogger(WorkOrderControl.class);

    @Autowired
    private DeviceinformationService deviceinformationService;
    @Autowired
    private SystemVariableService systemVariableService;
    @Autowired
    private EWorkOrderService workOrderService;
    @Autowired
    private EInspectionWorkOrderService inspectionWorkOrderService;
    @Autowired
    private EMaintenanceWorkOrderService maintenanceWorkOrderService;
    @Autowired
    private ERepairWorkOrderService repairWorkOrderService;
    @Autowired
    private EWorkOrderProgressService workOrderProgressService;
    @Autowired
    private EWorkOrderRepository workOrderRepository;
    @Autowired
    private OperatorOrCompanyManageService operatorOrCompanyManageService;
    @Autowired
    private EMessageCenterService messageCenterService;
    @Autowired
    private MaintenanceRecordService maintenanceRecordService;
    @Autowired
    private InspectionRecordService inspectionRecordService;
    @Autowired
    private MaintenanceAccessoriesService maintenanceAccessoriesService;

    // 设备信息
    static class DeviceInfoQueryBean extends BasePageQueryBean {

        @PredicateMethod("queryByType")
        private String keyword;

        @IgnorePredicate
        private Integer queryType;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public Integer getQueryType() {
            return queryType;
        }

        public void setQueryType(Integer queryType) {
            this.queryType = queryType;
        }

        public Predicate queryByType() {
            QDeviceinformation df = QDeviceinformation.deviceinformation;
            if (queryType == 1) { //设备编号模糊查询
                return df.deviceCode.like("%" + keyword + "%");
            }
            return null;
        }
    }

    // 工单
    static class WorkOrderQueryBean extends BasePageQueryBean {

        private String code;

        // 排序方式 1按距离 2按时间
        @IgnorePredicate
        private Integer workOrderSort;

        // 工单状态
        private Integer handleStatus;

        // 工单类型 1巡检工单 2保养工单 3维修工单
        private Integer workOrderType;

        public Integer getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(Integer handleStatus) {
            this.handleStatus = handleStatus;
        }

        public Integer getWorkOrderType() {
            return workOrderType;
        }

        public void setWorkOrderType(Integer workOrderType) {
            this.workOrderType = workOrderType;
        }

        public Integer getWorkOrderSort() {
            return workOrderSort;
        }

        public void setWorkOrderSort(Integer workOrderSort) {
            this.workOrderSort = workOrderSort;
        }

    }

    /**
     * 根据关键字查询设备信息
     *
     * @param query
     * @return
     */
    @RequestMapping("/queryDeviceInfo")
    public BaseResponse queryDeviceInfo(String keywork, Integer page, Integer pageSize, Integer queryType) {
        RespData data = new RespData();
        try {
            DeviceInfoQueryBean query = new DeviceInfoQueryBean();
            query.setKeyword(keywork);
            query.setQueryType(queryType);
            query.setPage(page);
            query.setPageSize(pageSize);
            if (query.queryType != null && query.queryType == 2) { //编号精确查询
                data.putData("deviceList", Arrays.asList(deviceinformationService.getItemByDeviceCode(query.getKeyword())));
                BaseResponse baseResponse = new BaseResponse(data);
                baseResponse.setState(RespCode.SUCCESS);
                return baseResponse;
            }
            data.putData("deviceList", deviceinformationService.getAllItemPageByQuerybean(query).getItems());
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("根据关键字查询设备信息出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "根据关键字查询设备信息出错");
        }
    }

    /**
     * 查询所有维修项目
     *
     * @param dictionaryCode
     * @return
     */
    @RequestMapping("/queryAllRepairByCode")
    public BaseResponse queryAllRepairByCode(String dictionaryCode) {
        RespData data = new RespData();
        try {
            data.putData("dicList", systemVariableService.queryAllRepairByCode(dictionaryCode));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询所有维修项目出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "查询所有维修项目出错");
        }
    }

    /**
     * 通过查询bean进行工单分页查询数据
     *
     * @param query
     * @return
     */
    @RequestMapping("/workOrderList")
    public BaseResponse workOrderList(long id, Integer page, Integer pageSize, String pLongitude, String pLatitude,
                                      Integer workOrderType, Integer handleStatus, Integer workOrderSort) {
        RespData data = new RespData();
        try {
            data.putData("workOrderList", workOrderRepository
                    .workOrderList(page, pageSize, pLongitude, pLatitude, workOrderType, handleStatus, workOrderSort, id));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("工单分页查询数据出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "工单分页查询数据出错");
        }
    }

    /**
     * 查询巡检工单详情
     *
     * @param workOrderId 巡检工单的id
     * @return
     */
    @RequestMapping("/queryInspectOrderInfo")
    public ApiResult<InspectionWorkOrderVo> queryInspectOrderInfo(long workOrderId) {
        RespData data = new RespData();
        try {
            InspectionWorkOrderVo result = inspectionWorkOrderService.queryInspectOrderInfo(workOrderId);
            var qi = QInspectionRecord.inspectionRecord;
            List<InspectionRecord> records = inspectionRecordService.getAllItems(query -> query.where(qi.patrollerNo.eq(workOrderId))
                    .orderBy(qi.created.desc()));
            result.setRecords(records);
            return ApiResult.success(result);
        } catch (Exception e) {
            log.error(" 查询巡检工单详情出错:" + e.getMessage(), e);
            return ApiResult.fail("查询巡检工单详情出错:" + e.getMessage());
        }
    }

    /**
     * 查询保养记录
     * @param id
     * @return
     */
    @RequestMapping("/queryInspectRecordInfo/{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String, Object>> queryInspectRecordInfo(@PathVariable long id) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("recordInfo", inspectionRecordService.getItemById(id));
            QMaintenanceAccessories qm = QMaintenanceAccessories.maintenanceAccessories;
            List<MaintenanceAccessories>  images = maintenanceAccessoriesService.getAllItems(query -> query
                    .where(qm.recordID.eq(id))
                    .where(qm.type.eq(2)) // 1-是保养工单
                    .where(qm.fileType.eq(1))
                    .orderBy(qm.created.asc())
            );
            result.put("images", images);
            return ApiResult.success(result);
        } catch (Exception e) {
            log.error("查询保养记录对象出错:" + e.getMessage(), e);
            return ApiResult.fail("查询代保养记录对象出错:" + e.getMessage());
        }
    }

    /**
     * 巡检工单设置确认处理完成
     *
     * @param workOrderId 工单的id
     * @return
     */
    @ViLog(operEvent = "巡检工单设置确认处理完成", operType =2)//日志记录
    @RequestMapping("/inspectConfirm")
    public ApiResult inspectConfirm(long workOrderId) {
        try {
            EInspectionWorkOrder entity = new EInspectionWorkOrder();
            entity.setId(workOrderId);
            entity.setHandleStatus(30); // 设置30为确认处理状态
            inspectionWorkOrderService.updateWorkOrderStatus(entity);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("巡检工单确认处理出错:" + e.getMessage(), e);
            return ApiResult.fail("巡检工单确认处理出错:" + e.getMessage());
        }
    }

    /**
     * 巡检工单设置完成处理完成
     *
     * @param workOrderId
     * @return
     */
    @ViLog(operEvent = "巡检工单设置完成处理完成", operType =2)//日志记录
    @RequestMapping("/inspectHandleFinish")
    public ApiResult inspectHandleFinish(long workOrderId) {
        try {
            EInspectionWorkOrder entity = new EInspectionWorkOrder();
            entity.setId(workOrderId);
            entity.setHandleStatus(40); // 设置30为确认处理状态
            inspectionWorkOrderService.updateWorkOrderStatus(entity);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("巡检工单确认处理出错:" + e.getMessage(), e);
            return ApiResult.fail("巡检工单确认处理出错:" + e.getMessage());
        }
    }

    /**
     * 巡检工单批量设置保养记录完成
     *
     * @param workOrderId 巡检工单的id
     * @param ids         记录id
     * @return
     */
    @ViLog(operEvent = "巡检工单批量设置保养记录完成", operType =2)//日志记录
    @RequestMapping("/batchSetInspectRecordFinish")
    public ApiResult batchSetInspectRecordFinish(long workOrderId, long[] ids) {
        try {
            inspectionWorkOrderService.batchHandleRecord(ids, workOrderId);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("巡检工单批量设置保养记录完成出错:" + e.getMessage(), e);
            return ApiResult.fail("巡检工单批量设置保养记录完成出错:" + e.getMessage());
        }
    }

    /**
     * 查询保养工单详情
     *
     * @param workOrderId
     * @return
     */
    @RequestMapping("/queryMaintainceOrderInfo")
    public ApiResult<MaintenanceWorkOrderVo> queryMaintainceOrderInfo(long workOrderId) {
        try {
            MaintenanceWorkOrderVo result = maintenanceWorkOrderService.queryMaintainceOrderInfo(workOrderId);
            var qm = QMaintenanceRecord.maintenanceRecord;
            List<MaintenanceRecord> recordList = maintenanceRecordService.getAllItems(query -> query.where(qm.maintainNo.eq(workOrderId))
                    .orderBy(qm.created.desc()));
            result.setRecords(recordList);
            return ApiResult.success(result);
        } catch (Exception e) {
            log.error("查询保养工单详情出错:" + e.getMessage(), e);
            return ApiResult.fail("查询保养工单详情出错:" + e.getMessage());
        }
    }

    /**
     * 查询保养记录
     * @param id
     * @return
     */
    @RequestMapping("/queryMaintainceRecordInfo/{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String, Object>> queryMaintainceRecordInfo(@PathVariable long id) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("recordInfo", maintenanceRecordService.getItemById(id));
            QMaintenanceAccessories qm = QMaintenanceAccessories.maintenanceAccessories;
            List<MaintenanceAccessories>  images = maintenanceAccessoriesService.getAllItems(query -> query
                    .where(qm.recordID.eq(id))
                    .where(qm.type.eq(1)) // 1-是保养工单
                    .where(qm.fileType.eq(1))
                    .orderBy(qm.created.asc())
            );
            result.put("images", images);
            return ApiResult.success(result);
        } catch (Exception e) {
            log.error("查询保养记录对象出错:" + e.getMessage(), e);
            return ApiResult.fail("查询代保养记录对象出错:" + e.getMessage());
        }
    }


    /**
     * 保养工单设置确认处理完成
     *
     * @param workOrderId 工单的id
     * @return
     */
    @ViLog(operEvent = "保养工单设置确认处理完成", operType =2)//日志记录
    @RequestMapping("/mainTainceConfirm")
    public ApiResult mainTainceConfirm(long workOrderId) {
        try {
            EMaintenanceWorkOrder entity = new EMaintenanceWorkOrder();
            entity.setId(workOrderId);
            entity.setHandleStatus(30); // 设置30为确认处理状态
            maintenanceWorkOrderService.updateWorkOrderStatus(entity);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("保养工单确认处理出错:" + e.getMessage(), e);
            return ApiResult.fail("保养工单确认处理出错:" + e.getMessage());
        }
    }

    /**
     * 保养工单设置完成处理完成
     *
     * @param workOrderId
     * @return
     */
    @ViLog(operEvent = "保养工单设置完成处理完成", operType =2)//日志记录
    @RequestMapping("/mainTainceHandleFinish")
    public ApiResult mainTainceHandleFinish(long workOrderId) {
        try {
            EMaintenanceWorkOrder entity = new EMaintenanceWorkOrder();
            entity.setId(workOrderId);
            entity.setHandleStatus(40); // 设置30为确认处理状态
            maintenanceWorkOrderService.updateWorkOrderStatus(entity);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("保养工单确认处理出错:" + e.getMessage(), e);
            return ApiResult.fail("保养工单确认处理出错:" + e.getMessage());
        }
    }

    /**
     * 保养工单批量设置保养记录完成
     *
     * @param workOrderId 保养工单的id
     * @param ids         记录id
     * @return
     */
    @ViLog(operEvent = "保养工单批量设置保养记录完成", operType =2)//日志记录
    @RequestMapping("/batchSetMainTainceRecordFinish")
    public ApiResult batchSetMainTainceRecordFinish(long workOrderId, long[] ids) {
        try {
            maintenanceWorkOrderService.batchHandleRecord(ids, workOrderId);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("保养工单批量设置保养记录完成出错:" + e.getMessage(), e);
            return ApiResult.fail("保养工单批量设置保养记录完成出错:" + e.getMessage());
        }
    }

    /**
     * 工单分页列表的查询对象
     *
     * @author linjf
     */
    public static class QueryBean extends BasePageQueryBean {

        private Integer handleStatus;

        private Date startTime;

        private Date endTime;

        public Integer getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(Integer handleStatus) {
            this.handleStatus = handleStatus;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }
    }

    /**
     * 查询当前用户正在处理或已经处理完毕的的保养工单的列表
     *
     * @param queryBean
     * @return
     */
    @RequestMapping("/queryMaintainceOrderList")
    public ApiResult<PageListData<EMaintenanceWorkOrder>> queryMaintainceOrderList(@RequestBody WorkOrderControl.QueryBean queryBean) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        Long userID = loginUser.getId();
        try {
            var qm = QEMaintenanceWorkOrder.eMaintenanceWorkOrder;
            // 查询当前处理人为当前用户，并且已经发布的保养工单
            // 当前处理人为创建人
            var v1 = qm.creator.eq(userID);
            var bean = maintenanceWorkOrderService.getAllItemsByPage(
                    query -> {
                        var p1 = qm.handlerId.eq(userID); // 当前人为处理人
                        if(queryBean.getHandleStatus().intValue() > 10) {
                            query.where(qm.handleStatus.eq(queryBean.getHandleStatus()));
                        }else {
                            query.where(qm.handleStatus.ne(10));
                        }
                        if(queryBean.getStartTime() != null && queryBean.getEndTime() != null){
                            query.where(qm.created.between(queryBean.getStartTime(), DateUtil.getEndDate(queryBean.getEndTime())));
                        }
//                        query.where(qm.handlerId.eq(userID)).orderBy(qm.handleStatus.asc(), qm.created.desc());
                        query.where(p1.or(v1)).orderBy(qm.handleStatus.asc(), qm.created.desc());
                        return query;
                    },
                    queryBean.getPage(),
                    queryBean.getPageSize()
            );
            return ApiResult.success(bean);
        } catch (Exception e) {
            log.error("查询保养工单对象出错:" + e.getMessage(), e);
            return ApiResult.fail("查询保养工单对象出错:" + e.getMessage());
        }
    }

    /**
     * 查询当前用户正在处理或已经处理完毕的的巡检工单的列表
     *
     * @param queryBean
     * @return
     */
    @RequestMapping("/queryERepairWorkOrderList")
    public ApiResult<PageListData<ERepairWorkOrder>> queryERepairWorkOrderList(@RequestBody WorkOrderControl.QueryBean queryBean) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        Long userID = loginUser.getId();
        try {
            var qm = QERepairWorkOrder.eRepairWorkOrder;
            // 查询当前处理人为当前用户，并且已经发布的维修工单
            // 当前处理人为创建人
            var v1 = qm.creator.eq(userID);
            var bean = repairWorkOrderService.getAllItemsByPage(
                    query -> {
                        var p1 = qm.handlerId.eq(userID); // 当前人为处理人
                        if(queryBean.getHandleStatus().intValue() > 10) {
                            query.where(qm.handleStatus.eq(queryBean.getHandleStatus()));
                        }else {
                            query.where(qm.handleStatus.ne(10));// 当前工单已经发布
                        }
                        if(queryBean.getStartTime() != null && queryBean.getEndTime() != null){
                            query.where(qm.created.between(queryBean.getStartTime(), DateUtil.getEndDate(queryBean.getEndTime())));
                        }
                        query.where(p1.or(v1)).orderBy(qm.handleStatus.asc(), qm.created.desc());
                        return query;
                    },
                    queryBean.getPage(),
                    queryBean.getPageSize()
            );
            return ApiResult.success(bean);
        } catch (Exception e) {
            log.error("查询维修工单对象出错:" + e.getMessage(), e);
            return ApiResult.fail("查询维修工单对象出错:" + e.getMessage());
        }
    }

    /**
     * 查询当前用户正在处理或已经处理完毕的的巡检工单的列表
     *
     * @param queryBean
     * @return
     */
    @RequestMapping("/queryEInspectionWorkOrderList")
    public ApiResult<PageListData<EInspectionWorkOrder>> queryEInspectionWorkOrderList(@RequestBody WorkOrderControl.QueryBean queryBean) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        Long userID = loginUser.getId();
        try {
            var qm = QEInspectionWorkOrder.eInspectionWorkOrder;
            // 查询当前处理人为当前用户，并且已经发布的巡检工单
            // 当前处理人为创建人
            var v1 = qm.creator.eq(userID);
            var bean = inspectionWorkOrderService.getAllItemsByPage(
                    query -> {
                        var p1 = qm.handlerId.eq(userID); // 当前人为处理人
                        if(queryBean.getHandleStatus().intValue() > 10) {
                            query.where(qm.handleStatus.eq(queryBean.getHandleStatus()));
                        }else {
                            query.where(qm.handleStatus.ne(10));
                        }
                        if(queryBean.getStartTime() != null && queryBean.getEndTime() != null){
                            query.where(qm.created.between(queryBean.getStartTime(), DateUtil.getEndDate(queryBean.getEndTime())));
                        }
//                        query.where(qm.handlerId.eq(userID)).orderBy(qm.handleStatus.asc(), qm.created.desc());
                        query.where(p1.or(v1)).orderBy(qm.handleStatus.asc(), qm.created.desc());
                        return query;
                    },
                    queryBean.getPage(),
                    queryBean.getPageSize()
            );
            return ApiResult.success(bean);
        } catch (Exception e) {
            log.error("查询巡检工单对象出错:" + e.getMessage(), e);
            return ApiResult.fail("查询巡检工单对象出错:" + e.getMessage());
        }
    }

    /**
     * 查询维修工单详情
     *
     * @param workOrderId
     * @return
     */
    @RequestMapping("/queryRepairOrderInfo")
    public ApiResult<RepairWorkOrderVo> queryRepairOrderInfo(long workOrderId) {
        try {
            return ApiResult.success(repairWorkOrderService.queryRepairOrderInfo(workOrderId));
        } catch (Exception e) {
            log.error("查询维修工单详情出错:" + e.getMessage(), e);
            return ApiResult.fail("查询维修工单详情出错:" + e.getMessage());
        }
    }

    /**
     * 维修工单设置确认处理完成
     *
     * @param workOrderId 工单的id
     * @return
     */
    @ViLog(operEvent = "维修工单设置确认处理完成", operType =1)//日志记录
    @RequestMapping("/repairOrderConfirm")
    public ApiResult repairOrderConfirm(long workOrderId) {
        try {
            ERepairWorkOrder entity = new ERepairWorkOrder();
            entity.setId(workOrderId);
            entity.setHandleStatus(30); // 设置30为确认处理状态
            repairWorkOrderService.updateWorkOrderStatus(entity);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("维修工单确认处理出错:" + e.getMessage(), e);
            return ApiResult.fail("维修工单确认处理出错:" + e.getMessage());
        }
    }

    /**
     * 保养工单设置完成处理完成
     *
     * @param workOrderId
     * @return
     */
    @ViLog(operEvent = "保养工单设置完成处理完成", operType =2)//日志记录
    @RequestMapping("/repairOrderHandleFinish")
    public ApiResult repairOrderHandleFinish(long workOrderId) {
        try {
            ERepairWorkOrder entity = new ERepairWorkOrder();
            entity.setId(workOrderId);
            entity.setHandleStatus(40); // 设置30为确认处理状态
            repairWorkOrderService.updateWorkOrderStatus(entity);
            return ApiResult.success("操作成功!");
        } catch (Exception e) {
            log.error("维修工单完成处理出错:" + e.getMessage(), e);
            return ApiResult.fail("保维修工单完成处理出错:" + e.getMessage());
        }
    }


    /**
     * 查询工单进度
     *
     * @param workOrderId
     * @return
     */
    @RequestMapping("/queryOrderRoute")
    public BaseResponse queryOrderRoute(long workOrderId, int workOrderType) {
        RespData data = new RespData();
        try {
            QEWorkOrder eWorkOrder = QEWorkOrder.eWorkOrder;
            data.putData("deviceCode", workOrderService.getItem(query ->
                    query.where(eWorkOrder.id.eq(workOrderId)).where(eWorkOrder.workOrderType.eq(workOrderType))).getCode());
            data.putData("progressList", workOrderProgressService.getAllItemsByIdAndType(workOrderId, workOrderType));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询工单进度出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "查询工单进度出错");
        }
    }

    /**
     * 创建巡检工单
     *
     * @param workOrderId
     * @return
     */
    @ViLog(operEvent = "创建巡检工单", operType =1)//日志记录
    @RequestMapping("/createInspectOrder")
    public BaseResponse createInspectOrder(Long id, String desc, long deviceId, String imageUrls, String remark, Long handlerId) {
        RespData data = new RespData();
        try {
            data.putData("inspectionInfo", inspectionWorkOrderService.createInspectOrder(id, desc, deviceId, imageUrls, remark, handlerId));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            // 消息中心
            messageCenterService.insertNewItem(1, 1, id);
            return baseResponse;
        } catch (Exception e) {
            log.error("创建巡检工单出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "创建巡检工单出错");
        }
    }

    /**
     * 创建保养工单
     *
     * @param desc
     * @param deviceId
     * @param imageUrls
     * @param remark
     * @return
     */
    @ViLog(operEvent = "创建保养工单", operType =1)//日志记录
    @RequestMapping("/createMaintainceOrder")
    public BaseResponse createMaintainceOrder(String desc, long deviceId, String imageUrls,
                                              String remark, String maintainenceItems, Long handlerId) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
        try {
            data.putData("maintanceInfo", maintenanceWorkOrderService.createMaintainceOrder(loginUser.getId(), desc, deviceId,
                    imageUrls, remark, maintainenceItems, handlerId));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            // 消息中心
            messageCenterService.insertNewItem(1, 1, loginUser.getId());
            return baseResponse;
        } catch (Exception e) {
            log.error("创建保养工单出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "创建保养工单出错");
        }
    }

    /**
     * 创建维修工单
     *
     * @param id
     * @param desc
     * @param deviceId
     * @param imageUrls
     * @param remark
     * @param repairItems
     * @return
     */
    @ViLog(operEvent = "创建维修工单", operType =1)//日志记录
    @RequestMapping("/createRepairOrder")
    public BaseResponse createRepairOrder(String desc, long deviceId, String imageUrls,
                                          String remark, String repairItems, Long handlerId) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
        try {
            data.putData("repairInfo", repairWorkOrderService.createRepairOrder(loginUser.getId(), desc, deviceId, imageUrls, remark, repairItems, handlerId));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            // 消息中心
            messageCenterService.insertNewItem(1, 1, loginUser.getId());
            return baseResponse;
        } catch (Exception e) {
            log.error("创建维修工单出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "创建维修工单出错");
        }
    }

    /**
     * 指派或完成工单
     *
     * @param imageUrls
     * @param nextOperator
     * @param remark
     * @param workOrderId
     * @param workOrderType
     * @param status
     * @return
     */
    @ViLog(operEvent = "指派或完成工单", operType =1)//日志记录
    @RequestMapping("/updateOrderStatus")
    public BaseResponse updateOrderStatus(String imageUrls, Long nextOperator, String remark,
                                          long workOrderId, int workOrderType, int status) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
        try {
            data.putData("progressInfo", workOrderProgressService.updateOrderStatus(imageUrls, nextOperator, loginUser.getId(),
                    workOrderId, workOrderType, status, remark));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            // 消息中心
            messageCenterService.insertNewItem(2, 1, loginUser.getId());
            return baseResponse;
        } catch (Exception e) {
            log.error("指派完成工单出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "指派完成工单出错");
        }
    }

    static class OperatorQueryBean extends BasePageQueryBean {
        @Like
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    /**
     * 通过查询bean进行分页查询数据
     *
     * @param query
     * @return
     */
    @RequestMapping("/getAllOperatorOrCompany")
    public BaseResponse query(int page, int pageSize, String name) {
        RespData data = new RespData();
        try {
            OperatorQueryBean query = new OperatorQueryBean();
            query.setName(name);
            query.setPage(page);
            query.setPageSize(pageSize);
            data.putData("operatorList", operatorOrCompanyManageService.getAllItemPageByQuerybean(query).getItems());
            BaseResponse baseResponse = new BaseResponse(data);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询运维人员/公司管理对象出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "查询运维人员/公司管理对象出错");
        }
    }


    /**
     * 确认工单
     *
     * @param workOrderId
     * @param workOrderType
     * @return
     */
    @ViLog(operEvent = "确认工单", operType =2)//日志记录
    @RequestMapping("/confirmWorkOrder")
    public BaseResponse confirmWorkOrder(long workOrderId, int workOrderType) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
        try {
            workOrderProgressService.confirmWorkOrder(workOrderId, workOrderType, loginUser.getId());
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("确认工单出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "确认工单出错");
        }
    }


    /**
     * 退回工单
     *
     * @param workOrderId
     * @param workOrderType
     * @return
     */
    @ViLog(operEvent = "退回工单", operType =2)//日志记录
    @RequestMapping("/returnWorkOrder")
    public BaseResponse retuanWorkOrder(long workOrderId, int workOrderType) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
        try {
            workOrderProgressService.returnWorkOrder(workOrderId, workOrderType, loginUser.getId());
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("退回工单出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "确认工单出错");
        }
    }

}
