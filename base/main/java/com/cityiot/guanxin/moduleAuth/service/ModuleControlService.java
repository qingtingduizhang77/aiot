package com.cityiot.guanxin.moduleAuth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityiot.guanxin.common.service.JDBCDaoImp;
import com.cityiot.guanxin.moduleAuth.entity.ModuleAuth;
import com.cityiot.guanxin.moduleAuth.entity.ModuleAuthMumber;
import com.cityiot.guanxin.moduleAuth.entity.QModuleAuth;
import com.cityiot.guanxin.moduleAuth.entity.QModuleAuthMumber;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.role.service.RoleService;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserviewService;

import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

@Service
public class ModuleControlService {

	
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(ModuleControlService.class);
	
	@Autowired
	private ModuleAuthService moduleAuthService;
	
	
	@Autowired
	private ModuleAuthMumberService moduleAuthMumberService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserviewService userviewService;
	
	
	@Autowired
	private JDBCDaoImp jdbcDaoImp;
	
	
	
	@Transactional
	public boolean addModuleAuth(ModuleAuth moduleAuth)
	{
		String roleId=moduleAuth.getRoleId();
		String userId=moduleAuth.getUserId();
		moduleAuth =moduleAuthService.insertItem(moduleAuth);
		
		if(roleId!=null && !roleId.equals("") )
		{
			String[] roles=roleId.split(",");
			for(String rId:roles)
			{
				Role role=roleService.getItemById(new Long(rId.trim()));
				if(role!=null)
				{
					ModuleAuthMumber moduleAuthMumber=new ModuleAuthMumber();
					moduleAuthMumber.setMemberId(role.getId());
					moduleAuthMumber.setMemberName(role.getCnName());
					moduleAuthMumber.setMemberType(1);
					moduleAuthMumber.setMoAuthId(moduleAuth.getId());
					moduleAuthMumberService.insertItem(moduleAuthMumber);
				}
				
			}
			
		}
		
		
		if(userId!=null && !userId.equals("") )
		{
			
			String[] users=userId.split(",");
			for(String uId:users)
			{
				Userview userview=userviewService.getItemById(new Long(uId.trim()));
				
				if(userview!=null)
				{
					ModuleAuthMumber moduleAuthMumber=new ModuleAuthMumber();
					moduleAuthMumber.setMemberId(userview.getId());
					moduleAuthMumber.setMemberName(userview.getUsername());
					moduleAuthMumber.setMemberType(2);
					moduleAuthMumber.setMoAuthId(moduleAuth.getId());
					moduleAuthMumberService.insertItem(moduleAuthMumber);
				}
				
			}
			
		}
		
		return true;
	}
	
	
	@Transactional
	public boolean updModuleAuth(ModuleAuth moduleAuth)
	{
		String roleId=moduleAuth.getRoleId();
		String userId=moduleAuth.getUserId();
		moduleAuth =moduleAuthService.updateItem(moduleAuth);
		
		
		List<String> sqlList=new ArrayList<String>();
		Long mId=moduleAuth.getId();
		
		if(roleId!=null && !roleId.equals("") )
		{
			
			StringBuffer delSql=new StringBuffer("DELETE  FROM module_auth_mumber WHERE MO_AUTH_ID="+moduleAuth.getId()+" AND MEMBER_TYPE=1  ");
			String[] roles=roleId.split(",");
			for(String rId:roles)
			{
				Role role=roleService.getItemById(new Long(rId.trim()));
				if(role!=null)
				{
					delSql.append("  AND MEMBER_ID!="+role.getId()+" ");
					QModuleAuthMumber qmoduleAuthMumber=QModuleAuthMumber.moduleAuthMumber;
					ModuleAuthMumber moduleAuthMumber=moduleAuthMumberService.getItem(qu->
					    qu.where(qmoduleAuthMumber.memberId.eq(role.getId()).and(qmoduleAuthMumber.memberType.eq(1)).and(qmoduleAuthMumber.memberId.eq(mId))));
					if(moduleAuthMumber==null)
					{
						 moduleAuthMumber=new ModuleAuthMumber();
						 moduleAuthMumber.setMemberId(role.getId());
						 moduleAuthMumber.setMemberName(role.getCnName());
						 moduleAuthMumber.setMemberType(1);
						 moduleAuthMumber.setMoAuthId(moduleAuth.getId());
						 moduleAuthMumberService.insertItem(moduleAuthMumber);
					}
					
				}
				
			}
			
			sqlList.add(delSql.toString());
		}
		else
		{
			sqlList.add("DELETE  FROM module_auth_mumber WHERE MO_AUTH_ID="+moduleAuth.getId()+" AND MEMBER_TYPE=1");
		}
		
		if(userId!=null && !userId.equals("") )
		{
			StringBuffer delSql=new StringBuffer("DELETE  FROM module_auth_mumber WHERE MO_AUTH_ID="+moduleAuth.getId()+" AND MEMBER_TYPE=2  ");
			
			
			String[] users=userId.split(",");
			for(String uId:users)
			{
				Userview userview=userviewService.getItemById(new Long(uId.trim()));
				
				if(userview!=null)
				{
					delSql.append("  AND MEMBER_ID!="+userview.getId()+" ");
					QModuleAuthMumber qmoduleAuthMumber=QModuleAuthMumber.moduleAuthMumber;
					ModuleAuthMumber moduleAuthMumber=moduleAuthMumberService.getItem(qu->
				    qu.where(qmoduleAuthMumber.memberId.eq(userview.getId()).and(qmoduleAuthMumber.memberType.eq(2)).and(qmoduleAuthMumber.memberId.eq(mId))));
					
					if(moduleAuthMumber==null)
					{
						moduleAuthMumber=new ModuleAuthMumber();
						moduleAuthMumber.setMemberId(userview.getId());
						moduleAuthMumber.setMemberName(userview.getUsername());
						moduleAuthMumber.setMemberType(2);
						moduleAuthMumber.setMoAuthId(moduleAuth.getId());
						moduleAuthMumberService.insertItem(moduleAuthMumber);
					}
					
				}
				
			}
			
			sqlList.add(delSql.toString());
			
		}
		else {
			sqlList.add("DELETE  FROM module_auth_mumber WHERE MO_AUTH_ID="+moduleAuth.getId()+" AND MEMBER_TYPE=2  ");
		}
		
		
		if(sqlList.size()>0)
		{
			jdbcDaoImp.batchExecuteSql(sqlList);
		}
		
		return true;
	}
	
	
	public PageListData<ModuleAuth> getModuleAuthList(BasePageQueryBean queryBean)
	{
		if(queryBean.getSorts()==null || queryBean.getSorts().length<=0)
		{
			queryBean.setSorts(new String[] {"+name"});
		}
		PageListData<ModuleAuth> pageList=moduleAuthService.getAllItemPageByQuerybean(queryBean);
		
		if(pageList!=null && pageList.getItems()!=null && pageList.getItems().size()>0)
		{
			for(ModuleAuth ma:pageList.getItems())
			{
				String usersql="SELECT  GROUP_CONCAT(MEMBER_ID) AS use_id,GROUP_CONCAT(MEMBER_NAME SEPARATOR '#&#') AS user_name FROM   module_auth_mumber  WHERE MO_AUTH_ID=? AND MEMBER_TYPE=2";
			    List<Map<String,Object>> userlist=this.jdbcDaoImp.queryForMap(usersql, new Object[] {ma.getId()});
			    if(userlist!=null && userlist.size()>0 && userlist.get(0).get("use_id")!=null)
			    {
			    	ma.setUserId(userlist.get(0).get("use_id").toString());
			    	
			    	ma.setUserName(userlist.get(0).get("user_name").toString());
			    }
				
				
				String rolesql="SELECT  GROUP_CONCAT(MEMBER_ID) AS use_id,GROUP_CONCAT(MEMBER_NAME SEPARATOR '#&#') AS user_name FROM   module_auth_mumber  WHERE MO_AUTH_ID=? AND  MEMBER_TYPE=1";
				List<Map<String,Object>> rolelist=this.jdbcDaoImp.queryForMap(rolesql, new Object[] {ma.getId()});
				 if(rolelist!=null && rolelist.size()>0 && rolelist.get(0).get("use_id")!=null)
				    {
				    	ma.setRoleId(rolelist.get(0).get("use_id").toString());
				    	
				    	ma.setRoleName(rolelist.get(0).get("user_name").toString());
				    }
					
			}
		}
		
		return pageList;
	}
	
