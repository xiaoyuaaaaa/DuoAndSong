package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月8日
 * 培训经历
 */
@ApiModel(value="NewTraining",description="培训经历实体bean")
public class NewTraining {
	
	@ApiModelProperty(value="简历ID",required=true)
	private int userId;
	
	@ApiModelProperty(value="培训开始时间",required=false)
	private String startTime;
	
	@ApiModelProperty(value="培训结束时间",required=false)
	private String endTime;
	
	@ApiModelProperty(value="培训名称",required=true)
	private String trainName;
	
	@ApiModelProperty(value="获得证书名称",required=false)
	private String certificateName;
	
	@ApiModelProperty(value="培训地址",required=false)
	private String address;
	
	@ApiModelProperty(value="培训机构",required=true)
	private String machinery;
	
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
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMachinery() {
		return machinery;
	}
	public void setMachinery(String machinery) {
		this.machinery = machinery;
	}
	
}
