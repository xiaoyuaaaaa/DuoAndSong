package com.cloud.notice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月27日
 */
@ApiModel(value="NoticeMode",description="通知实体bean")
public class NoticeModel {
	
	@ApiModelProperty(value="消息通知ID（创建时忽略此参数）",required=false)
	private int id;
	
	@ApiModelProperty(value="标题",required=true)
	private String title;
	
	@ApiModelProperty(value="消息内容",required=true)
	private String content;
	
	@ApiModelProperty(value="消息图片",required=false)
	private String noticeImg;
	
	@ApiModelProperty(value="链接地址",required=false)
	private String noticeUrl;
	
	@ApiModelProperty(value="发送时间（创建时忽略此参数）",required=false)
	private String creatTime;
	
	@ApiModelProperty(value="是否已读：0.未读、1.已读",required=false)
	private int isRead;
	
	@ApiModelProperty(value="阅读时间",required=false)
	private String readTime;
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the noticeImg
	 */
	public String getNoticeImg() {
		return noticeImg;
	}

	/**
	 * @param noticeImg the noticeImg to set
	 */
	public void setNoticeImg(String noticeImg) {
		this.noticeImg = noticeImg;
	}

	/**
	 * @return the noticeUrl
	 */
	public String getNoticeUrl() {
		return noticeUrl;
	}

	/**
	 * @param noticeUrl the noticeUrl to set
	 */
	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
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
	 * @return the isRead
	 */
	public int getIsRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the readTime
	 */
	public String getReadTime() {
		return readTime;
	}

	/**
	 * @param readTime the readTime to set
	 */
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	
}
