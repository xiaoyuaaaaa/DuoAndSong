package com.cloud.search.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import com.cloud.search.model.SearchLogsModel;
import com.cloud.search.model.SearchModel;
import com.cloud.search.model.SearchResultModel;

/** 
 * @author tobber
 * @version 2017年11月7日
 */
public interface SearchService {
	
	/**
     * 简历搜索
     * @param request
     * @return
     */
    public ResponseEntity<SearchResultModel> cvsearch(
    	SearchModel searchModel,
		HttpServletResponse response,
		HttpServletRequest request
    );
    
    /**
     * 获取前3条搜索记录
     * @param request
     * @return
     */
    public ResponseEntity<SearchLogsModel> getSearchLogs(HttpServletResponse response,HttpServletRequest request);

}
