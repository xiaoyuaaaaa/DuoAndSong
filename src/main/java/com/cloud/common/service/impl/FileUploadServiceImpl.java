package com.cloud.common.service.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.common.service.FileUploadService;
import com.cloud.util.SysConfig;

/** 
 * @author tobber
 * @version 2017年11月15日
 */

@Service
public class FileUploadServiceImpl implements FileUploadService{

	@Override
	public ResponseEntity<ResultBaseModel> heandImgUpload(MultipartFile file,HttpServletResponse response, HttpServletRequest request) {
		try {
			if(file.getSize()>0 && file.getSize()<2*1024*1024){ //限制文件大小2M
				String path = SysConfig.getValue("UPLOAD_HEADIMG_PATH").toString(); 
		        String fileName = file.getOriginalFilename(); 
		        String newfileName = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		        String imgName =request.getSession().getAttribute("userId")+newfileName;
		        if(newfileName.equalsIgnoreCase(".png") || newfileName.equalsIgnoreCase(".gif") || 
		        		newfileName.equalsIgnoreCase(".jpeg") || newfileName.equalsIgnoreCase(".jpg")){
		        	File targetFile = new File(path, imgName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }
			        file.transferTo(targetFile); //保存
			        return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"上传成功"), HttpStatus.OK);
		        }else{
			        return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"文件格式不正确"), HttpStatus.OK);
		        }
			}else{
		        return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"文件过大，限制2M"), HttpStatus.OK);
			}
	    } catch (Exception e) {  
	        e.printStackTrace();
	        return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
	    } 
	}

}
