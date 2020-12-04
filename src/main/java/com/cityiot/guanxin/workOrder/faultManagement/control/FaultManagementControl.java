package com.cityiot.guanxin.workOrder.faultManagement.control;


import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.faultManagement.entity.FaultManagement;
import com.cityiot.guanxin.workOrder.faultManagement.service.FaultManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;

/**
 * 设备故障管理数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备故障管理数据访问API接口")
@RequestMapping("/api/faultManagement")
public class FaultManagementControl {
	
	private static final Logger log = LoggerFactory.getLogger(FaultManagementControl.class);

	@Autowired
	private FaultManagementService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备故障查询对象")
	@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
			name = "id", joinEntityClass = DeviceModel.class)
	static class QueryBean extends BasePageQueryBean{

		@ApiModelProperty(value="设备id",name="deviceId",example="")
		@JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
		private Long deviceId;

		// 设备型号
		@ApiModelProperty(value="设备型号id",name="deviceModelId",example="")
		@FieldPath(name = "deviceModelId",joinEntityClass = Deviceinformation.class)
		private Long deviceModelId;

		// 设备类型
		@ApiModelProperty(value="设备类型id",name="deviceBrandId",example="")
		@FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
		private Long deviceBrandId;

		// 设备品牌
		@ApiModelProperty(value="设备品牌id",name="deviceTypeId",example="")
		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
		private Long deviceTypeId;

		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		@ApiModelProperty(value="处理时间时间",name="handleTime",example="")
		private Date handleTime;

		@ApiModelProperty(value="处理状态",name="handleStatus",example="")
		private Integer handleStatus;

		public Long getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(Long deviceId) {
			this.deviceId = deviceId;
		}

		public Long getDeviceModelId() {
			return deviceModelId;
		}

		public void setDeviceModelId(Long deviceModelId) {
			this.deviceModelId = deviceModelId;
		}

		public Long getDeviceBrandId() {
			return deviceBrandId;
		}

		public void setDeviceBrandId(Long deviceBrandId) {
			this.deviceBrandId = deviceBrandId;
		}

		public Long getDeviceTypeId() {
			return deviceTypeId;
		}

		public void setDeviceTypeId(Long deviceTypeId) {
			this.deviceTypeId = deviceTypeId;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public Date getHandleTime() {
			return handleTime;
		}

		public void setHandleTime(Date handleTime) {
			this.handleTime = handleTime;
		}

		public Integer getHandleStatus() {
			return handleStatus;
		}

		public void setHandleStatus(Integer handleStatus) {
			this.handleStatus = handleStatus;
		}
	}
	
	/**
	 * 新增一个新的设备故障管理对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004001001","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "新增一个新的设备故障管理对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备故障管理对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<FaultManagement> saveNewFaultManagement(@RequestBody FaultManagement item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增设备故障管理对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备故障管理对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004001002","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "删除设备故障管理对象")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "设备故障ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除设备故障管理对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteFaultManagement(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备故障管理对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备故障管理对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备故障管理对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004001003","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "修改设备故障管理对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备故障管理对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<FaultManagement> saveFaultManagement(@RequestBody FaultManagement item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改设备故障管理对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备故障管理对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@RequiresPermissions(value={"004001004","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<FaultManagement>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备故障管理对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备故障管理对象出错:"+e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据id取设备故障信息")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备故障Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<FaultManagement> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

	@ApiOperation(value = "转换工单")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "转换工单", operType =2)//日志记录
	@RequestMapping("/ConversionWorkOrder")
	@SuppressWarnings("unchecked")
	public ApiResult<FaultManagement> ConversionWorkOrder(@RequestBody FaultManagement item){
		try {
			return ApiResult.success(service.ConversionWorkOrder(item));
		} catch (Exception e) {
			log.error("转换工单出错:"+e.getMessage(),e);
			return ApiResult.fail("转换工单出错:"+e.getMessage());
		}
	}

	/**
	 * 完成处理
	 * @param item
	 * @return
	 */
	
	@ApiOperation(value = "完成处理")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "完成处理", operType =2)//日志记录
	@RequestMapping("/handleStatus")
	@SuppressWarnings("unchecked")
	public ApiResult<FaultManagement> handleStatus(@RequestBody FaultManagement item){
		try {
			return ApiResult.success(service.handleStatus(item));
		} catch (Exception e) {
			log.error("完成处理出错:"+e.getMessage(),e);
			return ApiResult.fail("完成处理出错:"+e.getMessage());
		}
	}
}
