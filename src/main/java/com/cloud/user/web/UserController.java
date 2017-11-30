package com.cloud.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.model.User;
import com.cloud.user.service.UserService;

/** 
 * @author tobber
 * @version 2017年11月7日
 */

@Api(value="user",description="用户信息获取、修改类")
@Controller
@RequestMapping(value="user")
public class UserController {
	
	@Resource
	private UserService userService;

	@ApiOperation(value="个人中心路由",httpMethod="GET")
	@RequestMapping(value="user",method=RequestMethod.GET)
	public ModelAndView user(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("user");
	}
	
	@ApiOperation(value="企业认证路由",httpMethod="GET")
	@RequestMapping(value="auth",method=RequestMethod.GET)
	public ModelAndView auth(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("companyAuth");
	}
	
	@ApiOperation(value="获取用户信息接口",httpMethod="GET")
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=User.class)
	})
	@RequestMapping(value="getUserInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> getUserInfo(HttpServletResponse response,HttpServletRequest request){
		return userService.getUserInfo(response, request);
	}
	
	@ApiOperation(value="用户退出登录",httpMethod="GET")
	@ApiResponses({
		@ApiResponse(code=200,message="用户退出登录",response=ResultBaseModel.class)
	})
	@RequestMapping(value="userCancel",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> userCancel(HttpServletResponse response,HttpServletRequest request){
		return userService.userCancel(response, request);
	}
	
	@ApiOperation(value="发送邮箱验证Email",httpMethod="GET")
	@ApiImplicitParam(name = "imgCode", value = "图片验证码", required = true, dataType = "String", paramType = "query")
	@ApiResponses({
		@ApiResponse(code=200,message="发送成功",response=ResultBaseModel.class)
	})
	@RequestMapping(value="sendCheckEmail",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> sendCheckEmail(String imgCode,HttpServletResponse response,HttpServletRequest request){
		return userService.sendCheckEmail(imgCode,response, request);
	}
	
	@ApiOperation(value="邮箱验证",httpMethod="GET")
	@ApiImplicitParam(name = "email_code", value = "邮件验证码", required = true, dataType = "String", paramType = "query")
	@ApiResponses({
		@ApiResponse(code=200,message="验证成功",response=ResultBaseModel.class)
	})
	@RequestMapping(value="checkEmail",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> checkEmail(String email_code,HttpServletResponse response,HttpServletRequest request){
		return userService.checkEmail(email_code,response, request);
	}
}
