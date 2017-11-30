package com.cloud.common.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.common.model.ResultBaseModel;
import com.cloud.user.dao.EditDao;
import com.cloud.util.CheckUtil;

/** 
 * @author tobber
 * @version 2017年11月3日
 */
@Api(value="sms",description="发送短信Controller")
@Controller
@RequestMapping(value="sms")
public class SendSMSCodeController {
	
	@Resource
	private EditDao editDao;
	
	@ApiOperation(value="发用短信验证码并验证码图片验证码",httpMethod="POST")
	@RequestMapping(value = "sendSMS",method=RequestMethod.POST)
	@ResponseBody
	@ApiImplicitParams({
		@ApiImplicitParam(value="手机号码",name="mobile",required=true,dataType="String",paramType="query"),
		@ApiImplicitParam(value="图片验证码",name="imgCode",required=true,dataType="String",paramType="query"),
		@ApiImplicitParam(value="验证码类型：忘记密码发送传1，其他不传此参数：",name="type",required=false,dataType="int",paramType="query")
	})
	@ApiResponse(code=200,message="验证成功",response=ResultBaseModel.class)
	public ResponseEntity<ResultBaseModel> sendSMS(String mobile,String imgCode,Integer type,HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			if(CheckUtil.checkMobie(mobile)){
				if(request.getSession().getAttribute("imgCheckcode")!=null){
					//获取图片验证码的Code
					String imgCheckcode = request.getSession().getAttribute("imgCheckcode").toString();
					request.getSession().removeAttribute("imgCheckcode");
					if(imgCheckcode.toLowerCase().equals(imgCode.toLowerCase())){ //验证验证码是否正确
						int num = editDao.findPhoneByPhone(mobile);
						if((num>0 && type!=null && type==1) || (num<1 && (type==null || type!=1))){
							String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
							HttpClient client = new HttpClient(); 
							PostMethod method = new PostMethod(Url); 
							client.getParams().setContentCharset("UTF-8");
							method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
							int mobile_code = (int)((Math.random()*9+1)*100000);
						    String content = new String("您的验证码是："+mobile_code+"。"); 
							NameValuePair[] data = {//提交短息
								    new NameValuePair("account", "cf_jackchenxm"),  
								    new NameValuePair("password", "43216836a"), //密码可以使用明文或者使用32位MD5加密
								    new NameValuePair("mobile", mobile), 
								    new NameValuePair("content", content),
							};
							method.setRequestBody(data);		
							client.executeMethod(method);	
							String SubmitResult =method.getResponseBodyAsString();
							Document doc = DocumentHelper.parseText(SubmitResult); 
							Element root = doc.getRootElement();
							String code = root.elementText("code");	
							if("2".equals(code)){
								request.getSession().setAttribute("sms", mobile_code+"_"+System.currentTimeMillis()+"_"+mobile);
								return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(200,"发送成功"), HttpStatus.OK);
							}else{
								return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"短信发送失败"), HttpStatus.OK);
							}
						}else{
							if(type!=null && type==1){
								return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"手机未注册，请确认手机号码"), HttpStatus.OK);
							}else{
								return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"手机号码已被使用"), HttpStatus.OK);
							}
						}
					}else{
						return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"图片验证码错误"), HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请获取先图片验证码"), HttpStatus.OK);
				}
			}else{
				return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"请输入正确的手机号码"), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResultBaseModel>(new ResultBaseModel(301,"服务器内部错误"), HttpStatus.OK);
		}
    }

}
