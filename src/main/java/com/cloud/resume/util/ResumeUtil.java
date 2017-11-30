package com.cloud.resume.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cloud.resume.model.NewEducations;
import com.cloud.resume.model.NewLanguage;
import com.cloud.resume.model.NewProfeSkill;
import com.cloud.resume.model.NewProjectExp;
import com.cloud.resume.model.NewResume;
import com.cloud.resume.model.NewTraining;
import com.cloud.resume.model.NewWorkExp;

import net.sf.json.JSONObject;

public class ResumeUtil {
	public static NewResume converted(JSONObject jsonObject){
		if(jsonObject.get("selfEvaluate") !=null){
			jsonObject.put("selfEvaluate", filterEmoji(jsonObject.get("selfEvaluate").toString()).replace("😃", ""));
		}
		
		NewResume resume = (NewResume) JSONObject.toBean(jsonObject, NewResume.class);
		resume.setEducation(setEud(resume.getEducation()));
		//edu
		net.sf.json.JSONArray eduArray=net.sf.json.JSONArray.fromObject(jsonObject.get("eduList").toString()); 
		if(eduArray!=null && eduArray.size()>0){
			List<NewEducations> eduList = new ArrayList<NewEducations>();
			for (int i = 0; i < eduArray.size(); i++) {
				NewEducations educations = new NewEducations();
				Map<String, Object> eduMap = (Map<String, Object>)eduArray.get(i);
				if(eduMap.get("startTime")!=null && isNull(eduMap.get("startTime").toString())){
					educations.setStartTime(eduMap.get("startTime").toString().trim());
				}else{
					educations.setStartTime("0");
				}
				if(eduMap.get("endTime")!=null && isNull(eduMap.get("endTime").toString())){
					educations.setEndTime(eduMap.get("endTime").toString().trim());
				}else{
					educations.setEndTime("0");
				}
				if(eduMap.get("education")!=null){
					educations.setEducation(Integer.parseInt(eduMap.get("education").toString()));
				}
				if(eduMap.get("schoolName") !=null){
					educations.setSchoolName(filterEmoji(eduMap.get("schoolName").toString()));
				}
				if(eduMap.get("specialty") !=null){
					educations.setSpecialty(filterEmoji(eduMap.get("specialty").toString()));
				}
				educations.setEducation(setEud(educations.getEducation()));
				eduList.add(educations);
			}
			resume.setEduList(eduList);
		}else{
			resume.setEduList(null);
		}
		
		//工作经历
		net.sf.json.JSONArray workArray=net.sf.json.JSONArray.fromObject(jsonObject.get("workList").toString()); 
		if(workArray!=null && workArray.size()>0){
			List<NewWorkExp> workList = new ArrayList<NewWorkExp>();
			for (int i = 0; i < workArray.size(); i++) {
				NewWorkExp workExp = new NewWorkExp();
				Map<String, Object> workMap = (Map<String, Object>)workArray.get(i);
				if(workMap.get("compIndustry")!=null){
					workExp.setCompIndustry(workMap.get("compIndustry").toString());
				}
				if(workMap.get("compName")!=null){
					workExp.setCompName(filterEmoji(workMap.get("compName").toString()).replace("弢", ""));
				}
				
				if(workMap.get("compProperty")!=null && isNull(workMap.get("compProperty").toString())){
					workExp.setCompProperty(Integer.parseInt(workMap.get("compProperty").toString()));
				}
				if(workMap.get("compSize")!=null && isNull(workMap.get("compSize").toString())){
					workExp.setCompSize(Integer.parseInt(workMap.get("compSize").toString()));
				}
				if(workMap.get("startTime")!=null){
					workExp.setStartTime(workMap.get("startTime").toString());
				}
				if(workMap.get("endTime")!=null && isNull(workMap.get("endTime").toString())){
					workExp.setEndTime(workMap.get("endTime").toString());
				}else{
					workExp.setEndTime("0");
				}
				if(workMap.get("jobTitle")!=null){
					workExp.setJobTitle(workMap.get("jobTitle").toString());	
				}
				if(workMap.get("salary")!=null){
					workExp.setSalary(workMap.get("salary").toString());	
				}
				if(workMap.get("workDesc")!=null){
					workExp.setWorkDesc(filterEmoji(workMap.get("workDesc").toString()));
				}
				workList.add(workExp);
			}
			resume.setWorkList(workList);
		}else{
			resume.setWorkList(null);
		}
		
		net.sf.json.JSONArray projectArray=net.sf.json.JSONArray.fromObject(jsonObject.get("projectList").toString()); 
		if(projectArray!=null && projectArray.size()>0){
			List<NewProjectExp> projectList = new ArrayList<NewProjectExp>();
			for (int i = 0; i < projectArray.size(); i++) {
				NewProjectExp projects = new NewProjectExp();
				Map<String, String> projectMap = (Map<String, String>)projectArray.get(i);
				projects.setDevTools(projectMap.get("devTools"));
				projects.setStartTime(projectMap.get("startTime"));
				if(isNull(projectMap.get("endTime"))){
					projects.setEndTime(projectMap.get("endTime"));
				}else{
					projects.setEndTime("0");
				}
				projects.setHardware(projectMap.get("hardware"));
				
				if(projectMap.get("projectDesc") !=null){
					projects.setProjectDesc(filterEmoji(projectMap.get("projectDesc").toString()));
				}
				projects.setProjectName(projectMap.get("projectName"));
				if(projectMap.get("responsibilityDesc") !=null){
					projects.setResponsibilityDesc(filterEmoji(projectMap.get("responsibilityDesc").toString()));
				}
				
				projects.setSoftware(projectMap.get("software"));
				projectList.add(projects);
			}
			resume.setProjectList(projectList);
		}else{
			resume.setProjectList(null);
		}
		
		net.sf.json.JSONArray TrainArray=net.sf.json.JSONArray.fromObject(jsonObject.get("trainList").toString());
		if(TrainArray!=null && TrainArray.size()>0){
			List<NewTraining> trainList = new ArrayList<NewTraining>();
			for (int i = 0; i < TrainArray.size(); i++) {
				NewTraining train = new NewTraining();
				Map<String, String> trainMap = (Map<String, String>)TrainArray.get(i);
				train.setStartTime(trainMap.get("startTime"));
				if(isNull(trainMap.get("endTime"))){
					train.setEndTime(trainMap.get("endTime"));
				}else{
					train.setEndTime("0");
				}
				train.setAddress(trainMap.get("address"));
				train.setCertificateName(trainMap.get("certificateName"));
				train.setMachinery(trainMap.get("machinery"));
				train.setTrainName(trainMap.get("trainName"));
				trainList.add(train);
			}
			resume.setTrainList(trainList);
		}else{
			resume.setTrainList(null);
		}
		
		net.sf.json.JSONArray skillArray=net.sf.json.JSONArray.fromObject(jsonObject.get("skillsList").toString()); 
		if(skillArray!=null && skillArray.size()>0){
			List<NewProfeSkill> skillList = new ArrayList<NewProfeSkill>();
			for (int i = 0; i < skillArray.size(); i++) {
				NewProfeSkill skill = new NewProfeSkill();
				Map<String, Object> skillMap = (Map<String, Object>)skillArray.get(i);
				if(skillMap.get("skillName")!=null){
					skill.setSkillName(skillMap.get("skillName").toString().replace("😊", ""));
				}
				if(skillMap.get("masterDegree")!=null){
					skill.setMasterDegree(skillMap.get("masterDegree").toString());
				}
				if (skillMap.get("usedMonths")!=null) {
					skill.setUsedMonths(Integer.parseInt(skillMap.get("usedMonths").toString()));
				}
				skillList.add(skill);
			}
			resume.setSkillsList(skillList);
		}else{
			resume.setSkillsList(null);
		}
		
		net.sf.json.JSONArray languagesArray=net.sf.json.JSONArray.fromObject(jsonObject.get("languagesList").toString());
		if(languagesArray!=null && languagesArray.size()>0){
			List<NewLanguage> languagesList = new ArrayList<NewLanguage>();
			for (int i = 0; i < languagesArray.size(); i++) {
				NewLanguage languages = new NewLanguage();
				Map<String, String> languagesMap = (Map<String, String>)languagesArray.get(i);
				languages.setHearSpeakSkill(languagesMap.get("hearSpeakSkill"));
				languages.setLanguageName(languagesMap.get("languageName"));
				languages.setReadWriteSkill(languagesMap.get("readWriteSkill"));
				languagesList.add(languages);
			}
			resume.setLanguagesList(languagesList);
		}else{
			resume.setLanguagesList(null);
		}
		
		return resume;
	}
	
