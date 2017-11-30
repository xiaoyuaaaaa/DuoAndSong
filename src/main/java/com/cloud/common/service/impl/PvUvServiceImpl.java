package com.cloud.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cloud.common.dao.PvUvDao;
import com.cloud.common.service.PvUvService;
import com.cloud.util.Util;

/** 
 * @author tobber
 * @version 2017年11月29日
 */
@Service
public class PvUvServiceImpl implements PvUvService{
	@Resource
	private PvUvDao pvUvDao;
	
	@Override
	public void addPvUv(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ip", Util.getIpAddr(request));
		paramMap.put("addr", request.getRequestURL().toString());
		if(request.getSession().getAttribute("userId")!=null){
			paramMap.put("userId", request.getSession().getAttribute("userId").toString());
		}
		pvUvDao.addPvUvLogs(paramMap);
	}

}
