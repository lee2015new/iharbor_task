package com.iharbor.common.session.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iharbor.common.session.SessionService;
import com.iharbor.core.redis.RedisManager;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	private RedisManager redis;
	
	
	
}
