package com.cityiot.guanxin.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cityiot.guanxin.app.entity.AppVersion;
import com.cityiot.guanxin.app.service.AppVersionService;
import com.cityiot.guanxin.common.service.AliyunSmsService;
import com.cityiot.guanxin.common.service.UserCheckCodeService;
import com.cityiot.guanxin.common.smsConfig.MessageCode;
import com.cityiot.guanxin.common.smsConfig.smsConfig;
import com.cityiot.guanxin.common.utils.AliyunSmsBuilder;
import com.cityiot.guanxin.common.utils.InputCheckUtil;
import com.cityiot.guanxin.common.utils.MD5Util;
import com.cityiot.guanxin.common.utils.PasswordHelper;
import com.cityiot.guanxin.common.utils.RedisUtil;
import com.cityiot.guanxin.common.utils.Result;
import com.cityiot.guanxin.index.control.indexControl;
import com.cityiot.guanxin.response.BaseResponse;
import com.cityiot.guanxin.response.RespCode;
import com.cityiot.guanxin.response.RespData;
import com.cityiot.guanxin.service.common.UploadService;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.AccountinfoService;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.user.service.UserviewService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import swallow.framework.web.ApiResult;

/**
 * CustomerInfo控制器类
 *
 * @author ly
 */
@RestController
@Api(value = "/guanxin-api/account",tags = "app帐户登录接口")
@RequestMapping("/guanxin-api/account")
public class UserInfoController {

	private static final String DEFAULT_TOKEN_KEY = "token_";
    @Value("${nginxUrl}")
    private String nginxUrl;
    private static final Logger log = LoggerFactory.getLogger(indexControl.class);
    
    @Autowired
    DefaultSecurityManager defaultSecurityManager;
    @Autowired
    private OperatorOrCompanyManageService operatorService;
    @Autowired
    private AccountinfoService accountinfoService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private UserCheckCodeService userCheckCodeService;
    @Autowired
    private AliyunSmsService aliyunSmsService;
    @Autowired
    private UploadService uploadService;
    @Autowired
   	private UserviewService userviewService;
    @Autowired
   	private UserService userService;

    protected final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    
    
    
      
    
   
    @ApiOperation(value = "自动登录 ")
    @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String")
    @RequestMapping("autologin")
    public BaseResponse autologin(HttpServletRequest request,  String token) {
    	 RespData data = new RespData();
    	Map<String,Object> re=new HashMap<String, Object>();
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
       
        Accountinfo userInfo=accountinfoService.getUserByAPPToken(token);
      
    	    if(userInfo!=null)
    	    {
    		     UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userInfo.getAccount(),userInfo.getPassword());
    		
    		      try {
    	            subject.login(usernamePasswordToken);
    	           }catch (UnknownAccountException e){
    	        	  System.out.println("token错误");
    	              return new BaseResponse(data, RespCode.PARAM_MISSING, "token错误");
    	          }
    	           catch (IncorrectCredentialsException e){
    	        	 System.out.println("token错误");
    	              return new BaseResponse(data, RespCode.PARAM_MISSING, "token错误");
    	          }
    	              catch (LockedAccountException e){
    	        	  System.out.println("账号已被锁定，请联系管理员");
    	               return new BaseResponse(data, RespCode.PARAM_MISSING, "账号已被锁定，请联系管理员");
    	          }
    	     }
    	    else
    	    {
    		
    		    return new BaseResponse(data, RespCode.SESSION_INVALIDATE, "登录失败");
    	    }
     
    	    String newtoken =null;
             try {
        	
        	  newtoken = MD5Util.MD5(userInfo.getId()+"@@APP@@"+System.currentTimeMillis()).toUpperCase();
             userInfo.setApptoken(newtoken);
             accountinfoService.updateItem(userInfo);
             redisUtil.remove(DEFAULT_TOKEN_KEY+token);
             redisUtil.set(DEFAULT_TOKEN_KEY+newtoken, subject.getSession().getId());
          
               re.put("token", token);
              }catch (Exception e) {
        	     return new BaseResponse(data, RespCode.PARAM_MISSING, "登录成功失败");
		      }
    
        
           
             Map<String,Object> reData=new HashMap<String,Object>();
             
             
             Userview  userview = userviewService.getItemById( userInfo.getId());
             
