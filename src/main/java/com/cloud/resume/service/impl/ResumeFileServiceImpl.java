package com.cloud.resume.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.resume.dao.ResumeDao;
import com.cloud.resume.model.NewResume;
import com.cloud.resume.service.ResumeFileService;
import com.cloud.resume.util.fileConverter.PDFConverter;
import com.cloud.resume.util.fileConverter.WordConverter;
import com.cloud.util.CheckUtil;
import com.cloud.util.SysConfig;

import net.sf.json.JSONObject;

/**
 * @version 2017年11月14日10:18:09
 * @author zqs
 *
 */
@Service
public class ResumeFileServiceImpl implements ResumeFileService{
	@Resource
	private ResumeDao resumeDao;
	
	@Autowired
	@Qualifier("taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public ModelAndView initPdf(String userId,HttpServletRequest request,HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		if(CheckUtil.matcheVerify(userId, "[1-9][0-9]{0,10}")){
			NewResume resume = resumeDao.queryResumeByResumeId(Integer.parseInt(userId));
			if(resume!=null){
				mav.addObject("resume",resume);
				mav.addObject("projects",resume.getProjectList());
				mav.addObject("educations",resume.getEduList());
				mav.addObject("workEx",resume.getWorkList());
				mav.addObject("trainings",resume.getTrainList());
				mav.addObject("profeSkills",resume.getSkillsList());
				mav.addObject("languages",resume.getLanguagesList());
			}
		}
		mav.setViewName("jsp/resumePdf");
		return mav;
	}
	
	@Override
	public ModelAndView initWord(String userId,HttpServletRequest request,HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		if(CheckUtil.matcheVerify(userId, "[1-9][0-9]{0,10}")){
			NewResume resume = resumeDao.queryResumeByResumeId(Integer.parseInt(userId));
			if(resume!=null){
				mav.addObject("resume",resume);
				mav.addObject("projects",resume.getProjectList());
				mav.addObject("educations",resume.getEduList());
				mav.addObject("workEx",resume.getWorkList());
				mav.addObject("trainings",resume.getTrainList());
				mav.addObject("profeSkills",resume.getSkillsList());
				mav.addObject("languages",resume.getLanguagesList());
			}
		}
		mav.setViewName("jsp/resumeWord");
		return mav;
	}
	
	@Override
	public void creatPdf(String userId,HttpServletRequest request,HttpServletResponse reponse) {
		if(CheckUtil.matcheVerify(userId, "[0-9,]{1,1000}")){
			String[] ids = userId.split(",");
			for (int i = 0; i < ids.length; i++) {
				taskExecutor.execute(new PDFConverter(SysConfig.getValue("PDF_FILE_PATH").toString(),ids[i]+".pdf",SysConfig.getValue("PDF_INIT_URL").toString()+ids[i]));
			}
		}
	}
	
	@Override
	public void creatWord(String userId,HttpServletRequest request,HttpServletResponse reponse) {
		if(CheckUtil.matcheVerify(userId, "[0-9,]{1,1000}")){
			String[] ids = userId.split(",");
			for (int i = 0; i < ids.length; i++) {
				taskExecutor.execute(new WordConverter(SysConfig.getValue("WORD_FILE_PATH").toString(),ids[i]+".doc",SysConfig.getValue("WORD_INIT_URL").toString()+ids[i]));
			}
		}
	}

}
