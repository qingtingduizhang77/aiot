package com.cityiot.guanxin.config.filter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import com.cityiot.guanxin.config.filter.annotation.NeedFilter;
import com.cityiot.guanxin.config.filter.handler.DataFilterQueryBeanHandler;
import com.cityiot.guanxin.config.filter.handler.DataFilterWhereHandler;
import com.cityiot.guanxin.config.filter.provider.FilterCauseProvider;
import com.cityiot.guanxin.config.filter.where.WhereJoiner;
import com.cityiot.guanxin.user.entity.Userview;
import com.querydsl.jpa.impl.JPAQuery;

import swallow.framework.web.BaseQueryBean;

public class DataFilterInterceptor implements MethodInterceptor {
    private List<FilterCauseProvider> filterCauseProviderList = Collections.emptyList();
    public List<FilterCauseProvider> getFilterCauseProviderList() {
        return filterCauseProviderList;
    }

    public void setFilterCauseProviderList(List<FilterCauseProvider> filterCauseProviderList) {
        this.filterCauseProviderList = filterCauseProviderList;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Subject subject = SecurityUtils.getSubject();
        Userview user = (Userview)subject.getPrincipals().getPrimaryPrincipal();
        // 获取方法上的注解，解析Handler和WhereJoiner
        Method m = invocation.getMethod();
        NeedFilter needFilter = AnnotationUtils.findAnnotation(m, NeedFilter.class);
        Class handlerClass = needFilter.value();
        Class whereJoinerClass = needFilter.where();
        String filedName = needFilter.filedName();
        if(Object.class==handlerClass) {
            throw new Throwable("必须设置数据过滤Handler");
        }
        if(!StringUtils.isEmpty(filedName) && whereJoinerClass!=Object.class) {
            throw new Throwable("数据过滤只能选择一种方式，不能同时设置where和filedName");
        }
        Object handleObject = createInstance(handlerClass);
        Object[] arguments = null;
        if(handleObject instanceof DataFilterWhereHandler) {
            if(Object.class==whereJoinerClass){
                throw new Throwable("Where模式下必须设置where");
            }
            if(subject.hasRole("admin")){
                // 超管用户不用过滤
                return invokeMethod(m,invocation.getThis(),invocation.getArguments());
            }
            arguments = invokeMethodForWhereFilter(invocation, (DataFilterWhereHandler)handleObject, whereJoinerClass);
        }else {
            if(StringUtils.isEmpty(filedName)){
                throw new Throwable("QueryBean模式下必须设置fileName");
            }
            if(subject.hasRole("admin")){
                // 超管用户不用过滤
                return invokeMethod(m,invocation.getThis(),invocation.getArguments());
            }
            arguments = invokeMethodForQueryBeanFilter(invocation,(DataFilterQueryBeanHandler)handleObject,filedName);
        }
        return invokeMethod(m,invocation.getThis(),arguments);
    }
    public Object[] invokeMethodForQueryBeanFilter(MethodInvocation invocation, DataFilterQueryBeanHandler dataFilterHandler,String filedName) throws Throwable {
        Object[] arguments = invocation.getArguments();
        // 判断当前哪个参数是QueryBean子类或者哪个参数上有NeedFilter注解
        BaseQueryBean targetArgument = null;
        int targetArgumentIndex = -1;
        for(int i=0;i<arguments.length;i++) {
            Object o = arguments[i];
            if(o instanceof BaseQueryBean) {
                targetArgument = (BaseQueryBean)o;
                targetArgumentIndex = i;
                break;
            }
        }
        // 当不存在当前参数，即不需要过滤
        if(null == targetArgument) {
            throw new Throwable("当前方法不存在QueryBean对象，确定需要过滤数据？");
        }
        // 判断当前Hander需要哪个Provider
        BaseQueryBean baseQueryBean = null;
        for(FilterCauseProvider filterCauseProvider : filterCauseProviderList) {
            if(dataFilterHandler.support(filterCauseProvider)) {
                // 执行Hander，将Provider和目标属性当作参数
                Field field = targetArgument.getClass().getDeclaredField(filedName);
                field.setAccessible(true);
                baseQueryBean = dataFilterHandler.inject(filterCauseProvider,targetArgument,field);
                break;
            }
        }
        if(null==baseQueryBean) {
            throw new Throwable("当前方法没有数据过滤Handler执行，确定需要过滤数据？");
        }
        arguments[targetArgumentIndex] = baseQueryBean;
        return arguments;
    }
    public Object[] invokeMethodForWhereFilter(MethodInvocation invocation,DataFilterWhereHandler dataFilterHandler,Class<WhereJoiner> whereJoinerClass) throws Throwable{
        Object[] arguments = invocation.getArguments();
        WhereJoiner whereJoiner = createInstance(whereJoinerClass);
        // 判断当前哪个参数是JPQuery子类或者哪个参数上有NeedFilter注解
        JPAQuery targetArgument = null;
        int targetArgumentIndex = -1;
        for(int i=0;i<arguments.length;i++) {
            Object o = arguments[i];
            if(o instanceof JPAQuery) {
                targetArgument = (JPAQuery)o;
                targetArgumentIndex = i;
                break;
            }
        }
        // 当不存在当前参数，即不需要过滤
        if(null == targetArgument) {
            throw new Throwable("当前方法不存在Query对象，确定需要过滤数据？");
        }
        // 判断当前Hander需要哪个Provider
        JPAQuery jpaQuery = null;
        for(FilterCauseProvider filterCauseProvider : filterCauseProviderList) {
            if(dataFilterHandler.support(filterCauseProvider)) {
                // 执行Hander，将Provider和WeherJoiner当作参数
                jpaQuery = dataFilterHandler.filter(filterCauseProvider,targetArgument,whereJoiner);
                break;
            }
        }
        if(null==jpaQuery) {
            throw new Throwable("当前方法没有数据过滤Handler执行，确定需要过滤数据？");
        }
        arguments[targetArgumentIndex] = jpaQuery;
        return arguments;
    }
    public <T> T createInstance(Class<? super T> clazz) throws IllegalAccessException, InstantiationException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return (T)clazz.getDeclaredConstructor().newInstance();
    }
    public Object invokeMethod(Method method,Object that,Object[] arguments) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(that,arguments);
    }
}
