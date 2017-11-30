package com.cloud.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 
 * @author tobber
 * @version 2017年11月3日
 */
public class Util {
	
	public static void responesWriter(HttpServletResponse response,String content) throws IOException {
        response.setContentType("application/json;charset=UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();
        out.write(content);
        out.flush();
	}

	/**
     * 删除文件
     * @param path
     */
    public static void deleteFile(String path){
        File file = new File(path);
        if (file.isFile() && file.exists()) {  
            file.delete();  
        }
    }

	/**
	 * 清空session和cokie
	 * @param length
	 * @return
	 */
	public static void wipeSession(HttpServletRequest request,HttpServletResponse response) {     
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie[] cookies = request.getCookies();  
		if(cookies!=null && cookies.length>0){
			for(int i=0;i<cookies.length;i++)    
			{  
				Cookie cookie = new Cookie(cookies[i].getName(), null); 
				cookie.setMaxAge(0);
				cookie.setPath("/");//根据你创建cookie的路径进行填写      
				response.addCookie(cookie);  
			}
		}
	}
	
	/**
	 * 获取客户端的外网IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    ip = ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	    return ip;
	}
}
