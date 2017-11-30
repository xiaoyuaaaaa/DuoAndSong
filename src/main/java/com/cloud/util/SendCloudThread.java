package com.cloud.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendCloudThread implements Runnable{

	private String invoke_name;
	private String substitution_vars;
	private String subject;
	private Boolean flag;
	
	/**
	 * 邮件设置
	 * @param invoke_name 邮件模板名称
	 * @param substitution_vars json格式的替换变量
	 * @param subject 邮件标题
	 */
	public SendCloudThread(String invoke_name,String substitution_vars,String subject,Boolean flag) {
		this.invoke_name = invoke_name;
		this.substitution_vars = substitution_vars;
		this.subject = subject;
		this.flag = flag;
	}
	
	@Override
	public void run() {
		try {
			String url = "https://sendcloud.sohu.com/webapi/mail.send_template.xml";
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httpost = new HttpPost(url);
		
		    List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		    nvps.add(new BasicNameValuePair("template_invoke_name", invoke_name)); //邮件模板名称
		    nvps.add(new BasicNameValuePair("substitution_vars", substitution_vars)); // json格式的替换变量
		    if(flag){ //批量
		    	 nvps.add(new BasicNameValuePair("api_user", SysConfig.getValue("api_user_all").toString())); //使用api_user和api_key进行验证
		    }else{ //触发
		    	 nvps.add(new BasicNameValuePair("api_user", SysConfig.getValue("api_user").toString())); //使用api_user和api_key进行验证
		    }
		    nvps.add(new BasicNameValuePair("api_key", SysConfig.getValue("api_key").toString()));
		    nvps.add(new BasicNameValuePair("from", "suggestion@duojianli.com"));// 发信人，用正确邮件地址替代
		    nvps.add(new BasicNameValuePair("fromname", "多简历"));// 发信人名称
		    nvps.add(new BasicNameValuePair("subject", subject));//邮件标题
		    httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		
		    HttpResponse response = httpclient.execute(httpost);
		
		    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
		        System.out.println(EntityUtils.toString(response.getEntity()));
		    } else {
		        System.err.println("error");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
