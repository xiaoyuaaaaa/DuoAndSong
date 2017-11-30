package com.cloud.binding.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.binding.dao.BindingDao;
import com.cloud.binding.model.BindingModel;
import com.cloud.binding.service.BindingService;
import com.cloud.common.model.ResultBaseModel;
import com.cloud.util.CheckUtil;
import com.cloud.util.HttpUtil;

import net.sf.json.JSONObject;
/**
 * @version 2017年11月9日11:59:41
 * @author zqs
 *
 */
@Service
public class BindingServiceImpl implements BindingService{

	@Resource
	private BindingDao bindingDao;

	@Override
	public ResponseEntity<ResultBaseModel> accountBinding(String type,String compName, String userName, String pwd,
			HttpServletResponse response, HttpServletRequest request) {
		
		try{
			if(CheckUtil.matcheVerify(type, "[1-9]{1}") 
					&& CheckUtil.strVerify(userName, 1, 50) 
					&& CheckUtil.strVerify(pwd, 1, 50)){
				
				Map<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("i_compUserId", request.getSession().getAttribute("userId").toString());
				dataMap.put("i_compName", "");
				dataMap.put("i_userName", userName);
				dataMap.put("i_userPassWord", pwd);
				dataMap.put("i_accountType", type);
				dataMap.put("i_requestIp", request.getRemoteAddr());
				dataMap.put("compUserId", request.getSession().getAttribute("userId").toString());
				dataMap.put("userName", userName);
				dataMap.put("password", pwd);
				dataMap.put("accountType", type);
				if(type.equals("1")){
					if(CheckUtil.strVerify(compName, 1, 50)){
						dataMap.put("i_compName", compName);
						dataMap.put("ctmName", compName);
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数错误"), HttpStatus.OK);
					}
				}
				
				int num = bindingDao.checkBingdingStatus(dataMap);
				if(num<1){
					String url = "";
					String ip =  HttpUtil.sendHttp("http://console.yifengjianli.com/getIp?auth=3", null, null, "POST").toString();
					ip = ip.substring(ip.indexOf("appIp")+8, ip.indexOf("}}")-1); 
					
					url = "http://"+ip+"/auth/accountBinding";
					
					Map<String, Object> resultMap = HttpUtil.sendHttp(url, null, dataMap, "POST");
					JSONObject resultJosn =JSONObject.fromObject(resultMap.get("result").toString());
					if(resultJosn.get("code") !=null && resultJosn.get("code").toString().equals("200")){
						dataMap.put("i_rencaiNum", 0);//人才夹简历数量
						dataMap.put("i_toudiNum", 0);//投递夹简历数量
						dataMap.put("i_compNumber", 0);//账户对应的公司
						dataMap.put("i_city", 0);//前程账户对应的城市
						if(resultJosn.get("downNum") !=null){
							dataMap.put("i_rencaiNum", resultJosn.get("downNum"));
						}
						if(resultJosn.get("deliveryNum") !=null){
							dataMap.put("i_toudiNum", resultJosn.get("deliveryNum"));
						}
						
						if(resultJosn.get("companyId") !=null){//公司ID
							dataMap.put("i_compNumber", resultJosn.get("companyId"));
						}
						
						if(resultJosn.get("city") !=null){//城市
							dataMap.put("i_city", resultJosn.get("city"));
						}
						
						bindingDao.accountBinding(dataMap);
						if(dataMap.get("resultNumber") !=null && dataMap.get("resultNumber").toString().equals("1")){
							request.getSession().setAttribute("status", 1);
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"绑定成功"), HttpStatus.OK);
						}else{
							return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"该账户已被绑定"), HttpStatus.OK);
						}
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,resultJosn.get("message").toString()), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"该账户已被绑定"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"参数错误"), HttpStatus.OK);
			}
		}catch (Exception e) { 
            e.printStackTrace();  
            return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
        }
	}

	@Override
	public ResponseEntity<BindingModel> getUserBingdingStatus(HttpServletResponse response,HttpServletRequest request) {
		try{
			BindingModel bindingModel = new BindingModel();
			bindingModel.setQc("0");
			bindingModel.setZl("0");
			bindingModel.setLg("0");
			bindingModel.setGj("0");
			bindingModel.setYc("0");
			bindingModel.setLp("0");
			bindingModel.setWb("0");
			
			List<Map<String,Object>> bingList = bindingDao.getUserBingdingStatus(request.getSession().getAttribute("userId").toString());
			if(bingList !=null && bingList.size()>0){
				for (int i = 0; i < bingList.size(); i++) {
					if(bingList.get(i).get("accountType").toString().equals("1")){
						bindingModel.setQc("1");
					}else if(bingList.get(i).get("accountType").toString().equals("2")){
						bindingModel.setZl("1");
					}else if(bingList.get(i).get("accountType").toString().equals("3")){
						bindingModel.setLg("1");
					}else if(bingList.get(i).get("accountType").toString().equals("4")){
						bindingModel.setGj("1");
					}else if(bingList.get(i).get("accountType").toString().equals("5")){
						bindingModel.setYc("1");
					}else if(bingList.get(i).get("accountType").toString().equals("6")){
						bindingModel.setLp("1");
					}else if(bingList.get(i).get("accountType").toString().equals("7")){
						bindingModel.setWb("1");
					}
				}
			}
			bindingModel.setCode(200);
			bindingModel.setMessage("获取成功");
			return new ResponseEntity<BindingModel>(bindingModel, HttpStatus.OK);
		}catch (Exception e) { 
            e.printStackTrace();  
            return new ResponseEntity<BindingModel>(new BindingModel(301,"服务器内部错误"), HttpStatus.OK);
        }
	}

}
