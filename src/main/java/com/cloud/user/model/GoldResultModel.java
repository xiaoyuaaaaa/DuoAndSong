package com.cloud.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月14日
 */

@ApiModel(value="GoldResultModel",description="金币记录列表实体bean")
public class GoldResultModel extends ResultBaseModel{
	
	@ApiModelProperty(value="记录列表",required=true)
	private List<GoldModel> logList;
	
	@ApiModelProperty(value="当前页码",required=true)
	private Integer page;
	
	@ApiModelProperty(value="每页显示条数",required=true)
	private Integer pageSize;
	
	@ApiModelProperty(value="总页数",required=true)
	private Integer totalPage;
	
	@ApiModelProperty(value="总条数",required=true)
	private Integer rowCount;
	
	public GoldResultModel(Integer code,String message) {
		super(code,message);
	}
	
	public GoldResultModel() {
		super();
	}

	/**
	 * @return the logList
	 */
	public List<GoldModel> getLogList() {
		return logList;
	}

	/**
	 * @param logList the logList to set
	 */
	public void setLogList(List<GoldModel> logList) {
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

}
