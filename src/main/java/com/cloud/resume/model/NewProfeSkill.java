package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月8日
 * 专业技能
 */
@ApiModel(value="NewProfeSkill",description="专业技能实体Bean")
public class NewProfeSkill {
	
	@ApiModelProperty(value="简历ID",required=true)
	private int userId;

	@ApiModelProperty(value="技能名称",required=true)
	private String skillName;
	
	@ApiModelProperty(value="使用时间",required=true)
	private int usedMonths;
	
	@ApiModelProperty(value="熟练度 ：一般、良好、熟练、精通",required=true)
	private String masterDegree;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getUsedMonths() {
		return usedMonths;
	}
	public void setUsedMonths(int usedMonths) {
		this.usedMonths = usedMonths;
	}
	public String getMasterDegree() {
		return masterDegree;
	}
	public void setMasterDegree(String masterDegree) {
		this.masterDegree = masterDegree;
	}
}
