package com.cloud.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.util.SysConfig;

/** 
 * @author tobber
 * @version 2017年11月15日
 */

@Api(value="icloudimg",description="文件下载")
@Controller
@RequestMapping(value="icloudimg")
public class ImgController {

    @ApiOperation(value="头像获取",httpMethod="GET")
    @RequestMapping(value = "getHeadImg",method=RequestMethod.GET)
    @ResponseBody
	public void getHeadImg(HttpServletRequest request,HttpServletResponse response) {
    	try {
    		String filePath = SysConfig.getString("UPLOAD_HEADIMG_PATH")+request.getSession().getAttribute("userId");
            //判断文件是否存在如果不存在就返回默认图标
            File file = new File(filePath+".png");
            if(!(file.exists() && file.canRead())){
            	file = new File(filePath+".gif");
            	if(!(file.exists() && file.canRead())){
            		file = new File(filePath+".jpeg");
            		if(!(file.exists() && file.canRead())){
            			file = new File(filePath+".jpg");
            			if(!(file.exists() && file.canRead())){
            				 file = new File(request.getSession().getServletContext().getRealPath("/") + "img/user/avatar-default.png");
                    	}
                	}
            	}
            }
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            inputStream.read(data);
            inputStream.close();
            response.setContentType("image/png");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @ApiOperation(value="获取消息通知图片",httpMethod="GET")
    @RequestMapping(value = "getNoticeImg",method=RequestMethod.GET)
    @ApiImplicitParam(name = "noticeImg", value = "图片名称", required = true, dataType = "String", paramType = "query")
	public void getNoticeImg(String noticeImg,HttpServletRequest request,HttpServletResponse response) {
    	try {
    		String filePath = SysConfig.getString("NOTICE_IMG_URL")+noticeImg;
            //判断文件是否存在如果不存在就返回默认图标
            File file = new File(filePath);
			if(!(file.exists() && file.canRead())){
				 file = new File(request.getSession().getServletContext().getRealPath("/") + "img/user/avatar-default.png");
        	}
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            inputStream.read(data);
            inputStream.close();
            response.setContentType("image/png");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
