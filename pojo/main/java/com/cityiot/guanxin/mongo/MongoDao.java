package com.cityiot.guanxin.mongo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * mongodb 操作 API 的具体实现
 * 
 * @author hxl
 *
 */
@Repository
public class MongoDao implements IMongoDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(
		MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void insert(
		Object objectToSave,
		String tableName) {
		mongoTemplate.insert(objectToSave, tableName);
	}
	public void insert(
		Object objectToSave) {
		mongoTemplate.insert(objectToSave);
	}
	public void save(
		Object objectToSave,
		String tableName) {
		mongoTemplate.save(objectToSave, tableName);
	}

	public void insertBath(
		Collection<? extends Object> batchToSave,
		String tableName) {
		mongoTemplate.insert(batchToSave, tableName);
	}

	public void saveBath(
		Collection<? extends Object> batchToSave,
		String tableName) {
		for (Object object : batchToSave) {
			mongoTemplate.save(object, tableName);
		}
	}

	public <T> Iterator<T> group(
		CriteriaMap criteriaMap,
		String tableName,
		Class<T> t,
		String[] groupBy,
		String initial,
		String reduce,
		String finalize) {

		Criteria criteria = CriteriaMap.criteriaMap2Criteria(criteriaMap);

		if (initial == null) {
			initial = "{}";
		}

		GroupBy group = GroupBy.key(groupBy).initialDocument(initial).reduceFunction(reduce).finalizeFunction(finalize);

		GroupByResults<T> results = mongoTemplate.group(criteria, tableName, group, t);
		Iterator<T> ite = results.iterator();
		return ite;

	}

	public void deleteByMap(
		CriteriaMap criteriaMap,
		String tableName) {

		Query query = getQuery(criteriaMap);

		mongoTemplate.remove(query, tableName);
	}

	public void deleteAll(
		String tableName) {
		mongoTemplate.dropCollection(tableName);
	}

	public void update(
		CriteriaMap criteriaMap,
		Map<String, Object> setMap,
		String tableName) {

		Query query = getQuery(criteriaMap);

		Update update = null;
		for (String key : setMap.keySet()) {
			Object obj = setMap.get(key);
			if (update == null) {
				update = Update.update(key, obj);
			} else {
				update = update.set(key, obj);
			}
		}

		mongoTemplate.updateMulti(query, update, tableName);
	}

	public <T> List<T> findByMap(
		CriteriaMap criteriaMap,
		Class<T> t,
		String tableName) {

		Query query = getQuery(criteriaMap);

		return mongoTemplate.find(query, t, tableName);
	}

	public <T> List<T> findByMapWithSorter(
		CriteriaMap criteriaMap,
		List<Sorter> sorters,
		Class<T> t,
		String tableName) {

		Query query = getQuery(criteriaMap);
		query = getQueryWithSort(query, sorters);

		return mongoTemplate.find(query, t, tableName);
	}

	public <T> List<T> findByMap(
		CriteriaMap criteriaMap,
		Class<T> t,
		int start,
		int limit,
		String tableName) {

		Query query = getQuery(criteriaMap);
		query.skip(start);
		query.limit(limit);

		return mongoTemplate.find(query, t, tableName);
	}

	public <T> List<T> findByMapWithSorter(
		CriteriaMap criteriaMap,
		List<Sorter> sorters,
		Class<T> t,
		int start,
		int limit,
		String tableName) {
		Query query = getQuery(criteriaMap);
		query.skip(start);
		query.limit(limit);
		query = getQueryWithSort(query, sorters);
		return mongoTemplate.find(query, t, tableName);
	}

	public <T> List<T> findByMap4Page(
		CriteriaMap criteriaMap,
		Class<T> t,
		int pageNo,
		int pageSize,
		String tableName) {

		Query query = getQuery(criteriaMap);

		query = query.skip((pageNo - 1) * pageSize).limit(pageSize);

		return mongoTemplate.find(query, t, tableName);
	}

	public <T> List<T> findByMap4PageWithSorter(
		CriteriaMap criteriaMap,
		List<Sorter> sorters,
		Class<T> t,
		int pageNo,
		int pageSize,
		String tableName) {

		Query query = getQuery(criteriaMap);
		query = query.skip((pageNo - 1) * pageSize).limit(pageSize);
		query = getQueryWithSort(query, sorters);
		return mongoTemplate.find(query, t, tableName);
	}

	public <T> List<T> findAll(
		Class<T> t,
		String tableName) {

		return mongoTemplate.findAll(t, tableName);
	}

	public <T> List<T> findAllWithSorter(
		Class<T> t,
		List<Sorter> sorters,
		String tableName) {

		Query query = new Query();
		query = getQueryWithSort(query, sorters);
		return mongoTemplate.find(query, t, tableName);
	}

	public <T> T findAndModify(
		CriteriaMap criteriaMap,
		Map<String, Object> setMap,
		Class<T> t,
		String tableName) {
		Query query = getQuery(criteriaMap);
		Update update = getUpdater(setMap);
		return mongoTemplate.findAndModify(query, update, t, tableName);
	}

	public <T> T findAndRemove(
		CriteriaMap criteriaMap,
		Class<T> t,
		String tableName) {
		Query query = getQuery(criteriaMap);
		return mongoTemplate.findAndRemove(query, t, tableName);
	}

	public long count(
		CriteriaMap criteriaMap,
		String tableName) {
		Query query = getQuery(criteriaMap);
		return mongoTemplate.count(query, tableName);
	}

	private Query getQuery(
		CriteriaMap criteriaMap) {

		Criteria criteria = CriteriaMap.criteriaMap2Criteria(criteriaMap);
		Query query = new Query(criteria);
		return query;
	}

	private Query getQueryWithSort(
		Query query,
		List<Sorter> sorters) {

		for (Sorter sorter : sorters) {
			
			query = query.with(new Sort(new Sort.Order(
					sorter.getDirection() == Sorter.Direction.ASC ? Sort.Direction.ASC : Sort.Direction.DESC,
					sorter.getField())));
		}

		return query;
	}

	private Update getUpdater(
		Map<String, Object> setMap) {

		Update updater = null;
		for (String key : setMap.keySet()) {
			Object obj = setMap.get(key);
			if (updater == null) {
				updater = Update.update(key, obj);
			} else {
				updater = updater.set(key, obj);
			}
		}
		return updater;
	}

	/*@Override
	public List<DBObject> polygonsIncludePonit(String collection , String locationField, double[] points){
		
		DBObject query = new BasicDBObject();
        query.put(locationField, new BasicDBObject("$geoIntersects",
                new BasicDBObject("$geometry",
                        new BasicDBObject("type","Point")
                        .append("coordinates",points))));
        List<DBObject> array = mongoTemplate.getCollection(collection).find(query).toArray();
        return array;
	}*/

	@Override
	public boolean isCollectionExists(String collectionName) {
		return mongoTemplate.collectionExists(collectionName);
	}
}
