package com.cloud.search.dao;

import java.util.List;
import java.util.Map;

import com.cloud.search.model.SearchModel;

/** 
 * @author tobber
 * @version 2017年11月8日
 */
public interface SearchDao {
	
	/**
	 * 增加搜索记录
	 * @return
	 */
	public Integer addSearchLogs(SearchModel searchModel);
	
	/**
	 * 获取前三条搜索记录
	 * @return
	 */
	public List<SearchModel> getSearchLogs(String userId);
	
	/**
	 * 获取阅读记录
	 * @return
	 */
	public List<Integer> getReadLogs(Map<String, Object> paramMap);
}
