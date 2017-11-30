package com.cloud.notice.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.notice.model.NoticeModel;
import com.cloud.notice.service.NoticeService;
import com.cloud.user.model.User;
import com.cloud.util.resp.ResponsePageResult;
import com.cloud.util.resp.ResponseResult;

/** 
 * @author tobber
 * @version 2017年11月27日
 */

@Api(value="notice",description="消息通知Controller")
@Controller
@RequestMapping(value="notice")
public class NoticeController {
	
	@Resource
	private NoticeService noticeService;
	
	@ApiOperation(value="搜索路由",httpMethod="GET")
	@RequestMapping(value="notice",method=RequestMethod.GET)
	public ModelAndView login(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=UTF-8");
		return new ModelAndView("notice");
	}
	
	@ResponseBody
	@ApiOperation(value="根据消息通知ID获取通知详情",httpMethod="POST")
	@ApiImplicitParam(name = "noticeId", value = "消息通知ID", required = true, dataType = "int", paramType = "query")
	@RequestMapping(value="getNoticeInfo",method=RequestMethod.POST)
	public ResponseEntity<ResponseResult<NoticeModel>> getNoticeInfo(int noticeId,HttpServletRequest request){
		return noticeService.getNoticeInfo(noticeId,request);
	}
	
	@ResponseBody
	@ApiOperation(value="获取消息通知列表",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true, dataType = "int", paramType = "query")
	})
	@RequestMapping(value="getNoticeList",method=RequestMethod.POST)
	public ResponseEntity<ResponsePageResult<NoticeModel>> getNoticeList(int page, int pageSize,HttpServletRequest request){
		return noticeService.getNoticeList(page,pageSize,request);
	}
	
	@ResponseBody
	@ApiOperation(value="更新消息通知阅读状态",httpMethod="GET")
	@ApiImplicitParam(name = "noticeIds", value = "消息通知ID（多个用英文逗号隔开）", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value="editNoticeStatus",method=RequestMethod.GET)
	public ResponseEntity<ResponseResult> editNoticeStatus(String noticeIds,HttpServletRequest request){
		return noticeService.editNoticeStatus(noticeIds,request);
	}
	
}
