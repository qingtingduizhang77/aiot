package com.cityiot.guanxin.log.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解类
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface ViLog {
	/** 操作事件     */
    String operEvent () default "";

    /** 日志类型 1:新增 2：更新 3:删除 4：查询 */
    int operType ();
}
