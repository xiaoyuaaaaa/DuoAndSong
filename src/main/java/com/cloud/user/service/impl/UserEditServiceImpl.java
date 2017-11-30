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
import com.cloud.user.dao.EditDao;
import com.cloud.user.model.EditUser;
import com.cloud.user.service.UserEditService;
import com.cloud.util.CheckUtil;
import com.cloud.util.MD5Util;
import com.cloud.util.SendCloudThread;

/** 
 * @author tobber
 * @version 2017年11月14日
 */

@Service
public class UserEditServiceImpl implements UserEditService{
	
	@Resource
	private EditDao editDao;

	@Override
	public ResponseEntity<ResultBaseModel> editBaseInfo(EditUser user,HttpServletResponse response,HttpServletRequest request) {
		try {
			user.setUserId(Integer.parseInt(request.getSession().getAttribute("userId")+""));
			int num = editDao.editBaseInfo(user);
			if(num>0){
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"修改成功"), HttpStatus.OK);
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"修改失败"), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<ResultBaseModel> editPassword(String oldPassWord,
			String newPassWord, String confirmPassWord,
			HttpServletResponse response, HttpServletRequest request) {
		
		try {
			if(CheckUtil.matcheVerify(oldPassWord, "[0-9_A-Za-z]{6,16}") && CheckUtil.matcheVerify(newPassWord, "[0-9_A-Za-z]{6,16}")){
				if(newPassWord.equals(confirmPassWord)){
					if(!oldPassWord.equals(newPassWord)){
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("userId", request.getSession().getAttribute("userId").toString());
						paramMap.put("oldPassWord", MD5Util.encode2hex(oldPassWord));
						paramMap.put("newPassWord", MD5Util.encode2hex(newPassWord));
						int num = editDao.editPassWord(paramMap);
						if(num>0){
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"修改成功"), HttpStatus.OK);
						}else{
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"原密码错误"), HttpStatus.OK);
						}
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请确保新密码与原密码不同"), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"确认密码与新密码不一致"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请输入正确的密码格式"), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResultBaseModel> sendEidtEmail(String imgCode,String email,HttpServletResponse response, HttpServletRequest request) {
		try {
			if(request.getSession().getAttribute("imgCheckcode")!=null){
				if(CheckUtil.checkEmail(email)){
					String imgCheckcode = request.getSession().getAttribute("imgCheckcode").toString();
					request.getSession().setAttribute("imgCheckcode", null);
					if(imgCode!=null && imgCheckcode.toUpperCase().equals(imgCode.toUpperCase())){
						int isSuccessStatus = editDao.findEmaiByEmail(email);
						if(isSuccessStatus>0){
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"邮箱已被使用"), HttpStatus.OK);
						}else{
							int mobile_code = (int)((Math.random()*9+1)*100000);
							String substitution_vars = "{\"to\": [\""+email+"\"], \"sub\" : { \"%emailCode%\" : [\""+mobile_code+"\"]}}";
	                        new Thread(new SendCloudThread("rm_email_check", substitution_vars, "多简历验证码",false)).start();
	                        request.getSession().setAttribute("email_code", mobile_code+"AA_AA"+email);
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"发送成功"), HttpStatus.OK);
						}
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(305,"验证码错误"), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请输入正确的邮箱格式"), HttpStatus.OK);
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
	public ResponseEntity<ResultBaseModel> editEmail(String email_code,String email,HttpServletResponse response, HttpServletRequest request) {
		try {
			if(request.getSession().getAttribute("email_code")!=null){
				String code = request.getSession().getAttribute("email_code").toString().split("AA_AA")[0];
				String checkEmail = request.getSession().getAttribute("email_code").toString().split("AA_AA")[1];
				if(email_code!=null && code.toUpperCase().equals(email_code.toUpperCase())){
					if(CheckUtil.checkEmail(email) && email.equals(checkEmail)){
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("userId", request.getSession().getAttribute("userId").toString());
						paramMap.put("email", email);
						int num = editDao.editEmail(paramMap);
						if(num>0){
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"修改成功"), HttpStatus.OK);
						}else{
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"修改失败"), HttpStatus.OK);
						}
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请正确操作"), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(305,"验证码错误"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"验证码已失效，请重新发送"), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResultBaseModel> editPhone(String mobile_code,String telephone,HttpServletResponse response, HttpServletRequest request) {
		try {
			if(request.getSession().getAttribute("sms")!=null && !"".equals(request.getSession().getAttribute("sms"))){
				//mobileCode_时间戳_手机号
				String[] smsStr = request.getSession().getAttribute("sms").toString().split("_");
				if((System.currentTimeMillis()-Long.parseLong(smsStr[1]))<60000){
					if(mobile_code.equals(smsStr[0]) && telephone.equals(smsStr[2])){ //判断短信验证是否成功
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("userId", request.getSession().getAttribute("userId").toString());
						paramMap.put("telephone", telephone);
						int num = editDao.editTelephone(paramMap);
						if(num>0){
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"修改成功"), HttpStatus.OK);
						}else{
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"修改失败"), HttpStatus.OK);
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
