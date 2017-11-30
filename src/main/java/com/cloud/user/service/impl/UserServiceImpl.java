package com.cloud.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adobe.xmp.impl.Base64;
import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.dao.UserDao;
import com.cloud.user.model.User;
import com.cloud.user.service.UserService;
import com.cloud.util.SendCloudThread;
import com.cloud.util.Util;
import com.cloud.util.rsa.RSAcode;

/** 
 * @author tobber
 * @version 2017年11月7日
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public ResponseEntity<User> getUserInfo(HttpServletResponse response, HttpServletRequest request) {
		try {
			String userId = request.getSession().getAttribute("userId").toString();
			User user = userDao.getUserInfo(userId);
			if( user != null ){
				user.setCode(200);
				user.setMessage("获取成功");
				user.setShareCode(Base64.encode(user.getShareCode()));
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}else{
				return new ResponseEntity<User>(new User(301,"暂无用户信息"), HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(new User(301,"服务器内部错误"), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResultBaseModel> userCancel(HttpServletResponse response, HttpServletRequest request) {
		try {
			Util.wipeSession(request, response);
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"退出成功"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResultBaseModel> sendCheckEmail(String imgCode ,HttpServletResponse response, HttpServletRequest request) {
		try {
			if(request.getSession().getAttribute("imgCheckcode")!=null){
				String imgCheckcode = request.getSession().getAttribute("imgCheckcode").toString();
				request.getSession().setAttribute("imgCheckcode", null);
				if(imgCode!=null && imgCheckcode.toUpperCase().equals(imgCode.toUpperCase())){
					int isSuccessStatus = userDao.getIsSuccess(request.getSession().getAttribute("userId").toString());
					if(isSuccessStatus>0){
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"您已经验证邮箱，无需重复验证"), HttpStatus.OK);
					}else{
						int mobile_code = (int)((Math.random()*9+1)*100000);
						String substitution_vars = "{\"to\": [\""+request.getSession().getAttribute("email")+"\"], \"sub\" : { \"%emailCode%\" : [\""+mobile_code+"\"]}}";
                        new Thread(new SendCloudThread("rm_email_check", substitution_vars, "简历云验证邮件",false)).start();
                        request.getSession().setAttribute("email_code", mobile_code);
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"发送成功"), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"验证码错误"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请正确操作"), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResultBaseModel> checkEmail(String email_code ,HttpServletResponse response, HttpServletRequest request) {
		try {
			if(request.getSession().getAttribute("email_code")!=null){
				String code = request.getSession().getAttribute("email_code").toString();
				if(email_code!=null && code.toUpperCase().equals(email_code.toUpperCase())){
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("userId", request.getSession().getAttribute("userId").toString());
					userDao.emailCheck(paramMap);
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(Integer.parseInt(paramMap.get("resultNumber").toString()),paramMap.get("resultMessage").toString()), HttpStatus.OK);
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"验证码错误"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"验证码已失效，请重新发送"), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
		}
	}

}
