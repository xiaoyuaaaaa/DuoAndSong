package com.cloud.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月6日
 */
public interface LoginService {
	/**
     * 用户登录
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> userLogin(
    		String userName, 
    		String passWord, 
    		HttpServletResponse response,
    		HttpServletRequest request
    		);
}
