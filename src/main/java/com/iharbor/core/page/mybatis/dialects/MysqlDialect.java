package com.iharbor.core.page.mybatis.dialects;

import com.iharbor.core.page.mybatis.Dialect;


/**
 * <pre>
 * 功能说明：mysql方言,处理sql分页
 * </pre>
 * @author Fred
 * @version 1.2 2014-04-03
 */
public class MysqlDialect extends Dialect{   

	/**
	 * @param sql
	 * @param offset 页数
	 * @param limit 每页记录数
	 */
    @Override    
    public String getLimitString(String sql, int offset, int limit) {   
    	sql = sql.trim();
    	//替换空白符为空格符
    	sql = sql.replaceAll("[\r\n\t]{1,}", " ");
    	//去掉多余的空格符
    	sql = sql.replaceAll(" {2,}", " "); 
        StringBuffer sb = new StringBuffer(sql);   
        sb.append(" LIMIT ").append(offset).append(',').append(limit);
  
        return sb.toString();
    }
       
    public static void main(String[] args){
    	String sql = "SELECT a.Financing_Event_ID, b.Enterprise_CN_Short, 	a.Invest_OrgCnName,		   ( CASE   WHEN a.Internate_One IS NOT NULL     	(    SELECT		CONVERT	  (	  	CN_NAME USING gbk))";
				
    	MysqlDialect mysqlDialect = new MysqlDialect();
    	sql = mysqlDialect.getLimitString(sql, 1, 10);
    	System.out.println(sql);
    }
    
}  

