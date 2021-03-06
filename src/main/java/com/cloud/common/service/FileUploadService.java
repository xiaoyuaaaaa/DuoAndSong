package com.cloud.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.common.model.ResultBaseModel;

/** 
 * @author tobber
 * @version 2017年11月15日
 */
public interface FileUploadService {
	
	/**
     * 用户头像上传
     * @param request
     * @return
     */
    public ResponseEntity<ResultBaseModel> heandImgUpload(
    		MultipartFile file,
    		HttpServletResponse response,
    		HttpServletRequest request
    		);

}
