package com.cloud.user.dao;

import java.util.Map;

import com.cloud.user.model.EditUser;

/** 
 * @author tobber
 * @version 2017年11月14日
 */
public interface ForgetDao {

	
	/**
	 * 重置密码
	 * @param user
	 * @return
	 */
	public Integer editPassword(Map<String, Object> paramMap);
}
