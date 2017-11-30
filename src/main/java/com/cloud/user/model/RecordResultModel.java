package com.cloud.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月13日
 */

@ApiModel(value="RecordResultModel",description="获取记录、暂存记录实体Bean")
public class RecordResultModel{
	
	@ApiModelProperty(value="用户ID",required=false)
	private Integer userId;
	
	@ApiModelProperty(value="简历ID",required=false)
	private String resumeId;
	
	@ApiModelProperty(value="搜索ID",required=false)
	private String searchId;
	
	@ApiModelProperty(value="搜索token",required=false)
	private String searchToken;
	
	@ApiModelProperty(value="职位",required=false)
	private String jobTitle;

	@ApiModelProperty(value="姓名",required=false)
	private String name;
	
	@ApiModelProperty(value="电话",required=false)
	private String telephone;
	
	@ApiModelProperty(value="邮箱",required=false)
	private String email;
	
	@ApiModelProperty(value="期望城市编码",required=false)
	private String expectCity;
	
	@ApiModelProperty(value="性别",required=false)
	private Integer sex;
	
	@ApiModelProperty(value="性别",required=false)
	private Integer age;
	
	@ApiModelProperty(value="学历",required=false)
	private Integer education;
	
	@ApiModelProperty(value="工作经验",required=false)
	private Integer jobYear;
	
	@ApiModelProperty(value="更新时间",required=true)
	private String lastTime;
	
	@ApiModelProperty(value="创建时间",required=true)
	private String creatTime;
	
	@ApiModelProperty(value="记录ID",required=true)
	private String id;
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	/**
	 * @return the resumeId
	 */
	public String getResumeId() {
		return resumeId;
	}


	/**
	 * @param resumeId the resumeId to set
	 */
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}


	/**
	 * @return the searchId
	 */
	public String getSearchId() {
		return searchId;
	}


	/**
	 * @param searchId the searchId to set
	 */
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}


	/**
	 * @return the searchToken
	 */
	public String getSearchToken() {
		return searchToken;
	}


	/**
	 * @param searchToken the searchToken to set
	 */
	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
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
		this.jobTitle = jobTitle;
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
		this.name = name;
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
		this.telephone = telephone;
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
		this.email = email;
	}


	/**
	 * @return the expectCity
	 */
	public String getExpectCity() {
		return expectCity;
	}


	/**
	 * @param expectCity the expectCity to set
	 */
	public void setExpectCity(String expectCity) {
		this.expectCity = expectCity;
	}


	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}


	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
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
		this.education = education;
	}


	/**
	 * @return the jobYear
	 */
	public Integer getJobYear() {
		return jobYear;
	}


	/**
	 * @param jobYear the jobYear to set
	 */
	public void setJobYear(Integer jobYear) {
		this.jobYear = jobYear;
	}


	/**
	 * @return the lastTime
	 */
	public String getLastTime() {
		return lastTime;
	}


	/**
	 * @param lastTime the lastTime to set
	 */
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}


	/**
	 * @return the creatTime
	 */
	public String getCreatTime() {
		return creatTime;
	}


	/**
	 * @param creatTime the creatTime to set
	 */
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}


	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}


	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	
}
