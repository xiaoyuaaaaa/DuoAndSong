package com.cloud.binding.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.binding.model.BindingModel;
import com.cloud.binding.service.BindingService;
import com.cloud.common.model.ResultBaseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * @version 2017年11月9日14:42:36
 * @author zqs
 *
 */
@Api(value="binding",description="账号绑定Controller")
@Controller
@RequestMapping(value="binding")
public class BindingController {

	@Resource
	private BindingService bindingService;
	
	@ApiOperation(value="账号绑定接口",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "绑定类型(正则：[1-9]{1}、1 前程无忧、2 智联、3 拉钩、4 赶集、5 英才、6猎聘、7 五八)", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "compName", value = "会员名称(长度:1~50)", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "userName", value = "用户名字(长度:1~50)", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "pwd", value = "密码(长度:1~50)", required = true, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="accountBinding",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> accountBinding(
		String type, 
		String compName, 
		String userName,
		String pwd,
		HttpServletResponse response,HttpServletRequest request
	){
		return bindingService.accountBinding(type, compName, userName, pwd, response, request);
	}
	
	
	@ApiOperation(value="获取用户绑定状态 接口",httpMethod="GET")
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=BindingModel.class)
	})
	@RequestMapping(value="getUserBingdingStatus",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BindingModel> getUserBingdingStatus(
		HttpServletResponse response,HttpServletRequest request
	){
		return bindingService.getUserBingdingStatus(response, request);
	}
}
