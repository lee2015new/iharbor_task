package com.iharbor.core.mongo;

import java.util.List;

import com.iharbor.core.page.Page;
import com.mongodb.WriteResult;

/**
 * <pre>
 * 功能说明：spring date 数据库持久层接口
 * </pre>
 * @author Fred
 * @param <T>
 * @version 1.0 2014-03-24
 */
public interface MongoDao{
	
	/**
	 * 查询，并返回全部符合条件的文档
	 * @param params 查询条件指标
	 * @param entityClass 文档对应实体类
	 * @return List
	 */
	public <T> List<T> findObject(Object params, Class<T> entityClass);
	
	/**
	 * 分页查询文档
	 * @param params 查询指标
	 * @param page 分页对象
	 * @param entityClass 文档对应实体类
	 * @return List 
	 */
	public <T> List<T> findObjectOnPage(Object params, Page page, Class<T> entityClass);
	
	/**
	 * 查询文档数量
	 * @param params 查询条件指标
	 * @param entityClass 文档对应实体类
	 * @return long 符合条件结果数量
	 */
	public <T> long count(Object params, Class<T> entityClass);
	
	
	/**
	 * 通过主键查询文档对象
	 * @param id 主键ObjectId
	 * @param entityClass 文档对应实体类
	 * @return 
	 */
	public <T> T getObjectById(String id, Class<T> entityClass);
	
	/**
	 * 文档是否存在
	 * @param entity
	 * @param entityClass
	 * @return
	 */
	public <T> boolean isExist(T entity,Class<T> entityClass);
	
	/**
	 * 添加文档对象
	 * @param entity 实体类对象
	 */
	public <T> void insertObject(T entity);
	
	/**
	 * 批量添加文档对象 list数据量不超过16M
	 * @param entitys 实体对象
	 */
	public <T> void insertAllObject(List<T> entitys);
	
	/**
	 * 更新单条文档
	 * @param id 文档主键 
	 * @param entity 文档实体对象
	 * @param entityClass 文档对应实体类
	 * @return WriteResult 更新结果
	 */
	public <T> WriteResult updateObjectById(String id, T entity, Class<T> entityClass);
	
	/**
	 * 批量更新文档
	 * @param params 更新范围限定指标
	 * @param entity 文档实体对象  保存要更新的内容
	 * @param entityClass 文档对应的实体类
	 * @return WriteResult 更新结果
	 */
	public <T> WriteResult update(Object params, T entity, Class<T> entityClass);
	
	/**
	 * 批量删除文档，根据查询条件删除文档
	 * @param params 删除范围限定指标
	 * @param entityClass 文档对应的实体类
	 */
	public <T> void deleteObject(Object params , Class<T> entityClass);
	
	/**
	 * 根据主键删除文档
	 * @param id 文档主键
	 * @param entityClass 文档对应的实体类
	 */
	public <T> void deleteObjectById(String id, Class<T> entityClass);
	
	/**
	 * 根据条件查询文档 并更新这些文档
	 * @param params 限制条件
	 * @param entity 更新的文档对象
	 * @param entityClass 
	 * @return T 返回更新前文档
	 */
	public <T> T findAndUpdate(Object params, T entity, Class<T> entityClass);
	
	/**
	 * 根据条件查询出文档 并删除这些文档
	 * @param params 限制条件 
	 * @param entityClass
	 * @return T 返回删除前文档
	 */
	public <T> T findAndDelete(Object params, Class<T> entityClass);
	
	/**
	 * 创建文档集合
	 * @param entityClass
	 */
	public <T> void createCollection(Class<T> entityClass);
	
	/**
	 * 删除文档集合
	 * @param entityClass
	 */
	public <T> void dropCollection(Class<T> entityClass);
	
}
