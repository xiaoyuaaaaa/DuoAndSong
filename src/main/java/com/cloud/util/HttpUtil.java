package com.cloud.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;


/** 
 * @author tobber
 * @version 2017年7月26日
 */
public class HttpUtil {
	
	public static void main(String[] args) {
		String ip =  HttpUtil.sendHttp("http://console.yifengjianli.com/getIp?auth=1", null, null, "POST").toString();
		ip = ip.substring(ip.indexOf("appIp")+8, ip.indexOf("}}")-6);
		System.out.println(ip);
	}
	/**
	 * 模拟文件上传
	 * @param file 待上传的文件路径
	 * @param uploadUrl 上传服务接口路径
	 * @param fileName 文件名称，服务器获取的文件名称
	 * @return
	 */
	public static String uploadFile(String file, String uploadUrl) {
		try {
			String boundary = "Boundary-b1ed-4060-99b9-fca7ff59c113";
			String Enter = "\r\n";
			File xml = new File(file);
			FileInputStream fis = new FileInputStream(xml);
			URL url = new URL(uploadUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
			conn.setRequestProperty("Charsert","UTF-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
			conn.connect();
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			String part1 = "--" + boundary + Enter
					+ "Content-Type: application/octet-stream" + Enter+ "Content-Disposition: form-data; filename=\""
					+ URLEncoder.encode(xml.getName(), "UTF-8") + "\"; name=\"upload\"" + Enter + Enter;
			String part2 = Enter + "--" + boundary + Enter+ "Content-Type: text/plain" + Enter+ "Content-Disposition: form-data; name=\"parameter\""
					+ Enter + Enter + "hifreud" + Enter + "--" + boundary+ "--";
			byte[] xmlBytes = new byte[fis.available()];
			fis.read(xmlBytes);
			dos.writeBytes(part1);
			dos.write(xmlBytes);
			dos.writeBytes(part2);
			dos.flush();
			dos.close();
			fis.close();
			//获取返回值
			InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String sl;
            String result = "";
            while ((sl = br.readLine()) != null)
                result = result + sl;
            br.close();
            is.close();
            conn.disconnect();
            //System.out.print(result);
			conn.disconnect();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	
	public static JSONObject json_Http(String url,Map<String, String> setPropertyMap,Map<String, Object> dataMap,String method){
		JSONObject jsonObject = null;
		try {
			if(url!=null){
				URL realUrl = new URL(url);
				trustAllHttpsCertificates();//信任所有证书
				// 打开和URL之间的连接
				HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
				// 设置通用的请求属性
		    	connection.setRequestProperty("Pragma", "no-cache");
		    	if(setPropertyMap!=null && setPropertyMap.get("redirects")!=null && setPropertyMap.get("redirects").equals("false")){
		    		connection.setInstanceFollowRedirects(false);
		    	}
				if(setPropertyMap!=null && setPropertyMap.size()>0){
					for (String key:setPropertyMap.keySet()) {
						connection.setRequestProperty(key, setPropertyMap.get(key));
					}
				}
				connection.setRequestMethod(method);
				if(dataMap!=null && dataMap.size()>0){
					connection.setDoInput(true);// 使用 URL 连接进行输入  
					connection.setDoOutput(true);// 使用 URL 连接进行输出     
		            connection.setUseCaches(false);// 忽略缓存 
					StringBuffer dataBuffer = new StringBuffer();
						for(String key : dataMap.keySet()){
							dataBuffer.append(key+"="+dataMap.get(key)+"&");
				    	}
					OutputStream outputStream = connection.getOutputStream();  
		            outputStream.write(dataBuffer.toString().substring(0, dataBuffer.toString().length()-1).getBytes("UTF-8"));
		            outputStream.flush();
				}
				connection.connect();
				if(connection.getResponseCode()==200){                //200表示请求成功  
					BufferedReader is=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
					String line = null;
					StringBuffer bf = new StringBuffer();
					while ((line = is.readLine()) != null) {
						bf.append(line);
					}
		            //转换成json数据处理  
		            JSONArray jsonArray=new JSONArray("["+bf.toString()+"]");  
		            jsonObject = jsonArray.getJSONObject(0);
		        }  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static String base_http(String url,Map<String, String> setPropertyMap,Map<String, Object> dataMap,String method){
		String result = null;
		try {
			if(url!=null){
				URL realUrl = new URL(url);
				trustAllHttpsCertificates();//信任所有证书
				// 打开和URL之间的连接
				HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
				// 设置通用的请求属性
		    	connection.setRequestProperty("Pragma", "no-cache");
		    	if(setPropertyMap!=null && setPropertyMap.get("redirects")!=null && setPropertyMap.get("redirects").equals("false")){
		    		connection.setInstanceFollowRedirects(false);
		    	}
				if(setPropertyMap!=null && setPropertyMap.size()>0){
					for (String key:setPropertyMap.keySet()) {
						connection.setRequestProperty(key, setPropertyMap.get(key));
					}
				}
				connection.setRequestMethod(method);
				if(dataMap!=null && dataMap.size()>0){
					connection.setDoInput(true);// 使用 URL 连接进行输入  
					connection.setDoOutput(true);// 使用 URL 连接进行输出     
		            connection.setUseCaches(false);// 忽略缓存 
					StringBuffer dataBuffer = new StringBuffer();
						for(String key : dataMap.keySet()){
							dataBuffer.append(key+"="+dataMap.get(key)+"&");
				    	}
					OutputStream outputStream = connection.getOutputStream();  
		            outputStream.write(dataBuffer.toString().substring(0, dataBuffer.toString().length()-1).getBytes("UTF-8"));
		            outputStream.flush();
				}
				connection.connect();
				if(connection.getResponseCode()==200){                //200表示请求成功  
					BufferedReader is=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
					String line = null;
					StringBuffer bf = new StringBuffer();
					while ((line = is.readLine()) != null) {
						bf.append(line);
					}
					result = bf.toString();
		        }  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * 发送HTTP请求
	 * 
	 * */
	public static Map<String, Object> sendHttp(String url,Map<String, String> setPropertyMap,Map<String, Object> dataMap,String method){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(url!=null){
				URL realUrl = new URL(url);
				trustAllHttpsCertificates();//信任所有证书
				// 打开和URL之间的连接
				HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
				// 设置通用的请求属性
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("user-agent",
		            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		    	connection.setRequestProperty("Pragma", "no-cache");
		    	
				if(setPropertyMap!=null && setPropertyMap.size()>0){
					for (String key:setPropertyMap.keySet()) {
						connection.setRequestProperty(key, setPropertyMap.get(key));
					}
				}
				connection.setRequestMethod(method);
				if(dataMap!=null && dataMap.size()>0){
					connection.setDoInput(true);// 使用 URL 连接进行输入  
					connection.setDoOutput(true);// 使用 URL 连接进行输出     
		            connection.setUseCaches(false);// 忽略缓存 
					StringBuffer dataBuffer = new StringBuffer();
						for(String key : dataMap.keySet()){
							dataBuffer.append(key+"="+dataMap.get(key)+"&");
				    	}
					OutputStream outputStream = connection.getOutputStream();  
		            outputStream.write(dataBuffer.toString().substring(0, dataBuffer.toString().length()-1).getBytes("UTF-8"));
		            outputStream.flush();
				}
				connection.connect();
				// 获取所有响应头字段
				Map<String, List<String>> map = connection.getHeaderFields();
				
				BufferedReader in = new BufferedReader(
	                    new InputStreamReader(connection.getInputStream(),"UTF-8"));
	            String line;
	            String result="";
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
				
				/*Html pageHtml = AuthUtil.getPageHtml(new BufferedReader(new InputStreamReader(
		                connection.getInputStream(),"UTF-8")));
				resultMap.put("pageHtml", pageHtml);*/
				//resultMap.put("Cookie", getCookies(map));
				resultMap.put("Location", connection.getHeaderField("Location"));
				resultMap.put("map", map);
				resultMap.put("result", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	private static void trustAllHttpsCertificates() throws Exception {  
        TrustManager[] trustAllCerts = new TrustManager[1];  
        TrustManager tm = new miTM();  
        trustAllCerts[0] = tm;  
        SSLContext sc = SSLContext.getInstance("SSL");  
        sc.init(null, trustAllCerts, null);  
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());  
    }  
  
    static class miTM implements TrustManager, X509TrustManager {  
        public X509Certificate[] getAcceptedIssuers() {  
            return null;  
        }  
  
        public boolean isServerTrusted(  
                java.security.cert.X509Certificate[] certs) {  
            return true;  
        }  
  
        public boolean isClientTrusted(  
                java.security.cert.X509Certificate[] certs) {  
            return true;  
        }  
  
        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {  
            return;  
        }  
  
        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {  
            return;  
        }  
    }
}
