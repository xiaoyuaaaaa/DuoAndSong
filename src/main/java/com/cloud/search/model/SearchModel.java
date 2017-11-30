package com.cloud.search.model;

import com.cloud.util.CheckUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月7日
 */

@ApiModel(value="SearchModel",description="简历搜索参数")
public class SearchModel {
	
	@ApiModelProperty(value = "搜索记录ID (搜索忽略该参数)", required = false)
	private Integer id;
	
	@ApiModelProperty(value = "用户ID (搜索忽略该参数)", required = false)
	private String userId;
	
	@ApiModelProperty(value = "当前页面数", required = true)
	private Integer page;
	
	@ApiModelProperty(value = "每页显示的条数:15、30、60", required = true)
	private Integer pageSize;
	
	@ApiModelProperty(value = "期望城市编码(多个英文逗号隔开)", required = false)
	private String RM_S_1_1;
	
	@ApiModelProperty(value = "期望职位(多个英文逗号隔开)", required = false)
	private String RM_S_1_2;
	
	@ApiModelProperty(value = "公司名称：长度：1~50不能包含空格", required = false)
	private String RM_S_1_3;
	
	@ApiModelProperty(value = "是否勾选当前公司：1.表示已勾选", required = false)
	private String RM_S_1_4;
	
	@ApiModelProperty(value="期望薪资："
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
	private String RM_S_1_5;
	
	@ApiModelProperty(value="求职状态："
			+ "1.我目前处于离职状态，可立即上岗、"
			+ "2.我目前在职，正考虑换个新环境（如有合适的工作机会，到岗时间一个月左右）、"
			+ "3.目前暂无跳槽打算、"
			+ "4.我对现有工作还算满意，如有更好的工作机会，我也可以考虑。（到岗时间另议）、"
			+ "5.应届毕业生",required=false)
	private String RM_S_1_6;
	
	@ApiModelProperty(value = "性别", required = false)
	private String RM_S_1_7;
	
	@ApiModelProperty(value = "更新日期", required = false)
	private String RM_S_1_8;
	
	@ApiModelProperty(value = "学历", required = false)
	private String RM_S_1_9;
	
	@ApiModelProperty(value = "年龄", required = false)
	private String RM_S_1_10;
	
	@ApiModelProperty(value = "工作年限", required = false)
	private String RM_S_1_11;
	
	@ApiModelProperty(value = "关键字", required = false)
	private String RM_S_1_12;
	
	@ApiModelProperty(value = "搜索记录Code（搜索忽略该参数）", required = false)
	private String searchCode;
	
	@ApiModelProperty(value = "搜索接口简历ID（搜索忽略该参数）", required = false)
	private String search_result;

	/**
	 * @return 当前页码数
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param 当前页码数
	 */
	public void setPage(Integer page) {
		if (CheckUtil.matcheVerify(page+"", "[1-9][0-9]{0,5}")) {
			this.page = page;
		}else{
			this.page = 1;
		}
		
	}

	/**
	 * @return 每页显示条数
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param 每页显示条数
	 */
	public void setPageSize(Integer pageSize) {
		if (CheckUtil.matcheVerify(pageSize+"", "[136][05]")) {
			this.pageSize = pageSize;
		}else{
			this.pageSize = 15;
		}
	}

	/**
	 * @return 期望城市
	 */
	public String getRM_S_1_1() {
		return RM_S_1_1;
	}

	/**
	 * @param 期望城市
	 */
	public void setRM_S_1_1(String rM_S_1_1) {
		if(CheckUtil.matcheVerify(rM_S_1_1, "[0-9,]{1,100}")){
			this.RM_S_1_1 = rM_S_1_1;
		}else{
			this.RM_S_1_1 = null;
		}
	}

	/**
	 * @return 期望职位
	 */
	public String getRM_S_1_2() {
		return RM_S_1_2;
	}

	/**
	 * @param 期望职位
	 */
	public void setRM_S_1_2(String rM_S_1_2) {
		if(CheckUtil.matcheVerify(rM_S_1_2, "[0-9,]{1,100}")){
			this.RM_S_1_2 = rM_S_1_2;
		}else{
			this.RM_S_1_2 = null;
		}
	}

	/**
	 * @return 公司名称
	 */
	public String getRM_S_1_3() {
		return RM_S_1_3;
	}

	/**
	 * @param 公司名称
	 */
	public void setRM_S_1_3(String rM_S_1_3) {
		if(CheckUtil.strVerify(rM_S_1_3,1,50)){
			this.RM_S_1_3 = rM_S_1_3;
		}else{
			this.RM_S_1_3 = null;
		}
	}

	/**
	 * @return 是否勾选当前公司
	 */
	public String getRM_S_1_4() {
		return RM_S_1_4;
	}

	/**
	 * @param 是否勾选当前公司
	 */
	public void setRM_S_1_4(String rM_S_1_4) {
		if(CheckUtil.matcheVerify(rM_S_1_4,"[1]{1}")){
			this.RM_S_1_4 = rM_S_1_4;
		}else{
			this.RM_S_1_4 = null;
		}
	}

	/**
	 * @return 期望薪资范围
	 */
	public String getRM_S_1_5() {
		return RM_S_1_5;
	}

	/**
	 * @param 期望薪资范围
	 */
	public void setRM_S_1_5(String rM_S_1_5) {
		if(CheckUtil.matcheVerify(rM_S_1_5,"[0-9]{10}")){
			this.RM_S_1_5 = rM_S_1_5;
		}else{
			this.RM_S_1_5 = null;
		}
	}

	/**
	 * @return 求职状态
	 */
	public String getRM_S_1_6() {
		return RM_S_1_6;
	}

	/**
	 * @param 求职状态
	 */
	public void setRM_S_1_6(String rM_S_1_6) {
		RM_S_1_6 = rM_S_1_6;
		if(CheckUtil.matcheVerify(rM_S_1_6, "[1-5]")){
			this.RM_S_1_6=rM_S_1_6;
		}else{
			this.RM_S_1_6=null;
		}
	}

	/**
	 * @return 性别
	 */
	public String getRM_S_1_7() {
		return RM_S_1_7;
	}

	/**
	 * @param 性别
	 */
	public void setRM_S_1_7(String rM_S_1_7) {
		if(CheckUtil.matcheVerify(rM_S_1_7, "[1-2]")){
			this.RM_S_1_7 = rM_S_1_7;
		}else{
			this.RM_S_1_7 = null;
		}
	}

	/**
	 * @return 更新日期
	 */
	public String getRM_S_1_8() {
		return RM_S_1_8;
	}

	/**
	 * @param 更新日期
	 */
	public void setRM_S_1_8(String rM_S_1_8) {
		if(CheckUtil.matcheVerify(rM_S_1_8, "[1-7]")){
			this.RM_S_1_8 = rM_S_1_8;
		}else{
			this.RM_S_1_8 = null;
		}
	}

	/**
	 * @return 学历
	 */
	public String getRM_S_1_9() {
		return RM_S_1_9;
	}

	/**
	 * @param 学历
	 */
	public void setRM_S_1_9(String rM_S_1_9) {
		if(CheckUtil.matcheVerify(rM_S_1_9, "[0-9,]{3,5}")){
			this.RM_S_1_9 = rM_S_1_9;
		}else{
			this.RM_S_1_9 = null;
		}
	}

	/**
	 * @return 年龄
	 */
	public String getRM_S_1_10() {
		return RM_S_1_10;
	}

	/**
	 * @param 年龄
	 */
	public void setRM_S_1_10(String rM_S_1_10) {
		if(CheckUtil.matcheVerify(rM_S_1_10, "[0-9]{2,2},[0-9]{2,2}")){
			this.RM_S_1_10 = rM_S_1_10;
		}else{
			this.RM_S_1_10 = null;
		}
	}

	/**
	 * @return 工作年限
	 */
	public String getRM_S_1_11() {
		return RM_S_1_11;
	}

	/**
	 * @param 工作年限
	 */
	public void setRM_S_1_11(String rM_S_1_11) {
		if(CheckUtil.matcheVerify(rM_S_1_11, "[0-9]{1,2},[0-9]{1,2}")){
			this.RM_S_1_11 = rM_S_1_11;
		}else{
			this.RM_S_1_11 = null;
		}
	}

	/**
	 * @return 关键字
	 */
	public String getRM_S_1_12() {
		return RM_S_1_12;
	}

	/**
	 * @param 关键字
	 */
	public void setRM_S_1_12(String rM_S_1_12) {
		if(CheckUtil.strVerify(rM_S_1_12, 1, 100)){
			this.RM_S_1_12 = rM_S_1_12;
		}else{
			this.RM_S_1_12 = null;
		}
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the search_result
	 */
	public String getSearch_result() {
		return search_result;
	}

	/**
	 * @param search_result the search_result to set
	 */
	public void setSearch_result(String search_result) {
		this.search_result = search_result;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the searchCode
	 */
	public String getSearchCode() {
		return searchCode;
	}

	/**
	 * @param searchCode the searchCode to set
	 */
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	
}
