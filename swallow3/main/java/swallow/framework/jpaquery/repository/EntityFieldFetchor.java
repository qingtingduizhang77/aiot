package swallow.framework.jpaquery.repository;

import java.lang.reflect.Field;

import org.springframework.util.Assert;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;

/**
 * 实体数据提取器
 * @author aohanhe
 *
 */
public class EntityFieldFetchor {
	private EntityPath<?> table;
	private Field field;
	private Path<?> entityPath;
	
	
	public EntityFieldFetchor(Field field,Path<?> entityPath,EntityPath<?> table) {
		Assert.notNull(table,"参数table不允许为空");
		Assert.notNull(field,"参数field不允许为空");
		Assert.notNull(entityPath,"参数entityPath不允许为空");
		
		this.field=field;
		this.entityPath=entityPath;
		this.table=table;
	}


	public Field getField() {
		return field;
	}


	public Path<?> getEntityPath() {
		return entityPath;
	}


	public EntityPath<?> getTable() {
		return table;
	}
	
	
	 

}
