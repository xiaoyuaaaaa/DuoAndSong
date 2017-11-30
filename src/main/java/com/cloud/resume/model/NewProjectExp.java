package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月8日
 */
@ApiModel(value="NewProjectExp",description="项目经验实体bean")
public class NewProjectExp {
	
	@ApiModelProperty(value="简历ID",required=true)
	private int userId;
	
	@ApiModelProperty(value="项目开始时间",required=false)
	private String startTime;
	
	@ApiModelProperty(value="项目结束时间",required=false)
	private String endTime;
	
	@ApiModelProperty(value="软件环境",required=false)
	private String software;
	
	@ApiModelProperty(value="硬件环境",required=false)
	private String hardware;

	@ApiModelProperty(value="开发工具",required=false)
	private String devTools;
	
	@ApiModelProperty(value="项目名称",required=true)
	private String projectName;

	@ApiModelProperty(value="项目描述",required=true)
	private String projectDesc;
	
	@ApiModelProperty(value="责任描述",required=false)
	private String responsibilityDesc;
	
	
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
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
	public String getHardware() {
		return hardware;
	}
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	public String getDevTools() {
		return devTools;
	}
	public void setDevTools(String devTools) {
		this.devTools = devTools;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public String getResponsibilityDesc() {
		return responsibilityDesc;
	}
	public void setResponsibilityDesc(String responsibilityDesc) {
		this.responsibilityDesc = responsibilityDesc;
	}
	
}
