package com.cloud.resume.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.resume.service.ResumeFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="resumeFile",description="简历文件操作Controller")
@Controller
@RequestMapping(value="resumeFile")
public class ResumeFileController {
	@Resource
	private ResumeFileService resumeFileService;
	
	@ApiOperation(value="简历模板PDF初始化路由",httpMethod="GET")
	@RequestMapping(value="initPdf",method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value = "简历ID",required = true,dataType="String",paramType="query"),
	})
	public ModelAndView initPdf(String userId,HttpServletResponse response,HttpServletRequest request){
		ModelAndView mdoe = resumeFileService.initPdf(userId, request, response);
		return mdoe;
	}
	
	
	@ApiOperation(value="简历模板WORD初始化路由",httpMethod="GET")
	@RequestMapping(value="initWord",method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value = "简历ID",required = true,dataType="String",paramType="query"),
	})
	public ModelAndView initWord(String userId,HttpServletResponse response,HttpServletRequest request){
		ModelAndView mdoe = resumeFileService.initWord(userId, request, response);
		return mdoe;
	}
	
	
	@ApiOperation(value="创建pdf模板",httpMethod="GET")
	@RequestMapping(value="creatPdf",method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value = "简历ID",required = true,dataType="String",paramType="query"),
	})
	public void creatPdf(String userId,HttpServletRequest request,HttpServletResponse response) {
		resumeFileService.creatPdf(userId,request, response);
	}
	
	
	@ApiOperation(value="创建Word",httpMethod="GET")
	@RequestMapping(value="creatWord",method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value = "简历ID",required = true,dataType="String",paramType="query"),
	})
	public void creatWord(String userId,HttpServletRequest request,HttpServletResponse response) {
		resumeFileService.creatWord(userId,request, response);
	}
}
