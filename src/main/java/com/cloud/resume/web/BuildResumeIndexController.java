package com.cloud.resume.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.resume.dao.BuildResumeIndexDao;
import com.cloud.resume.dao.ResumeDao;
import com.cloud.resume.model.NewResume;
import com.cloud.resume.service.BuildResumeIndexService;
import com.cloud.resume.util.ResumeUtil;
import com.cloud.util.HttpUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.json.JSONObject;

@Api(value="buildResume",description="Solr操作Controller")
@Controller
@RequestMapping(value="buildResume")
public class BuildResumeIndexController {
	@Resource
	private BuildResumeIndexService buildResumeIndexService;
	@Resource
	private BuildResumeIndexDao buildResumeIndexDao;
	@Resource
	private ResumeDao resumeDao;
	
	@ApiOperation(value="批量建立索引接口",httpMethod="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startResumeId", value = "开始简历ID(正则：[0-9]{1,11})", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "endResumeId", value = "最后简历ID(正则：[0-9]{1,11})", required = true, dataType = "int", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code=200,message="返回信息",response=ResultBaseModel.class)
	})
	@RequestMapping(value="buildResumeIndexBatch",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBaseModel> buildResumeIndexBatch(int startResumeId, int endResumeId, HttpServletResponse response,HttpServletRequest request){
		long startTime = System.currentTimeMillis();
		int buildNumber = 1000;//一次性简历多少索引
		int nextUserId = buildResumeIndexDao.getMaxUserId();//数据库下一个自增ID
		if(startResumeId==0) {
			startResumeId = 0;
		}
		if(endResumeId==0) {
			//全部建索引
			endResumeId = nextUserId;
		}
		int start = startResumeId/buildNumber;
		int end = endResumeId/buildNumber+1;
		//每次建立10000条简历的索引
		for(int i=start;i<end;i++) {
			int s = i*buildNumber;
			int e = s+buildNumber;
			if(s<startResumeId) {
				s = startResumeId;
			}
			if(e>endResumeId) {
				e = endResumeId;
			}
			buildResumeIndexService.buildResumeIndexBatch(s, e, response, request);
		}
		System.out.println("总执行耗时 : "
				+(System.currentTimeMillis()-startTime)/1000f+" 秒 ");
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("time", (System.currentTimeMillis()-startTime)/1000f+" 秒 ");
		return null;
		//return buildResumeIndexService.buildResumeIndexBatch(startResumeId, endResumeId, response, request);
	}
	
	@RequestMapping(value="importResume",method=RequestMethod.GET)
	public void importResume(HttpServletResponse response,HttpServletRequest request){
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			
			List<String> userList = FileUtils.readLines(new File("C:/Users/fangshaomin/Desktop/resume.txt"));
			for (int i = 870000; i < userList.size(); i++) {
				try{
					Map<String,Object> map = HttpUtil.sendHttp("http://www.yifengjianli.com/common/getResumeById?userId="+userList.get(i), null, null, "GET");
					JSONObject json = JSONObject.fromObject(map.get("result").toString());
					if(json.get("code") !=null && json.get("code").toString().equals("200")){
						json = JSONObject.fromObject(json.get("resume").toString());
						System.out.println(i+"=="+userList.get(i)+"=="+json.toString());
						NewResume resume = ResumeUtil.converted(json);
						if(resume.getName() !=null && resume.getName().length()>2){
							resume.setName(resume.getName().replace(" ", ""));
							if(resume.getExpectSalary() !=null && "".equals(resume.getExpectSalary())){
								resume.setExpectSalary("0000000000");
							}
							condition.clear();
							condition.put("resume", resume);
							String userId = resumeDao.getUserIdByPhone(resume.getTelephone());
							if(userId !=null && Integer.parseInt(userId)>0){
								condition.put("userId", userId);
								resumeDao.updateResumeInfo(condition);
								resumeDao.add_resume_detail(condition);
							}else{
								resumeDao.add_base_info(condition);
								
								userId = resumeDao.getUserIdByPhone(resume.getTelephone());
								if(userId !=null && Integer.parseInt(userId)>0){
									condition.put("userId", userId);
									resumeDao.add_resume_detail(condition);
								}
							}
						}						
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="importResumeSecond",method=RequestMethod.GET)
	public void importResumeSecond(HttpServletResponse response,HttpServletRequest request){
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			
			List<String> userList = FileUtils.readLines(new File("C:/Users/fangshaomin/Desktop/resume1.txt"));
			for (int i = 870000; i < userList.size(); i++) {
				try{
					Map<String,Object> map = HttpUtil.sendHttp("http://www.yifengjianli.com/common/getResumeById?userId="+userList.get(i), null, null, "GET");
					JSONObject json = JSONObject.fromObject(map.get("result").toString());
					if(json.get("code") !=null && json.get("code").toString().equals("200")){
						json = JSONObject.fromObject(json.get("resume").toString());
						System.out.println("second=="+i+"=="+userList.get(i)+"=="+json.toString());
						NewResume resume = ResumeUtil.converted(json);
						if(resume.getName() !=null && resume.getName().length()>2){
							resume.setName(resume.getName().replace(" ", ""));
							if(resume.getExpectSalary() !=null && "".equals(resume.getExpectSalary())){
								resume.setExpectSalary("0000000000");
							}
							condition.clear();
							condition.put("resume", resume);
							String userId = resumeDao.getUserIdByPhone(resume.getTelephone());
							if(userId !=null && Integer.parseInt(userId)>0){
								condition.put("userId", userId);
								resumeDao.updateResumeInfo(condition);
								resumeDao.add_resume_detail(condition);
							}else{
								resumeDao.add_base_info(condition);
								
								userId = resumeDao.getUserIdByPhone(resume.getTelephone());
								if(userId !=null && Integer.parseInt(userId)>0){
									condition.put("userId", userId);
									resumeDao.add_resume_detail(condition);
								}
							}
						}
						
						System.out.println(json.toString());
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
