package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.control;


import java.util.Date;
import java.util.List;

import com.cityiot.guanxin.log.annotation.ViLog;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.service.AccountinfoService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.Vo.IdCardVo;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;

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
 * 运维人员/公司管理数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "运维人员/公司管理数据访问API接口")
@RequestMapping("/api/operatorOrCompanyManage")
public class OperatorOrCompanyManageControl {
	
	private static final Logger log = LoggerFactory.getLogger(OperatorOrCompanyManageControl.class);

	@Autowired
	private AccountinfoService accountinfoService;
	@Autowired
	private OperatorOrCompanyManageService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="运维人员查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 账户
		@ApiModelProperty(value="帐号",name="account",example="")
		@Like
		private String account;

		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		@ApiModelProperty(value="姓名",name="gender",example="")
		@Like
		private String name;

		@ApiModelProperty(value="性别",name="gender",example="1男2女")
		private Integer gender;

		@ApiModelProperty(value="联系电话",name="phone",example="")
		@Like
		private String phone;

		@ApiModelProperty(value="在岗状态",name="status",example="")
		private Integer status;

		@ApiModelProperty(value="禁用状态",name="disable",example="1启动2禁用")
		private  Integer disable;

		@ApiModelProperty(value="类型",name="operatorType",example="1员工2公司")
		private Integer operatorType;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getGender() {
			return gender;
		}

		public void setGender(Integer gender) {
			this.gender = gender;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getDisable() {
			return disable;
		}

		public void setDisable(Integer disable) {
			this.disable = disable;
		}

		public Integer getOperatorType() {
			return operatorType;
		}

		public void setOperatorType(Integer operatorType) {
			this.operatorType = operatorType;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}
	}
	
	/**
	 * 新增一个新的运维人员/公司管理对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004007001","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "新增一个新的运维人员/公司管理对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的运维人员/公司管理对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@Transactional
	public ApiResult<OperatorOrCompanyManage> saveNewOperatorOrCompanyManage(@RequestBody IdCardVo item) {
		try {
			
			OperatorOrCompanyManage operatorOrCompanyManage=service.insertItem(item.getEntity(),item.getPositiveImages(),item.getNegativeImages());
			if(operatorOrCompanyManage!=null)
			{
			  Accountinfo accountinfo=new Accountinfo();
			  accountinfo.setAccount(operatorOrCompanyManage.getAccount());
			 
			  accountinfo.setPassword(operatorOrCompanyManage.getPassword());
			  
			  accountinfo.setDid(operatorOrCompanyManage.getId().intValue());
			  accountinfo.setType(2);
			  int disable=operatorOrCompanyManage.getDisable()==2?1:0;
			  accountinfo.setDisable(disable);
			  accountinfo.setPhone(operatorOrCompanyManage.getPhone());
			  accountinfoService.insertItem(accountinfo);
			  operatorOrCompanyManage.setAccountid(accountinfo.getId());
			  service.updateItem(operatorOrCompanyManage);
			  
			}
			return ApiResult.success(operatorOrCompanyManage);
		} catch (SwallowException e) {
			log.error("新增运维人员/公司管理对象出错:"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除运维人员/公司管理对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004007002","SUPERADMIN"}, logical= Logical.OR)
	 @ApiOperation(value = "删除运维人员/公司管理对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "运维人员/公司IDs数组", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除运维人员/公司管理对象", operType =3)//日志记录
	@DeleteMapping
	@Transactional
	public BaseApiResult deleteOperatorOrCompanyManage(@RequestBody long []ids) {
		try {
			for(var id:ids)
			{
				OperatorOrCompanyManage operatorOrCompanyManage=service.getItemById(id);
				if(operatorOrCompanyManage!=null)
				{
					accountinfoService.deleteItemById(operatorOrCompanyManage.getAccountid());
				}
				
				service.deleteItemById(id);
			}
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除运维人员/公司管理对象出错:"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return BaseApiResult.failResult(500,"删除运维人员/公司管理对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改运维人员/公司管理对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004007003","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = " 修改运维人员/公司管理对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改运维人员/公司管理对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@Transactional
	public ApiResult<OperatorOrCompanyManage> saveOperatorOrCompanyManage(@RequestBody IdCardVo item){
		OperatorOrCompanyManage operatorOrCompanyManage = null;
		try {
			OperatorOrCompanyManage userQuery = service.getItemById(item.getEntity().getId());
			if(null == userQuery){
				return ApiResult.fail("要修改的用户不存在");
			}
			item.getEntity().setPassword(userQuery.getPassword()); // 将查询得到的密码设置进去，前端不用设置
			operatorOrCompanyManage=service.updateItem(item.getEntity()
					,item.getPositiveImages()
					,item.getDeletePositiveImages()
					,item.getNegativeImages()
					,item.getDeleteNegativeImages());
			
			if(operatorOrCompanyManage!=null)
			{
				Accountinfo accountinfo=accountinfoService.getItemById(operatorOrCompanyManage.getAccountid());
				if(accountinfo!=null)
				{
					accountinfo.setAccount(operatorOrCompanyManage.getAccount());
					//accountinfo.setPassword(operatorOrCompanyManage.getPassword());
					accountinfo.setDid(operatorOrCompanyManage.getId().intValue());
					accountinfo.setType(2);
					int disable=operatorOrCompanyManage.getDisable()==2?1:0;
					accountinfo.setDisable(disable);
					accountinfo.setPhone(operatorOrCompanyManage.getPhone());
					accountinfoService.updateItem(accountinfo)  ;
				}
				else
				{
					  Accountinfo accountinfo2=new Accountinfo();
					  accountinfo2.setAccount(operatorOrCompanyManage.getAccount());
					  accountinfo2.setPassword(operatorOrCompanyManage.getPassword());
					  accountinfo2.setDid(operatorOrCompanyManage.getId().intValue());
					  accountinfo2.setType(2);
					  int disable=operatorOrCompanyManage.getDisable()==2?1:0;
					  accountinfo2.setDisable(disable);
					  accountinfo2.setPhone(operatorOrCompanyManage.getPhone());
					  accountinfoService.insertItem(accountinfo2);
					  operatorOrCompanyManage.setAccountid(accountinfo2.getId());
					  service.updateItem(operatorOrCompanyManage);
				}
			}

		} catch (SwallowException e) {
			log.error("修改运维人员出错:"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ApiResult.fail("修改运维人员出错:"+e.getMessage());
		}
		if(null!=operatorOrCompanyManage){
			// 成功
//			operatorOrCompanyManage.setPassword(null);
			return ApiResult.success(operatorOrCompanyManage);
		}else{
			return ApiResult.fail("修改运维人员出错");
		}

	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@RequiresPermissions(value={"004007004","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = " 通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<OperatorOrCompanyManage>> query(@RequestBody  QueryBean query){
		try {			
			var item = service.getAllItemPageByQuerybean(query);

			if(item !=null){
				for(var items:item.getItems()){
					items.setPassword(null);
				}
			}
			return ApiResult.success(item);
		} catch (Exception e) {
			log.error("查询运维人员/公司管理对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询运维人员/公司管理对象出错:"+e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据id取运维人员/公司信息")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "运维人员/公司Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<OperatorOrCompanyManage> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}


	@ApiOperation(value = "不错所有的运维人员/公司信息列表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@RequestMapping("getAllItems")
	public ApiResult<List<OperatorOrCompanyManage>> getAllItems(){
		try {
			return ApiResult.success(service.getAllItems(query -> query));
		} catch (Exception e){
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
