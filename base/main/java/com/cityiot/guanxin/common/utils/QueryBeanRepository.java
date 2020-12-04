package com.cityiot.guanxin.common.utils;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import swallow.framework.jpaentity.IOnlyIdEntity;
import swallow.framework.jpaquery.repository.EntityFieldFetchor;

import javax.persistence.EntityManager;
import java.util.List;

public class QueryBeanRepository<T extends IOnlyIdEntity, K> {
	private static final Logger logger = LoggerFactory.getLogger(QueryBeanRepository.class);
	@Autowired
	protected EntityManager em;

	private Class<T> entityClassInfo;

	private Class<K> idClassInfo;

	private EntityPath<T> mainTable;

	/**
	 * querydsl构建工具
	 */

	protected JPAQueryFactory factory;
	
	/**
	 * id字段的路径
	 */
	private Path<K> idPath;

	/**
	 * 实体查询的主体查询部分，包括了select 以及 from
	 */
	private JPAQuery<Tuple> query;

	/**
	 * 提取数据使用的映射列表
	 */
	private List<EntityFieldFetchor> fecthDataList;


	// 从`取得item数据
	public T fectItemFromTuple(Tuple tuple) {
		T item = (T) tuple.get(this.mainTable);
		fecthDataList.forEach(fetch -> {
			try {
				fetch.getField().set(item, tuple.get(fetch.getEntityPath()));
			} catch (Exception e) {
				logger.error("从tuple提取数据失败:" + e.getMessage(), e);
			}
		});

		return item;
	}
	
	protected JPAQuery<Tuple> getQuery() {
		return this.query.clone();
	}
}
