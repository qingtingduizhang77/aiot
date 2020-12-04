package com.cityiot.guanxin.response;

/**
 * 响应状态码
 * @author mozhi
 *
 */

public class RespCode {
	//系统级
	public static final int SESSION_INVALIDATE = -1; //session无效
	public static final int NO_AUTH = -2; //无权限操作
	public static final int ERROR =0; //系统错误
	public static final int SUCCESS =1; //请求成功
	public static final int PARAM_MISSING = 2; //缺少参数
	public static final int SMS_SEND_EROOR = 3; //验证码发送出错
	public static final int REQUEST_TIMEOUT = 4; //请求超时
	public static final int SIG_VERIFICATION_ERROR = 5; //签名验证出错
	
	//用户账号
	public static final int USER_UNREGISTERED = 10; //用户未注册
	public static final int USER_NOT_LOGGED = 11; //用户尚未登录
	public static final int USER_ACCOUNT_STATUS_ERROR = 12; //用户账号状态异常 删除或禁用
	
	public static final int USER_NOT_PASSWORD = 13;
	
	
}
