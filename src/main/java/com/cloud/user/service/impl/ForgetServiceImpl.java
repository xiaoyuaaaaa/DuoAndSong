package com.cloud.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.dao.ForgetDao;
import com.cloud.user.service.ForgetService;
import com.cloud.util.MD5Util;

/** 
 * @author tobber
 * @version 2017年11月15日
 */

@Service
public class ForgetServiceImpl implements ForgetService{
	
	@Resource
	private ForgetDao forgetDao;
	
	@Override
	public ResponseEntity<ResultBaseModel> forgetPassword(String mobile_code,String telephone,String password,HttpServletResponse response, HttpServletRequest request) {
		try {
			if(request.getSession().getAttribute("sms")!=null && !"".equals(request.getSession().getAttribute("sms"))){
				//mobileCode_时间戳_手机号
				String[] smsStr = request.getSession().getAttribute("sms").toString().split("_");
				if((System.currentTimeMillis()-Long.parseLong(smsStr[1]))<60000){
					if(mobile_code.equals(smsStr[0]) && telephone.equals(smsStr[2])){ //判断短信验证是否成功
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("password", MD5Util.encode2hex(password));
						paramMap.put("telephone", telephone);
						int num = forgetDao.editPassword(paramMap);
						if(num>0){
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"重置成功"), HttpStatus.OK);
						}else{
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"重置失败"), HttpStatus.OK);
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
