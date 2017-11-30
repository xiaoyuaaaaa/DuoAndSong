package com.cloud.user.dao;

import java.util.List;

import com.cloud.user.model.GoldModel;
import com.cloud.user.model.RecordQueryModel;
import com.cloud.user.model.RecordResultModel;
import com.cloud.user.model.StorageQueryModel;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
public interface RecordDao {
	/**
	 * 获取记录列表
	 * @return
	 */
	public List<RecordResultModel> getGainLogs (RecordQueryModel recordQueryModel);
	public Integer getGainLogsTotal(RecordQueryModel recordQueryModel);
	
	/**
	 * 获取暂存记录列表
	 * @return
	 */
	public List<RecordResultModel> getStorageLogs (StorageQueryModel storageQueryModel);
	public Integer getStorageLogsTotal(StorageQueryModel storageQueryModel);
	
	/**
	 * 获取金币记录
	 * @return
	 */
	public List<GoldModel> getGoldLogs (String userId,Integer page,Integer pageSize);
	public Integer getGoldLogsTotal(String userId,Integer page,Integer pageSize);
}
