package com.cloud.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.notice.dao.NoticeDao;
import com.cloud.notice.model.NoticeModel;
import com.cloud.notice.service.NoticeService;
import com.cloud.util.CheckUtil;
import com.cloud.util.resp.PageModel;
import com.cloud.util.resp.RespResultGenerator;
import com.cloud.util.resp.ResponsePageResult;
import com.cloud.util.resp.ResponseResult;

/** 
 * @author tobber
 * @version 2017年11月27日
 */

@Service
public class NoticeServcieImpl implements NoticeService{

	@Resource
	private NoticeDao noticeDao;
	
	
	@Override
	public ResponseEntity<ResponseResult<NoticeModel>> getNoticeInfo(int noticeId,HttpServletRequest request) {
		NoticeModel noticeModel = noticeDao.getNoticeInfo(noticeId);
		if(noticeModel!=null && noticeModel.getId()>0){
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userId", request.getSession().getAttribute("userId")+"");
			paramMap.put("noticeIds", noticeId);
			noticeDao.updateNoticeReadStatus(paramMap);
			return RespResultGenerator.genOK(noticeModel,"获取成功");
		}else{
			return RespResultGenerator.genError(null,"获取失败");	
		}
	}

	@Override
	public ResponseEntity<ResponsePageResult<NoticeModel>> getNoticeList(int page, int pageSize,HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
 		if(CheckUtil.matcheVerify(page+"", "[1-9][0-9]{0,1}")){
 			paramMap.put("page", page);
		}else{
			paramMap.put("page", 1);
		}
 		if(CheckUtil.matcheVerify(pageSize+"", "[1-9][0-9]")){
 			paramMap.put("pageSize", pageSize);
		}else{
			paramMap.put("pageSize", 15);
		}
 		paramMap.put("userId", request.getSession().getAttribute("userId")+"");
 		List<NoticeModel> list = noticeDao.getNoticeList(paramMap);
 		int rowCount = noticeDao.getNoticeListTotal(paramMap);
 		PageModel pageModel= PageModel.getPageModel(page, pageSize, rowCount);
		return RespResultGenerator.genOK(list, pageModel, "获取成功");
	}
	
	@Override
	public ResponseEntity<ResponseResult> editNoticeStatus(String noticeIds,HttpServletRequest request) {
		
		if(CheckUtil.matcheVerify(noticeIds, "[0-9,]{1,500}")){
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userId", request.getSession().getAttribute("userId")+"");
			paramMap.put("noticeIds", noticeIds);
			noticeDao.updateNoticeReadStatus(paramMap);
			return RespResultGenerator.genOK("操作成功");
		}else{
			return RespResultGenerator.genError("请正确操作");	
		}
	}
}
