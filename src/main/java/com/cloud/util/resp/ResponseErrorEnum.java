package com.cloud.util.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author tobber
 * @version 2017年11月21日
 */

@ApiModel(value="ResponseErrorEnum",description="统一错误码model")
public class ResponseErrorEnum {

	@ApiModelProperty(value = "错误码", required = true)
	private String code;
	
	@ApiModelProperty(value = "错误信息", required = true)
    private String message;
    
    private ResponseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 序列化enum
    private Map<String, Object> serialize() {
        Map<String, Object> valueMap = new HashMap<>(2);
        valueMap.put("code", this.code);
        valueMap.put("message", this.message);
        return valueMap;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