	public ModuleAuth getModuleAuthInfoById(Long id)
	{
		ModuleAuth ma=moduleAuthService.getItemById(id);
		
		if(ma!=null)
		{
			String usersql="SELECT  GROUP_CONCAT(MEMBER_ID) AS use_id,GROUP_CONCAT(MEMBER_NAME SEPARATOR '#&#') AS user_name FROM   module_auth_mumber  WHERE MO_AUTH_ID=? AND MEMBER_TYPE=2";
		    List<Map<String,Object>> userlist=this.jdbcDaoImp.queryForMap(usersql, new Object[] {ma.getId()});
		    if(userlist!=null && userlist.size()>0 && userlist.get(0).get("use_id")!=null)
		    {
		    	ma.setUserId(userlist.get(0).get("use_id").toString());
		    	
		    	ma.setUserName(userlist.get(0).get("user_name").toString());
		    }
			
			
			String rolesql="SELECT  GROUP_CONCAT(MEMBER_ID) AS use_id,GROUP_CONCAT(MEMBER_NAME SEPARATOR '#&#') AS user_name FROM   module_auth_mumber  WHERE MO_AUTH_ID= ? AND MEMBER_TYPE=1";
			List<Map<String,Object>> rolelist=this.jdbcDaoImp.queryForMap(rolesql, new Object[] {ma.getId()});
			 if(rolelist!=null && rolelist.size()>0 && rolelist.get(0).get("use_id")!=null)
			    {
			    	ma.setRoleId(rolelist.get(0).get("use_id").toString());
			    	
			    	ma.setRoleName(rolelist.get(0).get("user_name").toString());
			    }
		}
		
		return ma;
	}
	
	
	
