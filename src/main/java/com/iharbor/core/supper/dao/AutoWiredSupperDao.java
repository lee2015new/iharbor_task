package com.iharbor.core.supper.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iharbor.core.mongo.MongoDao;

/**
 * <pre>
 * 功能说明:装配supperDao
 * 需要使用supperDao的类 可以通过继承此类获得
 * </pre>
 * @author Fred
 * @version 1.0 2014-03-31
 */
public abstract class AutoWiredSupperDao {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/** 默认supperDao名称  名称为注册在spring容器中的beanId**/
	private String defaultSupperDaoName = "supperDao";
	public void setDefaultSupperDaoName(String supperDaoName){
		this.defaultSupperDaoName = supperDaoName;
	}
	
	@Autowired(required = false)
	protected MongoDao mongoDao;
	public void setMongoDao(MongoDao mongoDao){
		this.mongoDao = mongoDao;
	}
	
	@Autowired
	private Map<String,SupperDao> supperDaoContext = new HashMap<String,SupperDao>();
	public void setSupperDaoContext(Map<String,SupperDao> supperDaoContext){
		this.supperDaoContext = supperDaoContext;
	}
	
	/**
	 * 获得指定名称的supperDao
	 * @param String supperDaoName spring容器中注册的supperDao名称
	 * @return
	 */
	public SupperDao getSupperDao(String supperDaoName){
		return supperDaoContext.get(supperDaoName);
	}
	
	/**
	 * 获得默认 supperDao
	 * @return
	 */
	public SupperDao getSupperDao(){
		return supperDaoContext.get(defaultSupperDaoName);
	}
		
}
