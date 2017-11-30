package com.cloud.resume.dao;

import java.util.List;
import java.util.Map;

public interface BuildResumeIndexDao {
	//获取简历表数据
	public List<Map<String, Object>> getResumeInfoList(Map<String, Object> condition);
	
	//获取工作经历数据
	public List<Map<String, Object>> getWorkList(Map<String, Object> condition);
	
	public List<Map<String, Object>> getWorkListById(String userId);
	
	//获取项目经历数据
	public List<Map<String, Object>> getProjectList(Map<String, Object> condition);
	
	//获取教育经历数据
	public List<Map<String, Object>> getEducationList(Map<String, Object> condition);
	
	public List<Map<String, Object>> getEducationListById(String userId);
	
	//擅长技能数据
	public List<Map<String, Object>> getProfeSkillList(Map<String, Object> condition);
	
	//获取城市数据
	public List<Map<String, Object>> getCityList(Map<String, Object> condition);
	
	//获取自增id的最大值
	public Integer getMaxUserId();
	
	//获取所有子类ID
	public String getChildList(String id,String type);
	
	//获取所有父类ID
	public String getParentList(String id,String type);
}
