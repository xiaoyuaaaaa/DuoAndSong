package com.cloud.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @author tobber
 * @version 2017年11月29日
 */
public interface PvUvService {
	
	/**
	 * PV、UV统计
	 * @param request
	 * @param response
	 */
	public void addPvUv(HttpServletRequest request,HttpServletResponse response);
}
