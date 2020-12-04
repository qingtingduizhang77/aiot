package com.cityiot.guanxin.permission.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.permission.service.PermissionService;

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
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;

/**
 * 权限项数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "权限项数据访问API接口")
@RequestMapping("/api/permission")
public class PermissionControl {
	
	private static final Logger log = LoggerFactory.getLogger(PermissionControl.class);

	@Autowired
	private PermissionService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="权限项查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 权限名称
		@ApiModelProperty(value="权限名称",name="name",example="")
		@Like
		private String name;

		// 权限中文名称
		@ApiModelProperty(value="权限中文名称",name="cnName",example="")
		@Like
		private String cnName;

		// 模块Id
		@ApiModelProperty(value="模块Id",name="moduleId",example="")
		private Long moduleId;

		@CnName("修改时间")
		@ApiModelProperty(value="修改时间",name="endTime",example="")
		private Date lastmodi;

		@CnName("权限唯一m码")
		@ApiModelProperty(value="权限唯一m码",name="code",example="")
		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public String getCnName() {
			return cnName;
		}

		public void setCnName(String cnName) {
			this.cnName = cnName;
		}
		public Long getModuleId() {
			return moduleId;
		}

		public void setModuleId(Long moduleId) {
			this.moduleId = moduleId;
		}
	}
	
	/**
	 * 新增一个新的权限项对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 新增一个新的权限项对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的权限项对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@RequiresPermissions(value={"001002001","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Permission> saveNewPermission(@RequestBody Permission item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增权限项对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除权限项对象
	 * @param ids
	 * @return
	 */
	 @ApiOperation(value = "删除权限项对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "权限项IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除权限项对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"001002002","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deletePermission(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除权限项对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除权限项对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改权限项对象
	 * @param item
	 * @return
	 */
	 @ApiOperation(value = "修改权限项对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改权限项对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001002003","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Permission> savePermission(@RequestBody Permission item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改权限项对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改权限项对象出错:"+e.getMessage());
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
	@RequiresPermissions(value={"001002004","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<Permission>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询权限项对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询权限项对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取权限项信息")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "权限项Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001002005","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Permission> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
