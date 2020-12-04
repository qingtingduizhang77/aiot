package com.cityiot.guanxin.config.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import swallow.framework.exception.SwallowException;
import swallow.framework.web.ApiResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SpringHandlerExceptionResolver implements HandlerExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(SpringHandlerExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("系统异常：",ex);
        ModelAndView mv = new ModelAndView();
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResult apiResult = ApiResult.fail("系统错误，请稍后再试");
        String json = null;
        if(ex instanceof UnauthenticatedException) {
            apiResult = ApiResult.fail("未登录无法进行此操作");
        }
        else if(ex instanceof UnauthorizedException ||ex instanceof  AuthorizationException) {
            apiResult = ApiResult.fail("未授权无法进行此操作");
        }else if(ex instanceof SwallowException){
            apiResult = ApiResult.fail(ex.getMessage());
        }
        try {
            json = objectMapper.writeValueAsString(apiResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }
}
