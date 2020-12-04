package com.cityiot.guanxin.serviceClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceClientUtil {
	
	
	 @Value("${socketconfig.socketPort}")
	 private String socketPort;
	 
	 @Value("${socketconfig.socketUrl}")
	 private String socketUrl;
	

	/**

 一、关灯
1.参数类型：JSon字符串
2.格式：{"function":"deviceControl","type":"closelamp","appcode":"yameida","cid":"5004"}
3.成功返回值：{\"Code\":0,"Msg":"通讯链路中断","cid":"920","Result":"成功","Command":"手动单灯开启"}
4.失败返回值：{\"Code\":1,"Msg":"通讯链路中断","cid":"920","Result":"失败","Command":"手动单灯开启"}

 一、开灯
1.参数类型：JSon字符串
2.格式：{"function":"deviceControl","type":"openlamp","appcode":"yameida","cid":"5004"}
3.成功返回值：{\"Code\":0,"Msg":"通讯链路中断","cid":"920","Result":"成功","Command":"手动单灯关闭"}
4.失败返回值：{\"Code\":1,"Msg":"通讯链路中断","cid":"920","Result":"失败","Command":"手动单灯关闭"}

	 **/

	/**
	 * 统一调用服务接口
	 * @param param
	 * @return
	 */
	public  String csExtend(String param) {
		String jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-ReturnIsNULL"));
		Socket socket = null;
		OutputStream os = null;
		PrintWriter pw = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			String mqsUrl =socketUrl;
			String mqsPort = socketPort;
			//1.创建客户端Socket，指定服务器地址和端口
			socket = new Socket(mqsUrl, new Integer(mqsPort.trim()));
			//2.获取输出流，向服务器端发送信息
			os = socket.getOutputStream();//字节输出流
			pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"),true);//将输出流包装为打印流
			pw.write(param);
			pw.flush();
			socket.shutdownOutput();//关闭输出流
			//3.获取输入流，并读取服务器端的响应信息
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String info = null;
			while((info=br.readLine())!=null) {
				jsonResult =info; 
			}
			
		} catch (ConnectException e) {
			e.printStackTrace();
			jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-ConnectException.ServiceClientUtil"));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-Exception.ServiceClientUtil"));
		} finally {
			//关闭资源
			try {
				if(br!=null)
					br.close();
				if(is!=null)
					is.close();
				if(pw!=null)
					pw.close();
				if(os!=null)
					os.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-IOException.ServiceClientUtil"));
			}
		}
		return jsonResult;
	}

}
