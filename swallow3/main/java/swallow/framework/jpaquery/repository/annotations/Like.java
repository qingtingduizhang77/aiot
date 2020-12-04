package swallow.framework.jpaquery.repository.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定查询bean在生成查询条件时，字串比较使用Like
 * @author aohanhe
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Like {
	/**
	 * 指定是否以开字串比较，例如 张三% ,否则使用 %张三%
	 * @return
	 */
	boolean isStartWith() default false;
}
