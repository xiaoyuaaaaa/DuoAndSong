package com.cloud.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * 统一返回结果模型
 * @author tobber
 * @version 2017年11月3日
 */
@ApiModel(value="ReultBaseMode",description="错误提示模型")
public class ResultBaseModel{
	
	@ApiModelProperty(value = "状态码：200表示成功其他则失败", required = true)
	private Integer code;
	
	@ApiModelProperty(value = "提示信息", required = true)
	private String message;
	
	public ResultBaseModel(Integer code,String message) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public ResultBaseModel() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
