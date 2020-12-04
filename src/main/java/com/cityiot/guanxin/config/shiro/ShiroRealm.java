package com.cityiot.guanxin.config.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityiot.guanxin.common.utils.PasswordHelper;
import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.AccountinfoService;
import com.cityiot.guanxin.user.service.UserRoleRelationService;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.user.service.UserviewService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;


public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private AccountinfoService accountinfoService;
    @Autowired
   	private UserService userService;
    @Autowired
    private OperatorOrCompanyManageService operatorService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Autowired
   	private UserviewService userviewService;
    public ShiroRealm() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        this.setCredentialsMatcher(matcher);
    }

    // 授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Userview user = (Userview)principals.getPrimaryPrincipal();
       // if(user.getType()==1)
        if(1==1)
        {
       
        List<Role> roleList = userRoleRelationService.getAllRoleByUserId(user.getId());
        boolean isSuperAdmin = false;
        if(null==roleList) {
            return null;
        }
        for(Role role:roleList) {
            if(role.getName()!=null && role.getName().toUpperCase().equals("ADMIN")){
                isSuperAdmin = true;
            }
            authorizationInfo.addRole(role.getName());
        }
        List<Permission> permissionList = userService.getAllPermissionByUserId(user.getId());
       
        if(null==permissionList) {
        	// System.out.println("*********permissionList:0");
            return null;
        }
        for(Permission permission:permissionList) {
        	//System.out.println("#############:"+permission.getCode());
            authorizationInfo.addStringPermission(permission.getCode().trim());
        }
        
        if(isSuperAdmin){
            authorizationInfo.addStringPermission("SUPERADMIN");
        }
     	
        }
        return authorizationInfo;
    }

    // 认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	
    	SimpleAuthenticationInfo    authenticationInfo =null;
    	  String username =(String)token.getPrincipal();
          String password = new String((char[])token.getCredentials());
        
          Userview user = userviewService.getUserByAccount(username);
        System.out.println("wwwww");
        if(null==user) {
            throw new UnknownAccountException("用户不存在");
        }
          Accountinfo accountinfo = accountinfoService.getItemById(user.getId());
             if( accountinfo==null) {
                 throw new UnknownAccountException("用户不存在");
             }
             if(user.getDisable()!=0) {
                 throw new LockedAccountException("用户已经被锁定");
             }
             
             try {
                    authenticationInfo = new SimpleAuthenticationInfo(
                     user, // 用户
                     PasswordHelper.encryptPassword(accountinfo.getPassword()), //密码
                     ByteSource.Util.bytes("test"),//salt
                     getName()  //realm name
             );
      
             }catch (Exception e) {
            	 throw new UnknownAccountException("用户不存在");
			 }
       

         
        return authenticationInfo;
    }
}
