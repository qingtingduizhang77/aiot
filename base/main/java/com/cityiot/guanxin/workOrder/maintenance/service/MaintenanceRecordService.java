package com.cityiot.guanxin.workOrder.maintenance.service;


import com.cityiot.guanxin.branchLeader.entity.BranchLeader;
import com.cityiot.guanxin.branchLeader.entity.QBranchLeader;
import com.cityiot.guanxin.branchLeader.service.BranchLeaderService;
import com.cityiot.guanxin.deviceInformation.service.DeviceTypeService;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.workOrder.entity.EMaintenanceWorkOrder;
import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderProgress;
import com.cityiot.guanxin.workOrder.faultManagement.entity.FaultManagement;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceRecord;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.repository.MaintenanceRecordRepository;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderImagesService;
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
 * MaintenanceRecord 数据Service
 * @author aohanhe
 *
 */
@Service
public class MaintenanceRecordService extends BaseService<MaintenanceRecordRepository, MaintenanceRecord>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(MaintenanceRecordService.class);
	@Autowired
	MaintenanceAccessoriesService accessoriesService;
	@Autowired
	private EWorkOrderImagesService workOrderImagesService;
	@Autowired
	private DeviceinformationService deviceinformationService;
	@Autowired
	private EWorkOrderProgressService workOrderProgressService;
	@Autowired
	private OperatorOrCompanyManageService operatorService;
	@Autowired
	private EMaintenanceWorkOrderService maintenanceWorkOrderService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private UserService userService;
	@Autowired
	private OperatorOrCompanyManageService operatorOrCompanyManageService;
	@Autowired
	private ERepairWorkOrderService repairWorkOrderService;
	@Autowired
	private BranchLeaderService branchLeaderService;

	public MaintenanceRecord ConversionWorkOrder(MaintenanceRecord item) {
		var entity = super.updateItem(item);
		// 转换工单
		EMaintenanceWorkOrder maintenanceWorkOrder = new EMaintenanceWorkOrder();
//		maintenanceWorkOrder.setRecordId(entity.getId());
		maintenanceWorkOrder.setRemark(entity.getRemark());
//		maintenanceWorkOrder.setDesContent(desc);
		maintenanceWorkOrder.setCode("BY"+ System.currentTimeMillis());
		maintenanceWorkOrder.setMaintainenceItems(entity.getDeviceName());
		// 用户
		Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
		if (userview.getType() == 1){
			User user = userService.getItemById(userview.getDid());
			maintenanceWorkOrder.setOriginatorPhone(user.getPhone());
		}else if(userview.getType() == 2){
			OperatorOrCompanyManage model = operatorOrCompanyManageService.getItemById(userview.getDid());
			maintenanceWorkOrder.setOriginatorPhone(model.getPhone());
		}
		maintenanceWorkOrder.setCreator(userview.getId());
		maintenanceWorkOrder.setOriginator(userview.getUsername());
		//设备型号负责人
		Long handlerId = branchLeaderService.getHandleId(entity.getDeviceId());
		if (handlerId != null){
			maintenanceWorkOrder.setHandlerId(handlerId);
		}else{
			maintenanceWorkOrder.setHandlerId(userview.getId());
		}
		// 设备
		//maintenanceWorkOrder.setDeviceInfoId(entity.getDeviceId());
		// 工单状态
		maintenanceWorkOrder.setHandleStatus(20);
		//
		EMaintenanceWorkOrder insertItem = maintenanceWorkOrderService.insertItem(maintenanceWorkOrder);
		entity.setMaintainNo(insertItem.getId());

		// 附件
		List<MaintenanceAccessories> images = accessoriesService.getAllItems(imquery ->{
			return imquery.where(QMaintenanceAccessories.maintenanceAccessories.recordID.eq(entity.getId()));
		});
		for(var image:images){
			EWorkOrderImages workOrderImages =new EWorkOrderImages();
			workOrderImages.setWorkOrderId(insertItem.getId());
			workOrderImages.setImageUrl(image.getSaveRoute());
			//图片工单类型
			workOrderImages.setWorkOrderType(2);
			workOrderImages.setImgType(1);
		}

		// 进度
		EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
		workOrderProgress.setWorkOrderId(insertItem.getId());
		//进度工单类型
		workOrderProgress.setWorkOrderType(1);
		workOrderProgress.setProgressType(20);
		workOrderProgress.setTime(new Date());
		workOrderProgress.setOperatorId(userview.getId());
		workOrderProgress.setCreator(userview.getId());
		workOrderProgress.setProgressContent(userview.getUsername()+"后台转工单");
		workOrderProgressService.insertItem(workOrderProgress);

		// 新增工单待办
		maintenanceWorkOrderService.addEWorkOrder(insertItem);

        maintenanceWorkOrderService.pushMessageToSingle(insertItem);
		return entity;
	}




	public MaintenanceRecord insertItem(MaintenanceRecord maintenanceRecord, List<MaintenanceAccessories> maintenancePictures) throws SwallowException {
		maintenanceRecord = super.insertItem(maintenanceRecord);

		//异常处理并需创建维修工单
		if(maintenanceRecord.getMaintainStatus() == 2 && maintenanceRecord.getIsCreatedFault() == 0){
			ConversionERepairWorkOrder(maintenanceRecord);
		}

		addPatrolPictures(maintenanceRecord.getId(),maintenancePictures);
		return maintenanceRecord;
	}

	public MaintenanceRecord updateItem(MaintenanceRecord maintenanceRecord
			, List<MaintenanceAccessories> maintenancePictures
			,List<MaintenanceAccessories> deleteMaintenancePictures) throws SwallowException {

		//异常处理并需创建维修工单
		if(maintenanceRecord.getMaintainStatus() == 2 && maintenanceRecord.getIsCreatedFault() == 0){
			ConversionERepairWorkOrder(maintenanceRecord);
		}


		addPatrolPictures(maintenanceRecord.getId(),maintenancePictures);
		deleteCarBrandImages(deleteMaintenancePictures);
		return super.updateItem(maintenanceRecord);


	}




	public void addPatrolPictures(long accessoryId, List<MaintenanceAccessories> patrolPictures) throws SwallowException {
		if(null!=patrolPictures && patrolPictures.size()!=0) {
			for(var images:patrolPictures){
				MaintenanceAccessories accessories = images;
				accessories.setRecordID(accessoryId);
				accessories.setFileType(1);
				accessories.setType(1);
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
	 * 保养记录状态异常处理并需创建维修工单时
	 * @return
	 */
	public MaintenanceRecord ConversionERepairWorkOrder(MaintenanceRecord entity) {

		ERepairWorkOrder repairWorkOrder = new ERepairWorkOrder();
		repairWorkOrder.setRecordId(entity.getId());
		repairWorkOrder.setRecordType(2);
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
			workOrderImages.setWorkOrderType(3);
			workOrderImages.setImgType(1);
		}

		// 进度
		EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
		workOrderProgress.setWorkOrderId(insertItem.getId());
		//进度工单类型
		workOrderProgress.setWorkOrderType(3);
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

