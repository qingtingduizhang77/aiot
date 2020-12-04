package com.cityiot.guanxin.light.control;


import com.cityiot.guanxin.light.entity.LightControlRule;
import com.cityiot.guanxin.light.entity.vo.LightControlRuleAndDetailVo;
import com.cityiot.guanxin.light.entity.vo.dto.LightControlRuleAndDetailsDto;
import com.cityiot.guanxin.light.service.LightControlRuleService;

import com.cityiot.guanxin.log.annotation.ViLog;

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
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 路灯控制规则实体数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "路灯控制规则实体数据访问API接口")
@RequestMapping("/api/lightControlRule")
public class LightControlRuleControl {
	
	private static final Logger log = LoggerFactory.getLogger(LightControlRuleControl.class);

	@Autowired
	private LightControlRuleService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="路灯控制规则查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 规则名称
		@ApiModelProperty(value="规则名称",name="name",example="")
		@Like
		private String name;

		// 规则编号
		@ApiModelProperty(value="规则编号",name="code",example="")
		@Like
		private String code;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
	
	/**
	 * 新增一个新的路灯控制规则实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的路灯控制规则实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的路灯控制规则实体对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@RequiresPermissions(value={"lightCtrlRule01","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<LightControlRule> saveNewLightControlRule(@RequestBody LightControlRuleAndDetailVo item) {
		try {
			
			return ApiResult.success(service.addNewLightControlRule(item));
		} catch (SwallowException e) {
			log.error("新增路灯控制规则实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除路灯控制规则实体对象
	 * @param ids
	 * @return
	 */
	 @ApiOperation(value = "删除路灯控制规则实体对象")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "路灯控制规则IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除路灯控制规则实体对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"lightCtrlRule02","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deleteLightControlRule(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除路灯控制规则实体对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除路灯控制规则实体对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改路灯控制规则实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 修改路灯控制规则实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改路灯控制规则实体对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"lightCtrlRule03","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<LightControlRule> saveLightControlRule(@RequestBody LightControlRuleAndDetailVo item){
		try {
			return ApiResult.success(service.updateLightControlRule(item));
		} catch (SwallowException e) {
			log.error("修改路灯控制规则实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改路灯控制规则实体对象出错:"+e.getMessage());
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
	@RequiresPermissions(value={"lightCtrlRule04","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<LightControlRule>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询路灯控制规则实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询路灯控制规则实体对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取路灯控制规则信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "路灯控制规则Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"lightCtrlRule05","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<LightControlRule> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
   
	 @ApiModel(value="路灯控制规则查询对象")
	static class ControlRuleAndDetailsQueryBean extends BasePageQueryBean{

	}
	 
	@ApiOperation(value = "所有的路灯控制规则列表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@RequestMapping("/allLightControlRuleAndDetails")
	@RequiresPermissions(value={"lightCtrlRule06","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<LightControlRuleAndDetailsDto>> getAllLightControlRuleAndDetails(@RequestBody  ControlRuleAndDetailsQueryBean basePageQueryBean) {
		return ApiResult.success(service.getAllLightControlRuleAndDetails(basePageQueryBean));
	}

}
