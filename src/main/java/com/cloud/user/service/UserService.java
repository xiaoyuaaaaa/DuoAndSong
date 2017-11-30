package com.cloud.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.model.User;

/** 
 * @author tobber
 * @version 2017年11月7日
 */
public interface UserService {
	
	/**
     * 获取用户基本信息
     * @param request
     * @return
     */
    public ResponseEntity<User> getUserInfo(HttpServletResponse response,HttpServletRequest request);
    
    /**
     * 用户退出登录
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> userCancel(HttpServletResponse response,HttpServletRequest request);

    /**
     * 发送邮箱验证Email
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> sendCheckEmail(String imgcode, HttpServletResponse response,HttpServletRequest request);

    /**
     * 邮箱验证
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> checkEmail(String email_code, HttpServletResponse response,HttpServletRequest request);

}
