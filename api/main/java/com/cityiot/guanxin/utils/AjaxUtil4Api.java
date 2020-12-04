package com.cityiot.guanxin.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxUtil4Api {
	
	/** 日志对象  */
	protected static final Logger log = LoggerFactory.getLogger(AjaxUtil4Api.class);

	private static final String CODE = "code";

	/**
	 * AJAX输出
	 * 
	 * @param content 内容
	 * @param type 类型
	 */
	private static final void ajax(HttpServletResponse response, Object content, String type) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			PrintWriter writer = response.getWriter();
			writer.write(content == null ? "" : content.toString());
			writer.flush();
			writer.close();
			writer = null;
		} catch (IOException e) {
			log.error("AjaxUtil.ajax()方法异步输出数据出错.", e);
		}
	}

	/**
	 * AJAX输出文本
	 * 
	 * @param text 内容
	 */
	public static final void text(HttpServletResponse response, Object text) {
		ajax(response, text, "text/plain");
	}

	/**
	 * AJAX输出HTML
	 * 
	 * @param html 内容
	 */
	public static final void html(HttpServletResponse response, Object html) {
		ajax(response, html, "text/html");
	}

	/**
	 * AJAX输出XML
	 * 
	 * @param xml 内容
	 */
	public static final void xml(HttpServletResponse response, Object xml) {
		ajax(response, xml, "text/xml");
	}

	/**
	 * AJAX输出JSON
	 * 
	 * @param jsonString JSON字符串
	 */
	public static final void json(HttpServletResponse response, String jsonString) {
		ajax(response, jsonString, "application/json");
	}
	
	/**
	 * 输出响应结果(AJAX请求)
	 * 
	 * <pre>
	 * 	消息内容 : {"code":code}
	 * </pre>
	 * 
	 */
	public static final void ajax(HttpServletResponse response, int code) {
		JSONObject obj = new JSONObject();
		obj.put(CODE, code);
		json(response,obj.toString());
	}
	
	
	
}
