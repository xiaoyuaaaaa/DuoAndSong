package com.cloud.common.web;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.util.VeriCodeUtil;

/** 
 * @author tobber
 * @version 2017年11月3日
 */
@Api(value="imgCode",description="获取图片验证码Controller")
@Controller
@RequestMapping(value="imgCode")
public class CheckImgController {
	
	@ApiOperation(value="发送手机短信时获取图片验证码",httpMethod="GET")
	@RequestMapping(value = "getDrawImage",method=RequestMethod.GET)//获取验证码
	@ResponseBody
	public void DrawImage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 String createTypeFlag = request.getParameter("createTypeFlag");//接收客户端传递的createTypeFlag标识
	      //1.在内存中创建一张图片
	      BufferedImage bi = new BufferedImage(VeriCodeUtil.WIDTH, VeriCodeUtil.HEIGHT,BufferedImage.TYPE_INT_RGB);
	      //2.得到图片
	      Graphics g = bi.getGraphics();
	      //3.设置图片的背影色
	      VeriCodeUtil.setBackGround(g);
	      //4.设置图片的边框
	      VeriCodeUtil.setBorder(g);
	      //5.在图片上画干扰线
	      VeriCodeUtil.drawRandomLine(g);
	      //6.写在图片上随机数
	      String random = VeriCodeUtil.drawRandomNum((Graphics2D) g,createTypeFlag);//根据客户端传递的createTypeFlag标识生成验证码图片
	      //7.将随机数存在session中
	      request.getSession().setAttribute("imgCheckcode", random);
	      //8.设置响应头通知浏览器以图片的形式打开
	      response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
	      //9.设置响应头控制浏览器不要缓存
	      response.setDateHeader("expries", -1);
	      response.setHeader("Cache-Control", "no-cache");
	      response.setHeader("Pragma", "no-cache");
	      //10.将图片写给浏览器
	      ImageIO.write(bi, "jpg", response.getOutputStream());
	} 
}
