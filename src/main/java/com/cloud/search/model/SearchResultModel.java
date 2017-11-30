package com.cloud.search.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月7日
 */

@ApiModel(value="SearchResultModel",description="简历搜索返回数据模型")
public class SearchResultModel extends ResultBaseModel{
	
	@ApiModelProperty(value = "简历列表集", required = true)
	private List<SolrResultModel> resumeList;
	
	@ApiModelProperty(value = "当前页码数", required = true)
	private Integer page;
	
	@ApiModelProperty(value = "每页显示的条数", required = true)
	private Integer pageSize;
	
	@ApiModelProperty(value = "总条数", required = true)
	private Long rowcount;
	
	@ApiModelProperty(value = "总页数", required = true)
	private Long totalPage;
	
	@ApiModelProperty(value = "搜索ID", required = true)
	private Integer searchId;
	
	@ApiModelProperty(value = "搜索Code", required = true)
	private String searchCode;

	public SearchResultModel(Integer code,String message) {
		super(code,message);
	}

	public SearchResultModel() {
		super();
	}

	/**
	 * @return the resumeList
	 */
	public List<SolrResultModel> getResumeList() {
		return resumeList;
	}

	/**
	 * @param resumeList the resumeList to set
	 */
	public void setResumeList(List<SolrResultModel> resumeList) {
		this.resumeList = resumeList;
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
	 * @return the rowcount
	 */
	public Long getRowcount() {
		return rowcount;
	}

	/**
	 * @param rowcount the rowcount to set
	 */
	public void setRowcount(Long rowcount) {
		this.rowcount = rowcount;
	}

	/**
	 * @return the totalPage
	 */
	public Long getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the searchId
	 */
	public Integer getSearchId() {
		return searchId;
	}

	/**
	 * @param searchId the searchId to set
	 */
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	/**
	 * @return the searchCode
	 */
	public String getSearchCode() {
		return searchCode;
	}

	/**
	 * @param searchCode the searchCode to set
	 */
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	
}
