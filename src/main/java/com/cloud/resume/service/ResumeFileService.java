package com.cloud.resume.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * @version 2017年11月14日10:17:28
 * @author zqs
 *
 */
public interface ResumeFileService {
	/**
     * 生成pdf简历文件
     * @param request
     * @param file 
     * @param filePath 路径
     * @return
    */
	public ModelAndView initPdf(String userId,HttpServletRequest request,HttpServletResponse reponse);
	
	/**
     * 生成word简历文件
     * @param request
     * @param file 
     * @param filePath 路径
     * @return
    */
	public ModelAndView initWord(String userId,HttpServletRequest request,HttpServletResponse reponse);
	
	/**
     * 初始化pdf模板简历
     * @param request
     * @param file 
     * @param filePath 路径
     * @return
    */
	public void creatPdf(String userIds,HttpServletRequest request,HttpServletResponse reponse);
	
	/**
     * 初始化Word模板简历
     * @param request
     * @param file 
     * @param filePath 路径
     * @return
    */
	public void creatWord(String userIds,HttpServletRequest request,HttpServletResponse reponse);
}
