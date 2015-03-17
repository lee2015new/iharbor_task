package com.iharbor.core.page.mybatis.dialects;

import com.iharbor.core.page.mybatis.Dialect;


/**
 * oracle方言
 * @author Fred
 * 2013-1-27 下午02:45:53
 */
public class OracleDialect extends  Dialect{   

	/**
	 * @param sql
	 * @param offset 页数
	 * @param limit 每页记录数
	 */
    @Override    
    public  String getLimitString(String sql,  int  offset,  int  limit) {   
    	
        sql = sql.trim();   
        StringBuffer sb = new StringBuffer(sql.length() + 100); 
        sb.append("select * from ( select row_.*, rownum rownum_ from ( ")   
                    .append(sql).append(" ) row_ where rownum <= ").append(   
                            offset + limit).append(") where rownum_ > ")   
                    .append(offset);   
        
        return sb.toString();   
    }  

}  

