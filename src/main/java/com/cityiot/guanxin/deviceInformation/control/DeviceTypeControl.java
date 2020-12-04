package com.cityiot.guanxin.deviceInformation.control;


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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.service.DeviceTypeService;

import java.util.Date;
import java.util.List;

/**
 * 设备类型数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备类型数据访问API接口")
@RequestMapping("/api/deviceType")
public class DeviceTypeControl {
	
	private static final Logger log = LoggerFactory.getLogger(DeviceTypeControl.class);

	@Autowired
	private DeviceTypeService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备类型查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 设备类型
		@ApiModelProperty(value="设备类型名称",name="deviceTypeName",example="")
		@Like
		private String deviceTypeName;
		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;
		@ApiModelProperty(value="id",name="id",example="")
		private Long id;

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public String getDeviceTypeName() {
			return deviceTypeName;
		}

		public void setDeviceTypeName(String deviceTypeName) {
			this.deviceTypeName = deviceTypeName;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	}
	
	/**
	 * 新增一个新的设备类型对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的设备类型对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备类型对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<DeviceType> saveNewDeviceType(@RequestBody DeviceType item) {
		try {
			if (service.checkForPresenceInfo(item)) {
				return ApiResult.fail("设备类型名称不能存在相同！");
			}
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增设备类型对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备类型对象
	 * @param item
	 * @return
	 */
	 @ApiOperation(value = "删除设备类型对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "设备类型IDs数组", required = true, allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除设备类型对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteDeviceType(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备类型对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备类型对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备类型对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 修改设备类型对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备类型对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceType> saveDeviceType(@RequestBody DeviceType item){
		try {
			if (service.checkForPresenceInfo(item)) {
				return ApiResult.fail("设备类型名称不能存在相同！");
			}
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改设备类型对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备类型对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<DeviceType>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备类型对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备类型对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据Id取设备类型列表")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceType> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

	@ApiOperation(value = "取所有的设备类型列表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@RequestMapping("getAllItems")
	public ApiResult<List<DeviceType>> getAllItems(){
		try {
			return ApiResult.success(service.getAllItems(query -> query));
		} catch (Exception e){
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
