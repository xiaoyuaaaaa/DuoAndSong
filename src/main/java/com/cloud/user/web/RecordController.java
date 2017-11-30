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
import com.cloud.user.model.GoldResultModel;
import com.cloud.user.model.RecordListModel;
import com.cloud.user.model.RecordQueryModel;
import com.cloud.user.model.StorageQueryModel;
import com.cloud.user.service.RecordService;

/** 
 * @author tobber
 * @version 2017年11月13日
 */

@Api(value="record",description="操作记录Controller")
@Controller
@RequestMapping(value="record")
public class RecordController {
	
	@Resource
	private RecordService recordService;
	
	@ApiOperation(value="金币记录路由",httpMethod="GET")
	@RequestMapping(value="gold",method=RequestMethod.GET)
	public ModelAndView gold(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("goldRecord");
	}
	
	@ApiOperation(value="记录管理路由",httpMethod="GET")
	@RequestMapping(value="main",method=RequestMethod.GET)
	public ModelAndView main(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("resumeManage");
	}
	
	
	@ApiOperation(value="获取记录列表",httpMethod="POST")
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=RecordListModel.class)
	})
	@RequestMapping(value="getGainLogs",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<RecordListModel> getGainLogs(RecordQueryModel recordQueryModel,HttpServletResponse response,HttpServletRequest request){
		return recordService.getGainLogs(recordQueryModel,response, request);
	}
	
	
	@ApiOperation(value="获取暂存记录列表",httpMethod="POST")
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=RecordListModel.class)
	})
	@RequestMapping(value="getStorageLogs",method=RequestMethod.POST)
	public ResponseEntity<RecordListModel> getStorageLogs(StorageQueryModel storageQueryModel,HttpServletResponse response,HttpServletRequest request){
		return recordService.getStorageLogs(storageQueryModel,response, request);
	}
	
	@ApiOperation(value="获取金币消费记录",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true, dataType = "int", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=GoldResultModel.class)
	})
	@RequestMapping(value="getGoldLogs",method=RequestMethod.POST)
	public ResponseEntity<GoldResultModel> getGoldLogs(Integer page,Integer pageSize,
			HttpServletResponse response,HttpServletRequest request){
		return recordService.getGoldLogs(page,pageSize,response, request);
	}
	
}
