package com.cloud.resume.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cloud.common.model.ResultBaseModel;
import com.cloud.resume.dao.OperationDao;
import com.cloud.resume.model.ContactModel;
import com.cloud.resume.service.OperationService;
import com.cloud.resume.util.DetailUtil;
import com.cloud.util.CheckUtil;

/** 
 * @author tobber
 * @version 2017年11月13日
 */
@Service
public class OperationServiceImpl implements OperationService{
	
	@Resource
	private OperationDao operationDao;
	
	@Override
	public ResponseEntity<ContactModel> getCvContact(Integer resumeId,
			Integer searchId, String token,String r_t,HttpServletResponse response,
			HttpServletRequest request) {
		try {
			if(DetailUtil.isGetDetail(resumeId, searchId, token, r_t)){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("resumeId", resumeId);
				paramMap.put("searchId", searchId);
				paramMap.put("token", token);
				paramMap.put("userId", request.getSession().getAttribute("userId")+"");
				operationDao.getCvContact(paramMap);
				if(paramMap.get("resultNumber")!=null && paramMap.get("resultNumber").toString().equals("200")){
					ContactModel contactModel = new ContactModel(Integer.parseInt(paramMap.get("resultNumber")+""),paramMap.get("resultMessage").toString());
					contactModel.setEmail(paramMap.get("resultEmail")+"");
					contactModel.setTelephone(paramMap.get("resultPhone")+"");
					contactModel.setName(paramMap.get("resultName")+"");
					return new ResponseEntity<ContactModel>(contactModel,HttpStatus.OK);
				}else{
					return new ResponseEntity<ContactModel>(new ContactModel(Integer.parseInt(paramMap.get("resultNumber")+""),paramMap.get("resultMessage").toString()),HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ContactModel>(new ContactModel(301,"请正确操作"),HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ContactModel>(new ContactModel(301,"服务器内部错误"),HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResultBaseModel> storageCv(String resumeIds,
			Integer searchId, String token,HttpServletResponse response,
			HttpServletRequest request) {
		try {
			if(CheckUtil.matcheVerify(resumeIds, "[0-9,]{1,1000}") && 
					CheckUtil.matcheVerify(searchId+"", "[0-9]{1,10}") && 
					CheckUtil.matcheVerify(token+"", "[0-9]{1,50}")){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("searchId", searchId);
				paramMap.put("token", token);
				paramMap.put("userId", request.getSession().getAttribute("userId")+"");
				int num = 0;
				for (String resumeId:resumeIds.split(",")) {
					paramMap.put("resumeId", resumeId);
					try {
						num = num+operationDao.addStorageLogs(paramMap);
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println("重复暂存");
					}
				}
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"操作成功"),HttpStatus.OK);
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请正确操作"),HttpStatus.OK);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"),HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResultBaseModel> cancelStorageCv(String ids,HttpServletResponse response,HttpServletRequest request) {
		try {
			if(CheckUtil.matcheVerify(ids, "[0-9,]{1,1000}")){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("userId", request.getSession().getAttribute("userId")+"");
				paramMap.put("ids", ids);
				operationDao.delStorageLogs(paramMap);
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"删除成功"),HttpStatus.OK);
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请正确操作"),HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"),HttpStatus.OK);
		}
	}
}
