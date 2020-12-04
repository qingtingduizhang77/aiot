package com.cityiot.guanxin.control.anno;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.common.utils.MD5Util;
import com.cityiot.guanxin.common.utils.PasswordHelper;
import com.cityiot.guanxin.common.utils.RedisUtil;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.AccountinfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import swallow.framework.web.ApiResult;

@Api(value = "/api/public",tags = "后台登录控制接口")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/public")
public class LoginControl {
	 private static final Logger log = LoggerFactory.getLogger(LoginControl.class);
	private static final String DEFAULT_TOKEN_KEY = "token_";
    @Autowired
    DefaultSecurityManager defaultSecurityManager;
    @Autowired
    private AccountinfoService accountinfoService;
    @Autowired
    private RedisUtil redisUtil;
    
    @ApiIgnore
    @RequestMapping("loginFailure")
    public ApiResult loginFailure(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 用户不存在：");
                return ApiResult.fail(401,"用户名或密码错误");
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                return ApiResult.fail(401,"用户名或密码错误");
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                return ApiResult.fail(401,"验证码错误");
            } else {
                System.out.println("else -- >" + exception);
                return ApiResult.fail(401,"系统错误，请稍后再试");
            }
        }
        //会话过期设置code=499
        return ApiResult.fail(499,"请登录后操作");
    }

    
    @ApiOperation(value = "后台自动登录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
		})
    @RequestMapping("autologin")
    public ApiResult<Map<String,Object>> autologin(HttpServletRequest request,String token) {
    	
    	Map<String,Object> re=new HashMap<String, Object>();
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
       
       
    	Accountinfo accountinfo=accountinfoService.getUserByPCToken(token);
    	if(accountinfo!=null)
    	{
    		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountinfo.getAccount(),accountinfo.getPassword());
    		
    		   try {
    	            subject.login(usernamePasswordToken);
    	        }catch (UnknownAccountException e){
    	        	System.out.println("token错误");
    	            return ApiResult.fail(401,"token错误");
    	        }
    	        catch (IncorrectCredentialsException e){
    	        	System.out.println("token错误");
    	            return ApiResult.fail(401,"token错误");
    	        }
    	        catch (LockedAccountException e){
    	        	System.out.println("账号已被锁定，请联系管理员");
    	            return ApiResult.fail(401,"账号已被锁定，请联系管理员");
    	        }
    	}
    	else
    	{
    		return ApiResult.fail(401,"登录失败");
    	}
     
     
        
        try {
        	
        	 String newtoken = MD5Util.MD5(accountinfo.getId()+"@@PC@@"+System.currentTimeMillis()).toUpperCase();
        	 accountinfo.setPctoken(newtoken);
             accountinfoService.updateItem(accountinfo);
             redisUtil.remove(DEFAULT_TOKEN_KEY+token);
             redisUtil.set(DEFAULT_TOKEN_KEY+newtoken, subject.getSession().getId());
          
            re.put("token", newtoken);
        }catch (Exception e) {
        	return ApiResult.fail(401,"登录成功失败");
		}
    
        
        return ApiResult.success(re);
    }
    
    @ApiOperation(value = "帐号密码登录")
    @RequestMapping("loginByUsername")
    public ApiResult<Map<String,Object>> login(HttpServletRequest request, @RequestBody User user) {
    	
    	Map<String,Object> re=new HashMap<String, Object>();
    	System.out.println("#############loginByUsername");
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //Session session = subject.getSession();
        if(subject.isAuthenticated()){
            return ApiResult.success("登录成功");
        }
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,PasswordHelper.encryptPassword(password));
       
        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
        	System.out.println("账号或密码错误");
            return ApiResult.fail(401,"账号或密码错误");
        }
        catch (IncorrectCredentialsException e){
        	System.out.println("账号或密码错误");
            return ApiResult.fail(401,"账号或密码错误");
        }
        catch (LockedAccountException e){
        	System.out.println("账号已被锁定，请联系管理员");
            return ApiResult.fail(401,"账号已被锁定，请联系管理员");
        }
        
        try {
        	
        	Userview loginUser = (Userview) subject.getPrincipal();
        	Accountinfo accountinfo=accountinfoService.getItemById(loginUser.getId());
        	String token = MD5Util.MD5(accountinfo.getId()+"@@PC@@"+System.currentTimeMillis()).toUpperCase();
        	String oldtoken = accountinfo.getPctoken();
        	if(oldtoken!=null && !oldtoken.equals(""))
        	{
        		 redisUtil.remove(DEFAULT_TOKEN_KEY+oldtoken);
        	}
        	accountinfo.setPctoken(token);
            accountinfoService.updateItem(accountinfo);
            redisUtil.set(DEFAULT_TOKEN_KEY+token, subject.getSession().getId());
          
            re.put("token", token);
            
        
//            log.trace("trace登录信息:用户【"+accountinfo.getAccount()+"】登录时间【"+new Date()+"】");
//            log.error("error登录信息:用户【"+accountinfo.getAccount()+"】登录时间【"+new Date()+"】");
//            log.info("info登录信息:用户【"+accountinfo.getAccount()+"】登录时间【"+new Date()+"】");
//            log.warn("warn登录信息:用户【"+accountinfo.getAccount()+"】登录时间【"+new Date()+"】");
//            log.debug("debug登录信息:用户【"+accountinfo.getAccount()+"】登录时间【"+new Date()+"】");
            
        }catch (Exception e) {
        	  e.printStackTrace();
        	 return ApiResult.fail(401,"账号或密码错误");
		}
    
        
        return ApiResult.success(re);
    }

    
    @ApiOperation(value = "登出")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("loginout")
    public ApiResult loginout() {
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
        	Userview loginUser = (Userview) subject.getPrincipal();
        	 subject.logout();
        	Accountinfo accountinfo=accountinfoService.getItemById(loginUser.getId());
        	if(accountinfo!=null && accountinfo.getPctoken()!=null)
        	{
        		 redisUtil.remove(DEFAULT_TOKEN_KEY+accountinfo.getPctoken());
        		 accountinfo.setPctoken(null);
                 accountinfoService.updateItem(accountinfo);
        	}
           
        }
        return ApiResult.success("退出登录成功");
    }

    @ApiIgnore
    @RequestMapping("checkLoginStatus")
    public ApiResult checkLoginStatus() {
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            return ApiResult.success("已登录");
        }
        return ApiResult.fail(401,"未登录，请登录后操作");
    }
    
    //错误页面展示
    @ApiIgnore
    @RequestMapping("403")
    public ApiResult error(){
    	 return ApiResult.fail(401,"未登录，请登录后操作");
    }
    
    
}
