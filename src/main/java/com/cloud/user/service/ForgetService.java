package com.cloud.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月15日
 */
public interface ForgetService {
	
	 /**
     * 忘记密码
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> forgetPassword(String mobile_code,String telephone,String password,HttpServletResponse response,HttpServletRequest request);


}
