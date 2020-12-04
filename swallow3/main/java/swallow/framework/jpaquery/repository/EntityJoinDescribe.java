package swallow.framework.jpaquery.repository;

import org.springframework.util.Assert;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * 实体join描述
 * @author aohanhe
 *
 */
public class EntityJoinDescribe {
	// 要联结表
	private EntityPath<?> table;
	
	// 联结条件表达式
	private Predicate condition;
	
	public EntityJoinDescribe(EntityPath<?> table,Predicate condition) {
		Assert.notNull(table,"参数table不允许为空");		
		Assert.notNull(condition,"参数condition不允许为空");
		
		this.table=table;
		
		this.condition=condition;
	}
	
	public EntityJoinDescribe(EntityPath<?> table,Path<?> mainTableFieldPath,Path<?> joinTableFieldPath) {
		Assert.notNull(table,"参数table不允许为空");	
		Assert.notNull(table,"参数mainTableFieldPath不允许为空");	
		Assert.notNull(table,"参数joinTableFieldPath不允许为空");	
		
		this.table=table;
		this.condition=Expressions.predicate(Ops.EQ,mainTableFieldPath,joinTableFieldPath);
	}
	
	public EntityJoinDescribe(EntityPath<?> table,Path<?> mainTableFieldPath,Path<?> joinTableFieldPath,Predicate extendCondition) {
		Assert.notNull(table,"参数table不允许为空");	
		Assert.notNull(table,"参数mainTableFieldPath不允许为空");	
		Assert.notNull(table,"参数joinTableFieldPath不允许为空");	
		Assert.notNull(table,"参数extendCondition不允许为空");
		
		this.table=table;
		this.condition=Expressions.predicate(Ops.EQ,mainTableFieldPath,joinTableFieldPath).and(extendCondition);
		
	}
	
	// 是否与指定的表同一个表
	public boolean isEqtable(EntityPath<?> table) {
		return this.table.equals(table);
	}
	
	/**
	 * 把当前的联结描述加到查询中去
	 * @param query
	 * @return
	 */
	public JPAQuery<Tuple> addToQuery(JPAQuery<Tuple> query){
		return query.leftJoin(this.table).on(condition);
	}

}
