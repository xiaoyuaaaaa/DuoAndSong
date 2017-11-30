package com.cloud.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.model.EditUser;
import com.cloud.user.model.User;

/** 
 * @author tobber
 * @version 2017年11月14日
 */
public interface UserEditService {
	
	/**
     * 修改用户基本信息
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> editBaseInfo(EditUser user,HttpServletResponse response,HttpServletRequest request);

    
    /**
     * 修改密码
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> editPassword(
    		String oldPassWord,
			String newPassWord,
			String confirmPassWord,
    		HttpServletResponse response,HttpServletRequest request);

    /**
     * 修改邮箱发送Email
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> sendEidtEmail(String imgcode,String email, HttpServletResponse response,HttpServletRequest request);
    
    /**
     * 修改邮箱
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> editEmail(String email_code,String email,HttpServletResponse response,HttpServletRequest request);

    /**
     * 修改手机号码
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> editPhone(String mobile_code,String telephone,HttpServletResponse response,HttpServletRequest request);

}
