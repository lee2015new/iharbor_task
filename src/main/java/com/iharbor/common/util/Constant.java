package com.iharbor.common.util;

public class Constant {

	/**
	 * Game id
	 */
	public static final int GAME_ID_DEFENDISLAND = 1;//保卫海岛
	public static final int GAME_ID_TRICKFLIGHT = 2;//特技飞行
	public static final int GAME_ID_PIRATETREASUREHUNT = 3;//海盗寻宝
	public static final int GAME_ID_JUNGLEADVENTURE = 4;//丛林探险
	public static final int GAME_ID_CATCHINGCRABS = 5;//抓螃蟹
	public static final int GAME_ID_COCOACLASS = 6;//可可课堂
	public static final int GAME_ID_ZOMBIEINVASION = 7;//僵尸入侵
	public static final int GAME_ID_MADMONEY = 8;//疯狂抢钱
	
	public static final String RTN_CODE_SUCCESS = "00001";//成功
	public static final String RTN_CODE_ILLEGAL = "00002";//应用无权限
	public static final String RTN_CODE_SESSION_FAIL = "00003";//session失效
	public static final String RTN_CODE_PARAM_IS_NULL = "00004";//无系统参数
	public static final String RTN_CODE_NO_USER_INFO = "00005";//无用户信息
	public static final String RTN_CODE_USER_REG_NAME_IS_DUP = "00006";//用户名重复
	
	public static final String RTN_VALUE_SUCCESS = "成功";
	public static final String RTN_VALUE_ILLEGAL = "应用无权限访问";
	public static final String RTN_VALUE_SESSION_FAIL = "session失效";
	public static final String RTN_VALUE_PARAM_IS_NULL = "无系统参数";
	public static final String RTN_VALUE_NO_USER_INFO = "无用户信息";
	public static final String RTN_VALUE_USER_REG_NAME_IS_DUP = "用户名重复";
	
	public static final int ABILITY_TYPE_SPEED = 1;
	public static final int ABILITY_TYPE_MEMORY = 2;
	public static final int ABILITY_TYPE_ATTENTION = 3;
	public static final int ABILITY_TYPE_FLEXIBILITY = 4;
	public static final int ABILITY_TYPE_THINKING = 5;
	public static final int ABILITY_TYPE_BPI = 6;
	
	public static final String SUPPERDAO = "supperDaoIharbor";
	public static final String USER_INFO = "userinfo";
	public static final String SESSION_ID = "sessionId";
	
	public static final Integer INIT_TEST_CYCLE = 1;
	public static final Integer INIT_TEST_DAY = 21;
	public static final int INIT_BPI = 100;
	
}
