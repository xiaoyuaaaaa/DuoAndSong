package com.cloud.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月6日
 */
public interface UserRegistService {
	
	/**
     * 用户注册
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> userRegister(
    		String userEmail, 
    		String passWord, 
    		String userName,
    		String userPhone,
    		String mobileCode,
    		String shareCode,
    		HttpServletResponse response,
    		HttpServletRequest request
    		);

}
