package com.iharbor.business.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iharbor.business.entity.UserMainInfo;
import com.iharbor.business.user.dao.UserDao;
import com.iharbor.common.util.Constant;
import com.iharbor.core.supper.dao.AutoWiredSupperDao;

@Repository("UserDao")
public class UserDaoImpl extends AutoWiredSupperDao implements UserDao{
	
	
	public List<UserMainInfo> findUserAbility(){
		return super.getSupperDao(Constant.SUPPERDAO).findList("TB_USER.", null);
	}
	
	public Integer saveUserDailyAbility(UserMainInfo usermaininfo) {
		return super.getSupperDao(Constant.SUPPERDAO).save("TB_USER.insertUserDailyAbility", usermaininfo);
	}
}
