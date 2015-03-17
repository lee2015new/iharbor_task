package com.iharbor.common.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iharbor.common.dao.SysDao;
import com.iharbor.common.service.SysInfoService;
import com.iharbor.core.redis.RedisManager;

@Service
public class SysInfoServiceImpl implements SysInfoService{
	private static final Logger logger = Logger.getLogger(SysInfoServiceImpl.class);
	
	@Autowired
	private SysDao sysDao;
	
	@Autowired
	private RedisManager redis;
	
}
