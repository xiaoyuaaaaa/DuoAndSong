package com.cloud.user.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.service.UserRegistService;

/** 
 * @author tobber
 * @version 2017年11月1日
 */
@Api(value="regist",description="用户注册Controller")
@Controller
@RequestMapping(value="regist")
public class RegisterController {
	@Resource
	private UserRegistService userRegistService;
	
	@ApiOperation(value="用户注册页面路由",httpMethod="GET")
	@RequestMapping(value="regist",method=RequestMethod.GET)
	public ModelAndView regist(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("signup");
	}
	
	@ApiOperation(value="用户注册接口",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userEmail", value = "用户邮箱(正则：^([a-z0-9A-Z]+[-|_\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "passWord", value = "注册密码(正则：[0-9_A-Za-z]{6,16})", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "userName", value = "用户名字(长度:1~50)", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "userPhone", value = "手机号码(正则:^1(3[0-9]|4[57]|5[0-35-9]|7[0-9]|8[0-9])\\d{8}$)", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "mobileCode", value = "短信验证码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "shareCode", value = "推荐码", required = false, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="userRegist",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> userRegist(
		String userEmail, 
		String passWord, 
		String userName,
		String userPhone,
		String mobileCode,
		String shareCode,
		HttpServletResponse response,HttpServletRequest request
	){
		return userRegistService.userRegister(userEmail, passWord, userName, userPhone, mobileCode,shareCode, response, request);
	}
	
	@ApiOperation(value="用户协议路由",httpMethod="GET")
	@RequestMapping(value="agreement",method=RequestMethod.GET)
	public ModelAndView agreement(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("agreement");
	}
}
