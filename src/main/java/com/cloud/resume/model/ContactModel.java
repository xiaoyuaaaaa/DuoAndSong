package com.cloud.resume.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月8日
 */

@ApiModel(value="ContactModel",description="获取简历联系方式实体bean")
public class ContactModel extends ResultBaseModel{
	
	@ApiModelProperty(value = "用户邮箱", required = true)
	private String email;
	
	@ApiModelProperty(value = "用户姓名", required = true)
	private String name;
	
	@ApiModelProperty(value = "用户电话", required = true)
	private String telephone;
	
	public ContactModel(Integer code,String message) {
		super(code,message);
	}
	
	public ContactModel() {
		super();
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	
}
