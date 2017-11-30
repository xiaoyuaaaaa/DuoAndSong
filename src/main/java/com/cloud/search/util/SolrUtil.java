package com.cloud.search.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONObject;
import org.apache.solr.common.SolrDocument;
import com.cloud.search.model.SearchModel;
import com.cloud.search.model.SolrResultModel;

/** 
 * @author tobber
 * @version 2017年11月7日
 */
public class SolrUtil {
	
	public static final String[] RESUME_POOL={
		"resume_expectedSalary_s", //期望薪资
		"talent_userId", //用户ID
		"resume_jobTitleName_ss", //期望职位
		"resume_lastTime_dt", //最近更新时间
		"resume_jobState_i", //职位状态
		"resume_jobYear_i", //工作年限
		"resume_education_i", //学历
		"resume_age_s", //年龄
		"resume_cityName_s", //期望城市
		"resume_sex_i", //性别
		"resume_livingCityName_s", //现居地
		"resume_lastEducation_t",//最高学历
		"resume_lastCompName_t"//最近公司
	};
	
	/**
	 * 根据SearchModel组装solr搜索条件
	 * @param searchModel
	 * @return
	 */
	public static String getSearchCondition(SearchModel searchModel){
		StringBuffer qeury = new StringBuffer();
		if(searchModel.getRM_S_1_1()!=null){
			qeury.append(" AND resume_destination_ss:("+searchModel.getRM_S_1_1().replaceAll(",", " OR ")+")");
		}
		if(searchModel.getRM_S_1_2()!=null){
			qeury.append(" AND resume_jobTitle_ss:("+searchModel.getRM_S_1_2().replaceAll(",", " OR ")+")");
		}
		if(searchModel.getRM_S_1_3()!=null){
			if(searchModel.getRM_S_1_4()!=null){
				qeury.append(" AND resume_latelyCompName_t:\""+searchModel.getRM_S_1_3()+"\"");
			}else{
				qeury.append(" AND resume_compName_t:\""+searchModel.getRM_S_1_3()+"\"");
			}
		}
		if(searchModel.getRM_S_1_5()!=null){
			qeury.append(" AND resume_expectedSalary_s:"+searchModel.getRM_S_1_5());
		}
		if(searchModel.getRM_S_1_6()!=null){
			qeury.append(" AND resume_jobState_i:"+searchModel.getRM_S_1_6());
		}
		if(searchModel.getRM_S_1_7()!=null){
			qeury.append(" AND resume_sex_i:"+searchModel.getRM_S_1_7());
		}
		if(searchModel.getRM_S_1_8()!=null){
			switch (searchModel.getRM_S_1_8()) {
			case "1":
				qeury.append(" AND resume_lastTime_dt:[NOW/DAY-1DAY TO *]");
				break;
			case "2":
				qeury.append(" AND resume_lastTime_dt:[NOW/DAY-3DAY TO *]");
				break;
			case "3":
				qeury.append(" AND resume_lastTime_dt:[NOW/DAY-7DAY TO *]");
				break;
			case "4":
				qeury.append(" AND resume_lastTime_dt:[NOW/DAY-30DAY TO *]");
				break;
			case "5":
				qeury.append(" AND resume_lastTime_dt:[NOW/DAY-90DAY TO *]");
				break;
			case "6":
				qeury.append(" AND resume_lastTime_dt:[NOW/DAY-365DAY TO *]");
				break;
			case "7":
				qeury.append(" AND resume_lastTime_dt:[NOW/DAY-182DAY TO *]");
				break;
			}
		}
		if(searchModel.getRM_S_1_9()!=null){
			qeury.append(" AND resume_education_i:["+searchModel.getRM_S_1_9().replace(",", " TO ")+"]");
		}
		if(searchModel.getRM_S_1_10()!=null){
			qeury.append(" AND resume_age_s:["+searchModel.getRM_S_1_10().replace(",", " TO ")+"]");
		}
		if(searchModel.getRM_S_1_11()!=null){
			qeury.append(" AND resume_jobYear_i:["+searchModel.getRM_S_1_11().replace(",", " TO ")+"]");
		}
		if(searchModel.getRM_S_1_12()!=null){
			for (String keyword : searchModel.getRM_S_1_12().split(" ")) {
				qeury.append(" AND resume_selfEvaluate_t:\""+keyword+"\"");
			}
		}
		if(qeury!=null && qeury.length()>5){
			return qeury.toString().substring(5);
		}else{
			return null;
		}
	}
	
	public static SolrResultModel setResumePool(SolrDocument solrDocument){
		SolrResultModel resume = new SolrResultModel();
		SimpleDateFormat sd = new SimpleDateFormat ("yyyy-MM-dd");
		resume.setUserId(Integer.parseInt(solrDocument.get("talent_userId").toString()));
		if(solrDocument.get("resume_jobTitleName_ss")!=null){
			resume.setJobTitle(solrDocument.get("resume_jobTitleName_ss").toString().replace("[", "").replace("]", ""));
		}else{
			resume.setJobTitle("");
		}
		resume.setJobStatus(solrDocument.get("resume_jobState_i")+"");
		resume.setJobYear(solrDocument.get("resume_jobYear_i")+"");
		resume.setEducation(solrDocument.get("resume_education_i")+"");
		resume.setAge(solrDocument.get("resume_age_s")+"");
		resume.setSex(solrDocument.get("resume_sex_i")+"");
		resume.setCityName(solrDocument.get("resume_cityName_s")+"");
		resume.setExpectedSalary(solrDocument.get("resume_expectedSalary_s")+"");//期望薪资
		if(solrDocument.get("resume_lastTime_dt")!=null){
			JSONObject json = JSONObject.fromObject(solrDocument.get("resume_lastTime_dt"));
			String time=json.get("time").toString();
	        long data = Long.parseLong(time);
	        Date date = new Date(data);
			resume.setUpdateTime(sd.format(date));
		}
		if(solrDocument.get("resume_livingCityName_s")!=null && !solrDocument.get("resume_livingCityName_s").toString().equals("0")){
			resume.setLateCityName(solrDocument.get("resume_livingCityName_s")+"");
		}
		if(solrDocument.get("resume_lastEducation_t")!=null){
			String[] edus = solrDocument.get("resume_lastEducation_t").toString().split("==");
			if(edus.length==4){
				resume.setLateSchoolName(edus[0]);
				resume.setLateEduTime(edus[1]);
				resume.setLateMajor(edus[2]);
				resume.setLateEducation(edus[3]);
			}
		}
		
		if(solrDocument.get("resume_lastCompName_t")!=null){
			String[] companys = solrDocument.get("resume_lastCompName_t").toString().split("==");
			if(companys.length==8){
				resume.setLateCompTime(companys[1]);
				resume.setLateCompName(companys[0]);
				if(companys[2]!=null && !companys[2].equals("null") && !companys[2].trim().equals("")){
					resume.setLateJobTitle(companys[2]);
				}
				resume.setLateCompDesc(companys[7]);
			}
		}
		return resume;
	}
}
