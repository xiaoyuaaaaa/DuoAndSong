package com.cloud.user.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.user.dao.RecordDao;
import com.cloud.user.model.GoldResultModel;
import com.cloud.user.model.RecordListModel;
import com.cloud.user.model.RecordQueryModel;
import com.cloud.user.model.StorageQueryModel;
import com.cloud.user.service.RecordService;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
@Service
public class RecordServiceImpl implements RecordService{
	
	@Resource
	private RecordDao recordDao;
	

	@Override
	public ResponseEntity<RecordListModel> getGainLogs(
			RecordQueryModel recordQueryModel,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			RecordListModel recordModel = new RecordListModel(200,"获取成功");
			recordQueryModel.setUserId(request.getSession().getAttribute("userId")+"");
			int page = recordQueryModel.getPage();
			recordQueryModel.setPage((page-1)*recordQueryModel.getPageSize());
			int rowcount = recordDao.getGainLogsTotal(recordQueryModel);
			recordModel.setLogList(recordDao.getGainLogs(recordQueryModel));
			recordModel.setRowCount(rowcount);
			recordModel.setPage(page);
			recordModel.setPageSize(recordQueryModel.getPageSize());
			if(rowcount%recordQueryModel.getPageSize()==0){
				recordModel.setTotalPage(rowcount/recordQueryModel.getPageSize());
    		}else{
    			recordModel.setTotalPage((rowcount/recordQueryModel.getPageSize())+1);
    		}
			return new ResponseEntity<RecordListModel>(recordModel, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RecordListModel>(new RecordListModel(201,"服务器内部错误"), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<RecordListModel> getStorageLogs(
			StorageQueryModel storageQueryModel,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			RecordListModel recordModel = new RecordListModel(200,"获取成功");
			storageQueryModel.setUserId(request.getSession().getAttribute("userId")+"");
			int page = storageQueryModel.getPage();
			storageQueryModel.setPage((page-1)*storageQueryModel.getPageSize());
			recordModel.setLogList(recordDao.getStorageLogs(storageQueryModel));
			int rowcount=recordDao.getStorageLogsTotal(storageQueryModel);
			recordModel.setRowCount(rowcount);
			recordModel.setPage(page);
			recordModel.setPageSize(storageQueryModel.getPageSize());
			if(rowcount%storageQueryModel.getPageSize()==0){
				recordModel.setTotalPage(rowcount/storageQueryModel.getPageSize());
    		}else{
    			recordModel.setTotalPage((rowcount/storageQueryModel.getPageSize())+1);
    		}
			return new ResponseEntity<RecordListModel>(recordModel, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RecordListModel>(new RecordListModel(201,"服务器内部错误"), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<GoldResultModel> getGoldLogs(
			Integer page,Integer pageSize,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			GoldResultModel recordModel = new GoldResultModel(200,"获取成功");
			recordModel.setLogList(recordDao.getGoldLogs(request.getSession().getAttribute("userId")+"", (page-1)*pageSize, pageSize));
			int rowcount = recordDao.getGoldLogsTotal(request.getSession().getAttribute("userId")+"", page, pageSize);
			recordModel.setRowCount(rowcount);
			recordModel.setPage(page);
			recordModel.setPageSize(pageSize);
			if(rowcount%pageSize==0){
				recordModel.setTotalPage(rowcount/pageSize);
    		}else{
    			recordModel.setTotalPage((rowcount/pageSize)+1);
    		}
			return new ResponseEntity<GoldResultModel>(recordModel, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GoldResultModel>(new GoldResultModel(201,"服务器内部错误"), HttpStatus.OK);
		}
	}

}
