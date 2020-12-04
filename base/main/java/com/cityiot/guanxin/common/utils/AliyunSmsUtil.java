package com.cityiot.guanxin.common.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Created on 17/6/7. 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可) 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar 2:aliyun-java-sdk-Test111.jar
 *
 * 备注:Demo工程编码采用UTF-8 国际短信发送请勿参照此DEMO
 */
@Configuration
public class AliyunSmsUtil {

	// 产品名称:云通信短信API产品,开发者无需替换
	@Value("${aliyun.sms.product}")
	private String product ;
	// 产品域名,开发者无需替换
	@Value("${aliyun.sms.domain}")
	private String domain ;

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	@Value("${aliyun.sms.accessKeyId}")
	private String accessKeyId ;
	@Value("${aliyun.sms.accessKeySecret}")
	private String accessKeySecret ;

	/**
	 * @param aliyunSmsBuilder
	 * @throws Exception
	 */
	public  SendSmsResponse sendMsg(AliyunSmsBuilder aliyunSmsBuilder) throws Exception {


			if (StringUtils.isEmpty(aliyunSmsBuilder.getPhone())) {
				return null;
			}
			// 可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(aliyunSmsBuilder.getPhone());
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName(aliyunSmsBuilder.getTitle());
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(aliyunSmsBuilder.getMessageCode());

			// 可选:您在中山市公务用车综合管理平台的注册信息已审核通过！账号为${account}，密码默认与账号相同，也可使用手机号码登录！
			request.setTemplateParam(getMessage(aliyunSmsBuilder.getParams()));

			// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");

			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			// request.setOutId("yourOutId");

			// hint 此处可能会抛出异常，注意catch
			SendSmsResponse sendSmsResponse = null;
			try {
				sendSmsResponse = acsClient.getAcsResponse(request);
			} catch (ClientException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			return sendSmsResponse;
    }


	private String getMessage(Map<String, String> params) {
		if (params.size() == 0) {
			return null;
		}
		StringBuilder builder = new StringBuilder("{");
		for (String key : params.keySet()) {
			builder.append("\"" + key + "\":");
			builder.append("\"" + params.get(key) + "\",");
		}
		builder.append("}");
		builder.deleteCharAt(builder.length() - 2);
		return builder.toString();
	}


}