package com.cityiot.guanxin.light.control;


import com.cityiot.guanxin.light.entity.LightGroup;
import com.cityiot.guanxin.light.entity.vo.LightGroupAndGroupRuleVo;
import com.cityiot.guanxin.light.entity.vo.LightGroupAndLightDetailVo;
import com.cityiot.guanxin.light.service.LightGroupService;
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
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 路灯分组实体数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "路灯分组实体数据访问API接口")
@RequestMapping("/api/lightGroup")
public class LightGroupControl {
	
	private static final Logger log = LoggerFactory.getLogger(LightGroupControl.class);

	@Autowired
	private LightGroupService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="路灯分组查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 组名
		@ApiModelProperty(value="组名",name="groupName",example="")
		@Like
		private String groupName;

		// 定时开关状态
		@ApiModelProperty(value="定时开关状态",name="timeSwitchStatus",example="")
		private Integer timeSwitchStatus;

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public Integer getTimeSwitchStatus() {
			return timeSwitchStatus;
		}

		public void setTimeSwitchStatus(Integer timeSwitchStatus) {
			this.timeSwitchStatus = timeSwitchStatus;
		}
	}
	
	/**
	 * 新增一个新的路灯分组实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的路灯分组实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的路灯分组实体对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<LightGroup> saveNewLightGroup(@RequestBody LightGroupAndLightDetailVo item) {
		try {
			
			return ApiResult.success(service.addNewLightGroup(item));
		} catch (SwallowException e) {
			log.error("新增路灯分组实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增路灯分组实体对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除路灯分组实体对象
	 * @param ids
	 * @return
	 */
	 @ApiOperation(value = " 删除路灯分组实体对象")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "路灯分组IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除路灯分组实体对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteLightGroup(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除路灯分组实体对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除路灯分组实体对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改路灯分组实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改路灯分组实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改路灯分组实体对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<LightGroup> saveLightGroup(@RequestBody LightGroupAndGroupRuleVo item){
		try {
			return ApiResult.success(service.updateLightGroup(item));
		} catch (SwallowException e) {
			log.error("修改路灯分组实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改路灯分组实体对象出错:"+e.getMessage());
		}
	}

	/**
	 * 修改路灯分组实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 修改路灯分组实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("updateLightGroupAndLights")
	@SuppressWarnings("unchecked")
	public ApiResult<LightGroup> saveLightGroup(@RequestBody LightGroupAndLightDetailVo item){
		try {
			return ApiResult.success(service.updateLightGroupAndLights(item));
		} catch (SwallowException e) {
			log.error("修改路灯分组实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改路灯分组实体对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<LightGroup>> query(@RequestBody  QueryBean query){
		try {
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询路灯分组实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询路灯分组实体对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取路灯分组信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "路灯分组Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<LightGroup> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