	public static boolean isNull(String str){
		if(str!=null && str.length()>0 && !str.trim().equals("") && !str.trim().equals("null")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
     * emoji表情替换
     *
     * @param source 原字符串               
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source) {
    	if(source.trim().isEmpty()){  
            return source;  
        }  
        String pattern="[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";  
        String reStr="";  
        Pattern emoji=Pattern.compile(pattern);  
        Matcher  emojiMatcher=emoji.matcher(source);  
        source=emojiMatcher.replaceAll(reStr);  
        return source;  
    }
    
    //1 初中、2 中技、3 高中、4 中专、5 大专、6 本科、7 硕士、8 MBA、9 EMBA、10 博士、11 其他
    public static int setEud(int education){
    	int endEdu = 11;
    	try{
    		if(education==9){
        		endEdu=1;
        	}else if(education==13){
        		endEdu=2;
        	}else if(education==7){
        		endEdu=3;
        	}else if(education==12){
        		endEdu=4;
        	}else if(education==5){
        		endEdu=5;
        	}else if(education==4){
        		endEdu=6;
        	}else if(education==3){
        		endEdu=7;
        	}else if(education==10){
        		endEdu=8;
        	}else if(education==11){
        		endEdu=9;
        	}else if(education==1){
        		endEdu=10;
        	}else{
        		endEdu=11;
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return endEdu;
    }
}
