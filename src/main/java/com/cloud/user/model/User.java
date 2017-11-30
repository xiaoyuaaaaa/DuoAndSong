package com.cloud.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.util.CheckUtil;
import com.cloud.util.Util;

/** 
 * @author tobber
 * @version 2017年11月7日
 */

@ApiModel(value="User",description="用户信息模型")
public class User extends ResultBaseModel{

	@ApiModelProperty(value = "用户ID", required = true)
	private Integer userId;
	
	@ApiModelProperty(value = "用户邮箱", required = true)
	private String email;
	
	@ApiModelProperty(value = "用户是否邮箱验证：0.未验证、1.已验证", required = true)
	private Integer isSuccess;
	
	@ApiModelProperty(value = "是否已企业认证：0.否、1.已验证", required = true)
	private Integer status;
	
	@ApiModelProperty(value = "账号是否被拉黑：0.正常、1.已拉黑", required = true)
	private Integer isNormal;
	
	@ApiModelProperty(value = "用户手机号码", required = true)
	private String telephone;
	
	@ApiModelProperty(value = "用户名字（长度：1~50）", required = true)
	private String name;
	
	@ApiModelProperty(value = "用户职位（长度：1~50）", required = false)
	private String jobTitle;
	
	@ApiModelProperty(value = "用户推荐码", required = true)
	private String shareCode;
	
	@ApiModelProperty(value = "用户金币数", required = true)
	private Integer gainNum;
	
	@ApiModelProperty(value = "用户头像（正则：[0-9]{1,10}）", required = false)
	private String heandImg;
	
	@ApiModelProperty(value = "用户昵称（长度：1~50）", required = false)
	private String nickName;
	
	@ApiModelProperty(value = "用户QQ（正则：[1-9][0-9]{3,15}）", required = false)
	private String QQ;
	
	@ApiModelProperty(value = "行业（正则：[0-9]{1,20}）", required = false)
	private String industry;
	
	@ApiModelProperty(value = "已暂存简历数", required = false)
	private Integer storageCount;
	
	@ApiModelProperty(value = "已下载简历数", required = false)
	private Integer gainCount;
	
	@ApiModelProperty(value = "未读消息数", required = false)
	private Integer noticeNum;
	
	public User(Integer code,String message) {
		super(code,message);
	}

	public User() {
		super();
	}
	
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
	 * @return the isSuccess
	 */
	public Integer getIsSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the isNormal
	 */
	public Integer getIsNormal() {
		return isNormal;
	}

	/**
	 * @param isNormal the isNormal to set
	 */
	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
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
	 * @return the shareCode
	 */
	public String getShareCode() {
		return shareCode;
	}

	/**
	 * @param shareCode the shareCode to set
	 */
	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	/**
	 * @return the gainNum
	 */
	public Integer getGainNum() {
		return gainNum;
	}

	/**
	 * @param gainNum the gainNum to set
	 */
	public void setGainNum(Integer gainNum) {
		this.gainNum = gainNum;
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
		if(CheckUtil.matcheVerify(heandImg, "[0-9]{1,10}")){
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

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the storageCount
	 */
	public Integer getStorageCount() {
		return storageCount;
	}

	/**
	 * @param storageCount the storageCount to set
	 */
	public void setStorageCount(Integer storageCount) {
		this.storageCount = storageCount;
	}

	/**
	 * @return the gainCount
	 */
	public Integer getGainCount() {
		return gainCount;
	}

	/**
	 * @param gainCount the gainCount to set
	 */
	public void setGainCount(Integer gainCount) {
		this.gainCount = gainCount;
	}

	/**
	 * @return the noticeNum
	 */
	public Integer getNoticeNum() {
		return noticeNum;
	}

	/**
	 * @param noticeNum the noticeNum to set
	 */
	public void setNoticeNum(Integer noticeNum) {
		this.noticeNum = noticeNum;
	}
	
}
