package com.cloud.user.dao;

import java.util.Map;

import com.cloud.user.model.User;

/** 
 * @author tobber
 * @version 2017年11月7日
 */
public interface UserDao {
	
	/**
	 * 根据用户ID获取用户信息
	 * @return
	 */
	public User getUserInfo (String userId);
	
	/**
	 * 获取用户邮箱验证状态
	 * @return
	 */
	public Integer getIsSuccess (String userId);
	
	/**
	 * 邮箱验证
	 * @return
	 */
	public String emailCheck (Map<String, Object> paramMap);
}