             reData.put("type", userInfo.getType());
             reData.put("userid", userInfo.getId());
             reData.put("username", userview.getUsername());
             reData.put("headPortrait", userview.getHeadPortrait());
             reData.put("token", newtoken);
             reData.put("account", userview.getAccount());
             data.putData("userInfo", reData);   
             
            
           BaseResponse baseResponse = new BaseResponse(data);
           baseResponse.setState(RespCode.SUCCESS);
           return baseResponse;
    }


    /**
     * 用户退出当前登录
     *
     * 
     * @return
     */
  
     @ApiOperation(value = " 用户退出当前登录 ")
     @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
     @RequestMapping("/logout")
     public BaseResponse logout(HttpServletRequest request){
         Subject subject = SecurityUtils.getSubject();
         Userview loginUser = (Userview) subject.getPrincipal();
         Accountinfo acc = accountinfoService.getUserByAccount(loginUser.getAccount());
         acc.setClientId("");
         accountinfoService.updateItem(acc);
         return new BaseResponse(new RespData());
     }


    /**
     * 用户登录
     *
     * @param request
     * @param phoneNumber
     * @param password
     * @param loginType
     * @param clientId(个推标识)
     * @return
     */
    @ApiOperation(value = " 用户登录")
    @RequestMapping("/login")
    public BaseResponse login(HttpServletRequest request,@ApiParam(name="phoneNumber",value="用户帐号",required=true)  String phoneNumber,
    		@ApiParam(name="password",value="用户密码",required=true) String password, Long loginType,
    		@ApiParam(name="clientId",value="个推标识",required=true) String clientId) {
    	
    	 SecurityUtils.setSecurityManager(defaultSecurityManager);
         Subject subject = SecurityUtils.getSubject();
         
         Accountinfo user =null;
        RespData data = new RespData();

        if (loginType == null) {
            return new BaseResponse(data, RespCode.PARAM_MISSING, "请选择登录类型");
        }
        
        if (loginType == 2 && StringUtils.isEmpty(phoneNumber)) {
            return new BaseResponse(data, RespCode.PARAM_MISSING, "请输入您的手机号");
        }
        /* 校验 */
        if (loginType == 2  && new InputCheckUtil().checkPhone(phoneNumber)) {
            return new BaseResponse(data, RespCode.PARAM_MISSING, "请输入正确的手机号码");
        }
        
        if(loginType == 2)
        {
        	 //查询用户
            user = accountinfoService.getUserByPhone(phoneNumber);
        }
        else  if(loginType == 1)
        {
        	 //查询用户
            user = accountinfoService.getUserByAccount(phoneNumber);
        }
       
         
        if (user == null) {
        	if(loginType == 2)
        	{
        		 return new BaseResponse(data, RespCode.ERROR, "该手机号未注册,请先注册");
        	}
        	else
        	{
        		 return new BaseResponse(data, RespCode.ERROR, "用户不存在");
        	}
           
        }

        if (loginType == 1) { //密码登录

            if (StringUtils.isEmpty(password)) {
                return new BaseResponse(data, RespCode.PARAM_MISSING, "输入您的密码");
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                return new BaseResponse(data, RespCode.USER_NOT_PASSWORD, "您的账号还未设置密码，用验证码登录之后设置密码");
            } else if (!user.getPassword().equals(PasswordHelper.encryptPassword(password.trim()))) {
                return new BaseResponse(data, RespCode.ERROR, "您输入的密码有误，请重新输入");
            }

        } else if (loginType == 2) { //验证码登录

            if (StringUtils.isEmpty(password)) {
                return new BaseResponse(data, RespCode.PARAM_MISSING, "请输入验证码");
            }
            //String verifyCode = Sessions.getValue(request, "verifyCode");
            String verifyCode = (String)redisUtil.get("verifyCode_" + phoneNumber);
            if (verifyCode == null) {
                return new BaseResponse(data, RespCode.ERROR, "验证码已失效，请重新获取验证码");
            }

            String phone_verifyCode = phoneNumber + "|" + password;
            if (!phone_verifyCode.equals(verifyCode)) {
                return new BaseResponse(data, RespCode.ERROR, "亲，手机号或验证码不正确");
            }
        }
        
        if(user.getDisable() == 2) {
            return new BaseResponse(data, RespCode.ERROR, "你已被禁用app,请联系客服！");
        }
        
        
        
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getAccount(),PasswordHelper.encryptPassword(password.trim()));
        
        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
        	System.out.println("账号或密码错误");
        	 return new BaseResponse(data, RespCode.ERROR, "登录失败，请重试");
        }
        catch (IncorrectCredentialsException e){
        	System.out.println("账号或密码错误");
        	 return new BaseResponse(data, RespCode.ERROR, "登录失败，请重试");
        }
        catch (LockedAccountException e){
        	System.out.println("账号已被锁定，请联系管理员");
        	 return new BaseResponse(data, RespCode.ERROR, "登录失败，请重试");
        }
        
        
        String token = MD5Util.MD5(user.getId()+"@@APP@@"+System.currentTimeMillis()).toUpperCase();
        user.setApptoken(token);
        user.setClientId(clientId);//推送标识
        accountinfoService.updateItem(user);
        redisUtil.set(DEFAULT_TOKEN_KEY+token, subject.getSession().getId());
        
        Map<String,Object> reData=new HashMap<String,Object>();
        
        
        Userview  userview = userviewService.getItemById( user.getId());
        
        reData.put("type", user.getType());
        reData.put("userid", user.getId());
        reData.put("username", userview.getUsername());
        reData.put("headPortrait", userview.getHeadPortrait());
        reData.put("token", token);
        reData.put("account", user.getAccount());
        reData.put("clientId",user.getClientId());
        data.putData("userInfo", reData);

      
        
        
        BaseResponse baseResponse = new BaseResponse(data);
        baseResponse.setState(RespCode.SUCCESS);
        return baseResponse;
    }
    
    
    
    

    /**
     * 获取当前用户信息
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "获取当前用户信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("getUserInfo")
    public BaseResponse getUserInfo(HttpServletRequest request) {
        RespData data = new RespData();
        Subject subject = SecurityUtils.getSubject();
		Userview loginUser = (Userview) subject.getPrincipal();
        if(loginUser==null){
            return new BaseResponse(data, RespCode.PARAM_MISSING, "请登录后再操作");
        }
        OperatorOrCompanyManage user = operatorService.getItemById(loginUser.getDid());
        user.setPassword(null);
        data.putData("userInfo", user);
        BaseResponse baseResponse = new BaseResponse(data);
        baseResponse.setState(RespCode.SUCCESS);
        return baseResponse;
    }

    /**
     * 检查app版本
     * @param request
     * @param model
     * @param appPlatformType
     * @param appInnerVersoin
     * @param appVersion
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "检查app版本 ")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/checkUpdate")
    public BaseResponse checkUpdateList(HttpServletRequest request, Model model,
                                        Integer appPlatformType, Integer appInnerVersoin, String appVersion)
            throws Exception {
        try {
            List<AppVersion> list = null;
            if (appPlatformType == 2) {
                Result< List<AppVersion>> result = appVersionService.getItemsByAppInnerVersion(appPlatformType, appInnerVersoin, 1);
                if(result.getCode() >= 0 ){
                    list = result.getValue();
                }

            } else if (appPlatformType == 1) {
                if (appInnerVersoin != null) {
                    Result< List<AppVersion>> result = appVersionService.getItemsByAppInnerVersion(appPlatformType, appInnerVersoin, 1);
                    if(result.getCode() >= 0 ){
                        list = result.getValue();
                    }
                } else {
                    Result< List<AppVersion>> result = appVersionService.getItemsByAppVersion(appPlatformType, appVersion, 1);
                    if(result.getCode() >= 0 ){
                        list = result.getValue();
                    }
                }
            }
            int forceUpdate = 0;
            RespData data = new RespData();
            if (list.size() > 0) {
                AppVersion av = list.get(0);
                if (av.getAppVersion().equals(appVersion)) {
                    data.putData("hasNew", 0);
                } else {
                    data.putData("hasNew", 1);
                }
                if (av.getUpdateDescription() != null) {
                    data.putData("updateDescription", av.getUpdateDescription());
                }
                if (av.getAppFileUrl() != null) {
                    data.putData("appFileUrl", nginxUrl + av.getAppFileUrl());
                }
                if (av.getAppVersion() != null) {
                    data.putData("appVersion", av.getAppVersion());
                }
                if (av.getAppInnerVersoin() > 0) {
                    data.putData("appInnerVersoin", av.getAppInnerVersoin());
                }
                if (av.getAppPlatformType() > 0) {
                    data.putData("appPlatformType", av.getAppPlatformType());
                } // 判断是否强制更新如果中间跨版本中间版本有一个强制更新也为强制更新
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getForceUpdate() == 1) {
                        forceUpdate = 1;
                        data.putData("forceUpdate", forceUpdate);
                    }
                }
            } else {
                data.putData("hasNew", 0);
            }
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception x) {
            x.printStackTrace();
            RespData data = new RespData();
            return new BaseResponse(data, RespCode.ERROR, "版本检测时发生系统故障");
        }
    }

    /**
     * 头像图片保存
     * @param request
     * @param headImgUrl
     * @return
     */
    @ApiOperation(value = "头像图片保存 ")
    @RequestMapping("/uploadHeadInfo")
    public ApiResult uploadHeadInfo(HttpServletRequest request, String  headImgUrl) {
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        RespData data = new RespData();
//        Long userId = NumberUtil.toLong(request.getParameter("id"));
        if(loginUser.getType()==2)
        {
        	  Result result = operatorService.uploadHeadInfo(new Long(loginUser.getDid()), headImgUrl);
              if (result.getCode() < 0) {
//                  return new BaseResponse(data, RespCode.ERROR, "保存失败，请重试");
                  log.error("保存头像失败了");
                  return ApiResult.fail("保存头像失败了");
              }
        }
        else
        {
        	try {
        	User  user=userService.getItemById(new Long(loginUser.getDid()));
        	if(user!=null)
        	{
        		user.setHeadPortrait(headImgUrl);
        		userService.updateItem(user);
        	}
        	}catch (Exception e) {
				e.printStackTrace();
				 return ApiResult.fail("保存头像失败了");
			}
        }
      
//        BaseResponse baseResponse = new BaseResponse(data);
//        baseResponse.setState(RespCode.SUCCESS);
//        return baseResponse;
        return ApiResult.success("成功保存!");
    }


    /**
     * 发送验证码
     * @param phoneNumber
     * @return
     */
    @ApiOperation(value = "发送验证码 ")
    @RequestMapping("/getPhoneCode")
    public BaseResponse sendSmsCode(String phoneNumber) {
        RespData data = new RespData();
        try {
            if (StringUtils.isEmpty(phoneNumber)) {
                return new BaseResponse(data, RespCode.PARAM_MISSING, "请输入手机号码");
            }
            /* 校验 */
            if (new InputCheckUtil().checkPhone(phoneNumber)) {
                return new BaseResponse(data, RespCode.PARAM_MISSING, "请输入正确的电话号码");
            }
            String verifyCode = String.valueOf(Math.random()).substring(2, 8);
            this.userCheckCodeService.putPhoneCheckCode(phoneNumber, verifyCode+"");
            String phone_verifyCode = phoneNumber + "|" + verifyCode;
            redisUtil.set("verifyCode_"+phoneNumber,phone_verifyCode,60*30l);//30分钟

            AliyunSmsBuilder aliyunSmsBuilder=new AliyunSmsBuilder();
            aliyunSmsBuilder.setTitle(smsConfig.messageTitle);
            aliyunSmsBuilder.setPhone(phoneNumber);
            aliyunSmsBuilder.setMessageCode(MessageCode.jiaoyanma.toString());
            aliyunSmsBuilder.addParam("code",verifyCode);
            aliyunSmsService.sendMsg(aliyunSmsBuilder);
            data.putData("phoneNumber", phoneNumber);
            return new BaseResponse(data, RespCode.SUCCESS, "验证码发送成功");
        } catch (Exception e) {
            logger.error("发送短信验证码出错:" + e.getMessage(), e);
            return new BaseResponse(data, RespCode.ERROR, "验证码发送失败");
        }
    }


    /**
     * 上传图片
     * @param type
     * @param file
     * @return
     */
    @ApiOperation(value = "上传图片 ")
    @PostMapping("/uploadFile")
    public ApiResult<Map<String,Object>> upload(@RequestParam("type") int type,@RequestParam("file") MultipartFile file) {
        Map<String,Object> reult=new HashMap<String, Object>();
        RespData data = new RespData();
        try {
            reult.put("headPortrait",uploadService.upload(type,file));
            return ApiResult.success(reult);
//            data.putData("headPortrait", uploadService.upload(type,file));
//            return new BaseResponse(data, RespCode.SUCCESS, "上传成功");
        } catch (IOException e) {
            log.error("上传头像出错:"+e.getMessage(),e);
            return ApiResult.fail("上传头像出错:"+e.getMessage());
//            return new BaseResponse(data, RespCode.ERROR, "上传失败");
        }
    }
}