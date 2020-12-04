package com.cityiot.guanxin.config.shiro;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

/**
 * 拦截器
 */
public class AuthMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {


    public AuthMethodInterceptor() {
        super(new AuthHandler());
    }

    public AuthMethodInterceptor(AnnotationResolver resolver) {
        super(new AuthHandler(), resolver);
    }

    @Override
    public void assertAuthorized(MethodInvocation mi) throws AuthorizationException {
        // 验证权限
        try {
            ((AuthHandler) this.getHandler()).assertAuthorized(getAnnotation(mi));
        } catch (AuthorizationException ae) {
            if (ae.getCause() == null) {
                ae.initCause(new AuthorizationException("当前的方法没有通过鉴权: " + mi.getMethod()));
            }
            throw ae;
        }
    }
}
