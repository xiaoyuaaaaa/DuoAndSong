package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月8日
 */

@ApiModel(value="NewResume",description="简历基本信息实体bean")
public class NewResume extends ResultBaseModel{
	
	@ApiModelProperty(value="简历ID",required=true)
	private int userId;
	
	@ApiModelProperty(value="是否已获取：0.表示未获取，大于0表示已获取",required=true)
	private int isPay;
	
	@ApiModelProperty(value="是否暂存：0.表示未暂存，大于0表示已暂存",required=true)
	private int isStorage;
	
	@ApiModelProperty(value="最近更新时间",required=true)
	private String lastTime;
	
	@ApiModelProperty(value="姓名",required=false)
	private String name;
	
	@ApiModelProperty(value="邮箱",required=false)
	private String email;
	
	@ApiModelProperty(value="电话",required=false)
	private String telephone;
	
	@ApiModelProperty(value="性别 1.男、2.女",required=true)
	private int sex;
	
	@ApiModelProperty(value="婚姻状态 :0 没有填写、1.未婚、2.已婚 、3 离异",required=true)
	private int maritalStatus;

	@ApiModelProperty(value="学历:1 初中、2 中技、3 高中、4 中专、5 大专、6 本科、7 硕士、8 MBA、9 EMBA、10 博士、11 其他",required=true)
	private int education;
	
	@ApiModelProperty(value="工作年限",required=true)
	private int jobYear;
	
	@ApiModelProperty(value="年龄",required=true)
	private int age;
	
	@ApiModelProperty(value="出生年月",required=false)
	private String birthYear;
	
	@ApiModelProperty(value="现居地 城市",required=false)
	private String city;

	@ApiModelProperty(value="现居地 省份",required=false)
	private String province;
	
	@ApiModelProperty(value="户口所在地 省份",required=false)
	private String hukouProvince;
	
	@ApiModelProperty(value="户口所在地 城市",required=false)
	private String hukouCity;

	@ApiModelProperty(value="期望职位",required=false)
	private String jobTitle;
	
