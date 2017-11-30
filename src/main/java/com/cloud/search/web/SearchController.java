package com.cloud.search.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

import com.cloud.search.model.SearchLogsModel;
import com.cloud.search.model.SearchModel;
import com.cloud.search.model.SearchResultModel;
import com.cloud.search.service.SearchService;

/** 
 * @author tobber
 * @version 2017年11月7日
 */

@Api(value="search",description="简历搜索Controller")
@Controller
@RequestMapping(value="search")
public class SearchController {
	
	@Resource
	private SearchService searchService;

	@ApiOperation(value="搜索路由",httpMethod="GET")
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView login(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("resumeSearch");
	}
	
	@ApiOperation(value="简历搜索",httpMethod="POST")
	@ApiImplicitParam(name = "searchModel", value = "简历搜索条件实体类", required = false, dataType = "SearchModel", paramType = "query")
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=SearchResultModel.class)
	})
	@RequestMapping(value="cvsearch",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SearchResultModel> cvsearch(
		SearchModel searchModel, 
		HttpServletResponse response,HttpServletRequest request
	){
		return searchService.cvsearch(searchModel,response, request);
	}
	
	@ApiOperation(value="获取前三条搜索记录",httpMethod="POST")
	@ApiResponses({
		@ApiResponse(code=200,message="获取成功",response=SearchLogsModel.class)
	})
	@RequestMapping(value="getSearchLogs",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SearchLogsModel> getSearchLogs(HttpServletResponse response,HttpServletRequest request){
		return searchService.getSearchLogs(response, request);
	}
}
