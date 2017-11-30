package com.cloud.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/** 
 * @author tobber
 * @version 2015年8月12日
 */
public class SendSMS implements Runnable{

	private String mobile;
	private String message;
	
	/**
	 * @param mobile 手机号码
	 * @param message 短信内容
	 */
	public SendSMS(String mobile,String message) {
		this.mobile = mobile;
		this.message = message;
	}
	
	@Override
	public void run() {
		try {
			
			String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
			HttpClient client = new HttpClient(); 
			PostMethod method = new PostMethod(Url); 
				
			client.getParams().setContentCharset("UTF-8");
			method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
			
			NameValuePair[] data = {//提交短息
				    new NameValuePair("account", "cf_jackchenxm"),  
				    new NameValuePair("password", "43216836aaa"), //密码可以使用明文或者使用32位MD5加密
				    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
				    new NameValuePair("mobile", mobile), 
				    new NameValuePair("content", message),
			};
			method.setRequestBody(data);		
				
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			if("2".equals(code)){
				System.out.println("短信发送成功");
			}else{
				System.out.println("短信发送失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("短信发送异常");
		}
	}

	public SendSMS() {
	}
	
}
