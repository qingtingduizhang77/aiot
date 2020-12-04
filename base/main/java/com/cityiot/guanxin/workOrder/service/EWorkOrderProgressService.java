package com.cityiot.guanxin.workOrder.service;


import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.workOrder.entity.QEWorkOrder;
import com.cityiot.guanxin.workOrder.entity.QEWorkOrderProgress;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;
import com.cityiot.guanxin.workOrder.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.workOrder.entity.EWorkOrderProgress;

import java.util.Date;
import java.util.List;

/**
 * EWorkOrderProgress 数据Service
 * @author aohanhe
 *
 */
@Service
public class EWorkOrderProgressService extends BaseService<EWorkOrderProgressRepository, EWorkOrderProgress>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(EWorkOrderProgressService.class);

	@Autowired
	private EWorkOrderImagesService workOrderImagesService;
	@Autowired
	private EInspectionWorkOrderRepository inspectionWorkOrderRepository;
	@Autowired
	private EMaintenanceWorkOrderRepository maintenanceWorkOrderRepository;
	@Autowired
	private ERepairWorkOrderRepository repairWorkOrderRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private OperatorOrCompanyManageService operatorService;

	public List<EWorkOrderProgress> getAllItemsByIdAndType(long workOrderId, int workOrderType) {
		QEWorkOrderProgress eWorkOrderProgress = QEWorkOrderProgress.eWorkOrderProgress;
		List<EWorkOrderProgress> progressList = this.getAllItems(query -> query
				.where(eWorkOrderProgress.workOrderId.eq(workOrderId))
				.where(eWorkOrderProgress.workOrderType.eq(workOrderType))
				.orderBy(eWorkOrderProgress.created.asc()));
		progressList.stream()
				.filter(pro -> pro.getProgressType() == 5)
				.forEach(pro -> pro.setCreatorName(userService.getItemById(pro.getOperatorId()).getName()));
		return progressList;
	}

	@Transactional(rollbackFor = Exception.class)
	public EWorkOrderProgress updateOrderStatus(String imageUrls, Long nextOperator,long id,
												long workOrderId, int workOrderType, int status, String remark) {
		if(status == 3){
			this.updateWorkOrderStatus(workOrderId, workOrderType, status);
			nextOperator = id;
		}
		// 更新当前工单处理人
		if(status == 2){
			this.inspectionWorkOrderRepository.updateWorkOrderHandler(nextOperator, workOrderId);
		}

		// 工单进度
		return this.createProgress(imageUrls, nextOperator, id, workOrderId, workOrderType, status, remark);
	}

	@Transactional(rollbackFor = Exception.class)
	public void returnWorkOrder(long workOrderId, int workOrderType, long id) {
		this.updateWorkOrderStatus(workOrderId, workOrderType, 4);

		// 工单进度
		this.createProgress(null, id, id, workOrderId,workOrderType,6, null);
	}


	@Transactional(rollbackFor = Exception.class)
	public EWorkOrderProgress createProgress(String imageUrls, Long nextOperator,long creator,
												long workOrderId, int workOrderType, int status, String remark) {
		EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
		workOrderProgress.setWorkOrderId(workOrderId);
		workOrderProgress.setWorkOrderType(workOrderType);
		workOrderProgress.setTime(new Date());
		workOrderProgress.setRemark(remark);
		workOrderProgress.setProgressType(status);
		workOrderProgress.setOperatorId(nextOperator);
		workOrderProgress.setCreator(creator);

		if(StringUtils.hasText(imageUrls) && status <= 3){
			workOrderImagesService.insertNewItem(workOrderId,imageUrls,workOrderType,status);
		}

		workOrderProgress.setProgressContent(this.getProgressContent(creator, nextOperator, status));
		return this.insertItem(workOrderProgress);
	}

	private String getProgressContent(long creator, Long nextOperator, int status) {
		String creatorName = operatorService.getItemById(creator).getName();
		String handlerName = operatorService.getItemById(nextOperator).getName();
		String username = userService.getItemById(creator).getName();
		// 1运维创建 2指派 3 完成 4确认工单 5转工单 6退回 7后台创建
		switch (status){
			case 1:return creatorName + "发起了工单, 指派人员: "+ handlerName;
			case 2:return creatorName + "把工单指派给了"+ handlerName;
			case 3:return creatorName + "完成了工单";
			case 4:return creatorName + "已确认并去处理工单";
			case 5:return "后台管理人员:" + username + "转工单, 指派人员: "+ handlerName;
			case 6:return creatorName + "退回工单";
			case 7:return "后台管理人员:" + username + "创建了工单, 指派人员: "+ handlerName;
			default:return "";
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void confirmWorkOrder(long workOrderId, int workOrderType, long id) {
		this.updateWorkOrderStatus(workOrderId, workOrderType, 2);

		// 工单进度
		this.createProgress(null, id, id, workOrderId,workOrderType,4, null);
	}

	// 更新工单状态
	private void updateWorkOrderStatus(long workOrderId, long workOrderType, int status){
		if(workOrderType == 1){
			this.inspectionWorkOrderRepository.updateNewItem(workOrderId, status);
		}else if(workOrderType == 2){
			this.maintenanceWorkOrderRepository.updateNewItem(workOrderId, status);
		}else if(workOrderType == 3){
			this.repairWorkOrderRepository.updateNewItem(workOrderId, status);
		}
	}


	public void deleteItemByWorkOrderId(long workOrderId, int workOrderType){
		getRepsitory().deleteEntityByColumns(
				query -> query.where(QEWorkOrderProgress.eWorkOrderProgress.workOrderId.eq(workOrderId)
						.and(QEWorkOrderProgress.eWorkOrderProgress.workOrderType.eq(workOrderType))));
	}
}
