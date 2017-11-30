package com.cloud.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cloud.notice.model.NoticeModel;

/** 
 * @author tobber
 * @version 2017年11月27日
 */
public interface NoticeDao {

	/**
	 * 根据通知信息ID获取详情
	 * @param noticeModel
	 * @return
	 */
	public NoticeModel getNoticeInfo(Integer noticeId);
	
	/**
	 * 获取消息通知列表
	 * @param noticeModel
	 * @return
	 */
	public List<NoticeModel> getNoticeList(Map<String, Object> paramMap);
	public int getNoticeListTotal(Map<String, Object> paramMap);
	
	/**
	 * 更改消息通知阅读状态
	 * @param paramMap
	 * @return
	 */
	public int updateNoticeReadStatus(Map<String, Object> paramMap);
	
}
