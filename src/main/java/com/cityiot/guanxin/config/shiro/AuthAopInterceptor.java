package com.cityiot.guanxin.config.shiro;

import org.apache.shiro.authz.aop.PermissionAnnotationMethodInterceptor;
import org.apache.shiro.spring.aop.SpringAnnotationResolver;
import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;

/**
 * shiro的aop切面
 */
public class AuthAopInterceptor extends AopAllianceAnnotationsAuthorizingMethodInterceptor {
    public AuthAopInterceptor() {
        super();
        // 添加自定义的注解拦截器
        this.methodInterceptors.removeIf(item -> {
            return item.getClass() == PermissionAnnotationMethodInterceptor.class;
        });
        this.methodInterceptors.add(new AuthMethodInterceptor(new SpringAnnotationResolver()));
    }
}