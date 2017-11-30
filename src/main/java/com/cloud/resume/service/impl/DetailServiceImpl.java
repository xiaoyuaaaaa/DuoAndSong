package com.cloud.resume.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cloud.resume.dao.DetailDao;
import com.cloud.resume.model.NewResume;
import com.cloud.resume.service.DetailService;
import com.cloud.resume.util.DetailUtil;

/** 
 * @author tobber
 * @version 2017年11月8日
 */
@Service
public class DetailServiceImpl implements DetailService{

	@Resource
	private DetailDao detailDao;
	
	@Override
	public ResponseEntity<NewResume> getCvDetail(Integer resumeId,
			Integer searchId, String token,String r_t,HttpServletResponse response,
			HttpServletRequest request) {
		try {
			if(DetailUtil.isGetDetail(resumeId, searchId, token, r_t)){
				if(request.getSession().getAttribute("status")!=null && request.getSession().getAttribute("status").toString().equals("1")){
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("resumeId", resumeId);
					paramMap.put("searchId", searchId);
					paramMap.put("token", token);
					paramMap.put("userId", request.getSession().getAttribute("userId")+"");
					NewResume resume = detailDao.getResumeDetailByResumeId(paramMap);
					if(resume!=null){
						resume.setCode(200);
						resume.setMessage("获取成功");
						if(resume.getIsPay()==0){
							resume.setEmail(null);
							resume.setTelephone(null);
							resume.setName(null);
						}
						detailDao.addReadLogs(paramMap);
						return new ResponseEntity<NewResume>(resume,HttpStatus.OK);
					}else{
						return new ResponseEntity<NewResume>(new NewResume(301,"获取失败"),HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<NewResume>(new NewResume(302,"抱歉您的账号还未企业认证无法查看简历"),HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<NewResume>(new NewResume(301,"请正确操作"),HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<NewResume>(new NewResume(301,"服务器内部错误"),HttpStatus.OK);
		}
	}
	
}
