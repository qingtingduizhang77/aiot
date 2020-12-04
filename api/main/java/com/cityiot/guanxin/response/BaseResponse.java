package com.cityiot.guanxin.response;

import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * 基础响应对象
 * 
 * @author ZHIFEN
 */
public class BaseResponse {

	private int state = RespCode.SUCCESS; //状态码
	private String msg = ""; //操作反馈文本，操作失败时必需
	public int SUCCESS=1;
	long  systemTime  = (new Date()).getTime();
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private HashMap<String, Object> data = new HashMap<>(); //数据
	
	public BaseResponse(RespData respData) {
		super();
		this.data = respData.getData();
		if(this.state == 1&& StringUtils.isEmpty(msg)){
			this.msg = "操作成功";
		}
	}
	public BaseResponse(RespData respData,int state,String msg) {
		super();
		this.data = respData.getData();
		this.msg=msg;
		this.state=state;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	public long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(long systemTime) {
		this.systemTime = systemTime;
	}

	
	

}
