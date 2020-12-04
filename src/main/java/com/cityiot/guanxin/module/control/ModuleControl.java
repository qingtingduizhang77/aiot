package com.cityiot.guanxin.module.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.module.entity.Module;
import com.cityiot.guanxin.module.service.ModuleExtendsService;
import com.cityiot.guanxin.module.service.ModuleService;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 * 模块数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "模块数据访问API接口")
@RequestMapping("/api/module")
public class ModuleControl {
	
	private static final Logger log = LoggerFactory.getLogger(ModuleControl.class);

	@Autowired
	private ModuleService service;
	@Autowired
	private ModuleExtendsService moduleExtendsService;
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="模块数据查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 模块名称
		@ApiModelProperty(value="模块名称",name="name",example="")
		@Like
		private String name;

		// 父级模块Id
		@ApiModelProperty(value="父级模块Id",name="parentId",example="")
		private Long parentId;

		// 模块编码
		@ApiModelProperty(value="模块编码",name="code",example="")
		@Like
		private String code;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public Long getParentId() {
			return parentId;
		}

		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
	
	/**
	 * 新增一个新的模块对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 新增一个新的模块对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的模块对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<Module> saveNewModule(@RequestBody Module item) {
		try {
			
			return ApiResult.success(moduleExtendsService.addModule(item));
		} catch (SwallowException e) {
			log.error("新增模块对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除模块对象
	 * @param item
	 * @return
	 */
	 @ApiOperation(value = "删除模块对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
	  		@ApiImplicitParam(name = "ids", value = "模块IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除模块对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteModule(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除模块对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除模块对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改模块对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改模块对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改模块对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<Module> saveModule(@RequestBody Module item){
		try {
			return ApiResult.success(moduleExtendsService.updateModule(item));
		} catch (SwallowException e) {
			log.error("修改模块对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改模块对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<Module>> query(@RequestBody  QueryBean query){
		try {			
			 Subject subject = SecurityUtils.getSubject();
			 Userview loginUser = (Userview) subject.getPrincipal();
			System.out.println("#############"+loginUser.getAccount());
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询模块对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询模块对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取模块信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "模块Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<Module> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
