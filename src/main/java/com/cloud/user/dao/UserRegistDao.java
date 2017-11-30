package com.cloud.user.dao;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/** 
 * @author tobber
 * @version 2017年11月6日
 */
public interface UserRegistDao {
	
	/**
	 * 企业注册
	 * @return
	 */
	@Transactional
	public void user_register(Map<String, Object> paramsMap);
}
