package com.cloud.util;

import org.apache.commons.lang.StringUtils;

/** 
 * @author tobber
 * @version 2017年11月3日
 */
public class CheckUtil {

	/**
	 * 手机验证
	 * @param phone
	 * @return
	 */
	public static Boolean checkMobie(String phone) {
		boolean flag = false;
		if(phone!=null && phone.trim().length()>1){
			flag = phone.matches("^1(3[0-9]|4[57]|5[0-35-9]|7[0-9]|8[0-9])\\d{8}$");
		}
		return flag;
	}
	
	/**
	 * 座机号码验证
	 * @param phone
	 * @return
	 */
	public static Boolean checkPhone(String phone) {
		boolean flag = false;
		if(phone!=null && phone.trim().length()>1){
			flag = phone.matches("^[0][0-9]{2,3}-[0-9]{5,10}(-[0-9]{3,5})?$");
		}
		return flag;
	}
	
	/**
	 * 邮箱验证
	 * @param phone
	 * @return
	 */
	public static Boolean checkEmail(String email) {
		boolean flag = false;
		if(email!=null && email.trim().length()>1){
			flag = email.matches("^([a-z0-9A-Z]+[-|_\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		}
		return flag;
	}
	
	/**
	 * 正则验证
	 * @param str 需要验证的字符串
	 * @param matche 正则表达式
	 * @return
	 */
	public static boolean matcheVerify(String value,String matche){
		boolean flag = StringUtils.isEmpty(value);
		if(flag){
			flag = false;
		}else{
			flag = value.matches(matche);
		}
		return flag;
	}
	
	/**
	 * 字符串长度验证
	 * @param value 验证字符串
	 * @param min 最小长度
	 * @param max 最大长度
	 * @return
	 */
	public static boolean strVerify(String value,int min,int max){
		boolean flag = StringUtils.isNotEmpty(value);
		if(flag && !value.equals("null")){
			flag = (value.trim().length()>=min && value.trim().length()<=max);
		}
		return flag;
	}
}
