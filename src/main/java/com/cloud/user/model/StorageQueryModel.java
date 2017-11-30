package com.cloud.user.model;

import com.cloud.util.CheckUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月13日
 */

@ApiModel(value="StorageQueryModel",description="暂存记录查询条件实体Bean")
public class StorageQueryModel {
	
	@ApiModelProperty(value="用户ID",required=false)
	private String userId;

	@ApiModelProperty(value="职位关键字",required=false)
	private String jobTitle;
	
	@ApiModelProperty(value="城市编码",required=false)
	private String cityCode;
	
	@ApiModelProperty(value="学历",required=false)
	private Integer education;
	
	@ApiModelProperty(value="暂存时间：1.当天、2.三天内、3.一周内、4.一个月内、5.三个月内、6.半年内、7.一年内",required=false)
	private Integer storageTime;
	
	@ApiModelProperty(value="工作经验 用逗号隔开",required=false)
	private String jobYear;
	
	@ApiModelProperty(value="当前页码",required=true)
	private Integer page;
	
	@ApiModelProperty(value="每页显示条数",required=true)
	private Integer pageSize;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		if(CheckUtil.strVerify(jobTitle, 1, 50)){
			this.jobTitle = jobTitle;
		}
	}


	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		if(CheckUtil.matcheVerify(cityCode, "[0-9]{1,20}")){
			this.cityCode = cityCode;
		}
	}

	/**
	 * @return the education
	 */
	public Integer getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(Integer education) {
		if(CheckUtil.matcheVerify(education+"", "[0-9]{1,2}")){
			this.education = education;
		}
	}

	/**
	 * @return the storageTime
	 */
	public Integer getStorageTime() {
		return storageTime;
	}

	/**
	 * @param storageTime the storageTime to set
	 */
	public void setStorageTime(Integer storageTime) {
		if(CheckUtil.matcheVerify(storageTime+"", "[1-7]")){
			switch (storageTime) {
			case 1: //当天
				this.storageTime = 0;
				break;
			case 2: //三天内
				this.storageTime = 3;
				break;
			case 3:
				this.storageTime = 7;
				break;
			case 4:
				this.storageTime = 30;
				break;
			case 5:
				this.storageTime = 90;
				break;
			case 6:
				this.storageTime = 180;
				break;
			case 7:
				this.storageTime = 360;
				break;
			}
		}
	}

	/**
	 * @return the jobYear
	 */
	public String getJobYear() {
		return jobYear;
	}

	/**
	 * @param jobYear the jobYear to set
	 */
	public void setJobYear(String jobYear) {
		if(CheckUtil.matcheVerify(jobYear, "[0-9,]{1,10}")){
			this.jobYear = jobYear.replace(",", " AND ");
		}
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
		if(CheckUtil.matcheVerify(page+"", "[0-9]{1,10}")){
			this.page = page;
		}else{
			this.page = 1;
		}
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
		if(CheckUtil.matcheVerify(pageSize+"", "[0-9]{1,10}")){
			this.pageSize = pageSize;
		}else{
			this.pageSize = 15;
		}
	}

}
