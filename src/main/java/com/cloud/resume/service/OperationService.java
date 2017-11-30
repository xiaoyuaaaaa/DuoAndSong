package com.cloud.resume.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import com.cloud.common.model.ResultBaseModel;
import com.cloud.resume.model.ContactModel;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
public interface OperationService {
	
	 /**
     * 获取简历联系方式
     * @param request
     * @return
     */
    public ResponseEntity<ContactModel> getCvContact(
    	Integer resumeId,
    	Integer searchId,
    	String token,
    	String r_t,
		HttpServletResponse response,
		HttpServletRequest request
    );
    
    /**
     * 简历暂存
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> storageCv(
    	String resumeId,
    	Integer searchId,
    	String token,
		HttpServletResponse response,
		HttpServletRequest request
    );
    
    /**
     * 取消暂存
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> cancelStorageCv(
    	String ids,
		HttpServletResponse response,
		HttpServletRequest request
    );

}
