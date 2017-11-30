package com.cloud.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adobe.xmp.impl.Base64;
import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.dao.UserRegistDao;
import com.cloud.user.service.UserRegistService;
import com.cloud.util.CheckUtil;
import com.cloud.util.MD5Util;

/** 
 * @author tobber
 * @version 2017年11月6日
 */
@Service
public class UserRegistServiceImpl implements UserRegistService{
	@Resource
	private UserRegistDao userRegistDao;

	@Override
	public ResponseEntity<ResultBaseModel> userRegister(
		String userEmail,
		String passWord, String userName,
		String userPhone,String mobileCode,String shareCode,
		HttpServletResponse response,HttpServletRequest request
	) {
		try {
			if(request.getSession().getAttribute("sms")!=null && !"".equals(request.getSession().getAttribute("sms"))){
				//mobileCode_时间戳_手机号
				String[] smsStr = request.getSession().getAttribute("sms").toString().split("_");
				if((System.currentTimeMillis()-Long.parseLong(smsStr[1]))<60000){
					if(mobileCode.equals(smsStr[0]) && userPhone.equals(smsStr[2])){ //判断短信验证是否成功
						if(CheckUtil.checkEmail(userEmail)){ //邮箱验证
							if(CheckUtil.matcheVerify(passWord, "[0-9_A-Za-z]{6,16}")){
								if(CheckUtil.strVerify(userName, 1, 50)){
									Map<String, Object> paramMap = new HashMap<String, Object>();
									paramMap.put("userEmail", userEmail);
									paramMap.put("passWord",  MD5Util.encode2hex(passWord));
									paramMap.put("userName", userName);
									paramMap.put("userPhone", userPhone);
									try {
										if(shareCode!=null && CheckUtil.matcheVerify(Base64.decode(shareCode),"[0-9]{1,10}")){
											paramMap.put("shareCode", Integer.parseInt(Base64.decode(shareCode)));
										}else{
											paramMap.put("shareCode", 0);
										}
									} catch (Exception e) {
										paramMap.put("shareCode", 0);
									}
									userRegistDao.user_register(paramMap);
									int resultNumber = Integer.parseInt(paramMap.get("resultNumber").toString());
									if(resultNumber>3){
										String _useName = userEmail.replace("@", "&");
				                        Cookie c= new Cookie("userEmail",_useName); 
				                        c.setPath("/");
				                        response.addCookie(c);
				                        request.getSession().setAttribute("userId", resultNumber);
				                        request.getSession().setAttribute("email", userEmail);
										return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"注册成功"), HttpStatus.OK);
									}else if(resultNumber==2){
										return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"邮箱已被注册"), HttpStatus.OK);
									}else{
										return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(302,"手机号码已被注册"), HttpStatus.OK);
									}
								}else{
									return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请输入正确的用户姓名"), HttpStatus.OK);
								}
							}else{
								return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"密码格式错误"), HttpStatus.OK);
							}
			        	}else{
			        		return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"邮箱格式错误"), HttpStatus.OK);
			        	}
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(302,"手机验证码错误"), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(302,"手机验证码已过期"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请正确操作"), HttpStatus.OK);
			}
        } catch (Exception e) { 
            e.printStackTrace();  
            return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
        }
	}

}
