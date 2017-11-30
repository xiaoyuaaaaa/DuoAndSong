package com.cloud.common.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.common.service.FileUploadService;

/** 
 * @author tobber
 * @version 2017年11月15日
 */

@Api(value="upload",description="文件上传")
@Controller
@RequestMapping(value="upload")
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService;
    
    @ApiOperation(value="头像上传",httpMethod="POST")
	@ApiResponse(code=200,message="上传成功",response=ResultBaseModel.class)
    @RequestMapping(value = "heandImgUpload",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<ResultBaseModel> heandImgUpload(HttpServletRequest request,MultipartFile file,HttpServletResponse response) {
    	return fileUploadService.heandImgUpload(file,response, request);
	}
}
