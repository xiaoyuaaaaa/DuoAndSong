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
import com.cloud.user.service.ForgetService;

/** 
 * @author tobber
 * @version 2017年11月15日
 */

@Api(value="forget",description="忘记密码Controller")
@Controller
@RequestMapping(value="forget")
public class ForgotPasswordController {
	
	@Resource
	private ForgetService forgetService;
	
	@ApiOperation(value="忘记密码路由",httpMethod="GET")
	@RequestMapping(value="forget",method=RequestMethod.GET)
	public ModelAndView forget(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("upwd");
	}
	
	@ApiOperation(value="忘记密码",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mobile_code", value = "手机验证码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "telephone", value = "手机号码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "password", value = "新密码", required = true, dataType = "String", paramType = "query")
	})
	@ApiResponses({
		@ApiResponse(code=200,message="修改成功",response=ResultBaseModel.class)
	})
	@RequestMapping(value="forgotPassword",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> forgetPassword(String mobile_code,String telephone,String password,HttpServletResponse response,HttpServletRequest request){
		return forgetService.forgetPassword(mobile_code,telephone,password,response, request);
	}

}
