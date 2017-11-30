package com.cloud.fileupload.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.fileupload.util.ExportExcelResume;
import com.cloud.fileupload.util.ZipUtil;
import com.cloud.resume.dao.ResumeDao;
import com.cloud.resume.model.NewResume;
import com.cloud.util.CheckUtil;
import com.cloud.util.HttpUtil;
import com.cloud.util.SysConfig;
import com.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @version 2017年11月14日17:02:56
 * @author zqs
 *
 */
@Api(value="downLoad",description="简历模板下载操作Controller")
@Controller
@RequestMapping(value="downLoad")
public class DownLoadController {
	@Resource
	private ResumeDao resumeDao;
	
	@ApiOperation(value="pdf word 生成接口",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "简历ID(正则：[1-9][0-9]{0,10}", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "type", value = "模板类型(长度1~5)", required = true, dataType = "String", paramType = "query"),

	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="createPdfWord",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> createPdfWord(
		String type, 
		String userId, 
		HttpServletResponse response,HttpServletRequest request
	) throws InterruptedException{
		if(CheckUtil.strVerify(type, 1, 5) && CheckUtil.matcheVerify(userId,"[1-9][0-9]{0,10}")){
			if(type.equals("pdf")){
				
				Util.deleteFile(SysConfig.getValue("PDF_FILE_PATH").toString()+userId+".pdf");
				HttpUtil.sendHttp(SysConfig.getValue("CREAT_PDF_FILE_PATH").toString()+userId, null, null, "GET");
				Thread.sleep(500);
			}else if(type.equals("word")){
				
				Util.deleteFile(SysConfig.getValue("WORD_FILE_PATH").toString()+userId+".doc");
				HttpUtil.sendHttp(SysConfig.getValue("CREAT_WORD_FILE_PATH").toString()+userId, null, null, "GET");
				Thread.sleep(500);
			}
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"文件生成成功"), HttpStatus.OK);
		}else{
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数错误"), HttpStatus.OK);
		}
	}
	
	
	@ApiOperation(value="EXCEL生成接口",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "简历ID(正则：[0-9,]{1,3000}", required = true, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="createExcel",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> createExcel(
		String userId, 
		HttpServletResponse response,HttpServletRequest request
	){
		if(CheckUtil.matcheVerify(userId,"[0-9,]{1,3000}")){
			Boolean flag = false;
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("resumeIds", userId);
			List<NewResume> resumeList = resumeDao.queryResumeByResumeIdList(condition);
			if(resumeList !=null && resumeList.size()>0){
				flag = ExportExcelResume.creatExcle(resumeList,request.getSession().getAttribute("userId").toString()+".xls");
			}
			
			if(flag){
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"创建成功"), HttpStatus.OK);
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"创建失败"), HttpStatus.OK);
			}
		}else{
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数错误"), HttpStatus.OK);
		}
	}
	
	
	@ApiOperation(value="生成PDF/WORD压缩包",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "简历ID(正则：[0-9,]{1,3000}", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "type", value = "简历模板类型(长度1~5)", required = true, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="createPdfWordZip",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> createPdfWordZip(
		String userId, 
		String type, 
		HttpServletResponse response,HttpServletRequest request
	) throws InterruptedException{
		if(CheckUtil.strVerify(type, 1, 5) && CheckUtil.matcheVerify(userId,"[0-9,]{1,3000}")){
			String targUrl = null;
			if(type.equals("pdf")){
				targUrl = SysConfig.getValue("CREAT_PDF_FILE_PATH").toString();
			}else if(type.equals("word")){
				targUrl = SysConfig.getValue("CREAT_WORD_FILE_PATH").toString();;
			}
			
			String[] resumeIds = userId.split(",");
			for (int i = 0; i < resumeIds.length; i++) {
				HttpUtil.sendHttp(targUrl+resumeIds[i], null, null, "GET");
				Thread.sleep(800);
			}
			Map<String,Object> parm = new HashMap<String, Object>();
			parm.put("compUserId", request.getSession().getAttribute("userId").toString());
			parm.put("resumeId", userId);
			
			List<Map<String,Object>> userList = resumeDao.getBatchDownUser(parm);
			
			if(userList!=null && userList.size()>0){
			
				List<String> pathList = new ArrayList<String>();
				String filePath = "";
				
				if(type.equals("pdf")){
					filePath =  SysConfig.getValue("PDF_FILE_PATH").toString();
					for (int i = 0; i < userList.size(); i++) {
				        File file = new File(filePath, userList.get(i).get("resumeId").toString()+".pdf");
						if(file!=null && file.isFile()){
							pathList.add(file.getAbsolutePath());
						}
					}
				}else if(type.equals("word")){
					filePath = SysConfig.getValue("WORD_FILE_PATH").toString();
					for (int i = 0; i < userList.size(); i++) {
				        File file = new File(filePath, userList.get(i).get("resumeId").toString()+".doc");
						if(file!=null && file.isFile()){
							pathList.add(file.getAbsolutePath());
						}
					}
				}
				
				boolean flag = ZipUtil.compressFilesZip(pathList.toArray(new String[pathList.size()]) , filePath+request.getSession().getAttribute("userId").toString()+".zip" , filePath);
				
				if(flag){
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"压缩完成"), HttpStatus.OK);
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"操作失败,请重新下载"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"未获取"), HttpStatus.OK);
			}				
		}else{
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数错误"), HttpStatus.OK);
		}
	}
	
	
	@ApiOperation(value="单份简历下载接口",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "简历ID(正则：[1-9][0-9]{0,10}", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "type", value = "简历模板类型(长度1~5)", required = true, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="downloadSingleResume",method=RequestMethod.GET)
	public ResponseEntity<ResultBaseModel> downloadSingleResume(
		String userId, 
		String type, 
		HttpServletResponse response,HttpServletRequest request
	) throws IOException{
		InputStream in = null;
		try{
			if(CheckUtil.matcheVerify(userId,"[1-9][0-9]{0,10}") && CheckUtil.strVerify(type, 1, 5)){
				Map<String, Object> parm = new HashMap<String, Object>();
				parm.put("compUserId", request.getSession().getAttribute("userId").toString());
				parm.put("resumeId", userId);				
				List<Map<String,Object>> userList = resumeDao.getBatchDownUser(parm);
				if(userList !=null && userList.size()>0){

					String filePath = "";
			        File file = null;
					if(type.equals("pdf")){//PDF
						filePath = SysConfig.getValue("PDF_FILE_PATH").toString();
						file = new File(filePath, userId+".pdf");
						
					}else if(type.equals("word")){//WORD
						filePath = SysConfig.getValue("WORD_FILE_PATH").toString();
						file = new File(filePath, userId+".doc");
					}else if(type.equals("excel")){//
						filePath = SysConfig.getValue("EXCEL_FILE_PATH").toString();
						file = new File(filePath, userId+".xls");
					}
					
					if(file!=null && file.isFile()){
			        	if(type.equals("pdf")){
			        		//1.获取要下载的文件的绝对路径
				        	response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(userId+".pdf", "UTF-8"));
			        	}else if(type.equals("word")){
			        		//1.获取要下载的文件的绝对路径
				        	response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(userId+".doc", "UTF-8"));
			        	}
						
			        	in = new FileInputStream(file);
			        	int len = 0;
			        	//5.创建数据缓冲区
			        	byte[] buffer = new byte[1024];
			        	//6.通过response对象获取OutputStream流
			        	OutputStream out = response.getOutputStream();
			        	//7.将FileInputStream流写入到buffer缓冲区
			        	while ((len = in.read(buffer)) > 0) {
			        		//8.使用OutputStream将缓冲区的数据输出到客户端浏览器
			        		out.write(buffer,0,len);
			        	}
			        	in.close();
			        	
			        	return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"下载成功"), HttpStatus.OK);
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"文件不存在"), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"未获取"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数错误"), HttpStatus.OK);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"下载失败"), HttpStatus.OK);
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@ApiOperation(value="简历压缩包下载接口",httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "简历模板类型(长度1~5)", required = true, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="downloadBatchResume",method=RequestMethod.GET)
	public ResponseEntity<ResultBaseModel> downloadBatchResume(
		String type, 
		HttpServletResponse response,HttpServletRequest request
	) throws IOException{
		InputStream in = null;
		try{
			if(CheckUtil.strVerify(type, 1, 5)){
				String filePath = "";
				
				if(type.equals("pdf")){
					filePath = SysConfig.getValue("PDF_FILE_PATH").toString();
					
				}else if(type.equals("word")){
					filePath = SysConfig.getValue("WORD_FILE_PATH").toString();
					
				}else if(type.equals("excel")){
					filePath = SysConfig.getValue("EXCEL_FILE_PATH").toString();
				}
				File zipFile = new File(filePath, request.getSession().getAttribute("userId").toString()+".zip");
				if(type.equals("excel")){
					zipFile = new File(filePath, request.getSession().getAttribute("userId").toString()+".xls");
				}else{
					zipFile = new File(filePath, request.getSession().getAttribute("userId").toString()+".zip");
				}
				if(zipFile!=null && zipFile.isFile()){
		        	//1.获取要下载的文件的绝对路径
					if(type.equals("excel")){
						response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(request.getSession().getAttribute("userId").toString()+".xls","UTF-8"));
					}else{
						response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(request.getSession().getAttribute("userId").toString()+".zip", "UTF-8"));
					}
		        	in = new FileInputStream(zipFile);
		        	int len = 0;
		        	//5.创建数据缓冲区
		        	byte[] buffer = new byte[1024];
		        	//6.通过response对象获取OutputStream流
		        	OutputStream out = response.getOutputStream();
		        	//7.将FileInputStream流写入到buffer缓冲区
		        	while ((len = in.read(buffer)) > 0) {
		        		//8.使用OutputStream将缓冲区的数据输出到客户端浏览器
		        		out.write(buffer,0,len);
		        	}
		        	in.close();
		        	
		        	return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"下载成功"), HttpStatus.OK);
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"文件不存在"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数错误"), HttpStatus.OK);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"下载失败"), HttpStatus.OK);
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
