package com.cityiot.guanxin.response;

import java.util.HashMap;

/**
 * 响应数据
 * @author mozhi
 *
 */
public class RespData {
	
	private HashMap<String, Object> data = new HashMap<>();
	
	
	
	
	public void putData(String key,Object val){
		data.put(key, val);
	}
	
	public HashMap<String, Object> getData(){
		return data;
	}
}
