package com.cloud.resume.web;

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

import com.cloud.resume.model.NewResume;
import com.cloud.resume.service.DetailService;
import com.cloud.util.rsa.RSAUtil;

/** 
 * @author tobber
 * @version 2017年11月8日
 */

@Api(value="cv",description="简历详情Controller")
@Controller
@RequestMapping(value="cv")
public class DetailController {

	@Resource
	private DetailService detailService;
	
	
	@ApiOperation(value="简历详情页面路由",httpMethod="GET")
	@RequestMapping(value="cv_detail",method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value = "简历ID",required = true,dataType="String",paramType="query"),
		@ApiImplicitParam(name="token",value = "搜索token",required = true,dataType="String",paramType="query"),
	})
	public ModelAndView cv_detail(String userId,String token,HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("detail");
		modelAndView.addObject("token",RSAUtil.encryptByPublicKey(userId+token));
		return modelAndView;
	}
	
	@ApiOperation(value="获取简历详情接口",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name="resumeId",value = "简历ID",required = true,dataType="int",paramType="query"),
		@ApiImplicitParam(name="searchId",value = "简历搜索ID",required = true,dataType="int",paramType="query"),
		@ApiImplicitParam(name="token",value = "搜索token",required = true,dataType="String",paramType="query"),
		@ApiImplicitParam(name="r_t",value = "详情页面中的动态码",required = true,dataType="String",paramType="query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=NewResume.class)
	})
	@RequestMapping(value="getCvDetail",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<NewResume> getCvDetail(
		Integer resumeId,
		Integer searchId,
		String token,
		String r_t,
		HttpServletResponse response,HttpServletRequest request
	){
		return detailService.getCvDetail(resumeId, searchId,token, r_t,response, request);
	}
	
}
