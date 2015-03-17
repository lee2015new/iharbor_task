package com.iharbor.core.mongo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.iharbor.core.page.Page;
import com.mongodb.WriteResult;

/**
 * <pre>
 * 功能说明：默认的MongoDao数据操作实现
 * </pre>
 * @author Fred
 * @param <T>
 * @version 1.0 2014-03-24
 */
@Repository("mongoDao")
public class DefalutMongoDao extends AbstractMongoDao
		implements MongoDao {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired(required = false)
	private QueryBuilder queryBuilder;
	public void setQueryBuilder(QueryBuilder queryBuilder){
		this.queryBuilder = queryBuilder;
	}
	
	public <T> List<T> findObject(Object params,Class<T> entityClass) {
		Query query = queryBuilder.getQuery(params);
		return this.getMongoOperations().find(query, entityClass);
	}

	public <T> List<T> findObjectOnPage(Object params, Page page,Class<T> entityClass) {
		
		Query query = queryBuilder.getQuery(params);
		long totalCount = this.count(query,entityClass);
		page.setTotalCount(totalCount);
		query.skip(page.getFirst());
		return this.getMongoOperations().find(query, entityClass);
	}

	public <T> long count(Object params,Class<T> entityClass) {
		Query query = queryBuilder.getQuery(params);
		return this.getMongoOperations().count(query, entityClass);
	}

	public <T> T getObjectById(String id, Class<T> entityClass) {
		return this.getMongoOperations().findById(id, entityClass);
	}
	
	public <T> boolean isExist(T entity,Class<T> entityClass){
		return this.getMongoOperations().exists(queryBuilder.getQuery(entity), entityClass);
	}
	
	public <T> void insertObject(T entity) {
		this.getMongoOperations().insert(entity);
	}

	public <T> void insertAllObject(List<T> entitys) {
		this.getMongoOperations().insertAll(entitys);
	}

	public <T> WriteResult updateObjectById(String id,T entity,Class<T> entityClass) {
		Criteria criteria = queryBuilder.getCriteria(id);
		Query query = Query.query(criteria);
		Update update = queryBuilder.getUpdate(entity);
		return this.getMongoOperations().updateFirst(query, update, entityClass);
	}

	public <T> WriteResult update(Object params, T entity, Class<T> entityClass) {
		Query query = queryBuilder.getQuery(params);
		Update update = queryBuilder.getUpdate(entity);
		return this.getMongoOperations().updateMulti(query, update, entityClass);
	}

	public <T> void deleteObject(Object params,Class<T> entityClass) {
		Query query = queryBuilder.getQuery(params);
		this.getMongoOperations().remove(query, entityClass);
	}

	public <T> void deleteObjectById(String id,Class<T> entityClass) {
		Criteria criteria = queryBuilder.getCriteria(id);
		Query query = Query.query(criteria);
		this.getMongoOperations().remove(query, entityClass);
	}

	public <T> T findAndUpdate(Object params, T entity, Class<T> entityClass) {
		Query query = queryBuilder.getQuery(params);
		Update update = queryBuilder.getUpdate(entity);
		return this.getMongoOperations().findAndModify(query, update, entityClass);
	}

	public <T> T findAndDelete(Object params,Class<T> entityClass) {
		Query query = queryBuilder.getQuery(params);
		return this.getMongoOperations().findAndRemove(query, entityClass);
	}

	public <T> void createCollection(Class<T> entityClass) {
		this.getMongoOperations().createCollection(entityClass);
	}

	public <T> void dropCollection(Class<T> entityClass) {
		this.getMongoOperations().dropCollection(entityClass);
	}
	
}
