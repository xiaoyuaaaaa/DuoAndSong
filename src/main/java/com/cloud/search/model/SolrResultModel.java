package com.cloud.search.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月7日
 */
@ApiModel(value="SolrResultModel",description="Solr返回结果转换实体类")
public class SolrResultModel {
	
	@ApiModelProperty(value = "简历ID", required = true)
	private Integer userId;
	
	@ApiModelProperty(value = "职位名称", required = true)
	private String jobTitle;
	
	@ApiModelProperty(value="求职状态："
			+ "1.我目前处于离职状态，可立即上岗、"
			+ "2.我目前在职，正考虑换个新环境（如有合适的工作机会，到岗时间一个月左右）、"
			+ "3.目前暂无跳槽打算、"
			+ "4.我对现有工作还算满意，如有更好的工作机会，我也可以考虑。（到岗时间另议）、"
			+ "5.应届毕业生",required=false)
	private String jobStatus;
	
	@ApiModelProperty(value = "工作年限", required = true)
	private String jobYear;
	
	@ApiModelProperty(value="学历:1.初中、2.中技、3.高中、4.中专、5.大专、6.本科、7.硕士、8.MBA、9.EMBA、10.博士、11.其他、",required=true)
	private String education;
	
	@ApiModelProperty(value = "年龄", required = true)
	private String age;
	
	@ApiModelProperty(value = "性别", required = true)
	private String sex;
	
	@ApiModelProperty(value = "期望城市", required = true)
	private String cityName;
	
	@ApiModelProperty(value="期望薪资："
			+ "0000001000 1000元/月以下、"
			+ "0100002000 1000-2000元/月、"
			+ "0200104000 2001-4000元/月、"
			+ "0400106000 4001-6000元/月、"
			+ "0600108000 6001-8000元/月、"
			+ "0800110000 8001-10000元/月、"
			+ "1000115000 10001-15000元/月、"
			+ "1500125000 15000-25000元/月、"
			+ "2500199999 25000元/月以上、"
			+ "0000000000 面议",required=false)
	private String expectedSalary;
	
	@ApiModelProperty(value = "更新时间", required = true)
	private String updateTime;
	
	@ApiModelProperty(value = "小详情：现居地", required = false)
	private String lateCityName;
	
	@ApiModelProperty(value = "小详情：公司名称", required = false)
	private String lateCompName;
	
	@ApiModelProperty(value = "小详情：最近工作时间", required = false)
	private String lateCompTime;
	
	@ApiModelProperty(value = "小详情：职位", required = false)
	private String lateJobTitle;
	
	@ApiModelProperty(value = "小详情：最近工作描述", required = false)
	private String lateCompDesc;
	
	@ApiModelProperty(value = "小详情：学校名称", required = false)
	private String  lateSchoolName;
	
	@ApiModelProperty(value = "小详情：所学专业", required = false)
	private String  lateMajor;
	
	@ApiModelProperty(value = "小详情：学校就读时间", required = false)
	private String  lateEduTime;
	
	@ApiModelProperty(value = "小详情：学历", required = false)
	private String  lateEducation;
	
	@ApiModelProperty(value = "是否已阅读：0.未阅读，1已阅读", required = false)
	private Integer isRead;

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
	 * @return the jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus the jobStatus to set
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
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
		this.jobYear = jobYear;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the expectedSalary
	 */
	public String getExpectedSalary() {
		return expectedSalary;
	}

	/**
	 * @param expectedSalary the expectedSalary to set
	 */
	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the lateCityName
	 */
	public String getLateCityName() {
		return lateCityName;
	}

	/**
	 * @param lateCityName the lateCityName to set
	 */
	public void setLateCityName(String lateCityName) {
		this.lateCityName = lateCityName;
	}

	/**
	 * @return the lateCompName
	 */
	public String getLateCompName() {
		return lateCompName;
	}

	/**
	 * @param lateCompName the lateCompName to set
	 */
	public void setLateCompName(String lateCompName) {
		this.lateCompName = lateCompName;
	}

	/**
	 * @return the lateCompTime
	 */
	public String getLateCompTime() {
		return lateCompTime;
	}

	/**
	 * @param lateCompTime the lateCompTime to set
	 */
	public void setLateCompTime(String lateCompTime) {
		this.lateCompTime = lateCompTime;
	}

	/**
	 * @return the lateJobTitle
	 */
	public String getLateJobTitle() {
		return lateJobTitle;
	}

	/**
	 * @param lateJobTitle the lateJobTitle to set
	 */
	public void setLateJobTitle(String lateJobTitle) {
		this.lateJobTitle = lateJobTitle;
	}

	/**
	 * @return the lateCompDesc
	 */
	public String getLateCompDesc() {
		return lateCompDesc;
	}

	/**
	 * @param lateCompDesc the lateCompDesc to set
	 */
	public void setLateCompDesc(String lateCompDesc) {
		this.lateCompDesc = lateCompDesc;
	}

	/**
	 * @return the lateSchoolName
	 */
	public String getLateSchoolName() {
		return lateSchoolName;
	}

	/**
	 * @param lateSchoolName the lateSchoolName to set
	 */
	public void setLateSchoolName(String lateSchoolName) {
		this.lateSchoolName = lateSchoolName;
	}

	/**
	 * @return the lateMajor
	 */
	public String getLateMajor() {
		return lateMajor;
	}

	/**
	 * @param lateMajor the lateMajor to set
	 */
	public void setLateMajor(String lateMajor) {
		this.lateMajor = lateMajor;
	}

	/**
	 * @return the lateEduTime
	 */
	public String getLateEduTime() {
		return lateEduTime;
	}

	/**
	 * @param lateEduTime the lateEduTime to set
	 */
	public void setLateEduTime(String lateEduTime) {
		this.lateEduTime = lateEduTime;
	}

	/**
	 * @return the lateEducation
	 */
	public String getLateEducation() {
		return lateEducation;
	}

	/**
	 * @param lateEducation the lateEducation to set
	 */
	public void setLateEducation(String lateEducation) {
		this.lateEducation = lateEducation;
	}

	/**
	 * @return the isRead
	 */
	public Integer getIsRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	
}
