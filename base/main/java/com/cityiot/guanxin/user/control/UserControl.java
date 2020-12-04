package com.cityiot.guanxin.user.control;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.common.utils.ImportExcelUtil;
import com.cityiot.guanxin.common.utils.PasswordHelper;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.entity.dto.UserInfo;
import com.cityiot.guanxin.user.entity.vo.AllocateUserAreaVo;
import com.cityiot.guanxin.user.entity.vo.AllocateUserRoleVo;
import com.cityiot.guanxin.user.entity.vo.UserVo;
import com.cityiot.guanxin.user.service.AccountinfoService;
import com.cityiot.guanxin.user.service.UserManagerService;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.user.service.UserviewService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;

import io.swagger.annotations.Api;
import swallow.framework.exception.SwallowConstrainException;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 用户数据访问API接口
 * @author aohanhe
 *
 */
@Api(tags = "系统用户数据访问API接口")
@RestController
@RequestMapping("/api/user")
public class UserControl {
	
	private static final Logger log = LoggerFactory.getLogger(UserControl.class);

	@Autowired
	private UserService service;
	@Autowired
	private AccountinfoService accountinfoService;
	@Autowired
	private UserManagerService userManagerService;
	@Autowired
	private UserviewService userviewService;
	@Autowired
    private OperatorOrCompanyManageService operatorService;
	
	

	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{

		// 姓名
		@Like
		private String name;

		// 用户名
		@Like
		private String username;

		@Like
		private String phone;

		// 部门Id
		private Long departmentId;

		// 是否禁用
		private Integer disable;

		private Integer sex;


		private Date lastmodi;

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
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
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
		public Integer getDisable() {
			return disable;
		}

		public void setDisable(Integer disable) {
			this.disable = disable;
		}

		public Long getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}
	}
	
	
	/**
	 * 管理员修改用户密码
	 * @param type 1 系统用户  2 运维人员
	 * @param pwd
	 * @param id    系统用户ID/ 运维人员ID
	 * @return
	 */
	@ViLog(operEvent = "管理员修改用户密码", operType =2)//日志记录
	@RequestMapping("udpUserpwd")
	@RequiresPermissions(value={"001001001","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public ApiResult<OperatorOrCompanyManage> udpUserpwd(int type,String pwd,long id){
		try {
			
			
			if(type==1)
			{
				User user = service.getItemById(id);
				if(user!=null)
				{
					Accountinfo accountinfo=accountinfoService.getItemById(user.getAccountid());
				   if(accountinfo!=null)
				      {
					    accountinfo.setPassword(PasswordHelper.encryptPassword(pwd));
					    accountinfoService.updateItem(accountinfo)  ;
				     }
				
				   service.updateItem(user);
				}
				
			}
			else if(type==2)
			{
				OperatorOrCompanyManage operatorOrCompanyManage=operatorService.getItemById(id);
				if(operatorOrCompanyManage!=null)
				{
				
					Accountinfo accountinfo=accountinfoService.getItemById(operatorOrCompanyManage.getAccountid());
					if(accountinfo!=null)
					{
						accountinfo.setPassword(PasswordHelper.encryptPassword(pwd));
						accountinfoService.updateItem(accountinfo)  ;
					}
					
					operatorOrCompanyManage.setPassword(PasswordHelper.encryptPassword(pwd));
					
					operatorService.updateItem(operatorOrCompanyManage);
					
				}
			}
			
			
			return ApiResult.success("修改成功！");
		} catch (Exception e) {
			log.error("管理员修改用户密码出错:"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ApiResult.fail("管理员修改用户密码出错:"+e.getMessage());
		}
	}
	
	
	
	/**
	 * 新增一个新的用户对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "新增一个新的用户对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@RequiresPermissions(value={"001001001","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public ApiResult<User> saveNewUser(@RequestBody User item) {
		    try {
			String password = item.getPassword();
			password = PasswordHelper.encryptPassword(password);
			item.setPassword(password);
			
			User user=service.insertItem(item);
			if(user!=null)
			{
				  Accountinfo accountinfo=new Accountinfo();
				  accountinfo.setAccount(user.getUsername());
				  accountinfo.setPassword(user.getPassword());
				  accountinfo.setDid(user.getId().intValue());
				  accountinfo.setType(1);
				  int disable=user.getDisable();
				  accountinfo.setDisable(disable);
				  accountinfo.setPhone(user.getPhone());
				  accountinfoService.insertItem(accountinfo);
				  user.setAccountid(accountinfo.getId());
				  service.updateItem(user);
				  
			}
			
			
			return ApiResult.success(user);
		    } catch (Exception e) {
				log.error("新增用户出错::"+e.getMessage(),e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return ApiResult.fail("新增用户出错:"+e.getMessage());
			}
	}



	/**
	 * 批量导入新的用户对象
	 * @author Guoyingzhao
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/excel")
	@ViLog(operEvent = "批量导入新的用户对象", operType =1)//日志记录
	@RequiresPermissions(value={"001001011","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public BaseApiResult UserImportExcel(@RequestParam(value = "file") MultipartFile file) {
		log.info("----------start----------UserImportExcel");
		if (file == null) {
			return BaseApiResult.failResult(500, "请重新上传文件！");
		}
		boolean flag = true;
		String msg = "";
		try {
			// 读取内容
			InputStream in = file.getInputStream();
			Map<String, Object> objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

			@SuppressWarnings("unchecked")
			List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
			if (objList != null && objList.size() > 1) {
				for (int i = 1; i < objList.size(); i++) {
					List<Object> list = objList.get(i);
					if (list.size() == 0 ||
							StringUtils.isEmpty(list.get(0).toString()) ||
							StringUtils.isEmpty(list.get(1).toString()) ||
							StringUtils.isEmpty(list.get(2).toString()) ||
							StringUtils.isEmpty(list.get(3).toString()) ) {
						msg = StringUtils.isEmpty(msg) ? "第" + i : msg + "、" + i;
						continue;
					}
					User item = new User();
					item.setUsername(list.get(0).toString());//用户名
					String password = PasswordHelper.encryptPassword(list.get(1).toString());
					item.setPassword(password);//密码
					item.setName(list.get(2).toString());//姓名
					item.setPhone(list.get(3).toString());//电话号码
					item.setSex(Integer.parseInt(list.get(4).toString()));//性别
					item.setDisable(Integer.parseInt(list.get(5).toString()));//是否禁用
					try{
					User user=service.insertItem(item);
					if (user != null) {
						Accountinfo accountinfo = new Accountinfo();
						accountinfo.setAccount(user.getUsername());
						accountinfo.setPassword(user.getPassword());
						accountinfo.setDid(user.getId().intValue());
						accountinfo.setType(1);
						int disable = user.getDisable();
						accountinfo.setDisable(disable);
						accountinfo.setPhone(user.getPhone());
						accountinfoService.insertItem(accountinfo);
						user.setAccountid(accountinfo.getId());
						service.updateItem(user);
					}
					}catch (SwallowConstrainException e){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						msg = StringUtils.isEmpty(msg) ? "第" + i : msg + "、" + i;
					}

				}
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return BaseApiResult.failResult(500, "导入失败！" + e.getMessage());
		}
		if (!flag) {
			return BaseApiResult.failResult(500, "导入失败，找不到要导入的数据！");
		}
		if(SwallowConstrainException.class!=null){
			msg = StringUtils.isEmpty(msg) ? "" : msg + "条的数据导入失败！\t失败原因：用户名(username)和手机号码(phone)字段不能与其他用户重复！";
			return BaseApiResult.failResult(500, "导入有误，"+msg);
		}

		msg = StringUtils.isEmpty(msg) ? "" : msg + "条数据添加失败！";
		log.info("----------end----------importDeviceInfo");
		return ApiResult.success("导入成功！" + msg);
	}
	
	
	
	
	
	/**
	 * 删除用户对象
	 * @return
	 */
	@ViLog(operEvent = "删除用户对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"001001002","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public BaseApiResult deleteUser(@RequestBody long []ids) {
		try {
			for(var id:ids)
			{
				User user=service.getItemById(id);
				if(user!=null)
				{
					accountinfoService.deleteItemById(user.getAccountid());
				}
				service.deleteItemById(id);
			}
				
			return BaseApiResult.successResult();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return BaseApiResult.failResult(500, "删除用户对象失败！" + e.getMessage());
		}
	}
	
	/**
	 * 修改用户对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "修改用户对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001001003","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public ApiResult<User> saveUser(@RequestBody UserVo item){
		User user = service.getItemById(item.getId());
		try {
			///String pwd=user.getPassword();
			
			if(null==user) {
				return ApiResult.fail("要修改的用户不存在");
			}
			BeanUtils.copyProperties(item,user);
			user = service.updateItem(user);
			
			
			if(user!=null)
			{
				Accountinfo accountinfo=accountinfoService.getItemById(user.getAccountid());
				if(accountinfo!=null)
				{
					accountinfo.setAccount(user.getUsername());
					//accountinfo.setPassword(user.getPassword());
					accountinfo.setDid(user.getId().intValue());
					accountinfo.setType(1);
					int disable=user.getDisable();
					accountinfo.setDisable(disable);
					accountinfo.setPhone(user.getPhone());
					accountinfoService.updateItem(accountinfo)  ;
					
					
				}
				else
				{
					  Accountinfo accountinfo2=new Accountinfo();
					  accountinfo2.setAccount(user.getUsername());
					  accountinfo2.setPassword(user.getPassword());
					  accountinfo2.setDid(user.getId().intValue());
					  accountinfo2.setType(2);
					  int disable=user.getDisable();
					  accountinfo2.setDisable(disable);
					  accountinfo2.setPhone(user.getPhone());
					  accountinfoService.insertItem(accountinfo2);
					  user.setAccountid(accountinfo2.getId());
					  service.updateItem(user);
				}
				
			}

	   } catch (Exception e) {
		   e.printStackTrace();
		   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		   return ApiResult.fail(500, "修改用户对象失败！" + e.getMessage());//BaseApiResult.failResult(500, "修改用户对象失败！" + e.getMessage());
	    }
//		user.setPassword(null);
		return ApiResult.success(user);
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001001004","SUPERADMIN"},logical= Logical.OR)
	public ApiResult<PageListData<User>> query(@RequestBody  QueryBean query){
			PageListData<User> userPageListData = service.getAllItemPageByQuerybean(query);
			for(User user:userPageListData.getItems()) {
				user.setPassword(null);
			}
			return ApiResult.success(userPageListData);
	}

	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001001005","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<User> getItemById(@PathVariable long id){
			return ApiResult.success(service.getItemById(id));
	}

	@RequestMapping("userinfo")
	public ApiResult<UserInfo> getUserInfo() {
			return ApiResult.success(userManagerService.getUserInfoByUserId());
	}

	/** 修改用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@ViLog(operEvent = "修改用户密码", operType =2)//日志记录
	@RequestMapping("updatePassword")
	@Transactional
	public ApiResult updatePassword(String oldPassword,String newPassword) {
		
		 Subject subject = SecurityUtils.getSubject();
	     Userview userview = (Userview)subject.getPrincipals().getPrimaryPrincipal();
	     
	     String oldPassword1 = PasswordHelper.encryptPassword(oldPassword);
	     try {
	     if(userview.getType()==1)
	     {
	    	 service.updatePassword(oldPassword,newPassword);
	     }
	     else
	     {
	    	 OperatorOrCompanyManage operatorOrCompanyManage=operatorService.getItemById(userview.getDid());
	    	 
	    	 if(operatorOrCompanyManage!=null)
	    	 {
	    		 if(!oldPassword1.equals(operatorOrCompanyManage.getPassword())){
		             throw new SwallowException("原密码不正确");
		          }
	    		 operatorOrCompanyManage.setPassword(PasswordHelper.encryptPassword(newPassword));
	    		 operatorService.updateItem(operatorOrCompanyManage);
	    		 
	    	 }
	     }
			
	     Accountinfo accountinfo= accountinfoService.getItemById(userview.getId());
	     if(accountinfo!=null)
	     {
	    	 if(!oldPassword1.equals(accountinfo.getPassword())){
	             throw new SwallowException("原密码不正确");
	          }
	    	 accountinfo.setPassword(PasswordHelper.encryptPassword(newPassword));
	    	 accountinfoService.updateItem(accountinfo);
	     }
	      
			return ApiResult.success("修改密码成功");
	     } catch (Exception e) {
			   e.printStackTrace();
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			   return ApiResult.fail(500, "修改用户密码失败！" + e.getMessage());//BaseApiResult.failResult(500, "修改用户对象失败！" + e.getMessage());
		}
	}

	/** 通过用户Id获取用户所有的角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("getAllRoleByUserId")
	@RequiresPermissions(value={"001001006","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<List<Role>> getAllRoleByUserId(long userId) {
			return ApiResult.success(userManagerService.getAllRoleByUserId(userId));
	}

	/** 用户角色分配
	 * @return
	 */
	@ViLog(operEvent = "用户角色分配", operType =1)//日志记录
	@RequestMapping("allocateUserRole")
	@RequiresPermissions(value={"001001007","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult allocateUserRole(@RequestBody AllocateUserRoleVo allocateUserRoleVo) {
			userManagerService.allocateUserRole(allocateUserRoleVo.getUserId(),
					allocateUserRoleVo.getReadyGiveUpRoleIds(),
					allocateUserRoleVo.getReadyGiveRoleIds());
			return ApiResult.success("分配角色成功");
	}

	/** 通过用户Id获取用户所有的区域
	 * @param userId
	 * @return
	 */
	@RequestMapping("getAllAreaByUserId")
	@RequiresPermissions(value={"001001008","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<List<Area>> getAllAreaByUserId(long userId) {
		return ApiResult.success(userManagerService.getAllUserAreaByUserId(userId));
	}

	/** 用户区域分配
	 * @return
	 */
	@ViLog(operEvent = "用户区域分配", operType =1)//日志记录
	@RequestMapping("allocateUserArea")
	@RequiresPermissions(value={"001001009","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult allocateUserArea(@RequestBody AllocateUserAreaVo allocateUserAreaVo) {
		userManagerService.allocateUserArea(allocateUserAreaVo.getUserId(),
				allocateUserAreaVo.getReadyGiveUpAreaIds(),
				allocateUserAreaVo.getReadyGiveAreaIds());
		return ApiResult.success("分配区域成功");
	}
	
	/**
	 * 
	 * @param type 0全部 1系统2运维
	 * @return
	 */
	@RequestMapping("getUserviewItems")
	public ApiResult<List<Userview>> getUserviewItems(@RequestParam("type") int type){
		try {
			return ApiResult.success(userviewService.getUserviewByType(type));
		} catch (Exception e){
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	
	
}
