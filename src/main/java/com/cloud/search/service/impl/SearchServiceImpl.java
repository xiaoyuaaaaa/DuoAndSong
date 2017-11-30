package com.cloud.search.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.common.SolrDocumentList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.resume.dao.ResumeDao;
import com.cloud.search.dao.SearchDao;
import com.cloud.search.model.SearchLogsModel;
import com.cloud.search.model.SearchModel;
import com.cloud.search.model.SearchResultModel;
import com.cloud.search.model.SolrResultModel;
import com.cloud.search.service.SearchService;
import com.cloud.search.util.SolrService;
import com.cloud.search.util.SolrUtil;

/** 
 * @author tobber
 * @version 2017年11月7日
 */
@Service
public class SearchServiceImpl implements SearchService{
	
	@Resource
	private SearchDao searchDao;

	@Override
	public ResponseEntity<SearchResultModel> cvsearch(
		SearchModel searchModel,
		HttpServletResponse response,
		HttpServletRequest request
	) {
		try {
			String queryQ = SolrUtil.getSearchCondition(searchModel);
			SolrDocumentList docList = SolrService.getSolrList(queryQ, "resume_lastTime_dt", 1, SolrUtil.RESUME_POOL, (searchModel.getPage()-1)*searchModel.getPageSize(), searchModel.getPageSize());
			SearchResultModel searchResultModel = new SearchResultModel(200,"获取成功");
			searchModel.setUserId(request.getSession().getAttribute("userId")+"");
			searchModel.setSearchCode(System.currentTimeMillis()+"");
			if(docList !=null && docList.size()>0){
				List<SolrResultModel> resumeList = new ArrayList<SolrResultModel>();
				StringBuffer resumeIds = new StringBuffer();
				for (int i = 0; i < docList.size(); i++) {
					resumeList.add(SolrUtil.setResumePool(docList.get(i)));
					resumeIds.append(","+docList.get(i).get("talent_userId"));
				}
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("userId", request.getSession().getAttribute("userId")+"");
				paramMap.put("resumeIds", resumeIds.toString().substring(1));
				Map<Integer, Integer> readMap = setMap(searchDao.getReadLogs(paramMap));
				for(SolrResultModel model:resumeList) {
					if(readMap.get(model.getUserId())!=null){
						model.setIsRead(1);
					}else{
						model.setIsRead(0);
					}
				}
				searchModel.setSearch_result(resumeIds.toString().substring(1));
				searchResultModel.setSearchCode(searchModel.getSearchCode());
				searchResultModel.setResumeList(resumeList);
				searchResultModel.setPage(searchModel.getPage());
				searchResultModel.setPageSize(searchModel.getPageSize());
				searchResultModel.setRowcount(docList.getNumFound());
        		if(docList.getNumFound()%searchModel.getPageSize()==0){
        			searchResultModel.setTotalPage(docList.getNumFound()/searchModel.getPageSize());
        		}else{
        			searchResultModel.setTotalPage((docList.getNumFound()/searchModel.getPageSize())+1);
        		}
			}
			searchDao.addSearchLogs(searchModel);
			searchResultModel.setSearchId(searchModel.getId());
			return new ResponseEntity<SearchResultModel>(searchResultModel,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SearchResultModel>(new SearchResultModel(301,"服务器内部错误"),HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<SearchLogsModel> getSearchLogs(HttpServletResponse response,HttpServletRequest request){
		try {
			String userId = request.getSession().getAttribute("userId")+"";
			List<SearchModel> logsList = searchDao.getSearchLogs(userId);
			SearchLogsModel baseModel =new SearchLogsModel(200,"获取成功");
			baseModel.setLogList(logsList);
			return new ResponseEntity<SearchLogsModel>(baseModel,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SearchLogsModel>(new SearchLogsModel(301,"服务器内部错误"),HttpStatus.OK);
		}
	}
	
	private Map<Integer, Integer> setMap(List<Integer> list){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer key : list) {
			map.put(key, 1);
		}
		return map;
	}

}
