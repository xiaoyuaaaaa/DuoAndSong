package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2016年5月31日
 */

@ApiModel(value="NewWorkExp",description="工作经历实体bean")
public class NewWorkExp {
	
	@ApiModelProperty(value="简历ID",required=true)
	private int userId;
	
	@ApiModelProperty(value="起始时间",required=true)
	private String startTime;
	
	@ApiModelProperty(value="结束时间",required=true)
	private String endTime;
	
	@ApiModelProperty(value="公司名称",required=true)
	private String compName;
	
	@ApiModelProperty(value="公司所属行业",required=false)
	private String compIndustry;
	
	@ApiModelProperty(value="公司所属行业名称",required=false)
	private String industryName;
	
	@ApiModelProperty(value="公司性质:1.国企、2.外商独资、3.代表处、4.合资、5.民营、8.股份制企业、9.上市公司、6.国家机关、10.事业单位、7.其它",required=false)
	private int compProperty;
	/**
	 * 公司规模：
	 * 1.20人以下、2.20-99人、3.100-499人、4.500-999人、5.1000-9999人、6.10000人以上
	 */
	@ApiModelProperty(value="公司规模:(1.20人以下、2.20-99人、3.100-499人、4.500-999人、5.1000-9999人、6.10000人以上)",required=false)
	private int compSize;
	
	@ApiModelProperty(value="职位",required=false)
	private String jobTitle;

	@ApiModelProperty(value="薪资："
			+ "0000001000 1000元/月以下、"
			+ "0100002000 1000-2000元/月、"
			+ "0200104000 2001-4000元/月、"
			+ "0400106000 4001-6000元/月、"
			+ "0600108000 6001-8000元/月、"
			+ "0800110000 8001-10000元/月、"
			+ "1000115000 10001-15000元/月、"
			+ "1500125000 15000-25000元/月、"
			+ "2500199999 25000元/月以上、"
			+ "0000000000 保密",required=false)
	private String salary;

	@ApiModelProperty(value="工作描述",required=false)
	private String workDesc;
	
	
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
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
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompIndustry() {
		return compIndustry;
	}
	public void setCompIndustry(String compIndustry) {
		this.compIndustry = compIndustry;
	}
	public int getCompProperty() {
		return compProperty;
	}
	public void setCompProperty(int compProperty) {
		this.compProperty = compProperty;
	}
	public int getCompSize() {
		return compSize;
	}
	public void setCompSize(int compSize) {
		this.compSize = compSize;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	
	
}
