package com.cityiot.guanxin.workOrder.service;


import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.cityiot.guanxin.branchLeader.entity.QBranchLeader;
import com.cityiot.guanxin.branchLeader.service.BranchLeaderService;
import com.cityiot.guanxin.branchLeader.service.BranchToAreaService;
import com.cityiot.guanxin.branchLeader.service.BranchToDeviceGroupService;
import com.cityiot.guanxin.branchLeader.service.BranchToDeviceModelService;
import com.cityiot.guanxin.common.ipush.entity.GeTuiBuilder;
import com.cityiot.guanxin.common.ipush.service.IPushService;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.service.AccountinfoService;
import com.cityiot.guanxin.workOrder.entity.*;
import com.gexin.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.user.service.UserviewService;
import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;
import com.cityiot.guanxin.workOrder.inspection.entity.QInspectionRecord;
import com.cityiot.guanxin.workOrder.inspection.service.InspectionRecordService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;
import com.cityiot.guanxin.workOrder.repository.EInspectionWorkOrderRepository;
import com.cityiot.guanxin.workOrder.vo.InspectionWorkOrderVo;

import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

/**
 * EInspectionWorkOrder 数据Service
 * @author aohanhe
 *
 */
@Service
public class EInspectionWorkOrderService extends BaseService<EInspectionWorkOrderRepository, EInspectionWorkOrder>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(EInspectionWorkOrderService.class);

	@Autowired
	private EWorkOrderImagesService workOrderImagesService;
	@Autowired
	private DeviceinformationService deviceinformationService;
	@Autowired
	private EWorkOrderProgressService workOrderProgressService;
	@Autowired
	private OperatorOrCompanyManageService operatorService;
	@Autowired
	private InspectionRecordService inspectionRecordService;
	@Autowired
	private UserService userService;
	@Autowired
	private OperatorOrCompanyManageService operatorOrCompanyManageService;
	@Autowired
	private EWorkOrderService eworkOrderService;
	@Autowired
	private UserviewService userviewService;
	@Autowired
	private BranchLeaderService branchLeaderService;
	@Autowired
	private BranchToDeviceModelService branchToDeviceModelService;
	@Autowired
	private BranchToAreaService branchToAreaService;
	@Autowired
	private IPushService iPushService;
	@Autowired
	private AccountinfoService accountinfoService;
	@Autowired
	private BranchToDeviceGroupService branchToDeviceGroupService;

	// 保养工单状态
	private final int STATUS_20 = 20;

	private final int STATUS_30 = 30;

	private final int STATUS_40 = 40;
	
	private final int STATUS_900 = 900;

	public final static long WORK_ORDER_STATUS = 102;

	public final static long WORK_ORDER_PROCESS_STATUS = 103;
	
	
	  public List<EInspectionWorkOrder> getEInspectionWorkOrderList(long handlerId) {
		  return this.getRepsitory().getEInspectionWorkOrderList(handlerId);
	  }
	

	public InspectionWorkOrderVo queryInspectOrderInfo(long workOrderId) {
		EInspectionWorkOrder inspectionWorkOrder = this.getItemById(workOrderId);
		List<EWorkOrderImages> workOrderImages = workOrderImagesService.getItemsByWorkOrderId(workOrderId, 1);
		InspectionWorkOrderVo inspectionWorkOrderVo = new InspectionWorkOrderVo();
//		BeanUtils.copyProperties(inspectionWorkOrder, inspectionWorkOrderVo);
		inspectionWorkOrderVo.setObjData(inspectionWorkOrder);
		inspectionWorkOrderVo.setWorkOrderImages(workOrderImages);
		return inspectionWorkOrderVo;
	}

	@Transactional(rollbackFor = Exception.class)
	public EInspectionWorkOrder createInspectOrder(long id, String desc, long deviceId, String imageUrls, String remark, Long handlerId) {
		EInspectionWorkOrder inspectionWorkOrder = new EInspectionWorkOrder();
		inspectionWorkOrder.setRemark(remark);
		inspectionWorkOrder.setDesContent(desc);
		inspectionWorkOrder.setCode("XJ"+ System.currentTimeMillis());
		OperatorOrCompanyManage user = operatorService.getItemById(id);
		inspectionWorkOrder.setCreator(id);
		inspectionWorkOrder.setOriginator(user.getName());
		inspectionWorkOrder.setOriginatorPhone(user.getPhone());
		inspectionWorkOrder.setHandlerId(handlerId);
//		inspectionWorkOrder.setDeviceInfoId(deviceId);
		inspectionWorkOrder.setHandleStatus(1);

		EInspectionWorkOrder insertItem = this.insertItem(inspectionWorkOrder);

		// 工单进度
		this.workOrderProgressService.createProgress(imageUrls, handlerId, id, insertItem.getId(),1,1, remark);

		return insertItem;
	}

	public EInspectionWorkOrder insertItem(EInspectionWorkOrder entity
			, List<EWorkOrderImages> images,int type) throws SwallowException {
		// 生成编号
		entity.setCode("XJ"+ System.currentTimeMillis());
		if (type == 1) {
			entity.setHandleStatus(STATUS_20);
		}
		entity = super.insertItem(entity);
		addPatrolPictures(entity.getId(),images);

		if (type == 1) {//保存并发布
			updateWorkOrderStatus(entity);
		}
		return entity;
	}
	public EInspectionWorkOrder updateItem(EInspectionWorkOrder entity
			, List<EWorkOrderImages> images
			,List<EWorkOrderImages> deleteImages
			,int type) throws SwallowException {

		addPatrolPictures(entity.getId(),images);
		deleteCarBrandImages(deleteImages);
		if (type == 1) {
			entity.setHandleStatus(STATUS_20);
		}
		//
		entity = super.updateItem(entity);

		if (type == 1) {//保存并发布
			updateWorkOrderStatus(entity);
		}
		return entity;


	}



	public void addPatrolPictures(long id,List<EWorkOrderImages> patrolPictures) throws SwallowException {
		if(null!=patrolPictures && patrolPictures.size()!=0) {
			for(var images:patrolPictures){
				EWorkOrderImages workOrderImages = images;
				workOrderImages.setWorkOrderId(id);
				workOrderImages.setWorkOrderType(1);
				workOrderImages.setImgType(1);
				workOrderImagesService.insertItem(workOrderImages);
			}
		}
	}

	public void deleteCarBrandImages(List<EWorkOrderImages> deletepatrolPictures) {
		if(null!=deletepatrolPictures && deletepatrolPictures.size()!=0) {
			for(var images:deletepatrolPictures){
				EWorkOrderImages workOrderImages = images;
				workOrderImages.setImgType(3);
				workOrderImagesService.deleteItemById(workOrderImages.getId());
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public EInspectionWorkOrder updateWorkOrderStatus(EInspectionWorkOrder entity){
		EInspectionWorkOrder item = this.getItemById(entity.getId());
		if (item == null) {
			throw new SwallowException("巡检工单不存在");
		}
		item.setHandleStatus(entity.getHandleStatus());
		if (entity.getHandleStatus() == STATUS_30){ // 设置处理人
			Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
			item.setHandlerId(userview.getId());
		}else if (entity.getHandleStatus() == STATUS_40){// 完成处理
			// 校验是否还有设备未巡检的巡检记录
			var qm = QInspectionRecord.inspectionRecord;
			List<InspectionRecord> list = inspectionRecordService.getAllItems(query -> query.where(qm.patrollerNo.eq(entity.getId())
					.and(qm.patrolStatus.eq(0))));
			if (list != null && list.size() > 0) {
				throw new SwallowException("完成所有设备巡检后才可点击按钮");
			}
			item.setHandleTime(new Date());
			
			//修改工单待办状态为已完成
			EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(item.getId(),1);
			if(eworkOrder!=null)
			{
				eworkOrder.setHandleStatus(3);
				eworkOrderService.updateItem(eworkOrder);
			}
		}
         else if (entity.getHandleStatus() == STATUS_900){// 退单
			
			//修改工单待办状态为已退单
			EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(item.getId(), 1);
			if(eworkOrder!=null)
			{
				eworkOrder.setHandleStatus(4);
				eworkOrderService.updateItem(eworkOrder);
			}
		}
		
		
		
		item.setLastmodi(new Date());
		item=super.updateItem(item);

		if (entity.getHandleStatus() == STATUS_20){// 巡检工单发布时  ==》 新增巡检记录
			if (!StringUtils.isEmpty(item.getDeviceIds())) {
				String[] deviceIds = item.getDeviceIds().split("#&#");
				for (var deviceId : deviceIds){
					InspectionRecord inspectionRecord = new InspectionRecord();
					inspectionRecord.setCreator(item.getCreator());
					inspectionRecord.setPatrolStatus(0);
					inspectionRecord.setPatrollerNo(item.getId());
					inspectionRecord.setDeviceId(Long.valueOf(deviceId));
					inspectionRecordService.insertItem(inspectionRecord);
				}
			}


			//发送个推提醒处理人处理工单
			pushMessageToSingle(item);

			//新增工单待办
			addEWorkOrder(item);
		}

		//添加流程进度
		insertWorkOrderProgress(item);
		return item;
	}

	//发送个推提醒用户处理工单
	public void pushMessageToSingle(EInspectionWorkOrder item){
		Accountinfo accountinfo = accountinfoService.getItemById(item.getHandlerId());
		GeTuiBuilder geTuiBuilder = new GeTuiBuilder();
		geTuiBuilder.setAccountId(accountinfo.getId());
		geTuiBuilder.setClientId(accountinfo.getClientId());
		HashMap<String, String> map = new HashMap<>();
		map.put("type","1");//1-巡检工单，2-保养工单，3-维修工单
		map.put("id",item.getId().toString());//id:对应的业务数据的主键ID
		map.put("messageTitle","消息的标题");
		map.put("messageContent","您有一条新的巡检工单["+ item.getCode() +"]待处理");
		geTuiBuilder.setMessage(JSON.toJSONString(map));
		iPushService.pushMessageToSingle(geTuiBuilder);
	}

	private void insertWorkOrderProgress(EInspectionWorkOrder entity){
		// 进度
		EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
		workOrderProgress.setWorkOrderId(entity.getId());
		//进度工单类型
		workOrderProgress.setWorkOrderType(2);
		workOrderProgress.setTime(new Date());
		workOrderProgress.setUserId(entity.getHandlerId());
		workOrderProgress.setOperatorId(entity.getCreator());
		workOrderProgress.setCreator(entity.getCreator());
		if (entity.getHandleStatus() == STATUS_20) {// 待处理
			workOrderProgress.setProgressType(STATUS_20);//发布
			workOrderProgress.setProgressContent(entity.getModifierName()+"后台发布工单");
		}else if(entity.getHandleStatus() == STATUS_30){// 处理中
			workOrderProgress.setProgressType(STATUS_30);//确认处理
			workOrderProgress.setProgressContent("工单确认处理，当前处理人：" + entity.getHandlerName());
		}else if(entity.getHandleStatus() == STATUS_40){//已处理
			workOrderProgress.setProgressType(STATUS_40);//完成处理
			workOrderProgress.setProgressContent(entity.getModifierName()+"后台完成工单");
		}else {// 退单
			workOrderProgress.setProgressType(900);//退订
			workOrderProgress.setProgressContent(entity.getModifierName()+"后台退回订单，流程中止");
		}
		workOrderProgressService.insertItem(workOrderProgress);
	}

	public void batchHandleRecord(long []ids, long id){
		Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
		var phone = "";
		if (userview.getType() == 1){
			User user = userService.getItemById(userview.getDid());
			phone = user.getPhone();
		}else if(userview.getType() == 2){
			OperatorOrCompanyManage model = operatorOrCompanyManageService.getItemById(userview.getDid());
			phone = model.getPhone();
		}
		for(var recordId:ids){
			var qi = QInspectionRecord.inspectionRecord;
			var bean = inspectionRecordService.getItem(query -> query.where(qi.patrollerNo.eq(id)
					.and(qi.id.eq(recordId))));
			if (bean != null) {
				bean.setPatrolStatus(1);
				bean.setPatrolTime(new Date());
				bean.setPatroller(userview.getId());
				bean.setPatrollerPhone(phone);
				inspectionRecordService.updateItem(bean);
			}
		}
	}

	/**
	 * 定时新增巡检工单
	 */
	public void addWorkOrder(){
		// 区域负责人列表
		var branchLeaderList = branchLeaderService.getRepsitory().getAllItems(query ->
			query.from(QBranchLeader.branchLeader)
		);
		for (var branch: branchLeaderList){
			// 负责人管理的所有型号ID
			Long[] deviceModelIds = branchToDeviceModelService.getAllDeviceModelIdsByBranchId(branch.getId());
			// 负责人管理的区域
			Long[] areaCodes = branchToAreaService.getAllAreaIdsIncludeParentByBranchId(branch.getId());
			// 负责人管理的设备分组及下层分组下的所有设备ID
			Long[] deviceIdArr = branchToDeviceGroupService.getDeviceIdsByBranchId(branch.getId());

			//设备列表
			var deviceinformationList = deviceinformationService.getAllItems(query ->{
				if (deviceModelIds != null && deviceModelIds.length > 0) {
					query.where(QDeviceinformation.deviceinformation.deviceModelId.in(deviceModelIds));
				}
				if (areaCodes != null && areaCodes.length > 0) {
					query.where(QDeviceinformation.deviceinformation.areaCode.in(areaCodes));
				}
				if (deviceIdArr != null && deviceIdArr.length > 0) {
					query.where(QDeviceinformation.deviceinformation.id.in(deviceModelIds));
				}
				return query;
			});

			// 设备ID
			List<String> deviceIds = Arrays.stream(
					deviceinformationList.stream().map(Deviceinformation::getId).distinct().toArray())
					.map(s -> String.valueOf(s))
					.collect(Collectors.toList());

			if (deviceIds.size() > 0) {
				String deviceIdStr = String.join("#&#", deviceIds);
				//设备名称
				String deviceNames =
						deviceinformationList.stream().map(Deviceinformation::getDeviceName)
								.distinct()
								.collect(Collectors.joining("#&#"));
				//新增巡检工单
				EInspectionWorkOrder item = new EInspectionWorkOrder();
				item.setDeviceIds(deviceIdStr);
				item.setHandlerId(branch.getOperatorOrCompanyManageId());
				item.setHandleStatus(STATUS_30);
				item.setCode("XJ"+ System.currentTimeMillis());
				item.setDeviceName(deviceNames);
				item = super.insertItem(item);

				// 新增巡检记录
				for (var deviceId : deviceIds){
					InspectionRecord inspectionRecord = new InspectionRecord();
					inspectionRecord.setCreator(item.getCreator());
					inspectionRecord.setPatrolStatus(0);
					inspectionRecord.setPatrollerNo(item.getId());
					inspectionRecord.setDeviceId(Long.valueOf(deviceId));
					inspectionRecord.setModifier(item.getCreator());
					inspectionRecordService.insertItem(inspectionRecord);
				}

				//新增工单待办
				addEWorkOrder(item);
                //添加流程进度
                insertWorkOrderProgress(item);
			}
		}
	}

	//新增工单待办
	public void addEWorkOrder(EInspectionWorkOrder item){
		boolean addEWorkOrder=false;
		EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(item.getId(), 1);
		if(eworkOrder==null)
		{
			eworkOrder=new EWorkOrder();
			addEWorkOrder=true;
		}

		eworkOrder.setTitle("巡检工单["+item.getCode()+"]");
		eworkOrder.setRecordId(item.getId());
		eworkOrder.setWorkOrderType(1);
		eworkOrder.setHandleStatus(2);
		eworkOrder.setHandlerId(item.getHandlerId());
		Userview userview = userviewService.getItemById(item.getHandlerId());
		if(userview!=null)
		{
			eworkOrder.setHandlerName(userview.getUsername());
		}

		if(addEWorkOrder)
		{
			eworkOrderService.insertItem(eworkOrder);
		}else
		{
			eworkOrderService.updateItem(eworkOrder);
		}
	}

	@Transactional
	public void deleteItemByIds(long []ids){
		for (var id : ids) {
			super.deleteItemById(id);
			eworkOrderService.deleteItemByRecordId(id, 1);// 1巡检工单 2保养工单 3维修工单
			workOrderProgressService.deleteItemByWorkOrderId(id, 2);// 1保养2巡检3故障;
		}
	}
}
