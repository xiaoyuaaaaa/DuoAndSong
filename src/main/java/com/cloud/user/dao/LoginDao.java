package com.cloud.user.dao;

import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @author tobber
 * @version 2017年11月6日
 */
public interface LoginDao {
	
	/**
	 * 用户登录
	 * @return
	 */
	@Transactional
	public String userLogin(Map<String, Object> paramsMap);
}
