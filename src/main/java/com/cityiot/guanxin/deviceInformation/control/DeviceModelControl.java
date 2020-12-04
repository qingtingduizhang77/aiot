package com.cityiot.guanxin.deviceInformation.control;


import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.service.DeviceModelService;
import com.cityiot.guanxin.log.annotation.ViLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 设备型号数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备型号数据访问API接口")
@RequestMapping("/api/deviceModel")
public class DeviceModelControl {
	
	private static final Logger log = LoggerFactory.getLogger(DeviceModelControl.class);

	@Autowired
	private DeviceModelService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备型号查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 设备类型ID
		@ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
		@JoinEntity(name = "id",joinEntityClass = DeviceType.class)
		private Long deviceTypeId;

		@ApiModelProperty(value="设备类型名称",name="deviceTypeName",example="")
		@FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
		private String deviceTypeName;

		@ApiModelProperty(value="设备厂家",name="deviceManufacturer",example="")
		@FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
		private String deviceManufacturer;

		// 设备品牌ID
		@ApiModelProperty(value="设备品牌ID",name="id",example="")
		@JoinEntity(name = "id",joinEntityClass = DeviceBrand.class)
		private Long deviceBrandId;

		@ApiModelProperty(value="设备品牌",name="deviceBrandName",example="")
		@FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
		private String deviceBrandName;

		// 设备型号
		@ApiModelProperty(value="设备型号",name="deviceModel",example="")
		@Like
		private String deviceModel;

		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		public String getDeviceTypeName() {
			return deviceTypeName;
		}

		public void setDeviceTypeName(String deviceTypeName) {
			this.deviceTypeName = deviceTypeName;
		}

		public String getDeviceManufacturer() {
			return deviceManufacturer;
		}

		public void setDeviceManufacturer(String deviceManufacturer) {
			this.deviceManufacturer = deviceManufacturer;
		}

		public String getDeviceBrandName() {
			return deviceBrandName;
		}

		public void setDeviceBrandName(String deviceBrandName) {
			this.deviceBrandName = deviceBrandName;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public Long getDeviceTypeId() {
			return deviceTypeId;
		}

		public void setDeviceTypeId(Long deviceTypeId) {
			this.deviceTypeId = deviceTypeId;
		}
		public Long getDeviceBrandId() {
			return deviceBrandId;
		}

		public void setDeviceBrandId(Long deviceBrandId) {
			this.deviceBrandId = deviceBrandId;
		}
		public String getDeviceModel() {
			return deviceModel;
		}

		public void setDeviceModel(String deviceModel) {
			this.deviceModel = deviceModel;
		}
	}
	
	/**
	 * 新增一个新的设备型号对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的设备型号对象")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备型号对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<DeviceModel> saveNewDeviceModel(@RequestBody DeviceModel item) {
		try {
			if (service.checkForPresenceInfo(item)) {
				return ApiResult.fail("设备类型、品牌和型号不能存在相同！");
			}
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增设备型号对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备型号对象
	 * @param item
	 * @return
	 */
	 @ApiOperation(value = "删除设备型号对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "设备IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除设备型号对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteDeviceModel(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备型号对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备型号对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备型号对象
	 * @param item
	 * @return
	 */
	 @ApiOperation(value = "修改设备型号对象")
	 @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备型号对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceModel> saveDeviceModel(@RequestBody DeviceModel item){
		try {
			if (service.checkForPresenceInfo(item)) {
				return ApiResult.fail("设备类型、品牌和型号不能存在相同！");
			}
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改设备型号对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备型号对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	 @ApiOperation(value = " 通过查询bean进行分页查询数据")
	 @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<DeviceModel>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备型号对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备型号对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据Id取设备类型号信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备类型号Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceModel> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

	 @ApiOperation(value = " 取所有的设备类型号列表")
	 @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@RequestMapping("getAllItems")
	public ApiResult<List<DeviceModel>> getAllItems(){
		try {
			return ApiResult.success(service.getAllItems(query -> query));
		} catch (Exception e){
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
