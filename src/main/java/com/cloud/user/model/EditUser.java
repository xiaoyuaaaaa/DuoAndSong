package com.cloud.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.cloud.util.CheckUtil;

/** 
 * @author tobber
 * @version 2017年11月14日
 */

@ApiModel(value="EditUser",description="修改用户信息模型")
public class EditUser {

	@ApiModelProperty(value = "用户ID", required = false,hidden=true)
	private Integer userId;
	
	@ApiModelProperty(value = "用户名字（长度：1~50）", required = false)
	private String name;
	
	@ApiModelProperty(value = "用户职位（长度：1~50）", required = false)
	private String jobTitle;
	
	@ApiModelProperty(value = "用户头像（正则：[0-9a-zA-Z,]{2,50}）", required = false)
	private String heandImg;
	
	@ApiModelProperty(value = "用户昵称（长度：1~50）", required = false)
	private String nickName;
	
	@ApiModelProperty(value = "用户QQ（正则：[1-9][0-9]{3,15}）", required = false)
	private String QQ;
	
	@ApiModelProperty(value = "行业（正则：[0-9]{1,20}）", required = false)
	private String industry;

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
	 * @return the heandImg
	 */
	public String getHeandImg() {
		return heandImg;
	}

	/**
	 * @param heandImg the heandImg to set
	 */
	public void setHeandImg(String heandImg) {
		if(CheckUtil.matcheVerify(heandImg, "[0-9a-zA-Z.]{2,50}")){
			this.heandImg = heandImg;
		}
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		if(CheckUtil.strVerify(nickName, 1, 50)){
			this.nickName = nickName;
		}
	}


	/**
	 * @return the qq
	 */
	public String getQQ() {
		return QQ;
	}


	/**
	 * @param qq the qq to set
	 */
	public void setQQ(String QQ) {
		if(CheckUtil.matcheVerify(QQ, "[1-9][0-9]{3,15}")){
			this.QQ = QQ;
		}
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		if(CheckUtil.matcheVerify(industry, "[0-9]{1,20}")){
			this.industry = industry;
		}
	}
}
