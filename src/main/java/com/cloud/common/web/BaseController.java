package com.cloud.common.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @author tobber
 * @version 2017年11月7日
 */

@Api(value="base",description="首页Controller")
@Controller
@RequestMapping(value="base")
public class BaseController {

	@ApiOperation(value="首页路由",httpMethod="GET")
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView login(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("index");
	}
}
