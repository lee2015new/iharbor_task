package com.iharbor.core.page.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iharbor.core.page.mybatis.dialects.MysqlDialect;
import com.iharbor.core.page.mybatis.dialects.OracleDialect;


/**
 * <pre> 
 * 功能说明：Mybatis的拦截器
 * </pre>
 * @title Mybatis Interceptor
 * @author Fred 
 * @version 1.0 2013-1-27
 */
@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	protected static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);
	
	private static final OracleDialect ORACLEDIALECT = new OracleDialect(); //处理oracle方言
	private static final MysqlDialect MYSQLDIALECT = new MysqlDialect();//处理mysql方言
	
	public Object intercept(Invocation invocation) throws Throwable {
		
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
		BoundSql boundSql = statementHandler.getBoundSql();//绑定的sql语句
		
		//获取数据库方言类型
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		Dialect dialect = this.getDialect(configuration);
		
		/*
		 * 处理分页查询
		 * 如果rowBounds由系统默认提供 说明没有传入分页参数，则不对绑定的sql进行分页处理，方法终止返回
		 */
		RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}
		metaStatementHandler.setValue("delegate.boundSql.sql"
				, dialect.getLimitString(boundSql.getSql(), rowBounds.getOffset(), rowBounds.getLimit()));
		
		metaStatementHandler.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_OFFSET);
		
		metaStatementHandler.setValue("delegate.rowBounds.limit",RowBounds.NO_ROW_LIMIT);
		
		//输出日志
		if(log.isDebugEnabled()){
			log.debug(" 生成分页SQL : " + boundSql.getSql());
		}
		
		return invocation.proceed();
	}
	
	/**
	 * 获取数据库类型
	 * @param configuration
	 * @return
	 */
	private Dialect getDialect(Configuration configuration){
		Dialect dialect = null;
		
		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			log.error("mybatis-config.xml中未设置数据库类型");
		}
		
		if (databaseType == null) {
			throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : " 
						+ configuration.getVariables().getProperty("dialect"));
		}
		
		switch (databaseType) {
		case ORACLE: // oracle 方言
			dialect = PaginationInterceptor.ORACLEDIALECT;
			break;
		case MYSQL: // MySQL 方言
			dialect = PaginationInterceptor.MYSQLDIALECT;
			break;
		}
		
		return dialect;
	}
	
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {

	}

}
