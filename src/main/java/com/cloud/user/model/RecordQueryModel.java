package com.cloud.user.model;

import com.cloud.util.CheckUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月13日
 */

@ApiModel(value="RecordQueryModel",description="获取记录、暂存记录查询条件实体Bean")
public class RecordQueryModel {
	
	@ApiModelProperty(value="用户ID",required=false)
	private String userId;

	@ApiModelProperty(value="职位关键字",required=false)
	private String jobTitle;

	@ApiModelProperty(value="姓名",required=false)
	private String name;
	
	@ApiModelProperty(value="电话",required=false)
	private String telephone;
	
	@ApiModelProperty(value="邮箱",required=false)
	private String email;
	
	@ApiModelProperty(value="城市编码",required=false)
	private String cityCode;
	
	@ApiModelProperty(value="当前页码",required=true)
	private Integer page;
	
	@ApiModelProperty(value="每页显示条数",required=true)
	private Integer pageSize;

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
		if(CheckUtil.matcheVerify(jobTitle, "[0-9]{1,20}")){
			this.jobTitle = jobTitle;
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if(CheckUtil.strVerify(name, 1, 50)){
			this.name = name;
		}
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		if(CheckUtil.checkMobie(telephone)){
			this.telephone = telephone;
		}
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if(CheckUtil.checkEmail(email)){
			this.email = email;
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
	
	
}
