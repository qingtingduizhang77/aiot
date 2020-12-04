package com.cityiot.guanxin.control.anno;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/anno",tags = "是否登录接口")
@RequestMapping("/anno")
public class AnnoControl {
	
	 @ApiOperation(value = " 是否登录")
	 @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	 @RequestMapping("islogin")
	 public Map<String,Object> islogin(HttpServletRequest request) {
  	   Map<String,Object> re=new HashMap<String,Object>();
  	   re.put("code", 1);
  	   re.put("message", "已登录");
  	   return re;
  	   
    }

}
