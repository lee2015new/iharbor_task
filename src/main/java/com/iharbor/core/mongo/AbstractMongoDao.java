package com.iharbor.core.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * <pre>
 * 功能说明：MongoDB 数据层操作 
 * 所有MongoDB操作的基类通过继承此抽象类，获得MongoTemplate对象的引用
 * </pre>
 * @author Fred
 * @version 1.0 2014-03-24
 */
public abstract class AbstractMongoDao {
	
	@Autowired(required = false)
	private MongoOperations mongoOperations;
	
	public final void setMongoOperations(MongoOperations mongoOperations){
		this.mongoOperations = mongoOperations;
	}
	
	protected final MongoOperations getMongoOperations(){
		return mongoOperations;
	}
}
