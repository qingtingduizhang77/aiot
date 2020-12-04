package com.cityiot.guanxin.workOrder.control;


import com.cityiot.guanxin.log.annotation.ViLog;
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
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;
import com.cityiot.guanxin.workOrder.service.EWorkOrderImagesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * 工单图片数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "工单图片数据访问API接口")
@RequestMapping("/api/eWorkOrderImages")
public class EWorkOrderImagesControl {
	
	private static final Logger log = LoggerFactory.getLogger(EWorkOrderImagesControl.class);

	@Autowired
	private EWorkOrderImagesService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="工单图片查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 图片类型
		@ApiModelProperty(value="图片类型",name="imgType",example="")
		private Integer imgType;

		// 工单类型
		@ApiModelProperty(value="工单类型",name="workOrderType",example="")
		private Integer workOrderType;

		// 工单ID
		@ApiModelProperty(value="工单ID",name="workOrderId",example="")
		private Long workOrderId;

		// 工单图片url
		@ApiModelProperty(value="工单图片url",name="imageUrl",example="")
		private String imageUrl;

		public Integer getImgType() {
			return imgType;
		}

		public void setImgType(Integer imgType) {
			this.imgType = imgType;
		}
		public Integer getWorkOrderType() {
			return workOrderType;
		}

		public void setWorkOrderType(Integer workOrderType) {
			this.workOrderType = workOrderType;
		}
		public Long getWorkOrderId() {
			return workOrderId;
		}

		public void setWorkOrderId(Long workOrderId) {
			this.workOrderId = workOrderId;
		}
		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	}
	
	/**
	 * 新增一个新的工单图片对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的工单图片对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的工单图片对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<EWorkOrderImages> saveNewEWorkOrderImages(@RequestBody EWorkOrderImages item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增工单图片对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除工单图片对象
	 * @param item
	 * @return
	 */
    @ApiOperation(value = "删除工单图片对象")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "工单图片ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除工单图片对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteEWorkOrderImages(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除工单图片对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除工单图片对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改工单图片对象
	 * @param item
	 * @return
	 */
    @ApiOperation(value = "修改工单图片对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改工单图片对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
//	@RequiresPermissions("")
	public ApiResult<EWorkOrderImages> saveEWorkOrderImages(@RequestBody EWorkOrderImages item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改工单图片对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改工单图片对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<EWorkOrderImages>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询工单图片对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询工单图片对象出错:"+e.getMessage());
		}
	}
	
    @ApiOperation(value = "根据id取工单图片信息")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "工单图片Id", required = true, dataType = "long")
  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<EWorkOrderImages> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