	@ApiModelProperty(value="期望工作性质:2.全职、1.兼职、4.实习",required=false)
	private int expectWorkType;
	/**
	 * 期望工作城市
	 */
	@ApiModelProperty(value="期望工作城市",required=false)
	private String expectCity;
	
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
			+ "0000000000 面议",required=false)
	private String expectSalary;

	@ApiModelProperty(value="期望从事行业",required=false)
	private String expectIndustry;

	@ApiModelProperty(value="求职状态："
			+ "1.我目前处于离职状态，可立即上岗、"
			+ "2.我目前在职，正考虑换个新环境（如有合适的工作机会，到岗时间一个月左右）、"
			+ "3.目前暂无跳槽打算、"
			+ "4.我对现有工作还算满意，如有更好的工作机会，我也可以考虑。（到岗时间另议）、"
			+ "5.应届毕业生",required=false)
	private int jobState;
	
	/**
	 * 自我评价
	 */
	@ApiModelProperty(value="自我评价",required=false)
	private String selfEvaluate;
	
	/**
	 * 来源
	 */
	@ApiModelProperty(value="来源",required=false)
	private int source;
	
	/**
	 * 期望城市名
	 */
	@ApiModelProperty(value="期望城市名",required=false)
	private String expectCityName;
	
	/**
	 * 行业名称
	 */
	@ApiModelProperty(value="行业名称",required=false)
	private String industryName;
	
	/**
	 * 学校名称
	 */
	@ApiModelProperty(value="学校名称",required=false)
	private String schoolName;
	
	/**
	 * 专业名称
	 */
	@ApiModelProperty(value="专业名称",required=false)
	private String specialty;
	
	/**
	 * 职位名称
	 */
	@ApiModelProperty(value="职位名称",required=false)
	private String jotTitleName;
	
	/**
	 * 简历名称
	 */
	@ApiModelProperty(value="简历名称",required=false)
	private String resumeName;
	
	/**
	 * 简历编号
	 */
	@ApiModelProperty(value="简历编号",required=false)
	private String resumeNumber;
	
	@ApiModelProperty(value="教育经历",required=false)
	private List<NewEducations> eduList;
	
	@ApiModelProperty(value="工作经历",required=false)
	private List<NewWorkExp> workList;
	
	@ApiModelProperty(value="项目经历",required=false)
	private List<NewProjectExp> projectList;
	
	@ApiModelProperty(value="语言能力",required=false)
	private List<NewLanguage> languagesList;
	
	@ApiModelProperty(value="专业能力",required=false)
	private List<NewProfeSkill> skillsList;

	@ApiModelProperty(value="培训经历",required=false)
	private List<NewTraining> trainList;
	
	
	public String getResumeNumber() {
		return resumeNumber;
	}

	public void setResumeNumber(String resumeNumber) {
		this.resumeNumber = resumeNumber;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
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

	public String getExpectCityName() {
		return expectCityName;
	}

	public void setExpectCityName(String expectCityName) {
		this.expectCityName = expectCityName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getJotTitleName() {
		return jotTitleName;
	}

	public void setJotTitleName(String jotTitleName) {
		this.jotTitleName = jotTitleName;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public NewResume(Integer code,String message) {
		super(code,message);
	}
	
	public NewResume() {
		super();
	}

	
	
	public String getLastTime() {
		return lastTime;
	}
	
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getJobYear() {
		return jobYear;
	}

	public void setJobYear(int jobYear) {
		this.jobYear = jobYear;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityDist() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getHukouProvince() {
		return hukouProvince;
	}

	public void setHukouProvince(String hukouProvince) {
		this.hukouProvince = hukouProvince;
	}
	
	public int getExpectWorkType() {
		return expectWorkType;
	}

	public void setExpectWorkType(int expectWorkType) {
		this.expectWorkType = expectWorkType;
	}

	public String getExpectCity() {
		return expectCity;
	}

	public void setExpectCity(String expectCity) {
		this.expectCity = expectCity;
	}

	public String getExpectSalary() {
		return expectSalary;
	}

	public void setExpectSalary(String expectSalary) {
		this.expectSalary = expectSalary;
	}

	public String getExpectIndustry() {
		return expectIndustry;
	}

	public void setExpectIndustry(String expectIndustry) {
		this.expectIndustry = expectIndustry;
	}

	public int getJobState() {
		return jobState;
	}

	public void setJobState(int jobState) {
		this.jobState = jobState;
	}

	public String getSelfEvaluate() {
		return selfEvaluate;
	}

	public void setSelfEvaluate(String selfEvaluate) {
		this.selfEvaluate = selfEvaluate;
	}

	public List<NewEducations> getEduList() {
		return eduList;
	}

	public void setEduList(List<NewEducations> eduList) {
		this.eduList = eduList;
	}

	public List<NewWorkExp> getWorkList() {
		return workList;
	}

	public void setWorkList(List<NewWorkExp> workList) {
		this.workList = workList;
	}

	public List<NewProjectExp> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<NewProjectExp> projectList) {
		this.projectList = projectList;
	}

	public List<NewLanguage> getLanguagesList() {
		return languagesList;
	}

	public void setLanguagesList(List<NewLanguage> languagesList) {
		this.languagesList = languagesList;
	}

	public List<NewProfeSkill> getSkillsList() {
		return skillsList;
	}

	public void setSkillsList(List<NewProfeSkill> skillsList) {
		this.skillsList = skillsList;
	}

	public String getHukouCity() {
		return hukouCity;
	}

	public void setHukouCity(String hukouCity) {
		this.hukouCity = hukouCity;
	}

	public String getProvince() {
		return province;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public List<NewTraining> getTrainList() {
		return trainList;
	}

	public void setTrainList(List<NewTraining> trainList) {
		this.trainList = trainList;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	/**
	 * @return the isPay
	 */
	public int getIsPay() {
		return isPay;
	}


	/**
	 * @param isPay the isPay to set
	 */
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}


	/**
	 * @return the isStorage
	 */
	public int getIsStorage() {
		return isStorage;
	}


	/**
	 * @param isStorage the isStorage to set
	 */
	public void setIsStorage(int isStorage) {
		this.isStorage = isStorage;
	}
	
	
}
