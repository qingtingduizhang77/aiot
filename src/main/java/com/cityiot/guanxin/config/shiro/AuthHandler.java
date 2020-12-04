package com.cityiot.guanxin.config.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;

/**
 * Auth注解的操作类
 */
public class AuthHandler extends AuthorizingAnnotationHandler {

    public AuthHandler() {
        //写入注解
        super(RequiresPermissions.class);
    }

    // 默认是OR关系
    @Override
    public void assertAuthorized(Annotation a) throws AuthorizationException {
        if (a instanceof RequiresPermissions) {
            RequiresPermissions annotation = (RequiresPermissions) a;
            String[] permissions = annotation.value();
            Logical logical = annotation.logical();
            //1.获取当前主题
            Subject subject = this.getSubject();
            // 验证是否超管用户，特殊处理
            if(subject.hasRole("admin")) {
                return;
            }
            //2.验证是否包含当前接口的权限有一个通过则通过
            boolean hasAtLeastOnePermission = false;
            for(String permission:permissions){
                    if(subject.isPermitted(permission)){
                        hasAtLeastOnePermission=true;
                        break;
                    }
                }
            if(!hasAtLeastOnePermission){
                throw new AuthorizationException("没有访问此接口的权限");
            }
        }
    }
}