	public ModuleAuth getModuleAuthInfoByCode(String code)
	{
		
		QModuleAuth qmoduleAuth=QModuleAuth.moduleAuth;
		ModuleAuth ma=moduleAuthService.getItem(qu->qu.where(qmoduleAuth.moCode.eq(code)));
		
		if(ma!=null)
		{
			String usersql="SELECT  GROUP_CONCAT(MEMBER_ID) AS use_id,GROUP_CONCAT(MEMBER_NAME SEPARATOR '#&#') AS user_name FROM   module_auth_mumber  WHERE MO_AUTH_ID=? AND MEMBER_TYPE=2";
		    List<Map<String,Object>> userlist=this.jdbcDaoImp.queryForMap(usersql, new Object[] {ma.getId()});
		    if(userlist!=null && userlist.size()>0 && userlist.get(0).get("use_id")!=null)
		    {
		    	ma.setUserId(userlist.get(0).get("use_id").toString());
		    	
		    	ma.setUserName(userlist.get(0).get("user_name").toString());
		    }
			
			
			String rolesql="SELECT  GROUP_CONCAT(MEMBER_ID) AS use_id,GROUP_CONCAT(MEMBER_NAME SEPARATOR '#&#') AS user_name FROM   module_auth_mumber  WHERE MO_AUTH_ID=? AND MEMBER_TYPE=1";
			List<Map<String,Object>> rolelist=this.jdbcDaoImp.queryForMap(rolesql, new Object[] {ma.getId()});
			 if(rolelist!=null && rolelist.size()>0 && rolelist.get(0).get("use_id")!=null)
			    {
			    	ma.setRoleId(rolelist.get(0).get("use_id").toString());
			    	
			    	ma.setRoleName(rolelist.get(0).get("user_name").toString());
			    }
		}
		
		return ma;
	}
	
	
	
	public boolean isAuth(long moduleCode,long userId)
	{
		
		StringBuffer  sql=new StringBuffer("SELECT COUNT(m.id) FROM  module_auth m WHERE m.MO_CODE=? ");
		
		sql.append("  AND( m.id IN(SELECT b.MO_AUTH_ID FROM module_auth_mumber b WHERE b.MEMBER_TYPE=2 AND b.MEMBER_ID=?  ) ");
		sql.append(" OR  m.id IN(SELECT b1.MO_AUTH_ID FROM module_auth_mumber b1 WHERE b1.MEMBER_TYPE=1");
		sql.append("  AND b1.MEMBER_ID IN(SELECT r.role_id FROM user_role_relation r WHERE r.user_id=?)))");
		Long count=this.jdbcDaoImp.queryForObject(Long.class, sql.toString(), new Object[] {moduleCode,userId,userId});
		if(count!=null && count>0)
		{
			return true;
		}
		return false;
	}
	
	
}
