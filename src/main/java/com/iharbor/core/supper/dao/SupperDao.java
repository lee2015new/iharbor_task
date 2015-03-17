package com.iharbor.core.supper.dao;

import java.io.Serializable;
import java.util.List;

import com.iharbor.core.page.Page;

/**
 * <pre>
 * 功能说明：单数据源 数据库操作接口
 * </pre>
 * @author Fred
 * @version 1.0 2014-04-10
 */
public interface SupperDao {
	
	
	/**
	 * 保存
	 * @param key
	 * @param object
	 */
	public int save(String key, Object object);

	/**
	 * 修改
	 * @param key
	 * @param object
	 */
	public int update(String key, Object object);
	
	/**
	 * 删除
	 * @param key
	 * @param id
	 */
	public int delete(String key, Serializable id);
	
	/**
	 * 删除
	 * @param key
	 * @param object
	 */
	public int delete(String key, Object object);
	
	/**
	 * 查询 返回一个结果
	 * @param <T>
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> T get(String key, Object params);
	
	/**
	 * 查询 返回一个结果
	 * @param <T>
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> T get(String key);
	
	/**
	 * 查询 返回多个结果
	 * @param <T>
	 * @param key
	 * @return
	 */
	public <T> List<T> findList(String key);
	
	/**
	 * 分页查询
	 * @param <T>
	 * @param key
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findList(String key,int offset,int pageSize);
	
	/**
	 * 分页查询
	 * @param <T>
	 * @param key
	 * @param page
	 * @return
	 */
	public <T> List<T> findList(String key,Page page);
	
	/**
	 * 查询 可带参数 返回多个结果
	 * @param <T>
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> List<T> findList(String key, Object params);
	
	/**
	 * 分页查询 可带参数
	 * @param <T>
	 * @param key
	 * @param params 参数
	 * @param pageOffset 开始记录数
	 * @param pageSize 每页记录数
	 * @return
	 */
	public <T> List<T> findList(String key, Object params,int pageOffset,int pageSize);
	
	/**
	 * 分页查询 可带参数
	 * @param <T>
	 * @param key
	 * @param params
	 * @param page
	 * @return
	 */
	public <T> List<T> findList(String key, Object params,Page page);
	
}
