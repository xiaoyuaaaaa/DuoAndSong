package com.cloud.resume.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.resume.dao.BuildResumeIndexDao;
import com.cloud.resume.service.BuildResumeIndexService;
import com.cloud.search.util.SolrService;
import com.cloud.util.CheckUtil;

/**
 * @version 2017年11月7日14:52:11
 * @author zqs
 *
 */
@Service
public class BuildResumeIndexServiceImpl implements BuildResumeIndexService{
	@Resource
	private BuildResumeIndexDao buildResumeIndexDao;
	
	private Map<String, Object> cityMap;
	
	@Override
	public ResponseEntity<ResultBaseModel> buildResumeIndexBatch(int startResumeId, int endResumeId,
			HttpServletResponse response, HttpServletRequest request) {
		
		try{
			if(CheckUtil.matcheVerify(startResumeId+"", "[0-9]{1,11}") && CheckUtil.matcheVerify(endResumeId+"", "[0-9]{1,11}")){
				long startTime=System.currentTimeMillis();
				if(cityMap==null) {
					List<Map<String, Object>> cityList = buildResumeIndexDao.getCityList(new HashMap<String, Object>());
					cityMap = new HashMap<String, Object>();
					for(Map<String, Object> m : cityList) {
						cityMap.put(m.get("id").toString(), m.get("name").toString());
					}
				}
				Integer maxUserId = buildResumeIndexDao.getMaxUserId();
				if(startResumeId==0) {
					startResumeId = 0;
				}
				if(endResumeId==0) {
					endResumeId = maxUserId;
				}
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("startUserId", startResumeId);
				m.put("endUserId", endResumeId);
				if(!buildIndex(m)) {
					System.out.println(startResumeId+" - "+endResumeId+"建立索引失败");
				}
				System.out.println(startResumeId+" - "+endResumeId+" 执行耗时 : "
						+(System.currentTimeMillis()-startTime)/1000f+" 秒 ");
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"创建成功"), HttpStatus.OK);
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数有误"), HttpStatus.OK);
			}
		}catch (Exception e) { 
            e.printStackTrace();  
            return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
        }
	}

	@Override
	public ResponseEntity<ResultBaseModel> buildResumeIndexById(int resumeId, HttpServletResponse response,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResultBaseModel> deleteResumeIndexById(int resumeId, HttpServletResponse response,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean buildIndex(Map<String, Object> condition) {
		try {
			if(cityMap==null) {
				List<Map<String, Object>> cityList = buildResumeIndexDao.getCityList(new HashMap<String, Object>());
				cityMap = new HashMap<String, Object>();
				for(Map<String, Object> m : cityList) {
					cityMap.put(m.get("id").toString(), m.get("name").toString());
				}
			}
			
			Map<String, String> searchMap = new HashMap<String, String>();
			Map<String, String> eduSearchMap = new HashMap<String, String>();
			Map<String, String> latelyCompNameMap = new HashMap<String, String>();
			Map<String, String> compNameMap = new HashMap<String, String>();
			
			//处理教育经历数据
			List<Map<String, Object>> eduList = buildResumeIndexDao.getEducationList(condition);
			for(Map<String, Object> edu : eduList) {
				String userId = edu.get("userId").toString();
				StringBuffer bfBuffer = new StringBuffer();
				if(searchMap.get(userId)!=null) {
					bfBuffer.append(searchMap.get(userId)+" ");
				}
				bfBuffer.append(edu.get("schoolName")+" ");
				bfBuffer.append(edu.get("specialty")+" ");
				searchMap.put(userId, bfBuffer.toString());
				try {
					if(eduSearchMap.get(userId)==null){
						StringBuffer edfBuffer = new StringBuffer();
						edfBuffer.append(edu.get("schoolName")+"=");
						if(edu.get("startTime")!=null){
							String[] startTime = edu.get("startTime").toString().replace(" ", "").split("-");
							if(startTime.length>=2){
								if(startTime[1]!=null && startTime[1].length()==2){
									edfBuffer.append(startTime[0]+startTime[1]);
								}else{
									edfBuffer.append(startTime[0]+"0"+startTime[1]);
								}
							}
						}
						if(edu.get("endTime")!=null && edu.get("endTime").toString().matches("[0-9].+")){
							String[] endTime = edu.get("endTime").toString().replace(" ", "").split("-");
							if(endTime.length>=2){
								if(endTime[1]!=null && endTime[1].length()==2){
									edfBuffer.append(endTime[0]+endTime[1]);
								}else{
									edfBuffer.append(endTime[0]+"0"+endTime[1]);
								}
							}
						}else{
							edfBuffer.append("111111");
						}
						edfBuffer.append("="+edu.get("specialty"));
						eduSearchMap.put(userId, edfBuffer.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//工作经历
			List<Map<String, Object>> workList = buildResumeIndexDao.getWorkList(condition);
			
			for(Map<String, Object> work : workList) {
				String userId = work.get("userId").toString();
				StringBuffer bfBuffer = new StringBuffer();
				StringBuffer compNameBuffer = new StringBuffer();
				
				if(searchMap.get(userId)!=null) {
					bfBuffer.append(searchMap.get(userId)+" ");
				}
				bfBuffer.append(work.get("jobTitle")+" ");
				bfBuffer.append(work.get("compName")+" ");
				bfBuffer.append(work.get("workDesc")+" ");
				searchMap.put(userId, bfBuffer.toString());
				if(compNameMap.get(userId)!=null) {
					compNameBuffer.append(compNameMap.get(userId)+" ");
				}
				compNameBuffer.append(work.get("compName")+" ");
				compNameMap.put(userId, compNameBuffer.toString());
				
				try {
					if(eduSearchMap.get(userId) !=null && (eduSearchMap.get(userId).length() - eduSearchMap.get(userId).replaceAll("=", "").length())<3){
						StringBuffer edfBuffer = new StringBuffer();
						edfBuffer.append("="+work.get("compName")+"=");
						if(work.get("startTime")!=null){
							String[] startTime = work.get("startTime").toString().replace(" ", "").split("-");
							if(startTime.length>=2){
								if(startTime[1]!=null && startTime[1].length()==2){
									edfBuffer.append(startTime[0]+startTime[1]);
								}else{
									edfBuffer.append(startTime[0]+"0"+startTime[1]);
								}
							}
						}
						if(work.get("endTime")!=null && work.get("endTime").toString().matches("[0-9].+")){
							String[] endTime = work.get("endTime").toString().replace(" ", "").split("-");
							if(endTime.length>=2){
								int newTime = 0;
								if(endTime[1]!=null && endTime[1].length()==2){
									edfBuffer.append(endTime[0]+endTime[1]);
									newTime = Integer.parseInt(endTime[0]+endTime[1]);
								}else{
									edfBuffer.append(endTime[0]+"0"+endTime[1]);
									newTime = Integer.parseInt(endTime[0]+endTime[1]);
								}
								if(latelyCompNameMap.get(userId)!=null){
									String[] lately = latelyCompNameMap.get(userId).toString().split("_");
									if(Integer.parseInt(lately[0])!=111111 && Integer.parseInt(lately[0])<newTime){
										latelyCompNameMap.put(userId, newTime+"_"+work.get("compName"));
									}
								}else{
									latelyCompNameMap.put(userId, newTime+"_"+work.get("compName"));
								}
							}
						}else{
							latelyCompNameMap.put(userId, "111111_"+work.get("compName"));
						}
						eduSearchMap.put(userId, eduSearchMap.get(userId)+edfBuffer.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//处理项目经历数据
			List<Map<String, Object>> projectList = buildResumeIndexDao.getProjectList(condition);
			for(Map<String, Object> projct : projectList) {
				String userId = projct.get("userId").toString();
				StringBuffer bfBuffer = new StringBuffer();
				if(searchMap.get(userId)!=null) {
					bfBuffer.append(searchMap.get(userId)+" ");
				}
				bfBuffer.append(projct.get("projectName")+" ");
				bfBuffer.append(projct.get("projectDesc")+" ");
				bfBuffer.append(projct.get("responsibilityDesc")+" ");
				searchMap.put(userId, bfBuffer.toString());
			}
			
			//处理技能信息
			List<Map<String, Object>> skillList = buildResumeIndexDao.getProfeSkillList(condition);
			for(Map<String, Object> skill : skillList) {
				String userId = skill.get("userId").toString();
				StringBuffer bfBuffer = new StringBuffer();
				if(searchMap.get(userId)!=null) {
					bfBuffer.append(searchMap.get(userId)+" ");
				}
				bfBuffer.append(skill.get("skillName")+" ");
				searchMap.put(userId, bfBuffer.toString());
			}
			
			List<Map<String, Object>> list = buildResumeIndexDao.getResumeInfoList(condition);
			SolrInputDocument doc = null;
			List<SolrInputDocument> docList = new ArrayList<SolrInputDocument>();
			for(Map<String, Object> info : list) {
				doc = new SolrInputDocument();
				
				List<Map<String, Object>> educationMap = buildResumeIndexDao.getEducationListById(info.get("userId").toString());
				if(educationMap !=null && educationMap.size()>0){
					if(educationMap.get(0).get("schoolName") !=null && !"".equals(educationMap.get(0).get("schoolName").toString())){
						String startTime = "null";
						String endTime = "null";
						String education = "null";
						String specialty = "null";
						
						if(educationMap.get(0).get("education") !=null && !"".equals(educationMap.get(0).get("education").toString())){
							education = educationMap.get(0).get("education").toString();
						}
						if(educationMap.get(0).get("specialty") !=null && !"".equals(educationMap.get(0).get("specialty").toString())){
							specialty = educationMap.get(0).get("specialty").toString();
						}
						
						if(educationMap.get(0).get("startTime") !=null && !"".equals(educationMap.get(0).get("startTime").toString())){
							startTime = educationMap.get(0).get("startTime").toString().replace("-", ".").replace(" 00:00:00", "");
						}
						if(educationMap.get(0).get("endTime") !=null && !"".equals(educationMap.get(0).get("endTime").toString())){
							endTime = educationMap.get(0).get("startTime").toString().replace("-", ".").replace(" 00:00:00", "");
							if(endTime.trim().equals("0")){
								endTime = "至今";
							}
						}
						
						//学校名==开始时间-结束时间==专业==学历
						doc.addField("resume_lastEducation_t",educationMap.get(0).get("schoolName").toString()+"=="+startTime+"-"+endTime+"=="+specialty+"=="+education);
						
					}
				}
				
				
				List<Map<String, Object>> workMap = buildResumeIndexDao.getWorkListById(info.get("userId").toString());
				if(workMap !=null && workMap.size()>0){
					if(workMap.get(0).get("compName") !=null && !"".equals(workMap.get(0).get("compName").toString())){
						String jobTitle = "null";
						String salary = "null";
						String compIndustry = "null";
						String compProperty = "null";
						String compSize = "null";
						String workDesc = "null";
						String startTime = "null";
						String endTime = "null";
						
						if(workMap.get(0).get("jobTitle") !=null && !"".equals(workMap.get(0).get("jobTitle").toString())){
							jobTitle = workMap.get(0).get("jobTitle").toString();
						}						
						if(workMap.get(0).get("salary") !=null && !"".equals(workMap.get(0).get("salary").toString())){
							salary = workMap.get(0).get("salary").toString();
						}
						if(workMap.get(0).get("compIndustry") !=null && !"".equals(workMap.get(0).get("compIndustry").toString())){
							compIndustry = workMap.get(0).get("compIndustry").toString();
						}
						if(workMap.get(0).get("compProperty") !=null && !"".equals(workMap.get(0).get("compProperty").toString())){
							compProperty = workMap.get(0).get("compProperty").toString();
						}
						if(workMap.get(0).get("compSize") !=null && !"".equals(workMap.get(0).get("compSize").toString())){
							compSize = workMap.get(0).get("compSize").toString();
						}
						if(workMap.get(0).get("workDesc") !=null && !"".equals(workMap.get(0).get("workDesc").toString())){
							workDesc = workMap.get(0).get("workDesc").toString();
						}
						if(workMap.get(0).get("startTime") !=null && !"".equals(workMap.get(0).get("startTime").toString())){
							startTime = workMap.get(0).get("startTime").toString().replace("-", ".").replace(" 00:00:00", "");
						}
						if(workMap.get(0).get("endTime") !=null && !"".equals(workMap.get(0).get("endTime").toString())){
							endTime = workMap.get(0).get("startTime").toString().replace("-", ".").replace(" 00:00:00", "");
							if(endTime.trim().equals("0")){
								endTime = "至今";
							}
						}
						
						//公司名==开始时间-结束时间==担任职位==薪资==公司行业==公司性质==公司规模==工作描述
						doc.addField("resume_lastCompName_t",workMap.get(0).get("compName").toString()+"=="+startTime+"-"+endTime+"=="+jobTitle+"=="+salary+"=="+compIndustry+"=="+compProperty+"=="+compSize+"=="+workDesc);
					}
				}				
				
				doc.addField("talent_userId", info.get("userId"));
				String expectCityName = "";
				if(info.get("expectCity")!=null && 
						!StringUtils.isEmpty(info.get("expectCity").toString())) {
					String[] cityArr = info.get("expectCity").toString().split(",");
					for(int i=0;i<cityArr.length;i++) {
						if(cityMap.get(cityArr[i])!=null) {
							cityArr[i] = cityMap.get(cityArr[i]).toString();
						}else {
							cityArr[i] = "全国";
						}
					}
					expectCityName = StringUtils.join(cityArr, ",");
					
					String[] city = info.get("expectCity").toString().split(",");
					String cityIds = "";
					for (int i = 0; i < city.length; i++) {
						String superId = "";
						if(!city[i].equals("530") && !city[i].equals("538") && !city[i].equals("531") && !city[i].equals("551")){
							superId = buildResumeIndexDao.getParentList(city[i], "2");
						}
						String[] superIdStr = superId.split(",");
						if(superIdStr.length>2){
							superId = superId.substring(0, superId.length()-2).replace(city[i]+",", "");
							cityIds+= buildResumeIndexDao.getChildList(city[i], "2").replace("$,", "")+","+superId+",";
						}else{
							cityIds+= buildResumeIndexDao.getChildList(city[i], "2").replace("$,", "")+",";
						}	
						
					}
					doc.addField("resume_destination_ss", Arrays.asList(cityIds.split(",")));
				}
				
				doc.addField("resume_cityName_s", expectCityName);
				doc.addField("resume_expectedSalary_s", info.get("expectSalary"));
				doc.addField("resume_skills_t", info.get("skills"));
				doc.addField("resume_creatTime_s", info.get("creatTime"));
				//doc.addField("resume_creatTime_dt", info.get("creatTime_dt"), 1.0f);
				doc.addField("resume_updateTime_s", info.get("updateTime"));
				//doc.addField("resume_updateTime_dt", info.get("updateTime_dt"), 1.0f);
				doc.addField("resume_lastTime_dt", info.get("lastTime_dt"), 1.0f);
				doc.addField("resume_cvBidStatus_i", info.get("cvBidStatus"));
				doc.addField("resume_cvCheckStatus_i", info.get("cvCheckStatus"));
				doc.addField("resume_cvCheckResult_i", info.get("cvCheckResult"));
				doc.addField("resume_checkTime_s", info.get("checkTime"));
				doc.addField("resume_source_i", info.get("source"));
				
				if(info.get("expectIndustry")!=null && info.get("expectIndustry").toString().length()>0){
					String[] industrys = info.get("expectIndustry").toString().split(",");
					String industryIds = "";
					for (int i = 0; i < industrys.length; i++) {
						String superId = buildResumeIndexDao.getParentList(industrys[i], "3");
						String[] superIdStr = superId.split(",");
						if(superIdStr.length>2){
							superId = superId.substring(0, superId.length()-2).replace(industrys[i]+",", "");
							industryIds+= buildResumeIndexDao.getChildList(industrys[i], "3").replace("$,", "")+","+superId+",";
						}else{
							industryIds+= buildResumeIndexDao.getChildList(industrys[i], "3").replace("$,", "")+",";
						}
						
					}
					doc.addField("resume_expectIndustry_ss",Arrays.asList(industryIds.split(",")));
				}else{
					doc.addField("resume_expectIndustry_ss",null);
				}
				if(info.get("industryName")!=null && info.get("industryName").toString().length()>0){
					doc.addField("resume_industryName_ss", Arrays.asList(info.get("industryName").toString().split(",")));
					searchMap.put(info.get("userId").toString(), searchMap.get(info.get("userId").toString())+" "+info.get("industryName").toString());
				}else{
					doc.addField("resume_industryName_ss", null);
				}
				if(info.get("jobTitle")!=null && info.get("jobTitle").toString().length()>0){
					String[] jobs = info.get("jobTitle").toString().split(",");
					String jobIds = "";
					for (int i = 0; i < jobs.length; i++) {
						String superId = buildResumeIndexDao.getParentList(jobs[i], "1");
						String[] superIdStr = superId.split(",");
						if(superIdStr.length>2){
							superId = superId.substring(0, superId.length()-2).replace(jobs[i]+",", "");
							jobIds+= buildResumeIndexDao.getChildList(jobs[i], "1").replace("$,", "")+","+superId+",";
						}else{
							jobIds+= buildResumeIndexDao.getChildList(jobs[i], "1").replace("$,", "")+",";
						}
					}
					doc.addField("resume_jobTitle_ss", Arrays.asList(jobIds.split(",")));
				}else{
					doc.addField("resume_jobTitle_ss", null);
				}
				if(info.get("jotTitleName")!=null && info.get("jotTitleName").toString().length()>0){
					doc.addField("resume_jobTitleName_ss", Arrays.asList(info.get("jotTitleName").toString().split(",")));
					searchMap.put(info.get("userId").toString(), searchMap.get(info.get("userId").toString())+" "+info.get("jotTitleName").toString());
				}else{
					doc.addField("resume_jobTitleName_ss", null);
				}
				
				doc.addField("resume_jobYear_i", info.get("jobYear"));
				//doc.addField("resume_name_s", info.get("name"));
				doc.addField("resume_sex_i", info.get("sex"));
				doc.addField("resume_age_s", info.get("age"));
				doc.addField("resume_education_i", info.get("education"));
				//doc.put("resume_rejectReason_s", info.get("rejectReason"));
				//doc.put("resume_summary_s", info.get("summary"));
				doc.addField("resume_readCount_i", info.get("readCount"));
				//doc.put("resume_details_s", info.get("details"));
				doc.addField("resume_jobState_i", info.get("jobState"));
				doc.addField("resume_code_s", "YFJL"+info.get("userId"));
				doc.addField("resume_status_i", info.get("status"));
				//doc.put("resume_isOnline_i", info.get("isOnline"));
				//doc.put("resume_productUrl_s_n", info.get("productUrl"));
				//doc.put("resume_productImg_s_n", info.get("productImg"));
				//doc.put("resume_commitTime_s", info.get("commitTime"));
				//doc.put("resume_shelvesTime_s", info.get("shelvesTime"));
				//doc.put("resume_offShelvesTime_s", info.get("offShelvesTime"));
				//doc.put("resume_memo_s", info.get("memo"));
				doc.addField("resume_judgePhone_i", info.get("judgePhone"));
				doc.addField("resume_isOnline_i", info.get("isOnline"));
				//doc.put("resume_gainCount_i", info.get("gainCount"));
				//doc.put("resume_type_i", info.get("type"));
				//doc.put("resume_openId_s_n", info.get("openId"));
				//doc.put("resume_QQ_s", info.get("QQ"));
				//doc.put("resume_activaCode_s_n", info.get("activaCode"));
				doc.addField("resume_isTao_i", info.get("isTao"));
				doc.addField("resume_price_i", 0);
				//doc.addField("resume_resumeName_t", info.get("resumeName"));
				//doc.addField("resume_resumeNumber_s", info.get("resumeNumber"));
				doc.addField("resume_maritalStatus_i", info.get("maritalStatus"));
				doc.addField("resume_expectWorkType_i", info.get("expectWorkType"));
				doc.addField("resume_zl_search_s", eduSearchMap.get(info.get("userId").toString()));
				doc.addField("resume_selfEvaluate_t", info.get("selfEvaluate")+searchMap.get(info.get("userId").toString()));
				doc.addField("resume_latelyCompName_t", latelyCompNameMap.get(info.get("userId").toString()));//最近工作过得企业名称
				doc.addField("resume_compName_t", compNameMap.get(info.get("userId").toString()));
				String cityName= "";
				String cityName1 = "0";
				String cityName2 = "0";
				if(info.get("cityName1").toString().equals("北京") || info.get("cityName1").toString().equals("天津")
						|| info.get("cityName1").toString().equals("上海") || info.get("cityName1").toString().equals("重庆")){
					cityName1 = "0";
				}else{
					cityName1 = info.get("cityName1").toString();
				}
				
				if(!info.get("cityName2").toString().equals(info.get("cityName3").toString())){
					cityName2 = info.get("cityName2").toString();
				}
				cityName = cityName1+"-"+cityName2+"-"+info.get("cityName3").toString();
				doc.addField("resume_livingCityName_s", cityName.replace("-0", "").replace("0-", ""));
				
				String cityId = "";
				if(info.get("superId").toString().equals("0") && info.get("firstSuperId").toString().equals("0")){
					cityId+=info.get("cityId").toString();
				}else if(!info.get("superId").toString().equals("0") && info.get("firstSuperId").toString().equals("0")){
					cityId+=info.get("cityId").toString()+","+info.get("superId").toString();
				}else if(!info.get("superId").toString().equals("0") && !info.get("firstSuperId").toString().equals("0")){
					cityId+=info.get("cityId").toString()+","+info.get("superId").toString()+","+info.get("firstSuperId").toString();
				}
				
				doc.addField("resume_livingCityId_ss", Arrays.asList(cityId.split(",")));
				docList.add(doc);
			}
			try {
				SolrService.buildIndex(docList);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
