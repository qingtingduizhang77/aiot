package com.cityiot.guanxin.config.filter;

import com.cityiot.guanxin.config.filter.annotation.NeedFilter;
import com.cityiot.guanxin.config.filter.provider.FilterCauseProvider;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class DataFilterAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Autowired
    private List<FilterCauseProvider> filterCauseProviderList = Collections.emptyList();

    DataFilterAdvisor() {
    }

    @PostConstruct
    public void init() {
        DataFilterInterceptor dataFilterInterceptor = new DataFilterInterceptor();
        dataFilterInterceptor.setFilterCauseProviderList(filterCauseProviderList);
        setAdvice(dataFilterInterceptor);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method m = method;
        if (targetClass != null) {
            try {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                return this.hasTargetAnnotaion(m);
            } catch (NoSuchMethodException ignored) {

            }
        }
        return false;
    }

    public boolean hasTargetAnnotaion(Method method) {
        return null != AnnotationUtils.findAnnotation(method, NeedFilter.class);
    }
}
