package com.cloud.resume.dao;

import java.util.List;
import java.util.Map;

import com.cloud.resume.model.NewResume;

public interface ResumeDao {
	
	public String getUserIdByPhone(String telephone);
	/**
	 * 添加简历基本信息
	 * @param condition
	 * @return
	 */
	public int add_base_info(Map<String, Object> condition);
	
	/**
	 * 更新简历基本信息
	 * @param condition
	 * @return
	 */
	public int updateResumeInfo(Map<String, Object> condition);
	
	/**
	 * 添加简历详情信息
	 * @param condition
	 * @return
	 */
	public int add_resume_detail(Map<String, Object> condition);
	
	
	/**
	 * 根据简历获取简历详情
	 * @param userId
	 * @return
	 */
	public NewResume queryResumeByResumeId(int userId);
	
	/**
	 * 导出EXCEL时获取简历信息
	 * @return
	 */
	public List<NewResume> queryResumeByResumeIdList(Map<String, Object> params);
	
	/**
	 * 获取下载简历列表
	 * @return
	 */
	public List<Map<String,Object>> getBatchDownUser(Map<String, Object> condition);
}
