package com.cityiot.guanxin.user.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.user.entity.RolePermissionRelation;
import com.cityiot.guanxin.user.entity.vo.PermissionOfRoleVo;
import com.cityiot.guanxin.user.service.RolePermissionRelationService;

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
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.List;

/**
 * 角色和权限的关系数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "角色和权限的关系数据访问API接口")
@RequestMapping("/api/rolePermissionRelation")
public class RolePermissionRelationControl {
	
	private static final Logger log = LoggerFactory.getLogger(RolePermissionRelationControl.class);

	@Autowired
	private RolePermissionRelationService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value=" 角色和权限查询对象")
	static class QueryBean extends BasePageQueryBean{

		// roleId
		@ApiModelProperty(value="角色id",name="roleId",example="")
		private Long roleId;

		// permissionId
		@ApiModelProperty(value="权限id",name="permissionId",example="")
		private Long permissionId;

		public Long getRoleId() {
			return roleId;
		}

		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}
		public Long getPermissionId() {
			return permissionId;
		}

		public void setPermissionId(Long permissionId) {
			this.permissionId = permissionId;
		}
	}
	
	/**
	 * 新增一个新的角色和权限的关系对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的角色和权限的关系对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的角色和权限的关系对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@RequiresPermissions(value={"001003006","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<RolePermissionRelation> saveNewRolePermissionRelation(@RequestBody RolePermissionRelation item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增角色和权限的关系对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除角色和权限的关系对象
	 * @param ids
	 * @return
	 */
	 @ApiOperation(value = "删除角色和权限的关系对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "角色和权限IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除角色和权限的关系对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"001003007","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deleteRolePermissionRelation(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除角色和权限的关系对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除角色和权限的关系对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改角色和权限的关系对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改角色和权限的关系对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改角色和权限的关系对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001003008","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<RolePermissionRelation> saveRolePermissionRelation(@RequestBody RolePermissionRelation item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改角色和权限的关系对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改角色和权限的关系对象出错:"+e.getMessage());
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
	@RequiresPermissions(value={"001003009","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<RolePermissionRelation>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询角色和权限的关系对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询角色和权限的关系对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取角色和权限信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "角色和权限Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001003010","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<RolePermissionRelation> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	 @ApiOperation(value = "根据角色id取角色和权限信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "roleId", value = "角色Id", required = true, dataType = "long")
	  		})
	@RequestMapping("allPermissionByRoleId/{roleId}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001003011","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PermissionOfRoleVo> getAllItemByRoleId(@PathVariable long roleId) {
	return ApiResult.success(service.getAllPermissionfRoleByRoleId(roleId));
	}
	/**
	 * 批量新增一个角色权限关系对象
	 * @param items
	 * @return
	 */
	@ApiOperation(value = "批量新增一个角色权限关系对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "批量新增一个角色权限关系对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PostMapping("newItems")
	@RequiresPermissions(value={"001003012","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult saveNewERolesRightItemAllocation(@RequestBody List<RolePermissionRelation> items) {
			return ApiResult.success(service.saveAllRolesRightItemAllocation(items));
	}
}
