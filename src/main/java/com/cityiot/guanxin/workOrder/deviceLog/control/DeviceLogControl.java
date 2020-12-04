package com.cityiot.guanxin.workOrder.deviceLog.control;


import com.cityiot.guanxin.deviceInformation.control.DeviceWarningControl;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.deviceLog.entity.QDeviceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import com.cityiot.guanxin.workOrder.deviceLog.entity.DeviceLog;
import com.cityiot.guanxin.workOrder.deviceLog.service.DeviceLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

/**
 * 设备日志数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备日志数据访问API接口")
@RequestMapping("/api/deviceLog")
public class DeviceLogControl {
	
	private static final Logger log = LoggerFactory.getLogger(DeviceLogControl.class);

	@Autowired
	private DeviceLogService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备日志查询对象")
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

		@ApiModelProperty(value="查询类型",name="selectType",example="")
		@CnName("查询类型")
		private Integer selectType;

		@ApiModelProperty(value="操作时间",name="selectType",example="")
		@CnName("查询类型")
		private Date markTime;

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

		public Integer getSelectType() {
			return selectType;
		}

		public void setSelectType(Integer selectType) {
			this.selectType = selectType;
		}

		public Date getMarkTime() {
			return markTime;
		}

		public void setMarkTime(Date markTime) {
			this.markTime = markTime;
		}
	}

	/**
	 * 新增一个新的设备日志对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 新增一个新的设备日志对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备日志对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<DeviceLog> saveNewDeviceLog(@RequestBody DeviceLog item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增设备日志对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备日志对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 删除设备日志对象")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "设备日志ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除设备日志对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteDeviceLog(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备日志对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备日志对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备日志对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改设备日志对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备日志对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceLog> saveDeviceLog(@RequestBody DeviceLog item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改设备日志对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备日志对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<DeviceLog>> query(@RequestBody QueryBean query){
		try {
			QDeviceLog qDeviceLog = QDeviceLog.deviceLog;
			QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
			Long deviceId = query.getDeviceId();
			Integer selectType = query.getSelectType();
			query.setSelectType(null);// 设置为null时，querybean不做此字段查询不报错（报错因DeviceWarningMsg无此属性字段）
			query.setDeviceId(null);
			return ApiResult.success(service.getAllItemPageByQuerybean(query, qu -> {
				qu.leftJoin(qDeviceinformation)
						.on(qDeviceinformation.id.eq(qDeviceLog.deviceId));
				if (selectType != null && selectType == 1 && deviceId != null && deviceId > 0) {// 查询灯杆及子级设备操作记录
					qu.where(qDeviceinformation.parentId.eq(deviceId)
							.or(qDeviceinformation.id.eq(deviceId)));
				}else if (selectType != null && selectType == 2 && deviceId != null && deviceId > 0){// 查询当前设备操作记录
					qu.where(qDeviceinformation.id.eq(deviceId));
				}
				return qu;
			}));
		} catch (Exception e) {
			log.error("查询设备日志对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备日志对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取设备日志信息")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备日志Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceLog> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
