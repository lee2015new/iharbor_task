package com.iharbor.business.user.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iharbor.business.user.dao.UserDao;
import com.iharbor.business.user.service.UserService;
import com.iharbor.common.dao.impl.SysDaoImpl;
import com.iharbor.core.redis.RedisManager;

@Service("UserService")
public class UserServiceImpl implements UserService{
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	

	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private SysDaoImpl sysdao;
	
	@Autowired
	private RedisManager redis;
	

}
