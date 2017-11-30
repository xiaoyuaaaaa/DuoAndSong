package com.cloud.resume.web;

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

import com.cloud.common.model.ResultBaseModel;
import com.cloud.resume.model.ContactModel;
import com.cloud.resume.service.OperationService;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
@Api(value="oper",description="简历操作Controller")
@Controller
@RequestMapping(value="oper")
public class OperationController {
	
	@Resource
	private OperationService operationService;

	@ApiOperation(value="获取简历联系方式",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name="resumeId",value = "简历ID",required = true,dataType="int",paramType="query"),
		@ApiImplicitParam(name="searchId",value = "简历搜索ID",required = true,dataType="int",paramType="query"),
		@ApiImplicitParam(name="token",value = "搜索token",required = true,dataType="String",paramType="query"),
		@ApiImplicitParam(name="r_t",value = "详情页面中的动态码",required = true,dataType="String",paramType="query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ContactModel.class)
	})
	@RequestMapping(value="getCvContact",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ContactModel> getCvContact(
		Integer resumeId,
		Integer searchId,
		String token,
		String r_t,
		HttpServletResponse response,HttpServletRequest request
	){
		return operationService.getCvContact(resumeId, searchId,token, r_t,response, request);
	}
	
	
	@ApiOperation(value="简历暂存",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name="resumeId",value = "简历ID,多个用英文逗号隔开",required = true,dataType="String",paramType="query"),
		@ApiImplicitParam(name="searchId",value = "简历搜索ID",required = true,dataType="int",paramType="query"),
		@ApiImplicitParam(name="token",value = "搜索token",required = true,dataType="String",paramType="query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="storageCv",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> storageCv(
		String resumeId,
		Integer searchId,
		String token,
		HttpServletResponse response,HttpServletRequest request
	){
		return operationService.storageCv(resumeId, searchId,token,response, request);
	}
	
	@ApiOperation(value="取消暂存",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name="ids",value = "暂存记录ID（多个用英文逗号隔开）",required = true,dataType="String",paramType="query")
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="cancelStorageCv",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> cancelStorageCv(
		String ids,
		HttpServletResponse response,HttpServletRequest request
	){
		return operationService.cancelStorageCv(ids,response, request);
	}
}
