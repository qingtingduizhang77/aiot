package com.cityiot.guanxin.role.control;


import java.util.Date;

import org.apache.shiro.authz.annotation.Logical;
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

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.role.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 角色数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "角色数据访问API接口")
@RequestMapping("/api/role")
public class RoleControl {
	
	private static final Logger log = LoggerFactory.getLogger(RoleControl.class);

	@Autowired
	private RoleService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="角色查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 角色名称
		@ApiModelProperty(value="角色名称",name="name",example="")
		@Like
		private String name;

		// 角色中文名称
		@ApiModelProperty(value="角色中文名称",name="cnName",example="")
		@Like
		private String cnName;

		// 角色唯一标识
		@ApiModelProperty(value="角色唯一标识",name="code",example="")
		@Like
		private String code;

		private Date lastmodi;

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
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
	
	/**
	 * 新增一个新的角色对象
	 * @param item
	 * @return
	 */
	
	@ApiOperation(value = "新增一个新的角色对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的角色对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@RequiresPermissions(value={"001003001","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Role> saveNewRole(@RequestBody Role item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增角色对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除角色对象
	 * @param ids
	 * @return
	 */
	
	@ApiOperation(value = "删除角色对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ApiImplicitParam(name = "ids", value = "角色对象IDs", required = true,allowMultiple=true, dataType = "Long")
	@ViLog(operEvent = "删除角色对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"001003002","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deleteRole(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除角色对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除角色对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改角色对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改角色对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改角色对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001003003","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Role> saveRole(@RequestBody Role item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改角色对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改角色对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "通过查询bean进行查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001003004","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<Role>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询角色对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询角色对象出错:"+e.getMessage());
		}
	}
	
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("queryComp")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<Role>> queryComp(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询角色对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询角色对象出错:"+e.getMessage());
		}
	}
	
	
	@ApiOperation(value = "通过角色Id进行查询角色数据")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
		@ApiImplicitParam(name = "id", value = "角色Id", required = true, dataType = "long")
		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001003005","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Role> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
