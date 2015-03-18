package com.iharbor.business.entity;

import java.io.Serializable;
import java.util.Date;

import com.iharbor.common.util.Constant;

public class UserMainInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6219560653671717606L;
	private Integer userid;
	private Integer testDay = Constant.INIT_TEST_DAY;
	private Integer testCycle = Constant.INIT_TEST_CYCLE ;
	private Date testStartTime;
	private Integer speed = Constant.INIT_BPI;
	private Integer memory = Constant.INIT_BPI;
	private Integer attention = Constant.INIT_BPI;
	private Integer flexibility = Constant.INIT_BPI;
	private Integer thinking = Constant.INIT_BPI;
	private Integer BPI = Constant.INIT_BPI;
	private Integer initScore = 0;
	private Date lastTime;
	
	
	public Integer getTestDay() {
		return testDay;
	}
	public void setTestDay(Integer testDay) {
		this.testDay = testDay;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getMemory() {
		return memory;
	}
	public void setMemory(Integer memory) {
		this.memory = memory;
	}
	public Integer getAttention() {
		return attention;
	}
	public void setAttention(Integer attention) {
		this.attention = attention;
	}
	public Integer getFlexibility() {
		return flexibility;
	}
	public void setFlexibility(Integer flexibility) {
		this.flexibility = flexibility;
	}
	
	public Integer getBPI() {
		return BPI;
	}
	public void setBPI(Integer bPI) {
		BPI = bPI;
	}
	public Integer getInitScore() {
		return initScore;
	}
	public void setInitScore(Integer initScore) {
		this.initScore = initScore;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getTestCycle() {
		return testCycle;
	}
	public void setTestCycle(Integer testCycle) {
		this.testCycle = testCycle;
	}
	public Date getTestStartTime() {
		return testStartTime;
	}
	public void setTestStartTime(Date testStartTime) {
		this.testStartTime = testStartTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Integer getThinking() {
		return thinking;
	}
	public void setThinking(Integer thinking) {
		this.thinking = thinking;
	}
	
	
	
}
