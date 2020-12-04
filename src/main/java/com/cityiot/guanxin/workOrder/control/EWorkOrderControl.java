package com.cityiot.guanxin.workOrder.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserviewService;
import com.cityiot.guanxin.workOrder.entity.EInspectionWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EMaintenanceWorkOrder;
import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderProgress;
import com.cityiot.guanxin.workOrder.service.EInspectionWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderProgressService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import swallow.framework.web.ApiResult;

/**
 * 工单
 * @author ouwt
 *
 */
@RestController
@Api(tags = "工单")
@RequestMapping("/api/eworkOrder")
public class EWorkOrderControl {
	private static final Logger log = LoggerFactory.getLogger(EWorkOrderControl.class);
	
	@Autowired
	private ERepairWorkOrderService erpairWorkOrderService;//维修工单
	
	@Autowired
	private EInspectionWorkOrderService einspectionWorkOrderService;//巡检工单
	
	@Autowired
	private EMaintenanceWorkOrderService emaintenanceWorkOrderService;//保养工单
	
	@Autowired
	private EWorkOrderService eworkOrderService;
	
	@Autowired
	private UserviewService userviewService;
	 
	@Autowired
	private EWorkOrderProgressService workOrderProgressService;
	

	
	
	
	
	/**
	 * 单条工单转办
	 * @param workOrderType  工单类型(1巡检工单 2保养工单 3维修工单)
	 * @param recordId  工单id_
	 * @param newhandlerId 新处理人ID
	 * @return
	 */
	 @ApiOperation(value = "单条工单转办")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "workOrderType", value = "工单类型(1巡检工单 2保养工单 3维修工单)", required = true, dataType = "long"),
	  		@ApiImplicitParam(name = "recordId", value = "工单id_", required = true, dataType = "int"),
	  		@ApiImplicitParam(name = "newhandlerId", value = "新处理人ID", required = true, dataType = "Long")
	  		})
	@ViLog(operEvent = "工单转办", operType =2)//日志记录
	@PostMapping("oneWorkTurnDo")
	@RequiresPermissions(value={"004008001","SUPERADMIN"}, logical= Logical.OR)
	@SuppressWarnings("unchecked")
	@Transactional
	public ApiResult<ERepairWorkOrder> oneWorkTurnDo(@RequestParam(value = "workOrderType") int workOrderType,
			@RequestParam(value = "recordId") long recordId,
			@RequestParam(value = "newhandlerId") long newhandlerId){
		try {
			
			Userview user = userviewService.getItemById(newhandlerId);
			if(user==null)
			{
				return ApiResult.fail("处理人不存在！");
			}
			
			EInspectionWorkOrder einspectionWorkOrder=null;
			EMaintenanceWorkOrder emaintenanceWorkOrder=null;
			ERepairWorkOrder erepairWorkOrder=null;
			EWorkOrder eworkOrder=null;
			EWorkOrderProgress workOrderProgress =null;
			if(workOrderType==1)//巡检工单
			{

				einspectionWorkOrder=einspectionWorkOrderService.getItemById(recordId);
				
				if(einspectionWorkOrder!=null)
				{
					
					
					if (einspectionWorkOrder.getHandleStatus() == 40)
					{
						return ApiResult.fail("工单已完成不能转办！");
					}
					einspectionWorkOrder.setHandlerId(newhandlerId);
					
					//待办
					 eworkOrder=eworkOrderService.getEWorkOrder(einspectionWorkOrder.getId(),1);
					if(eworkOrder!=null)
					{
						eworkOrder.setHandlerId(newhandlerId);
						eworkOrder.setHandlerName(user.getUsername());
						
					}
					
					// 进度
					 workOrderProgress = new EWorkOrderProgress();
					workOrderProgress.setWorkOrderId(einspectionWorkOrder.getId());
					workOrderProgress.setWorkOrderType(2);
					workOrderProgress.setTime(new Date());
					workOrderProgress.setUserId(einspectionWorkOrder.getHandlerId());
					workOrderProgress.setOperatorId(einspectionWorkOrder.getCreator());
					workOrderProgress.setCreator(einspectionWorkOrder.getCreator());
					workOrderProgress.setProgressType(einspectionWorkOrder.getHandleStatus());
				
					
				}
				else
				{
					return ApiResult.fail("工单不存在！");
				}
				
			}
			else if(workOrderType==2)//保养工单
			{
				emaintenanceWorkOrder=emaintenanceWorkOrderService.getItemById(recordId);
				
				if(emaintenanceWorkOrder!=null)
				{
					
					
					  if (emaintenanceWorkOrder.getHandleStatus()== 40)
					  {
						  return ApiResult.fail("工单已完成不能转办！");
					  }
					  
					  emaintenanceWorkOrder.setHandlerId(newhandlerId);
					
					 //待办
					   eworkOrder=eworkOrderService.getEWorkOrder(emaintenanceWorkOrder.getId(),2);
					 if(eworkOrder!=null)
					 {
						eworkOrder.setHandlerId(newhandlerId);
						eworkOrder.setHandlerName(user.getUsername());
						
					 }
					
					 // 进度
					  workOrderProgress = new EWorkOrderProgress();
					 workOrderProgress.setWorkOrderId(emaintenanceWorkOrder.getId());
					 workOrderProgress.setWorkOrderType(2);
					 workOrderProgress.setTime(new Date());
					 workOrderProgress.setUserId(emaintenanceWorkOrder.getHandlerId());
					 workOrderProgress.setOperatorId(emaintenanceWorkOrder.getCreator());
					 workOrderProgress.setCreator(emaintenanceWorkOrder.getCreator());
					 workOrderProgress.setProgressType(emaintenanceWorkOrder.getHandleStatus());
					 
					
				}
				else
				{
					return ApiResult.fail("工单不存在！");
				}
				
			}
			else if(workOrderType==3)//维修工单
			{
				erepairWorkOrder=erpairWorkOrderService.getItemById(recordId);
				
				
				
				 if(erepairWorkOrder!=null )
				 {
					 
				 
							
					if (erepairWorkOrder.getHandleStatus() == 40)
					{
						return ApiResult.fail("工单已完成不能转办！");
					}
					erepairWorkOrder.setHandlerId(newhandlerId);
					
					//待办
					 eworkOrder=eworkOrderService.getEWorkOrder(erepairWorkOrder.getId(),3);
					if(eworkOrder!=null)
					{
						eworkOrder.setHandlerId(newhandlerId);
						eworkOrder.setHandlerName(user.getUsername());
						
					}
					
					// 进度
					 workOrderProgress = new EWorkOrderProgress();
					workOrderProgress.setWorkOrderId(erepairWorkOrder.getId());
					workOrderProgress.setWorkOrderType(2);
					workOrderProgress.setTime(new Date());
					workOrderProgress.setUserId(erepairWorkOrder.getHandlerId());
					workOrderProgress.setOperatorId(erepairWorkOrder.getCreator());
					workOrderProgress.setCreator(erepairWorkOrder.getCreator());
					workOrderProgress.setProgressType(erepairWorkOrder.getHandleStatus());
					
				  
				 }
				 else
					{
						return ApiResult.fail("工单不存在！");
					}
				 
			 }
			 else
			 {
				return ApiResult.fail("工单类型错误！");
			 }
			
			
			if(einspectionWorkOrder!=null )
			{
				
				  einspectionWorkOrderService.updateItem(einspectionWorkOrder);
				  einspectionWorkOrderService.pushMessageToSingle(einspectionWorkOrder);
			  
			}
			
			if(emaintenanceWorkOrder!=null )
			{
				
				  emaintenanceWorkOrderService.updateItem(emaintenanceWorkOrder);
				  emaintenanceWorkOrderService.pushMessageToSingle(emaintenanceWorkOrder);
				
			}
			
			if(erepairWorkOrder!=null )
			 {
				 
			 
				erpairWorkOrderService.updateItem(erepairWorkOrder);
				erpairWorkOrderService.pushMessageToSingle(erepairWorkOrder);
			  
			}
			
			if(eworkOrder!=null )
			 {
			
				eworkOrderService.updateItem(eworkOrder);
			  
			}
			
			if(workOrderProgress!=null)
			 {
				 
			  
				workOrderProgress.setProgressContent("工单转办给：" + user.getUsername());
				workOrderProgressService.insertItem(workOrderProgress);
			  
			}
			
			
			return ApiResult.success("工单转办成功！");
		} catch (Exception e) {
			log.error("工单转办出错:"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ApiResult.fail("工单转办出错:"+e.getMessage());
		}
	}
	
	
	
	/**
	 * 批量工单转办
	 * @param workOrderType  工单类型(1巡检工单 2保养工单 3维修工单)
	 * @param oldhandlerId  原处理人ID
	 * @param newhandlerId 新处理人ID
	 * @return
	 */
	@ApiOperation(value = "批量工单转办")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "workOrderType", value = "工单类型(1巡检工单 2保养工单 3维修工单)", required = true, dataType = "int"),
	  		@ApiImplicitParam(name = "oldhandlerId", value = "原处理人ID", required = true, dataType = "Long"),
	  		@ApiImplicitParam(name = "newhandlerId", value = "新处理人ID", required = true, dataType = "Long")
	  		})
	@ViLog(operEvent = "工单转办", operType =2)//日志记录
	@PostMapping("workTurnDo")
	@RequiresPermissions(value={"004008001","SUPERADMIN"}, logical= Logical.OR)
	@SuppressWarnings("unchecked")
	@Transactional
	public ApiResult<ERepairWorkOrder> workTurnDo(@RequestParam(value = "workOrderType") int workOrderType,
			@RequestParam(value = "oldhandlerId") long oldhandlerId,
			@RequestParam(value = "newhandlerId") long newhandlerId){
		try {
			
			Userview user = userviewService.getItemById(newhandlerId);
			if(user==null)
			{
				return ApiResult.fail("处理人不存在！");
			}
			
			List<EInspectionWorkOrder> einspectionWorkOrderList=new ArrayList<EInspectionWorkOrder>();
			List<EMaintenanceWorkOrder> emaintenanceWorkOrderList=new ArrayList<EMaintenanceWorkOrder>();
			List<ERepairWorkOrder> erepairWorkOrderList=new ArrayList<ERepairWorkOrder>();
			List<EWorkOrder> eworkOrderList=new ArrayList<EWorkOrder>();
			List<EWorkOrderProgress> workOrderProgressList =new ArrayList<EWorkOrderProgress>();
			if(workOrderType==1)//巡检工单
			{

                  einspectionWorkOrderList=einspectionWorkOrderService.getEInspectionWorkOrderList(oldhandlerId);
				
				if(einspectionWorkOrderList!=null && einspectionWorkOrderList.size()>0)
				{
					for(EInspectionWorkOrder einspectionWorkOrder:einspectionWorkOrderList)
					{
						
					
					if (einspectionWorkOrder.getHandleStatus() == 40)
					{
						continue;
					}
					einspectionWorkOrder.setHandlerId(newhandlerId);
					
					//待办
					EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(einspectionWorkOrder.getId(),1);
					if(eworkOrder!=null)
					{
						eworkOrder.setHandlerId(newhandlerId);
						eworkOrder.setHandlerName(user.getUsername());
						eworkOrderList.add(eworkOrder);
					}
					
					// 进度
					EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
					workOrderProgress.setWorkOrderId(einspectionWorkOrder.getId());
					workOrderProgress.setWorkOrderType(2);
					workOrderProgress.setTime(new Date());
					workOrderProgress.setUserId(einspectionWorkOrder.getHandlerId());
					workOrderProgress.setOperatorId(einspectionWorkOrder.getCreator());
					workOrderProgress.setCreator(einspectionWorkOrder.getCreator());
					workOrderProgress.setProgressType(einspectionWorkOrder.getHandleStatus());
					workOrderProgressList.add(workOrderProgress);
					}
				}
				
			}
			else if(workOrderType==2)//保养工单
			{
				emaintenanceWorkOrderList=emaintenanceWorkOrderService.getEMaintenanceWorkOrderList(oldhandlerId);
				
				if(emaintenanceWorkOrderList!=null && emaintenanceWorkOrderList.size()>0)
				{
					for(EMaintenanceWorkOrder   emaintenanceWorkOrder:emaintenanceWorkOrderList)
					{
						
					
					  if (emaintenanceWorkOrder.getHandleStatus()== 40)
					  {
						  continue;
					  }
					  
					  emaintenanceWorkOrder.setHandlerId(newhandlerId);
					
					 //待办
					  EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(emaintenanceWorkOrder.getId(),2);
					 if(eworkOrder!=null)
					 {
						eworkOrder.setHandlerId(newhandlerId);
						eworkOrder.setHandlerName(user.getUsername());
						eworkOrderList.add(eworkOrder);
					 }
					
					 // 进度
					 EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
					 workOrderProgress.setWorkOrderId(emaintenanceWorkOrder.getId());
					 workOrderProgress.setWorkOrderType(2);
					 workOrderProgress.setTime(new Date());
					 workOrderProgress.setUserId(emaintenanceWorkOrder.getHandlerId());
					 workOrderProgress.setOperatorId(emaintenanceWorkOrder.getCreator());
					 workOrderProgress.setCreator(emaintenanceWorkOrder.getCreator());
					 workOrderProgress.setProgressType(emaintenanceWorkOrder.getHandleStatus());
					 workOrderProgressList.add(workOrderProgress);
					}
				}
				
				
			}
			else if(workOrderType==3)//维修工单
			{
				erepairWorkOrderList=erpairWorkOrderService.getERepairWorkOrderList(oldhandlerId);
				
				
				
				 if(erepairWorkOrderList!=null && erepairWorkOrderList.size()>0)
				 {
					 
				  for(ERepairWorkOrder   erepairWorkOrder:erepairWorkOrderList)
				  {
							
					if (erepairWorkOrder.getHandleStatus() == 40)
					{
						continue;
					}
					erepairWorkOrder.setHandlerId(newhandlerId);
					
					//待办
					EWorkOrder eworkOrder=eworkOrderService.getEWorkOrder(erepairWorkOrder.getId(),3);
					if(eworkOrder!=null)
					{
						eworkOrder.setHandlerId(newhandlerId);
						eworkOrder.setHandlerName(user.getUsername());
						eworkOrderList.add(eworkOrder);
					}
					
					// 进度
					EWorkOrderProgress workOrderProgress = new EWorkOrderProgress();
					workOrderProgress.setWorkOrderId(erepairWorkOrder.getId());
					workOrderProgress.setWorkOrderType(2);
					workOrderProgress.setTime(new Date());
					workOrderProgress.setUserId(erepairWorkOrder.getHandlerId());
					workOrderProgress.setOperatorId(erepairWorkOrder.getCreator());
					workOrderProgress.setCreator(erepairWorkOrder.getCreator());
					workOrderProgress.setProgressType(erepairWorkOrder.getHandleStatus());
					workOrderProgressList.add(workOrderProgress);
				  }
				 }
				 
			 }
			 else
			 {
				return ApiResult.fail("工单类型错误！");
			 }
			
			
			if(einspectionWorkOrderList!=null && einspectionWorkOrderList.size()>0)
			{
				for(EInspectionWorkOrder einspectionWorkOrder:einspectionWorkOrderList)
				{
				  einspectionWorkOrderService.updateItem(einspectionWorkOrder);
				  einspectionWorkOrderService.pushMessageToSingle(einspectionWorkOrder);
			   }
			}
			
			if(emaintenanceWorkOrderList!=null && emaintenanceWorkOrderList.size()>0)
			{
				for(EMaintenanceWorkOrder   emaintenanceWorkOrder:emaintenanceWorkOrderList)
				{
				  emaintenanceWorkOrderService.updateItem(emaintenanceWorkOrder);
				  emaintenanceWorkOrderService.pushMessageToSingle(emaintenanceWorkOrder);
				}
			}
			
			if(erepairWorkOrderList!=null && erepairWorkOrderList.size()>0)
			 {
				 
			  for(ERepairWorkOrder   erepairWorkOrder:erepairWorkOrderList)
			  {
				erpairWorkOrderService.updateItem(erepairWorkOrder);
				erpairWorkOrderService.pushMessageToSingle(erepairWorkOrder);
			  }
			}
			
			if(eworkOrderList!=null && eworkOrderList.size()>0)
			 {
				 
			  for(EWorkOrder   eworkOrder:eworkOrderList)
			  {
				eworkOrderService.updateItem(eworkOrder);
			  }
			}
			
			if(workOrderProgressList!=null && workOrderProgressList.size()>0)
			 {
				 
			  for(EWorkOrderProgress   workOrderProgress:workOrderProgressList)
			  {
				workOrderProgress.setProgressContent("工单转办给：" + user.getUsername());
				workOrderProgressService.insertItem(workOrderProgress);
			  }
			}
			
			
			return ApiResult.success("工单转办成功！");
		} catch (Exception e) {
			log.error("工单转办出错:"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ApiResult.fail("工单转办出错:"+e.getMessage());
		}
	}
	

}
