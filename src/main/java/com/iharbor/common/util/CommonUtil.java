package com.iharbor.common.util;

public class CommonUtil {

	public static String fetchData(String input){
		String rtn = input;
		if(input != null && input.indexOf("=")>=0){
			String[] keyValue = input.split("=");
			if(keyValue != null && keyValue.length == 2){
				rtn = keyValue[1];
			}
		}
		
		return rtn;
	}
}
