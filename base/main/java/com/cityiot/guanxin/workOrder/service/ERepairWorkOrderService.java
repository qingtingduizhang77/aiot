package com.cityiot.guanxin.workOrder.service;


import com.cityiot.guanxin.common.ipush.entity.GeTuiBuilder;
import com.cityiot.guanxin.common.ipush.service.IPushService;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.service.SystemVariableService;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.AccountinfoService;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.user.service.UserviewService;
import com.cityiot.guanxin.workOrder.entity.*;
import com.cityiot.guanxin.workOrder.faultManagement.entity.FaultManagement;
import com.cityiot.guanxin.workOrder.faultManagement.service.FaultManagementService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;
import com.cityiot.guanxin.workOrder.repository.ERepairWorkOrderRepository;
import com.cityiot.guanxin.workOrder.vo.RepairWorkOrderVo;
import com.gexin.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * ERepairWorkOrder 数据Service
 * @author aohanhe
 *
 */
@Service
public class ERepairWorkOrderService extends BaseService<ERepairWorkOrderRepository, ERepairWorkOrder>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(ERepairWorkOrderService.class);

	@Autowired
	private EWorkOrderImagesService workOrderImagesService;
	@Autowired
	private DeviceinformationService deviceinformationService;
	@Autowired
	private EWorkOrderProgressService workOrderProgressService;
	@Autowired
	private OperatorOrCompanyManageService operatorService;
	@Autowired
	private SystemVariableService systemVariableService;

	@Autowired
	private FaultManagementService faultManagementService;
	@Autowired
	private UserService userService;
	@Autowired
	private OperatorOrCompanyManageService operatorOrCompanyManageService;
	@Autowired
	private EWorkOrderService eworkOrderService;
	@Autowired
	private UserviewService userviewService;
	@Autowired
	private IPushService iPushService;
	@Autowired
	private AccountinfoService accountinfoService;

	public final static long WORK_ORDER_STATUS = 104;

	public RepairWorkOrderVo queryRepairOrderInfo(long workOrderId) {
		ERepairWorkOrder repairWorkOrder = this.getItemById(workOrderId);

		List<EWorkOrderImages> workOrderImages = workOrderImagesService.getItemsByWorkOrderId(workOrderId, 3);
		RepairWorkOrderVo repairWorkOrderVo = new RepairWorkOrderVo();
//		BeanUtils.copyProperties(repairWorkOrder, repairWorkOrderVo);
		repairWorkOrderVo.setObjData(repairWorkOrder);
		repairWorkOrderVo.setWorkOrderImages(workOrderImages);
		//维修项目
//		if(repairWorkOrder.getRepairItems() != null){
//			repairWorkOrderVo.setRepairItems(systemVariableService
//					.getAllByName("维修项目", repairWorkOrder.getRepairItems()));
//		}

		return repairWorkOrderVo;
	}

	@Transactional(rollbackFor = Exception.class)
	public ERepairWorkOrder createRepairOrder(Long id, String desc, long deviceId, String imageUrls,
											  String remark, String repairItems, Long handlerId) {
		ERepairWorkOrder repairWorkOrder = new ERepairWorkOrder();
		repairWorkOrder.setRemark(remark);
		repairWorkOrder.setDesContent(desc);
		repairWorkOrder.setCode("BY"+ System.currentTimeMillis());
		repairWorkOrder.setRepairItems(repairItems);
		repairWorkOrder.setCreator(id);
		OperatorOrCompanyManage user = operatorService.getItemById(id);
		repairWorkOrder.setOriginator(user.getName());
		repairWorkOrder.setOriginatorPhone(user.getPhone());
		repairWorkOrder.setHandlerId(handlerId);
		repairWorkOrder.setDeviceInfoId(deviceId);
		repairWorkOrder.setHandleStatus(20);

		ERepairWorkOrder insertItem = this.insertItem(repairWorkOrder);

		this.workOrderProgressService.createProgress(imageUrls, handlerId, id, insertItem.getId(),3,1, remark);

		return insertItem;
	}





	public ERepairWorkOrder insertItem(ERepairWorkOrder entity
			, List<EWorkOrderImages> images, int isProcess) throws SwallowException {
		// 生成编号
		entity.setCode("WX"+ System.currentTimeMillis());
		entity = super.insertItem(entity);
//		if (entity.getHandleStatus() == 40) {// 处理故障记录状态及完成时间
//			handleFaultManagementStatus(entity.getId(), entity.getHandleTime(),entity.getHandlerId());
//		}
		addPatrolPictures(entity.getId(),images,1);

		if (isProcess == 1){//保存并发布
			updateWorkOrderStatus(entity);
		}

		return entity;
	}

	public ERepairWorkOrder updateItem(ERepairWorkOrder entity
			, List<EWorkOrderImages> images
			,List<EWorkOrderImages> deleteImages,int type, int isProcess) throws SwallowException {

		addPatrolPictures(entity.getId(),images,type);
		deleteCarBrandImages(deleteImages);
		//
		entity=super.updateItem(entity);

		if (isProcess == 1) {//保存并发布
			updateWorkOrderStatus(entity);
		}

		return entity;


	}

	@Transactional(rollbackFor = Exception.class)
	public ERepairWorkOrder updateWorkOrderStatus(ERepairWorkOrder entity){
		ERepairWorkOrder item = this.getItemById(entity.getId());
		if (item == null) {
			throw new SwallowException("维修工单不存在");
		}
		item.setHandleStatus(entity.getHandleStatus());

		if (entity.getHandleStatus() == 20){
			// 新增工单待办
			addEWorkOrder(item);

			pushMessageToSingle(item);//发送个推提醒处理人处理工单

		}else if (entity.getHandleStatus() == 30){ // 设置处理人
			Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
			item.setHandlerId(userview.getId());

		}else if (entity.getHandleStatus() == 40){// 完成处理
			item.setHandleTime(new Date());

			//修改工单待办状态为已完成
			EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(item.getId(), 3);
			if(eworkOrder!=null)
			{
				eworkOrder.setHandleStatus(3);
				eworkOrderService.updateItem(eworkOrder);
			}

		}
		else if (entity.getHandleStatus() == 900){// 退单

			//修改工单待办状态为已退单
			EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(item.getId(), 3);
			if(eworkOrder!=null)
			{
				eworkOrder.setHandleStatus(4);
				eworkOrderService.updateItem(eworkOrder);
			}
		}

		item.setLastmodi(new Date());
		item=super.updateItem(item);


		//添加流程进度
		insertWorkOrderProgress(item);
		return item;
	}

	//发送个推提醒用户处理工单
	public void pushMessageToSingle(ERepairWorkOrder item){
		Accountinfo accountinfo = accountinfoService.getItemById(item.getHandlerId());
		GeTuiBuilder geTuiBuilder = new GeTuiBuilder();
		geTuiBuilder.setAccountId(accountinfo.getId());
		geTuiBuilder.setClientId(accountinfo.getClientId());
		HashMap<String, String> map = new HashMap<>();
		map.put("type","3");//1-巡检工单，2-保养工单，3-维修工单
		map.put("id",item.getId().toString());//id:对应的业务数据的主键ID
		map.put("messageTitle","消息的标题");
		map.put("messageContent","您有一条新的维修工单["+ item.getCode() +"]待处理");
		geTuiBuilder.setMessage(JSON.toJSONString(map));
		iPushService.pushMessageToSingle(geTuiBuilder);
	}
	
	
	 public List<ERepairWorkOrder> getERepairWorkOrderList(long handlerId) 
	 {
		 
		 return this.getRepsitory().getERepairWorkOrderList(handlerId);
	 }

	private void insertWorkOrderProgress(ERepairWorkOrder entity){
		// 进度
		EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
		workOrderProgress.setWorkOrderId(entity.getId());
		//进度工单类型
		workOrderProgress.setWorkOrderType(3);
		workOrderProgress.setTime(new Date());
		workOrderProgress.setUserId(entity.getHandlerId());
		workOrderProgress.setOperatorId(entity.getCreator());
		workOrderProgress.setCreator(entity.getCreator());
		if (entity.getHandleStatus() == 20) {// 待处理
			workOrderProgress.setProgressType(entity.getHandleStatus());//发布
			workOrderProgress.setProgressContent(entity.getModifierName()+"后台发布工单");
		}else if(entity.getHandleStatus() == 30){// 处理中
			workOrderProgress.setProgressType(entity.getHandleStatus());//确认处理
			workOrderProgress.setProgressContent("工单确认处理，当前处理人：" + entity.getHandlerName());
		}else if(entity.getHandleStatus() == 40){//已处理
			workOrderProgress.setProgressType(entity.getHandleStatus());//完成处理
			workOrderProgress.setProgressContent(entity.getModifierName()+"后台完成工单");
			handleFaultManagementStatus(entity.getId(), entity.getHandleTime(),entity.getHandlerId()); // 处理故障记录状态及完成时间
		}else {// 退单
			workOrderProgress.setProgressType(900);//退订
			workOrderProgress.setProgressContent(entity.getModifierName()+"后台退回订单，流程中止");
		}
		workOrderProgressService.insertItem(workOrderProgress);
	}

	public void addPatrolPictures(long id,List<EWorkOrderImages> patrolPictures,int type) throws SwallowException {
		if(null!=patrolPictures && patrolPictures.size()!=0) {
			for(var images:patrolPictures){
				EWorkOrderImages workOrderImages = images;
				workOrderImages.setWorkOrderId(id);
				workOrderImages.setWorkOrderType(3);
				workOrderImages.setImgType(type);
				workOrderImagesService.insertItem(workOrderImages);
			}
		}
	}

	public void deleteCarBrandImages(List<EWorkOrderImages> deletepatrolPictures) {
		if(null!=deletepatrolPictures && deletepatrolPictures.size()!=0) {
			for(var images:deletepatrolPictures){
				EWorkOrderImages workOrderImages = images;
				workOrderImagesService.deleteItemById(workOrderImages.getId());
			}
		}
	}

	public void handleFaultManagementStatus(long faultBillNo, Date handleTime, long handleId){
		FaultManagement item = faultManagementService.getFaultManagementByFaultBillNo(faultBillNo);
		if (item != null) {
			faultManagementService.handleStatusByWork(item, handleTime, handleId);
		}
	}

	//新增工单待办
	public void addEWorkOrder(ERepairWorkOrder item){
		//新增工单待办
		boolean addEWorkOrder=false;
		EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(item.getId(), 2);
		if(eworkOrder==null)
		{
			eworkOrder=new EWorkOrder();
			addEWorkOrder=true;
		}

		eworkOrder.setTitle("维修工单["+item.getCode()+"]");
		eworkOrder.setRecordId(item.getId());
		eworkOrder.setWorkOrderType(3);
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
			eworkOrderService.deleteItemByRecordId(id, 3);// 1巡检工单 2保养工单 3维修工单
			workOrderProgressService.deleteItemByWorkOrderId(id, 3);// 1保养2巡检3故障;
		}
	}
}
