package com.cloud.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.user.model.GoldResultModel;
import com.cloud.user.model.RecordListModel;
import com.cloud.user.model.RecordQueryModel;
import com.cloud.user.model.StorageQueryModel;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
public interface RecordService {

	/**
     * 获取用户的获取记录列表
     * @param request
     * @return
     */
	public ResponseEntity<RecordListModel> getGainLogs(
			RecordQueryModel recordQueryModel,HttpServletResponse response,HttpServletRequest request
		);
	
	/**
     * 获取暂存记录列表
     * @param request
     * @return
     */
	public ResponseEntity<RecordListModel> getStorageLogs(
			StorageQueryModel storageQueryModel,HttpServletResponse response,HttpServletRequest request
		);
	
	/**
     * 获取暂存记录列表
     * @param request
     * @return
     */
	public ResponseEntity<GoldResultModel> getGoldLogs(
			Integer page,Integer pageSize,HttpServletResponse response,HttpServletRequest request
		);
}
