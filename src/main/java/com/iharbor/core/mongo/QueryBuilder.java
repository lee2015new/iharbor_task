package com.iharbor.core.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * <pre>
 * 功能说明: 生成spring datamongo的查询条件对象 和更新对象
 * </pre>
 * @author Fred
 * @version 1.0 2014-03-28
 */
public interface QueryBuilder {
	
	/**
	 * 生成文档查询条件对象
	 * @param object
	 * @return
	 */
	public Query getQuery(Object object);
	
	/**
	 * 生成更新文档对象
	 * @param object
	 * @return
	 */
	public Update getUpdate(Object object);
	
	/**
	 * 生成查询指标条件
	 * @param id
	 * @return
	 */
	public Criteria getCriteria(String id);
	
}
