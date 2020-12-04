package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.control;


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

import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.IDCardAttachment;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.IDCardAttachmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * 身份证附件数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "身份证附件数据访问API接口")
@RequestMapping("/api/iDCardAttachment")
public class IDCardAttachmentControl {
	
	private static final Logger log = LoggerFactory.getLogger(IDCardAttachmentControl.class);

	@Autowired
	private IDCardAttachmentService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="身份证附件查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 运维ID
		@ApiModelProperty(value="运维ID",name="operatorId",example="")
		private Long operatorId;

		// 图片类型
		@ApiModelProperty(value="图片类型",name="imgType",example="")
		private Integer imgType;

		public Long getOperatorId() {
			return operatorId;
		}

		public void setOperatorId(Long operatorId) {
			this.operatorId = operatorId;
		}
		public Integer getImgType() {
			return imgType;
		}

		public void setImgType(Integer imgType) {
			this.imgType = imgType;
		}
	}
	
	/**
	 * 新增一个新的身份证附件对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的身份证附件对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的身份证附件对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<IDCardAttachment> saveNewIDCardAttachment(@RequestBody IDCardAttachment item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增身份证附件对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除身份证附件对象
	 * @param item
	 * @return
	 */
	 @ApiOperation(value = " 删除身份证附件对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "身份证附件IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除身份证附件对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteIDCardAttachment(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除身份证附件对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除身份证附件对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改身份证附件对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改身份证附件对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改身份证附件对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<IDCardAttachment> saveIDCardAttachment(@RequestBody IDCardAttachment item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改身份证附件对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改身份证附件对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<IDCardAttachment>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询身份证附件对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询身份证附件对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取身份证附件信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "身份证附件Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<IDCardAttachment> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
