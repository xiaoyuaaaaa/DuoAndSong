package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月8日
 */
@ApiModel(value="NewLanguage",description="语言能力实体bean")
public class NewLanguage {
	
	@ApiModelProperty(value = "简历ID", required = true)
	private int userId;
	/**
	 * 名称：英语、日语、法语、德语、俄语、韩语、西班牙语、葡萄牙语、阿拉伯语、意大利语、其他
	 */
	@ApiModelProperty(value = "语言名称", required = true)
	private String languageName;
	
	@ApiModelProperty(value = "读写能力：一般、良好、熟练、精通", required = true)
	private String readWriteSkill; 
	
	@ApiModelProperty(value = "听说能力：一般、良好、熟练、精通", required = true)
	private String hearSpeakSkill;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getReadWriteSkill() {
		return readWriteSkill;
	}
	public void setReadWriteSkill(String readWriteSkill) {
		this.readWriteSkill = readWriteSkill;
	}
	public String getHearSpeakSkill() {
		return hearSpeakSkill;
	}
	public void setHearSpeakSkill(String hearSpeakSkill) {
		this.hearSpeakSkill = hearSpeakSkill;
	}
}
