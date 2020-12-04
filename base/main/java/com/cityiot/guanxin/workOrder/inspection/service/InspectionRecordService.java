package com.cityiot.guanxin.workOrder.inspection.service;


import com.cityiot.guanxin.branchLeader.entity.BranchLeader;
import com.cityiot.guanxin.branchLeader.entity.QBranchLeader;
import com.cityiot.guanxin.branchLeader.service.BranchLeaderService;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.workOrder.entity.EInspectionWorkOrder;
import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderProgress;
import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;
import com.cityiot.guanxin.workOrder.inspection.repository.InspectionRecordRepository;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.service.MaintenanceAccessoriesService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;
import com.cityiot.guanxin.workOrder.service.EInspectionWorkOrderService;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderProgressService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.Date;
import java.util.List;

/**
 * InspectionRecord 数据Service
 * @author aohanhe
 *
 */
@Service
public class InspectionRecordService extends BaseService<InspectionRecordRepository, InspectionRecord>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(InspectionRecordService.class);
	@Autowired
	MaintenanceAccessoriesService accessoriesService;
	@Autowired
	private OperatorOrCompanyManageService operatorService;
	@Autowired
	private DeviceinformationService deviceinformationService;
	@Autowired
	private EWorkOrderProgressService workOrderProgressService;
	@Autowired
	private EInspectionWorkOrderService inspectionWorkOrderService;
	@Autowired
	private UserService userService;
	@Autowired
	private OperatorOrCompanyManageService operatorOrCompanyManageService;
	@Autowired
	private ERepairWorkOrderService repairWorkOrderService;
	@Autowired
	private BranchLeaderService branchLeaderService;

	public InspectionRecord ConversionWorkOrder(InspectionRecord item){
		var entity = super.updateItem(item);

		EInspectionWorkOrder inspectionWorkOrder = new EInspectionWorkOrder();
		// 转换工单
		inspectionWorkOrder.setCode("XJ"+ System.currentTimeMillis());
		inspectionWorkOrder.setRecordId(entity.getId());
//		inspectionWorkOrder.setDesContent(entity.get);
		// 用户
		Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
		if (userview.getType() == 1){
			User user = userService.getItemById(userview.getDid());
			inspectionWorkOrder.setOriginatorPhone(user.getPhone());
		}else if(userview.getType() == 2){
			OperatorOrCompanyManage model = operatorOrCompanyManageService.getItemById(userview.getDid());
			inspectionWorkOrder.setOriginatorPhone(model.getPhone());
		}
		inspectionWorkOrder.setCreator(userview.getId());
		inspectionWorkOrder.setOriginator(userview.getUsername());
		//设备型号负责人
		Long handlerId = branchLeaderService.getHandleId(entity.getDeviceId());
		if (handlerId != null){
			inspectionWorkOrder.setHandlerId(handlerId);
		}else{
			inspectionWorkOrder.setHandlerId(userview.getId());
		}
		// 设备
		//inspectionWorkOrder.setDeviceInfoId(entity.getDeviceId());
		// 工单状态
		inspectionWorkOrder.setHandleStatus(20);
		//
		EInspectionWorkOrder insertItem = inspectionWorkOrderService.insertItem(inspectionWorkOrder);
		entity.setPatrollerNo(insertItem.getId());

		// 附件
		List<MaintenanceAccessories> images = accessoriesService.getAllItems(imquery ->{
			return imquery.where(QMaintenanceAccessories.maintenanceAccessories.recordID.eq(entity.getId()));
		});
		for(var image:images){
			EWorkOrderImages workOrderImages =new EWorkOrderImages();
			workOrderImages.setWorkOrderId(insertItem.getId());
			workOrderImages.setImageUrl(image.getSaveRoute());
			//图片工单类型
			workOrderImages.setWorkOrderType(1);
			workOrderImages.setImgType(1);
		}

		// 进度
		EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
		workOrderProgress.setWorkOrderId(insertItem.getId());
		//进度工单类型
		workOrderProgress.setWorkOrderType(2);
		workOrderProgress.setProgressType(20);
		workOrderProgress.setTime(new Date());
		workOrderProgress.setOperatorId(userview.getId());
		workOrderProgress.setCreator(userview.getId());
		workOrderProgress.setProgressContent(userview.getUsername()+"后台转工单");
		workOrderProgressService.insertItem(workOrderProgress);

		// 新增工单待办
		inspectionWorkOrderService.addEWorkOrder(insertItem);
		// 消息推送
        inspectionWorkOrderService.pushMessageToSingle(insertItem);
		return entity;
	}

	public InspectionRecord insertItem(InspectionRecord inspectionRecord, List<MaintenanceAccessories> patrolPictures) throws SwallowException {
		inspectionRecord = super.insertItem(inspectionRecord);

		//异常处理并需创建维修工单
		if(inspectionRecord.getPatrolStatus() == 2 && inspectionRecord.getIsCreatedFault() == 0){
			ConversionERepairWorkOrder(inspectionRecord);
		}

		addPatrolPictures(inspectionRecord.getId(),patrolPictures);
		return inspectionRecord;
	}

	public InspectionRecord updateItem(InspectionRecord inspectionRecord
			, List<MaintenanceAccessories> patrolPictures
			,List<MaintenanceAccessories> deletepatrolPictures) throws SwallowException {

		//异常处理并需创建维修工单
		if(inspectionRecord.getPatrolStatus() == 2 && inspectionRecord.getIsCreatedFault() == 0){
			ConversionERepairWorkOrder(inspectionRecord);
		}

		addPatrolPictures(inspectionRecord.getId(),patrolPictures);
		deleteCarBrandImages(deletepatrolPictures);
		return super.updateItem(inspectionRecord);


	}


	public void addPatrolPictures(long accessoryId,List<MaintenanceAccessories> patrolPictures) throws SwallowException {
		if(null!=patrolPictures && patrolPictures.size()!=0) {
			for(var images:patrolPictures){
				MaintenanceAccessories accessories = images;
				accessories.setRecordID(accessoryId);
				accessories.setFileType(1);
				accessories.setType(2);
				accessoriesService.insertItem(accessories);
			}
		}
	}

	public void deleteCarBrandImages(List<MaintenanceAccessories> deletepatrolPictures) {
		if(null!=deletepatrolPictures && deletepatrolPictures.size()!=0) {
			for(var images:deletepatrolPictures){
				MaintenanceAccessories accessories = images;
				accessoriesService.deleteItemById(accessories.getId());
			}
		}
	}

	/**
	 * 巡检记录状态异常处理并需创建维修工单时
	 * @return
	 */
	public InspectionRecord ConversionERepairWorkOrder(InspectionRecord entity) {

		ERepairWorkOrder repairWorkOrder = new ERepairWorkOrder();
		repairWorkOrder.setRecordId(entity.getId());
		repairWorkOrder.setRecordType(3);
		repairWorkOrder.setRemark(entity.getRemark());
		repairWorkOrder.setRepairItems(entity.getDeviceName());
		repairWorkOrder.setCode("WX"+ System.currentTimeMillis());
		// 用户
		Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
		if (userview.getType() == 1){
			User user = userService.getItemById(userview.getDid());
			repairWorkOrder.setOriginatorPhone(user.getPhone());
		}else if(userview.getType() == 2){
			OperatorOrCompanyManage model = operatorOrCompanyManageService.getItemById(userview.getDid());
			repairWorkOrder.setOriginatorPhone(model.getPhone());
		}
		// 工单状态
		repairWorkOrder.setHandleStatus(20);
		repairWorkOrder.setCreator(userview.getId());
		repairWorkOrder.setOriginator(userview.getUsername());
		//设备型号负责人
		Long handlerId = branchLeaderService.getHandleId(entity.getDeviceId());
		if (handlerId != null){
			repairWorkOrder.setHandlerId(handlerId);
		}else{
			repairWorkOrder.setHandlerId(userview.getId());
		}
		// 设备
		repairWorkOrder.setDeviceInfoId(entity.getDeviceId());
		//
		ERepairWorkOrder insertItem = repairWorkOrderService.insertItem(repairWorkOrder);
		entity.setFaultBillNo(insertItem.getId());

		// 附件
		List<MaintenanceAccessories> images = accessoriesService.getAllItems(imquery ->{
			return imquery.where(QMaintenanceAccessories.maintenanceAccessories.recordID.eq(entity.getId()));
		});
		for(var image:images){
			EWorkOrderImages workOrderImages =new EWorkOrderImages();
			workOrderImages.setWorkOrderId(insertItem.getId());
			workOrderImages.setImageUrl(image.getSaveRoute());
			//图片工单类型
			workOrderImages.setWorkOrderType(1);
			workOrderImages.setImgType(1);
		}

		// 进度
		EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
		workOrderProgress.setWorkOrderId(insertItem.getId());
		//进度工单类型
		workOrderProgress.setWorkOrderType(2);
		workOrderProgress.setProgressType(20);
		workOrderProgress.setTime(new Date());
		workOrderProgress.setOperatorId(userview.getId());
		workOrderProgress.setCreator(userview.getId());
		workOrderProgress.setProgressContent(userview.getUsername()+"后台转工单");
		workOrderProgressService.insertItem(workOrderProgress);

		// 新增工单待办
		repairWorkOrderService.addEWorkOrder(insertItem);
		// 消息推送
		repairWorkOrderService.pushMessageToSingle(insertItem);

		return entity;

	}
}
