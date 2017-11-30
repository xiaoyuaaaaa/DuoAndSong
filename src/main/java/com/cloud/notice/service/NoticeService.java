package com.cloud.notice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.notice.model.NoticeModel;
import com.cloud.util.resp.ResponsePageResult;
import com.cloud.util.resp.ResponseResult;

/** 
 * @author tobber
 * @version 2017年11月27日
 */
public interface NoticeService {
	
    /**
     * 根据消息通知ID获取通知详情
     * @param request
     * @return
     */
    public ResponseEntity<ResponseResult<NoticeModel>> getNoticeInfo(int noticeId,HttpServletRequest request);
    
    /**
     * 获取消息通知列表
     * @param request
     * @return
     */
    public ResponseEntity<ResponsePageResult<NoticeModel>> getNoticeList(int page,int pageSize,HttpServletRequest request);
    

    /**
     * 更新消息通知阅读状态
     * @param request
     * @return
     */
    public ResponseEntity<ResponseResult> editNoticeStatus(String noticeIds,HttpServletRequest request);
}
