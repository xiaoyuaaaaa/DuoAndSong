package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月8日
 */

@ApiModel(value="NewEducations",description="教育经历实体bean")
public class NewEducations {
	
	@ApiModelProperty(value = "简历ID", required = true)
	private int userId;
	
	@ApiModelProperty(value = "入学时间", required = true)
	private String startTime;
	
	@ApiModelProperty(value = "毕业时间", required = true)
	private String endTime;

	@ApiModelProperty(value = "学校名称", required = true)
	private String schoolName;
	

	@ApiModelProperty(value = "所学专业", required = true)
	private String specialty;
	/**
	 * 学历：===简历搜索对应的学历：
	 *  -2  不限、
		-1 无学历要求、
		7 高中/中专/中技及以下、
		5 大专及同等学历、
		4 本科/学士及等同学历、
		3 硕士/研究生及等同学历、
		1 博士及以上、
		8 其他
		===
		填写教育背景对应的学历：9 初中、13 中技、7 高中、12 中专、5 大专、4 本科、3 硕士、10 MBA、11 EMBA、1 博士、 8 其他 
		
	 */
	@ApiModelProperty(value = "学历", required = true)
	private int education;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	
	
}
