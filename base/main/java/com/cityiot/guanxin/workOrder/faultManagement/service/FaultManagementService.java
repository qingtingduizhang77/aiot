package com.cityiot.guanxin.workOrder.faultManagement.service;


import com.cityiot.guanxin.branchLeader.entity.BranchLeader;
import com.cityiot.guanxin.branchLeader.entity.QBranchLeader;
import com.cityiot.guanxin.branchLeader.service.BranchLeaderService;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderProgress;
import com.cityiot.guanxin.workOrder.faultManagement.entity.FaultManagement;
import com.cityiot.guanxin.workOrder.faultManagement.entity.QFaultManagement;
import com.cityiot.guanxin.workOrder.faultManagement.repository.FaultManagementRepository;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.service.MaintenanceAccessoriesService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderImagesService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderProgressService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderService;
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
 * FaultManagement 数据Service
 * @author aohanhe
 *
 */
@Service
public class FaultManagementService extends BaseService<FaultManagementRepository, FaultManagement>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(FaultManagementService.class);

	@Autowired
	private EWorkOrderImagesService workOrderImagesService;
	@Autowired
	MaintenanceAccessoriesService accessoriesService;
	@Autowired
	private DeviceinformationService deviceinformationService;
	@Autowired
	private EWorkOrderProgressService workOrderProgressService;
	@Autowired
	private OperatorOrCompanyManageService operatorService;
	@Autowired
	private ERepairWorkOrderService repairWorkOrderService;
	@Autowired
	private UserService userService;
	@Autowired
	private OperatorOrCompanyManageService operatorOrCompanyManageService;
	@Autowired
	private BranchLeaderService branchLeaderService;
	@Autowired
	private EWorkOrderService eworkOrderService;

	public FaultManagement ConversionWorkOrder(FaultManagement item) {
		var entity = super.updateItem(item);

		ERepairWorkOrder repairWorkOrder = new ERepairWorkOrder();
		repairWorkOrder.setRecordId(entity.getId());
		repairWorkOrder.setRecordType(1);
		repairWorkOrder.setRemark(entity.getFaultInfo());
//		repairWorkOrder.setDesContent(desc);
		repairWorkOrder.setCode("WX"+ System.currentTimeMillis());
		repairWorkOrder.setRepairItems(entity.getDeviceName());
		// 用户
		Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
		if (userview.getType() == 1){
			User user = userService.getItemById(userview.getDid());
			repairWorkOrder.setOriginatorPhone(user.getPhone());
		}else if(userview.getType() == 2){
			OperatorOrCompanyManage model = operatorOrCompanyManageService.getItemById(userview.getDid());
			repairWorkOrder.setOriginatorPhone(model.getPhone());
		}
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
        // 工单状态
		repairWorkOrder.setHandleStatus(20);
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

	public FaultManagement handleStatus(FaultManagement item){
		FaultManagement item1 = super.getItemById(item.getId());
		if (item1 == null) {
			throw new SwallowException("故障记录不存在");
		}
		if (item1.getFaultBillNo() != null) {
			throw new SwallowException("故障记录存在维修工单");
		}
		item1.setHandlerId(item.getHandlerId());
		item1.setHandlerName(item.getHandlerName());
		item1.setHandleTime(item.getHandleTime());
		item1.setHandleStatus(1);
		return super.updateItem(item1);
	}

	public FaultManagement getFaultManagementByFaultBillNo(long faultBillNo){
		return super.getItem(query -> query.where(QFaultManagement.faultManagement.faultBillNo.eq(faultBillNo)));
	}

	public void handleStatusByWork(FaultManagement item, Date handleTime, long handleId){
		item.setHandleTime(handleTime);
		item.setHandleStatus(1);
		item.setHandlerId(handleId);
		super.updateItem(item);
	}
}
