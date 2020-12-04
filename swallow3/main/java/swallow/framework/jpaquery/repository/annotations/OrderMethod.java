package swallow.framework.jpaquery.repository.annotations;

/**
 * 排序表达式函数
 * @author aohanhe
 *
 */
public @interface OrderMethod {
	/**
	 * 排序用的表达式对应的函数名
	 * @return
	 */
	String value();
}
