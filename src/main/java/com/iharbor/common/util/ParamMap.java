package com.iharbor.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 继承了ConcurrentHashMap实现基础方法
 * 
 */

public class ParamMap extends ConcurrentHashMap<String, Object> {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getString(String key) {
		Object object = get(key);
		if(object != null){
			if(object instanceof String){
				return (String)object;
			}else{
				String[] obj = (String[]) object;
				StringBuffer val = new StringBuffer();
				if (obj != null && obj.length > 0) {
					val.append(obj[0]);
					return val.toString();
				}else if (val.toString().length() == 0)
					return null;
			}
		}
		return null;
	}
	
	public Integer getInteger(String key) {
		Integer val = null;
		if(get(key) instanceof Integer){
			return (Integer)get(key);
		}
		String obj = getString(key);
		if (obj != null && !obj.equals(""))
			val = Integer.valueOf(obj);
		return val;
	}
	
	public Integer[] getIntegerArray(String key) {
		String[] array = getStringArray(key);
		Integer[] val = null;
		if (array != null && array.length>0){
			val = new Integer[array.length];
			for (int i = 0; i < array.length; i++) {
				val[i] = Integer.valueOf(array[i]);
			}
		}
		return val;
	}

	public Long getLong(String key) {
		String obj = getString(key);
		Long val = null;
		if (obj != null && !obj.equals(""))
			val = Long.valueOf(obj);
		return val;
	}
	
	public Date getDateFromSimpleDate(String key) {
		String obj = getString(key);
		Date val = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (obj != null && !obj.equals(""))
			try {
				val = format.parse(obj);
			} catch (ParseException e) {
//				e.printStackTrace();
				log.error(e.getMessage());
			}
		return val;
	}
	
	@SuppressWarnings("deprecation")
	public Date getDateFromUTCStr(String key) {
		String obj = getString(key);
		Date date = new Date(obj);
		return date;
	}
	
	public Double getDouble(String key){
		String obj = getString(key);
		Double val = null;
		if (obj != null && !obj.equals(""))
			val = Double.valueOf(obj);
		return val;
	}

	public String[] getStringArray(String key) {
		Object object = get(key);
		String[] obj = null;
		if (object != null) {
			if(object instanceof String){
				obj = new String[1];
				obj[0] = (String)object;
			}else if(object instanceof String[]){
				obj = (String[]) object;
			}
		}
		return obj;
	}
	
	public Long[] getLongArray(String key) {
		String[] array = getStringArray(key);
		Long[] rs = null;
		if (array != null) {
			rs = new Long[array.length];
			for (int i = 0; i < array.length; i++) {
				rs[i] = Long.valueOf(array[i]);
			}
		}
		return rs;
	}

}
