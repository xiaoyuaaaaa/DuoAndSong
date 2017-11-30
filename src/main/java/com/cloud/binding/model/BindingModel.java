package com.cloud.binding.model;

import com.cloud.common.model.ResultBaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @version 2017年11月16日10:17:19
 * @author zqs
 *
 */
@ApiModel(value="BindingModel",description="绑定实体Bean")
public class BindingModel  extends ResultBaseModel{
	@ApiModelProperty(value="智联",required=false)
	private String zl;
	@ApiModelProperty(value="前程",required=false)
	private String qc;
	@ApiModelProperty(value="拉钩",required=false)
	private String lg;
	@ApiModelProperty(value="赶集",required=false)
	private String gj;
	@ApiModelProperty(value="中华英才",required=false)
	private String yc;
	@ApiModelProperty(value="猎聘",required=false)
	private String lp;
	@ApiModelProperty(value="五八",required=false)
	private String wb;

	public BindingModel(Integer code,String message) {
		super(code,message);
	}

	
	public BindingModel() {
		super();
	}
	
	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	public String getLg() {
		return lg;
	}

	public void setLg(String lg) {
		this.lg = lg;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getYc() {
		return yc;
	}

	public void setYc(String yc) {
		this.yc = yc;
	}

	public String getLp() {
		return lp;
	}

	public void setLp(String lp) {
		this.lp = lp;
	}

	public String getWb() {
		return wb;
	}

	public void setWb(String wb) {
		this.wb = wb;
	}
	
	
}
