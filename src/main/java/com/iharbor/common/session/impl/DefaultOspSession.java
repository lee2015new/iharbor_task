package com.iharbor.common.session.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.iharbor.common.session.Session;

/**
 * <pre>
 * 功能说明：默认的会话管理实现
 * </pre>
 * @author 
 * @version 1.0 
 */
public class DefaultOspSession implements Session{
	
	private static final long serialVersionUID = -6497359384343702064L;
	
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	public void setAttribute(String name, Object obj) {
		attributes.put(name, obj);
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public Set<String> getAttributeNames() {
		
		return attributes.keySet();
	}
	
	public Map<String, Object> getAllAttributes(){
		return attributes;
	}
	
	public void removeAttribute(String name){
		attributes.remove(name);
	}

}
