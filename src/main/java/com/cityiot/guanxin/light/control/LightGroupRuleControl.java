package com.cityiot.guanxin.light.control;


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

import com.cityiot.guanxin.light.entity.LightGroupRule;
import com.cityiot.guanxin.light.service.LightGroupRuleService;

/**
 * 路灯规则实体数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "路灯分组规则实体数据访问API接口")
@RequestMapping("/api/lightGroupRule")
public class LightGroupRuleControl {
	
	private static final Logger log = LoggerFactory.getLogger(LightGroupRuleControl.class);

	@Autowired
	private LightGroupRuleService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="路灯分组规则查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 分组Id
		@ApiModelProperty(value=" 分组Id",name="groupId",example="")
		private Long groupId;

		public Long getGroupId() {
			return groupId;
		}

		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}
	}
	
	/**
	 * 新增一个新的路灯规则实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的路灯规则实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的路灯规则实体对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<LightGroupRule> saveNewLightGroupRule(@RequestBody LightGroupRule item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增路灯规则实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除路灯规则实体对象
	 * @param item
	 * @return
	 */
	 @ApiOperation(value = "删除路灯规则实体对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "路灯规则IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除路灯规则实体对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteLightGroupRule(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除路灯规则实体对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除路灯规则实体对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改路灯规则实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改路灯规则实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改路灯规则实体对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<LightGroupRule> saveLightGroupRule(@RequestBody LightGroupRule item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改路灯规则实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改路灯规则实体对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<LightGroupRule>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询路灯规则实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询路灯规则实体对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "设备分组添加关联设备")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "路灯规则Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<LightGroupRule> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
