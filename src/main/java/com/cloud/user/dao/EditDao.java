package com.cloud.user.dao;

import java.util.Map;

import com.cloud.user.model.EditUser;

/** 
 * @author tobber
 * @version 2017年11月14日
 */
public interface EditDao {

	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 */
	public Integer editBaseInfo(EditUser user);
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public Integer editPassWord(Map<String, Object> paramMap);
	
	/**
	 * 根据邮箱查询用户
	 * @param user
	 * @return
	 */
	public Integer findEmaiByEmail(String email);
	
	/**
	 * 根据电话号码查询用户
	 * @param user
	 * @return
	 */
	public Integer findPhoneByPhone(String telephone);
	
	/**
	 * 修改邮箱
	 * @param user
	 * @return
	 */
	public Integer editEmail(Map<String, Object> paramMap);
	
	/**
	 * 修改手机号码
	 * @param user
	 * @return
	 */
	public Integer editTelephone(Map<String, Object> paramMap);
}
