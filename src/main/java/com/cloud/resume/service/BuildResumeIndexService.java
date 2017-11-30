package com.cloud.resume.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cloud.common.model.ResultBaseModel;
/**
 * @version 2017年11月7日14:39:30
 * @author zqs
 *
 */
public interface BuildResumeIndexService {
	/**
	 * 批量建立简历索引
	 * @param startResumeId
	 * @param endResumeId
	 * @param response
	 * @param request
	 * @return
	 */
    public ResponseEntity<ResultBaseModel> buildResumeIndexBatch(
    		int startResumeId, 
    		int endResumeId,
    		HttpServletResponse response,
    		HttpServletRequest request
    		);
    
    
    /**
     * 单份简历建立索引
     * @param resumeId
     * @param response
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> buildResumeIndexById(
    		int resumeId, 
    		HttpServletResponse response,
    		HttpServletRequest request
    		);
    
    /**
     * 单份简历删除索引
     * @param resumeId
     * @param response
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> deleteResumeIndexById(
    		int resumeId, 
    		HttpServletResponse response,
    		HttpServletRequest request
    		);
}
