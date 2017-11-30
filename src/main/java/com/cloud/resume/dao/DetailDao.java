package com.cloud.resume.dao;

import java.util.Map;
import com.cloud.resume.model.NewResume;

/** 
 * @author tobber
 * @version 2017年11月8日
 */
public interface DetailDao {
	/**
	 * 获取简历详情
	 * @return
	 */
	public NewResume getResumeDetailByResumeId(Map<String, Object> paramsMap);
	
	/**
	 * 添加阅读记录
	 * @return
	 */
	public int addReadLogs(Map<String, Object> paramsMap);
	
}
