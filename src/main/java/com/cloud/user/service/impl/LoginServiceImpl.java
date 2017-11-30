package com.cloud.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.dao.LoginDao;
import com.cloud.user.service.LoginService;
import com.cloud.util.CheckUtil;
import com.cloud.util.MD5Util;

/** 
 * @author tobber
 * @version 2017年11月6日
 */
@Service
public class LoginServiceImpl implements LoginService{
	
	@Resource
	private LoginDao loginDao;
	
	@Override
	public ResponseEntity<ResultBaseModel> userLogin(
		String userName,
		String passWord,
		HttpServletResponse response,HttpServletRequest request
	) {
		try {
			if(CheckUtil.checkEmail(userName) || CheckUtil.checkMobie(userName)){ //邮箱验证、手机号验证
				if(CheckUtil.matcheVerify(passWord, "[0-9_A-Za-z]{6,16}")){
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("userName", userName);
					paramMap.put("passWord",  MD5Util.encode2hex(passWord));
					loginDao.userLogin(paramMap);
					if(paramMap.get("resultNumber")!=null && paramMap.get("resultNumber").toString().equals("200")){
						String email = paramMap.get("resultEmail").toString().replace("@", "&");
                        Cookie c= new Cookie("userEmail",email); 
                        c.setPath("/");
                        response.addCookie(c);
                        request.getSession().setAttribute("userId", paramMap.get("resultUserId").toString());
                        request.getSession().setAttribute("status", paramMap.get("resultStatus").toString());
                        request.getSession().setAttribute("email", paramMap.get("resultEmail").toString());
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"登录成功"), HttpStatus.OK);
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(Integer.parseInt(paramMap.get("resultNumber").toString()),paramMap.get("resultMessage").toString()), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"密码格式错误"), HttpStatus.OK);
				}
        	}else{
        		return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"用户名格式错误"), HttpStatus.OK);
        	}
        } catch (Exception e) { 
            e.printStackTrace();  
            return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
        }
	}
}
