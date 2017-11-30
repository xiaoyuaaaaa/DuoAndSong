package com.cloud.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author tobber
 * @version 2017年11月14日
 */

@ApiModel(value="GlodModel",description="金币记录实体bean")
public class GoldModel {

	@ApiModelProperty(value="金币记录ID",required=true)
	private Integer id;
	
	@ApiModelProperty(value="操作金币数",required=true)
	private Integer gold;
	
	@ApiModelProperty(value="状态："
			+ "1.注册送金币、"
			+ "2.获取消耗金币、"
			+ "3.每日登陆送金币、"
			+ "4.邮箱验证送金币、"
			+ "5.智联绑定送金币、"
			+ "6.前程绑定送金币、"
			+ "7.拉勾绑定送金币、"
			+ "8.赶集绑定送金币、"
			+ "9.58绑定送金币、"
			+ "10.猎聘绑定送金币、"
			+ "11.中华英才绑定送金币、"
			+ "12.好友推荐送金币",required=true)
	private Integer status;
	
	@ApiModelProperty(value="增加或减少：0.减、1.加",required=true)
	private Integer type;
	
	@ApiModelProperty(value="剩余金币数",required=true)
	private Integer surplusGold;
	
	@ApiModelProperty(value="创建时间",required=true)
	private String creatTime;

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
	 * @return the gold
	 */
	public Integer getGold() {
		return gold;
	}

	/**
	 * @param gold the gold to set
	 */
	public void setGold(Integer gold) {
		this.gold = gold;
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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the surplusGold
	 */
	public Integer getSurplusGold() {
		return surplusGold;
	}

	/**
	 * @param surplusGold the surplusGold to set
	 */
	public void setSurplusGold(Integer surplusGold) {
		this.surplusGold = surplusGold;
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
	
}
