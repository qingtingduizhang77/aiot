package swallow.framework.jpaquery.repository.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义字段联结到指定表实体的指定字段
 * @author aohanhe
 *
 */
@Repeatable(JoinEntities.class)
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JoinEntity {
	/**
	 * 要联结实体的字段名
	 * @return
	 */
	String name();
	
	/**
	 * 主联结字段 不设置为当前字段，在字段上使用时，设置了也无效
	 * @return
	 */
	String mainFiledName() default "";
	/**
	 * 要联结实体的表ID
	 * @return
	 */
	Class<?> joinEntityClass();
	
	/**
	 * 联结实体的别名 不设置使用默认值
	 * @return
	 */
	String joinEntityAlias() default "";
	
	/**
	 * 主实体如果不设置默认为当前表 在字段上使用时，设置了也无效
	 * @return
	 */
	Class<?> mainEnityClass() default Object.class;
	
	/**
	 * 主实体的的别名，不设置使用默认值
	 * @return
	 */
	String mainEntityAlias() default "";
	
	/**
	 * 字段的数据类型值
	 * @return
	 */
	Class<?> fieldType() default Integer.class;
	
	/**
	 * 扩展的On条件的返回的方法，这个静态方法要写在当前实体中，并且定义为静态,参数为空返回值为Predicate
	 * @return
	 */
	String extOnMethod() default "";
}
