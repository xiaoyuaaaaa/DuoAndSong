package com.cloud.user.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
@ApiModel(value="RecordListModel",description="获取记录、暂存记录列表实体Bean")
public class RecordListModel extends ResultBaseModel{
	
	@ApiModelProperty(value="记录列表",required=true)
	private List<RecordResultModel> logList;
	
	@ApiModelProperty(value="当前页码",required=true)
	private Integer page;
	
	@ApiModelProperty(value="每页显示条数",required=true)
	private Integer pageSize;
	
	@ApiModelProperty(value="总条数",required=true)
	private Integer rowCount;

	@ApiModelProperty(value="总页数",required=true)
	private Integer totalPage;

	public RecordListModel(Integer code,String message) {
		super(code,message);
	}

	
	public RecordListModel() {
		super();
	}


	/**
	 * @return the logList
	 */
	public List<RecordResultModel> getLogList() {
		return logList;
	}


	/**
	 * @param logList the logList to set
	 */
	public void setLogList(List<RecordResultModel> logList) {
		this.logList = logList;
	}


	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}


	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}


	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	/**
	 * @return the rowCount
	 */
	public Integer getRowCount() {
		return rowCount;
	}


	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}


	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		return totalPage;
	}


	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	
}
