package com.cloud.resume.dao;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
public interface OperationDao {

	/**
	 * 获取联系方式
	 * @param paramsMap
	 */
	@Transactional
	public void getCvContact(Map<String, Object> paramsMap);
	
	/**
	 * 添加暂存记录 
	 * @param paramsMap
	 */
	public Integer addStorageLogs(Map<String, Object> paramsMap);
	

	/**
	 * 取消暂存 
	 * @param paramsMap
	 */
	public Integer delStorageLogs(Map<String, Object> paramsMap);
}
