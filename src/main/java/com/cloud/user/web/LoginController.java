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
import com.cloud.user.service.LoginService;

/** 
 * @author tobber
 * @version 2017年11月6日
 */

@Api(value="login",description="用户登录Controller")
@Controller
@RequestMapping(value="login")
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@ApiOperation(value="用户登录页面路由",httpMethod="GET")
	@RequestMapping(value="login",method=RequestMethod.GET)
	public ModelAndView login(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("signin");
	}
	
	@ApiOperation(value="用户登录接口",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userName", value = "用户邮箱或手机号码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "passWord", value = "注册密码", required = true, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@ResponseBody
	@RequestMapping(value="userLogin",method=RequestMethod.POST)
	public ResponseEntity<ResultBaseModel> userLogin(
		String userName, 
		String passWord, 
		HttpServletResponse response,HttpServletRequest request
	){
		return loginService.userLogin(userName, passWord, response, request);
	}
}
