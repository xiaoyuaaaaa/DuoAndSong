package com.cloud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @author tobber
 * @version 2016年1月6日
 */
public class UrlRewriteFilter implements Filter {
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		String hostName = req.getServerName();
        if (isRewrite(hostName)) {
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpServletRequest httpRequest = (HttpServletRequest) req;
            String queryString = (httpRequest.getQueryString() == null ? "" : "?" + httpRequest.getQueryString());
            response.setStatus(301);
            String requestUrl = httpRequest.getRequestURL().toString();
            requestUrl = requestUrl.replace(hostName, "www.yifengjianli.com");
            response.setHeader("Location", requestUrl + queryString);
            response.setHeader("Connection", "close");
        } else {
            chain.doFilter(req, resp);
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	private boolean isRewrite(String hostName){
		boolean flag = false;
		String[] rewriteUrl = new String[]{"120.24.52.194","www.shilipai.net","shilipai.net","yifengjianli.com"};
		for (int i = 0; i < rewriteUrl.length; i++) {
			if(rewriteUrl[i].startsWith(hostName)){
				flag = true;
			}
		}
		return flag;
	}

}
