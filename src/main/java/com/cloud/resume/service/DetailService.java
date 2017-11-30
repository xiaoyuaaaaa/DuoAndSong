package com.cloud.resume.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import com.cloud.resume.model.NewResume;

/** 
 * @author tobber
 * @version 2017年11月8日
 */
public interface DetailService {
	/**
     * 获取简历详情
     * @param request
     * @return
     */
    public ResponseEntity<NewResume> getCvDetail(
    	Integer resumeId,
    	Integer searchId,
    	String token,
    	String r_t,
		HttpServletResponse response,
		HttpServletRequest request
    );
    
}
