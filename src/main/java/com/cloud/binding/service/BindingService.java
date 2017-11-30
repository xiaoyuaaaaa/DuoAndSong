package com.cloud.binding.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.binding.model.BindingModel;
import com.cloud.common.model.ResultBaseModel;
/**
 * @version 2017年11月9日11:57:22
 * @author zqs
 *
 */
public interface BindingService {
	
	/**
	 * 账户绑定
	 * @param type
	 * @param compName
	 * @param userName
	 * @param pwd
	 * @param response
	 * @param request
	 * @return
	 */
	public ResponseEntity<ResultBaseModel> accountBinding(String type,String compName,String userName,String pwd,HttpServletResponse response,HttpServletRequest request);
	
	/**
	 * 获取用户绑定状态 
	 * @param response
	 * @param request
	 * @return
	 */
	public ResponseEntity<BindingModel> getUserBingdingStatus(HttpServletResponse response,HttpServletRequest request);
}
