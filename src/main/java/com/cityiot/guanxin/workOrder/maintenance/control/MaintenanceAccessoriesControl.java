package com.cityiot.guanxin.workOrder.maintenance.control;


import com.cityiot.guanxin.log.annotation.ViLog;
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

import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.service.MaintenanceAccessoriesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * 设备保养附件数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备保养附件数据访问API接口")
@RequestMapping("/api/maintenanceAccessories")
public class MaintenanceAccessoriesControl {
	
	private static final Logger log = LoggerFactory.getLogger(MaintenanceAccessoriesControl.class);

	@Autowired
	private MaintenanceAccessoriesService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备保养附件查询对象")
	static class QueryBean extends BasePageQueryBean{
		
		@ApiModelProperty(value="记录id",name="recordID",example="")
		private Long recordID;
		@ApiModelProperty(value="类型",name="type",example="")
		private Integer type;
		@ApiModelProperty(value="文件类型",name="fileType",example="")
		private Integer fileType;

		public Long getRecordID() {
			return recordID;
		}

		public void setRecordID(Long recordID) {
			this.recordID = recordID;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getFileType() {
			return fileType;
		}

		public void setFileType(Integer fileType) {
			this.fileType = fileType;
		}
	}
	
	/**
	 * 新增一个新的设备保养附件对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的设备保养附件对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备保养附件对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<MaintenanceAccessories> saveNewMaintenanceAccessories(@RequestBody MaintenanceAccessories item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增设备保养附件对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备保养附件对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 删除设备保养附件对象")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "设备保养附件ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除设备保养附件对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteMaintenanceAccessories(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备保养附件对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备保养附件对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备保养附件对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改设备保养附件对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备保养附件对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<MaintenanceAccessories> saveMaintenanceAccessories(@RequestBody MaintenanceAccessories item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改设备保养附件对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备保养附件对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<MaintenanceAccessories>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备保养附件对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备保养附件对象出错:"+e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据id取设备保养附件信息")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备保养附件Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<MaintenanceAccessories> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
