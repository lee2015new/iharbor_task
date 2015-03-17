package com.iharbor.core.page.mybatis;


/**
 * <pre>
 * mybatis物理分页 数据库方言
 * </pre>
 * @author Fred
 * @version 1.0 2013-1-27 下午02:45:28
 */
public abstract class Dialect {   

	public static enum Type{   
        MYSQL,  
        ORACLE  
    }  
	
	/**
	 * 生成预处理分页sql语句
	 * @param sql
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
    public abstract String getLimitString(String sql, int skipResults, int maxResults);
    
    
}  
