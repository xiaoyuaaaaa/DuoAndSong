package com.cloud.search.model;

import java.util.List;

import com.cloud.common.model.ResultBaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月9日
 */

@ApiModel(value="SearchLogsModel",description="搜索记录实体bean")
public class SearchLogsModel extends ResultBaseModel{
	
	
	@ApiModelProperty(value = "搜索记录ID (搜索忽略该参数)", required = false)
	private List<SearchModel> logList;
	
	public SearchLogsModel(Integer code,String message) {
		super(code,message);
	}
	
	public SearchLogsModel() {
		super();
	}

	/**
	 * @return the logList
	 */
	public List<SearchModel> getLogList() {
		return logList;
	}

	/**
	 * @param logList the logList to set
	 */
	public void setLogList(List<SearchModel> logList) {
		this.logList = logList;
	}
	
	
}
