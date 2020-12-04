package com.cityiot.guanxin.workOrder.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.entity.Vo.MaintenanceVo;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceRecord;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceRecord;
import com.cityiot.guanxin.workOrder.maintenance.service.MaintenanceRecordService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import com.cityiot.guanxin.workOrder.entity.EMaintenanceWorkOrder;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

/**
 * 保养工单数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "保养工单数据访问API接口")
@RequestMapping("/api/eMaintenanceWorkOrder")
public class EMaintenanceWorkOrderControl {
	
	private static final Logger log = LoggerFactory.getLogger(EMaintenanceWorkOrderControl.class);

	@Autowired
	private EMaintenanceWorkOrderService service;

	@Autowired
	private MaintenanceRecordService maintenanceRecordService;

	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */

	@ApiModel(value="保养工单查询对象")
	static class QueryBean extends BasePageQueryBean{
		@ApiModelProperty(value="设备名称",name="deviceName",example="")
		@Like
		private String deviceName;
		@ApiModelProperty(value="保养工单编号",name="deviceName",example="")
		@Like
		private String code;

		@CnName("处理时间")
		@ApiModelProperty(value="处理时间",name="handleTime",example="")
		private Date handleTime;

		@CnName("修改时间")
		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		@CnName("处理状态")
		@ApiModelProperty(value="处理状态",name="lastmodi",example="")
		private Integer handleStatus;

		public String getDeviceName() {
			return deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Date getHandleTime() {
			return handleTime;
		}

		public void setHandleTime(Date handleTime) {
			this.handleTime = handleTime;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public Integer getHandleStatus() {
			return handleStatus;
		}
	}
	
	/**
	 * 新增一个新的保养工单对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004003001","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "新增一个新的保养工单对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的保养工单对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<EMaintenanceWorkOrder> saveNewEMaintenanceWorkOrder(@RequestBody MaintenanceVo item) {
		try {
			
			return ApiResult.success(service.insertItem(item.getEntity(),item.getImages(), item.getType()));
		} catch (SwallowException e) {
			log.error("新增保养工单对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除保养工单对象
	 * @return
	 */
	@RequiresPermissions(value={"004003002","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "删除保养工单对象")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "保养工单ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除保养工单对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteEMaintenanceWorkOrder(@RequestBody long []ids) {
		try {
				service.deleteItemByIds(ids);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除保养工单对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除保养工单对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改保养工单对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004003003","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "修改保养工单对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改保养工单对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<EMaintenanceWorkOrder> saveEMaintenanceWorkOrder(@RequestBody MaintenanceVo item){
		try {
			return ApiResult.success(service.updateItem(item.getEntity(),item.getImages(),item.getDeleteImages(),item.getType()));
		} catch (SwallowException e) {
			log.error("修改保养工单对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改保养工单对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@RequiresPermissions(value={"004003004","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<EMaintenanceWorkOrder>> query(@RequestBody  QueryBean query){
		try {
			var bean = service.getAllItemPageByQuerybean(query);
			return ApiResult.success(bean);
		} catch (Exception e) {
			log.error("查询保养工单对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询保养工单对象出错:"+e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据id取保养工单信息")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "保养工单Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<EMaintenanceWorkOrder> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

	/**
	 * 更新保养工单状态
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "更新保养工单状态")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "更新保养工单状态", operType =2)//日志记录
	@SuppressWarnings("unchecked")
	@PostMapping(value = "updateWorkOrderStatus")
	public ApiResult<EMaintenanceWorkOrder> updateWorkOrderStatus(EMaintenanceWorkOrder item) {
		try {
			return ApiResult.success(service.updateWorkOrderStatus(item));
		} catch (SwallowException e) {
			log.error("保养工单流程处理出错:"+e.getMessage(),e);
			return ApiResult.fail("保养工单流程处理出错:"+e.getMessage());
		}
	}

	/**
	 * 通过保养工单ID查询保养记录列表
	 * @return
	 */
	@ApiOperation(value = "通过保养工单ID查询保养记录列表")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "保养工单Id", required = true, dataType = "long")
	  		})
	@PostMapping("record/list/{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<List<MaintenanceRecord>> recordList(@PathVariable long id){
		try {
			var qm = QMaintenanceRecord.maintenanceRecord;
			return ApiResult.success(maintenanceRecordService.getAllItems(query -> query.where(qm.maintainNo.eq(id))
					.orderBy(qm.created.desc())));
		} catch (Exception e) {
			log.error("查询保养记录对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询保养记录对象出错:"+e.getMessage());
		}
	}

	/**
	 * 批量设置保养记录状态
	 * @param ids 保养记录id
	 * @param id  保养工单ID
	 * @return
	 */
	
	@ApiOperation(value = "批量设置保养记录状态")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "保养记录id", required = true, dataType = "long"),
	  		@ApiImplicitParam(name = "ids", value = "保养工单ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "批量设置保养记录状态", operType =2)//日志记录
	@PostMapping("batch/handle/{id}")
	@SuppressWarnings("unchecked")
	public BaseApiResult batchHandleRecord(@RequestBody long []ids, @PathVariable long id) {
		try {
			service.batchHandleRecord(ids, id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("保养记录对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"保养记录对象出错:"+e.getMessage());
		}
	}
}
