package com.cityiot.guanxin.module.control;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.module.entity.Module;
import com.cityiot.guanxin.moduleAuth.entity.ModuleAuth;
import com.cityiot.guanxin.moduleAuth.service.ModuleControlService;
import com.cityiot.guanxin.user.entity.Userview;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;
/**
 * 
 * @author ouwt
 *
 */
@RestController
@Api(tags = "模块权限访问API接口")
@RequestMapping("/api/moduleAuth")
public class ModuleAuthControl {
	
	
	private static final Logger log = LoggerFactory.getLogger(ModuleAuthControl.class);
	
	@Autowired
	private ModuleControlService moduleControlService;
	
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="模块数权限查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 模块名称
		@ApiModelProperty(value="模块名称",name="name",example="")
		@Like
		private String name;

		 @ApiModelProperty(value="模块编码",name="moCode",example="")
		 @CnName("模块编码")
		 private String moCode;//模块;10001 告警信息管理员

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMoCode() {
			return moCode;
		}

		public void setMoCode(String moCode) {
			this.moCode = moCode;
		}

		
	}
	
	
	/**
	 * 新增模块权限对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增模块权限对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改模块权限对象", operType =2)//日志记录
	@PostMapping("addModule")
	@SuppressWarnings("unchecked")
	public ApiResult<ModuleAuth> addModule(@RequestBody ModuleAuth item){
		try {
			return ApiResult.success(moduleControlService.addModuleAuth(item));
		} catch (SwallowException e) {
			log.error("新增模块权限对象:"+e.getMessage(),e);
			return ApiResult.fail("新增模块权限对象:"+e.getMessage());
		}
	}
	
	
	
	/**
	 * 修改模块权限对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改模块权限对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改模块权限对象", operType =2)//日志记录
	@PostMapping("updModule")
	@SuppressWarnings("unchecked")
	public ApiResult<ModuleAuth> updModule(@RequestBody ModuleAuth item){
		try {
			return ApiResult.success(moduleControlService.updModuleAuth(item));
		} catch (SwallowException e) {
			log.error("修改模块权限对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改模块权限对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<ModuleAuth>> query(@RequestBody  QueryBean query){
		try {			
			 Subject subject = SecurityUtils.getSubject();
			 Userview loginUser = (Userview) subject.getPrincipal();
			 System.out.println("#############"+loginUser.getAccount());
			return ApiResult.success(moduleControlService.getModuleAuthList(query));
		} catch (Exception e) {
			log.error("查询模块权限对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询模块权限对象出错:"+e.getMessage());
		}
	}
	
	
	 @ApiOperation(value = "根据id取模块权限信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "模块Id", required = true, dataType = "long")
	  		})
	@PostMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<ModuleAuth> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(moduleControlService.getModuleAuthInfoById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	 
	 
	 @ApiOperation(value = "根据编码取模块权限信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		 @ApiImplicitParam(name = "code", value = "模块编码", required = true, dataType = "String",paramType="query")
	  		})
	@PostMapping("getItemByCode")
	@SuppressWarnings("unchecked")
	public ApiResult<ModuleAuth> getItemByCode(@RequestParam(value = "code", required = true) String code){
		try {			
			return ApiResult.success(moduleControlService.getModuleAuthInfoByCode(code));
		} catch (Exception e) {
			log.error("根据编码取模块权限信息出错:"+e.getMessage(),e);
			return ApiResult.fail("根据编码取模块权限信息出错:"+e.getMessage());
		}
	}
	
	

}
