package swallow.framework.web;



import java.io.Serializable;

import org.springframework.util.Assert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.exception.SwallowException;


/**
 * Api基础返回结果集
 * @author aohanhe
 *
 */
@ApiModel(value="基础结果集",description="带有执行代码（0表示成功）,错误信息")
public class BaseApiResult implements Serializable{
	private static final long serialVersionUID = -1776419335624861702L;
	
	@ApiModelProperty(name="返回状态码",value="返回状态码,0 表示成功")
	private int code= 0;
	@ApiModelProperty(name="错误消息",value="返回状态消息")
	private String  message;
	
	public BaseApiResult() {
		
	}
	
	public BaseApiResult(int code,String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 构造一个失败结果
	 * @param code
	 * @param message
	 * @return
	 * @throws ScanElectricityException 
	 */
	public static BaseApiResult failResult(int code,String message){		
		Assert.isTrue(code>0,"失败结果不能设置code为0");
		
		return new BaseApiResult(code,message);
	}
	
	/**
	 * 构造一个成功结果
	 * @param data
	 * @return
	 */
	public static BaseApiResult successResult() {
		BaseApiResult re=new BaseApiResult(0, "成功");
		
		return re;
	}
	
	/**
	 * 结果是否为成功
	 * @param result
	 * @return
	 */
	public static boolean isSuccess(BaseApiResult result) {
		return result.code==0;
	}
	
	/**
	 * 检查结果是否为成功，如果不成功，则抛出错误
	 */
	public void AssertSuccess() throws SwallowException {
		if(this.code!=0) {
			throw new SwallowException("接口调用失败，返回结果为不成功:"+this.getMessage());
		}		
	}

}
