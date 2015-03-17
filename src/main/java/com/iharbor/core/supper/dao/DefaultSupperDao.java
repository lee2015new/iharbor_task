package com.iharbor.core.supper.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iharbor.core.page.Page;

/**
 * <pre>
 * 功能说明：默认的单数据源 SupperDao接口实现类
 * 提供对数据库操作的方法封装
 * </pre>
 * @author Fred
 * @version 1.1 
 * 2013-1-25 下午11:39:27
 */
public class DefaultSupperDao implements SupperDao {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private SqlSession sqlSession;
	
	@Autowired(required = false)
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSession = sqlSessionTemplate;
	}
	
	public SqlSession getSqlSession(){
		return this.sqlSession;
	}
	/**
	 * 保存
	 * @param key
	 * @param object
	 */
	public int save(String key, Object object) {
		return getSqlSession().insert(key, object);
	}

	/**
	 * 修改
	 * @param key
	 * @param object
	 */
	public int update(String key, Object object) {
		return getSqlSession().update(key, object);
	}
	
	/**
	 * 删除
	 * @param key
	 * @param id
	 */
	public int delete(String key, Serializable id) {
		return getSqlSession().delete(key, id);
	}
	
	/**
	 * 删除
	 * @param key
	 * @param object
	 */
	public int delete(String key, Object object) {
		return getSqlSession().delete(key, object);
	}
	
	/**
	 * 查询 返回一个结果
	 * @param <T>
	 * @param key
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Object params) {
		return (T) getSqlSession().selectOne(key, params);
	}
	
	/**
	 * 查询 返回一个结果
	 * @param <T>
	 * @param key
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) getSqlSession().selectOne(key);
	}
	
	/**
	 * 查询 返回多个结果
	 * @param <T>
	 * @param key
	 * @return
	 */
	public <T> List<T> findList(String key) {
		return getSqlSession().selectList(key);
	}
	
	/**
	 * 分页查询
	 * @param <T>
	 * @param key
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findList(String key,int offset,int pageSize) {
		return getSqlSession().selectList(key,new RowBounds(offset, pageSize));
	}
	
	/**
	 * 分页查询
	 * @param <T>
	 * @param key
	 * @param page
	 * @return
	 */
	public <T> List<T> findList(String key,Page page) {
		List<T> result = getSqlSession().selectList(key,new RowBounds(page.getFirst(),page.getPageSize()));
		return result;
	}
	
	/**
	 * 查询 可带参数 返回多个结果
	 * @param <T>
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> List<T> findList(String key, Object params) {
		return getSqlSession().selectList(key, params);
	}
	
	/**
	 * 分页查询 可带参数
	 * @param <T>
	 * @param key
	 * @param params 参数
	 * @param pageOffset 开始记录数
	 * @param pageSize 每页记录数
	 * @return
	 */
	public <T> List<T> findList(String key, Object params,int pageOffset,int pageSize) {
		return getSqlSession().selectList(key, params,new RowBounds(pageOffset, pageSize));
	}
	
	/**
	 * 分页查询 可带参数
	 * @param <T>
	 * @param key
	 * @param params
	 * @param page
	 * @return
	 */
	public <T> List<T> findList(String key, Object params,Page page) {
		List<T> result = getSqlSession().selectList(key, params,new RowBounds(page.getFirst(),page.getPageSize()));
		return result;
	}

}
