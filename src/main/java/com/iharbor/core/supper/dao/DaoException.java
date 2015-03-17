package com.iharbor.core.supper.dao;


/**
 * <pre>
 * 功能说明：数据持久层异常
 * </pre>
 * @author Fred
 * @version 1.0 2014-05-28
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = -4204424970579127314L;
	
	 public DaoException(String message) {
	        super(message);
	 }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
