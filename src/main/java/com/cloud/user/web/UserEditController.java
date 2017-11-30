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

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.dao.EditDao;
import com.cloud.user.model.EditUser;
import com.cloud.user.model.User;
import com.cloud.user.service.UserEditService;

/** 
 * @author tobber
 * @version 2017年11月14日
 */

@Api(value="edit",description="用户信息修改")
@Controller
@RequestMapping(value="edit")
public class UserEditController {
	
	@Resource
	private UserEditService userEditService;

	@ApiOperation(value="修改用户基本信息",httpMethod="POST")
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="editBaseInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> editBaseInfo(EditUser user,HttpServletResponse response,HttpServletRequest request){
		return userEditService.editBaseInfo(user,response, request);
	}
	
	@ApiOperation(value="修改密码",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "oldPassWord", value = "原密码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "newPassWord", value = "新密码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "confirmPassWord", value = "确认密码", required = true, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="editPassword",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> editPassword(
			String oldPassWord,
			String newPassWord,
			String confirmPassWord,
			HttpServletResponse response,HttpServletRequest request){
		return userEditService.editPassword(oldPassWord,newPassWord,confirmPassWord,response, request);
	}
	
	@ApiOperation(value="修改邮箱发送Email",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "imgCode", value = "图片验证码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "email", value = "新邮箱", required = true, dataType = "String", paramType = "query")
	})
	@ApiResponses({
		@ApiResponse(code=200,message="发送成功",response=ResultBaseModel.class)
	})
	@RequestMapping(value="sendEidtEmail",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> sendEidtEmail(String imgCode,String email,HttpServletResponse response,HttpServletRequest request){
		return userEditService.sendEidtEmail(imgCode,email,response, request);
	}
	
	@ApiOperation(value="邮箱修改",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email_code", value = "邮箱验证码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "email", value = "新邮箱", required = true, dataType = "String", paramType = "query")
	})
	@ApiResponses({
		@ApiResponse(code=200,message="修改成功",response=ResultBaseModel.class)
	})
	@RequestMapping(value="editEmail",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> editEmail(String email_code,String email,HttpServletResponse response,HttpServletRequest request){
		return userEditService.editEmail(email_code,email,response, request);
	}
	
	@ApiOperation(value="修改手机号码",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mobile_code", value = "手机验证码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "telephone", value = "新手机号码", required = true, dataType = "String", paramType = "query")
	})
	@ApiResponses({
		@ApiResponse(code=200,message="修改成功",response=ResultBaseModel.class)
	})
	@RequestMapping(value="editPhone",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> editPhone(String mobile_code,String telephone,HttpServletResponse response,HttpServletRequest request){
		return userEditService.editPhone(mobile_code,telephone,response, request);
	}
}
