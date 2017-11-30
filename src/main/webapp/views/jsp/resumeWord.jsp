<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:w="urn:schemas-microsoft-com:office:word" xmlns="http://www.w3.org/TR/REC-html40">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="ProgId" content="Word.Document">
<meta name="Generator" content="Microsoft Word 14">
<meta name="Originator" content="Microsoft Word 14">
<title></title>
<link type="text/css" href="index.css"/>
<style type="text/css">
				@font-face {
			font-family: "Times New Roman";
		}
		
		@font-face {
			font-family: "宋体";
		}
		
		@font-face {
			font-family: "Wingdings";
		}
		
		@font-face {
			font-family: "Calibri";
		}
		
		@font-face {
			font-family: "微软雅黑";
		}
		
		p.MsoNormal {
			mso-style-name: 正文;
			mso-style-noshow: yes;
			mso-style-parent: "";
			margin-top: 0.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 0.0000pt;
			margin-left: 0.0000pt;
			mso-pagination: none;
			text-align: justify;
			text-justify: inter-ideograph;
			font-family: Calibri;
			mso-fareast-font-family: 宋体;
			mso-bidi-font-family: 'Times New Roman';
			font-size: 10.5000pt;
			mso-font-kerning: 1.0000pt;
		}
		
		h1 {
			mso-style-name: "标题 1";
			mso-style-next: 正文;
			margin-top: 5.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 5.0000pt;
			margin-left: 0.0000pt;
			mso-margin-top-alt: auto;
			mso-margin-bottom-alt: auto;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-weight: bold;
			font-size: 24.0000pt;
			mso-font-kerning: 22.0000pt;
		}
		
		h2 {
			mso-style-name: "标题 2";
			mso-style-next: 正文;
			margin-top: 5.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 5.0000pt;
			margin-left: 0.0000pt;
			mso-margin-top-alt: auto;
			mso-margin-bottom-alt: auto;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-weight: bold;
			font-size: 18.0000pt;
		}
		
		h3 {
			mso-style-name: "标题 3";
			mso-style-next: 正文;
			margin-top: 5.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 5.0000pt;
			margin-left: 0.0000pt;
			mso-margin-top-alt: auto;
			mso-margin-bottom-alt: auto;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-weight: bold;
			font-size: 13.5000pt;
		}
		
		h4 {
			mso-style-name: "标题 4";
			mso-style-next: 正文;
			margin-top: 5.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 5.0000pt;
			margin-left: 0.0000pt;
			mso-margin-top-alt: auto;
			mso-margin-bottom-alt: auto;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-weight: bold;
			font-size: 12.0000pt;
		}
		
		h5 {
			mso-style-name: "标题 5";
			mso-style-next: 正文;
			margin-top: 5.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 5.0000pt;
			margin-left: 0.0000pt;
			mso-margin-top-alt: auto;
			mso-margin-bottom-alt: auto;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-weight: bold;
			font-size: 10.0000pt;
		}
		
		h6 {
			mso-style-name: "标题 6";
			mso-style-next: 正文;
			margin-top: 5.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 5.0000pt;
			margin-left: 0.0000pt;
			mso-margin-top-alt: auto;
			mso-margin-bottom-alt: auto;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-weight: bold;
			font-size: 7.5000pt;
		}
		
		span.10 {
			font-family: 'Times New Roman';
		}
		
		span.15 {
			font-family: 'Times New Roman';
			color: rgb(128, 0, 128);
			text-decoration: underline;
			text-underline: single;
		}
		
		span.16 {
			font-family: 'Times New Roman';
			color: rgb(0, 0, 255);
			text-decoration: underline;
			text-underline: single;
		}
		
		span.17 {
			font-family: 'Times New Roman';
			font-size: 9.0000pt;
		}
		
		span.18 {
			font-family: 'Times New Roman';
		}
		
		span.19 {
			font-family: 'Times New Roman';
			font-size: 9.0000pt;
		}
		
		span.20 {
			font-family: 'Times New Roman';
			font-size: 9.0000pt;
		}
		
		p.MsoFooter {
			mso-style-name: 页脚;
			mso-style-noshow: yes;
			margin-top: 0.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 0.0000pt;
			margin-left: 0.0000pt;
			layout-grid-mode: char;
			mso-pagination: none;
			text-align: left;
			font-family: Calibri;
			mso-fareast-font-family: 宋体;
			mso-bidi-font-family: 'Times New Roman';
			font-size: 9.0000pt;
			mso-font-kerning: 1.0000pt;
		}
		
		p.MsoHeader {
			mso-style-name: 页眉;
			mso-style-noshow: yes;
			margin-top: 0.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 0.0000pt;
			margin-left: 0.0000pt;
			border-bottom: 1.0000pt solid windowtext;
			mso-border-bottom-alt: 0.7500pt solid windowtext;
			padding: 0pt 0pt 1pt 0pt;
			layout-grid-mode: char;
			mso-pagination: none;
			text-align: center;
			font-family: Calibri;
			mso-fareast-font-family: 宋体;
			mso-bidi-font-family: 'Times New Roman';
			font-size: 9.0000pt;
			mso-font-kerning: 1.0000pt;
		}
		
		p.MsoAcetate {
			mso-style-name: 批注框文本;
			mso-style-noshow: yes;
			margin-top: 0.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 0.0000pt;
			margin-left: 0.0000pt;
			mso-pagination: none;
			text-align: justify;
			text-justify: inter-ideograph;
			font-family: Calibri;
			mso-fareast-font-family: 宋体;
			mso-bidi-font-family: 'Times New Roman';
			font-size: 9.0000pt;
			mso-font-kerning: 1.0000pt;
		}
		
		p.pre {
			mso-style-name: "HTML 预设格式";
			mso-style-noshow: yes;
			margin-top: 0.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 0.0000pt;
			margin-left: 0.0000pt;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-size: 12.0000pt;
		}
		
		p.p {
			mso-style-name: "普通\(网站\)";
			mso-style-noshow: yes;
			margin-top: 5.0000pt;
			margin-right: 0.0000pt;
			margin-bottom: 5.0000pt;
			margin-left: 0.0000pt;
			mso-margin-top-alt: auto;
			mso-margin-bottom-alt: auto;
			mso-pagination: widow-orphan;
			text-align: left;
			font-family: 宋体;
			font-size: 12.0000pt;
		}
		
		span.msoIns {
			mso-style-type: export-only;
			mso-style-name: "";
			text-decoration: underline;
			text-underline: single;
			color: blue;
		}
		
		span.msoDel {
			mso-style-type: export-only;
			mso-style-name: "";
			text-decoration: line-through;
			color: red;
		}
		
		table.MsoNormalTable {
			mso-style-name: 普通表格;
			mso-style-parent: "";
			mso-style-noshow: yes;
			mso-tstyle-rowband-size: 0;
			mso-tstyle-colband-size: 0;
			mso-padding-alt: 0.0000pt 5.4000pt 0.0000pt 5.4000pt;
			mso-para-margin: 0pt;
			mso-para-margin-bottom: .0001pt;
			mso-pagination: widow-orphan;
			font-family: Calibri;
			mso-bidi-font-family: 'Times New Roman';
			font-size: 10.5000pt;
			mso-font-kerning: 1.0000pt;
			mso-ansi-language: #0400;
			mso-fareast-language: #0400;
			mso-bidi-language: #0400;
		}
		
		table.MsoTableGrid {
			mso-style-name: 网格型;
			mso-tstyle-rowband-size: 0;
			mso-tstyle-colband-size: 0;
			mso-padding-alt: 0.0000pt 5.4000pt 0.0000pt 5.4000pt;
			mso-border-top-alt: 0.5000pt solid rgb(0, 0, 0);
			mso-border-left-alt: 0.5000pt solid rgb(0, 0, 0);
			mso-border-bottom-alt: 0.5000pt solid rgb(0, 0, 0);
			mso-border-right-alt: 0.5000pt solid rgb(0, 0, 0);
			mso-border-insideh: 0.5000pt solid rgb(0, 0, 0);
			mso-border-insidev: 0.5000pt solid rgb(0, 0, 0);
			mso-para-margin: 0pt;
			mso-para-margin-bottom: .0001pt;
			mso-pagination: widow-orphan;
			font-family: Calibri;
			mso-bidi-font-family: 'Times New Roman';
			font-size: 10.5000pt;
			mso-font-kerning: 1.0000pt;
			mso-ansi-language: #0400;
			mso-fareast-language: #0400;
			mso-bidi-language: #0400;
		}
		
		@page {
			mso-page-border-surround-header: no;
			mso-page-border-surround-footer: no;
		}
		
		@page Section0 {
			margin-top: 36.0000pt;
			margin-bottom: 36.0000pt;
			margin-left: 36.0000pt;
			margin-right: 36.0000pt;
			size: 595.3000pt 841.9000pt;
			layout-grid: 15.6000pt;
		}
		
		div.Section0 {
			page: Section0;
		}
</style>
</head>
<body style="tab-interval:21pt;text-justify-trim:punctuation;">
	<!--StartFragment-->
	<div class="Section0" style="layout-grid:15.6000pt;">
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr>
					<td width="700" align="right">
						<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-right: 15px;">云简历</b>
					</td>
				</tr>
			</tbody>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" >
			<tbody>
				<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
					<td width="700">
						<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">基本信息</b>
					</td>
				</tr>
			</tbody>
		</table>
		<br style="height:16px">
		<table class="MsoNormalTable" width="700" cellpadding="0" cellspacing="0" border="0" style="border: 1.0000pt solid rgb(0,0,0);border-right:0;border-top:0;">
			<tbody>
				<tr height="64" style="line-height: 60px;">
					<td width="170"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:127.8500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
							<b><span style="mso-spacerun:'yes';font-family:微软雅黑;font-weight:bold;font-size:22.0000pt;mso-font-kerning:1.0000pt;">${resume.name}</span></b>
					</td>
					<td width="548" align="right"  colspan="3" style="line-height: 40px;border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:411.1500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
							<span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
							简历更新时间：${resume.lastTime}
							</span>
					</td>
				</tr>
				<tr style="height:21.2500pt;">
					<td width="170"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:127.8500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						性别：<c:choose>  
						    <c:when test="${resume.sex=='1'}">男</c:when>  
						    <c:otherwise>女</c:otherwise>  
						</c:choose>
						</span><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="185"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:139.4000pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						年龄：
						</span><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						${resume.age}&nbsp;岁&nbsp;
						<c:if test="${resume.birthYear!=null && !resume.birthYear.equals('')}">
						（${resume.birthYear}）
						</c:if>
						</span><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="179"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:134.7500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						经验：${resume.jobYear}年工作经验</span>
						<span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="182"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:137.0000pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						学历：</span><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						<c:choose>  
							    <c:when test="${resume.education=='1'}">初中</c:when>
							    <c:when test="${resume.education=='2'}">中技</c:when>  
							    <c:when test="${resume.education=='3'}">高中</c:when>  
							    <c:when test="${resume.education=='4'}">中专</c:when>  
							    <c:when test="${resume.education=='5'}">大专</c:when>
							    <c:when test="${resume.education=='6'}">本科</c:when>
							    <c:when test="${resume.education=='7'}">硕士</c:when>
							    <c:when test="${resume.education=='8'}">MBA</c:when>
							    <c:when test="${resume.education=='9'}">EMBA</c:when>
							    <c:when test="${resume.education=='10'}">博士</c:when>
							    <c:otherwise>其他</c:otherwise>  
							</c:choose></span>
							<span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
				</tr>
				<tr style="height:21.2500pt;">
					<td width="170"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:127.8500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						手机号：${resume.telephone}</span><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="185"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:139.4000pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						<c:if test="${!resume.email.contains('@yifengjianli.com')}">
							邮箱：
						</c:if>
						</span><span style="mso-spacerun:'yes';font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						<c:if test="${!resume.email.contains('@yifengjianli.com')}">
							${resume.email}
						</c:if>
						</span><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="179"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:134.7500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p>&nbsp;</o:p></span></p>
					</td>
					<td width="182"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:137.0000pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p>&nbsp;</o:p></span></p>
					</td>
				</tr>
				<!-- <tr style="height:21.2500pt;">
					<td width="170"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:127.8500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p>&nbsp;</o:p></span></p>
					</td>
					<td width="185"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:139.4000pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p>&nbsp;</o:p></span></p>
					</td>
					<td width="179"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:134.7500pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p>&nbsp;</o:p></span></p>
					</td>
					<td width="182"  style="border: 1.0000pt solid rgb(0,0,0);border-left:0;border-bottom:0;width:137.0000pt;padding:0.0000pt 7.5000pt 0.0000pt 7.5000pt ;">
						<p class="MsoNormal" style="word-break:break-all;"><span style="font-family:微软雅黑;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p>&nbsp;</o:p></span></p>
					</td>
				</tr> -->
			</tbody>
		</table>
		<br style="height:16px">
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
					<td width="700">
						<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">求职意向</b>
					</td>
				</tr>
			</tbody>
		</table>
		<br style="height:16px">
		<table class="MsoTableGrid" width="700" style="line-height: 25px;border-collapse:collapse;mso-table-layout-alt:fixed;mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;">
			<tbody>
				<tr>
					<td width="120"  style="width:90.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal">
						<span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						期望工作地区：
						</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;background:rgb(127,127,127);mso-shading:rgb(127,127,127);"><o:p></o:p></span></p>
					</td>
					<td width="591"  style="width:443.6500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal">
						<span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
							<c:if test="${resume.expectCityName!=null && !resume.expectCityName.equals('')}">
								${resume.expectCityName}
							</c:if>
						</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
				</tr>
				<tr>
					<td width="120"  style="width:90.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">期望月薪：</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;background:rgb(127,127,127);mso-shading:rgb(127,127,127);"><o:p></o:p></span></p>
					</td>
					<td width="591"  style="width:443.6500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						<c:choose>  
							    <c:when test="${resume.expectSalary=='0000001000'}">1000元/月以下</c:when>
							    <c:when test="${resume.expectSalary=='0100002000'}">1000-2000元/月</c:when>  
							    <c:when test="${resume.expectSalary=='0200104000'}">2001-4000元/月</c:when>  
							    <c:when test="${resume.expectSalary=='0400106000'}">4001-6000元/月</c:when>  
							    <c:when test="${resume.expectSalary=='0600108000'}">6001-8000元/月</c:when>
							    <c:when test="${resume.expectSalary=='0800110000'}">8001-10000元/月</c:when>
							    <c:when test="${resume.expectSalary=='1000115000'}">10001-15000</c:when>
							    <c:when test="${resume.expectSalary=='1500125000'}">15000-25000元/月</c:when>
							    <c:when test="${resume.expectSalary=='2500199999'}">25000元/月以上</c:when>
							    <c:otherwise>面议</c:otherwise>  
							</c:choose>
						</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
				</tr>
				<tr>
					<td width="120"  style="width:90.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">目前状况：</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="591"  style="width:443.6500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
						<c:choose>  
						    <c:when test="${resume.jobState=='1'}">我目前处于离职状态，可立即上岗</c:when>
						    <c:when test="${resume.jobState=='2'}">我目前在职，正考虑换个新环境</c:when>  
						    <c:when test="${resume.jobState=='3'}">目前暂无跳槽打算</c:when>  
						    <c:when test="${resume.jobState=='4'}">我对现有工作还算满意,可以考虑新环境。</c:when>  
						    <c:when test="${resume.jobState=='5'}">应届毕业生</c:when>
						</c:choose>
						</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
				</tr>
				<tr>
					<td width="120"  style="width:90.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">期望工作性质：</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="591"  style="width:443.6500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
							<c:choose>  
							    <c:when test="${resume.expectWorkType==-1}">不限</c:when> 
							    <c:when test="${resume.expectWorkType==1}">兼职</c:when>  
							    <c:when test="${resume.expectWorkType==2}">全职</c:when>  
							    <c:when test="${resume.expectWorkType==4}">实习</c:when>  
							    <c:otherwise>不限</c:otherwise>  
							</c:choose>
						</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
				</tr>
				<tr>
					<td width="120"  style="width:90.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">期望从事职业：</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="591"  style="width:443.6500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
							<c:if test="${resume.jotTitleName!=null && !resume.jotTitleName.equals('')}">
								${resume.jotTitleName}
							</c:if>
							<c:if test="${resume.jotTitleName==null || resume.jotTitleName.equals('')}">
								无
							</c:if>
						</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
				</tr>
				<tr>
					<td width="120"  style="width:90.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">期望从事行业：</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
					<td width="591"  style="width:443.6500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
						<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
							<c:if test="${resume.industryName!=null && !resume.industryName.equals('')}">
								${resume.industryName}
							</c:if>
						</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
					</td>
				</tr>
			</tbody>
		</table>
		<br style="height:16px">
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
					<td width="700">
						<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">自我评价</b>
					</td>
				</tr>
			</tbody>
		</table>
		<br style="height:16px">
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr>
					<td width="700">
						<p style="white-space: pre-wrap;mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;width: 680px;padding: 0 10px;line-height: 1.5;">
						<c:if test="${resume.selfEvaluate!=null && !resume.selfEvaluate.equals('')}">
							${resume.selfEvaluate.replace("<br />","")}
						</c:if>
						<c:if test="${resume.selfEvaluate==null || resume.selfEvaluate.equals('')}">
							暂无
						</c:if>
						</p>
					</td>
				</tr>
			</tbody>
		</table>
		<c:if test="${workEx!=null && workEx.size()>0}">
			<br style="height:16px">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
						<td width="700">
							<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">工作经历</b>
						</td>
					</tr>
				</tbody>
			</table>
			<br style="height:16px">
			<c:forEach var="item" items="${workEx}" varStatus="status">
				<table class="MsoTableGrid" width="700" style="line-height:25px;border-collapse:collapse;mso-table-layout-alt:fixed;mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;">
					<tbody>
						<tr>
							<td width="719"  colspan="2" style="width:539.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><b><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									<c:choose>  
										<c:when test="${item.startTime!=null && item.startTime.length()>=11}">${item.startTime.substring(0,11)}</c:when>
										<c:otherwise>${item.startTime}</c:otherwise>  
									</c:choose>
								&nbsp;-&nbsp;<font face="宋体">
									<c:choose>  
										<c:when test="${item.endTime!=null && item.endTime.length()>=11}">${item.endTime.substring(0,11)}</c:when>
										<c:when test="${item.endTime!=null && item.endTime.length()>=6}">${item.endTime}</c:when>
										<c:otherwise>至今</c:otherwise>  
									</c:choose>
								</font>
								<font face="Calibri">&nbsp;&nbsp;</font>
								<font face="宋体">${item.compName}</font>
								<font face="Calibri">&nbsp;&nbsp;</font>
								<font face="宋体"></font>
								</span></b><b><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span></b><b><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></b></p>
							</td>
						</tr>
						<tr>
							<td width="719"  colspan="2" style="width:539.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><b>
								<span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;">薪资&nbsp;
								<font face="Calibri">|&nbsp;
									<c:choose>  
										<c:when test="${item.salary=='0000001000'}">1000元/月以下</c:when>
										<c:when test="${item.salary=='0100002000'}">1000-2000元/月</c:when>  
										<c:when test="${item.salary=='0200104000'}">2001-4000元/月</c:when>  
										<c:when test="${item.salary=='0400106000'}">4001-6000元/月</c:when>  
										<c:when test="${item.salary=='0600108000'}">6001-8000元/月</c:when>
										<c:when test="${item.salary=='0800110000'}">8001-10000元/月</c:when>
										<c:when test="${item.salary=='1000115000'}">10001-15000</c:when>
										<c:when test="${item.salary=='1500125000'}">15000-25000元/月</c:when>
										<c:when test="${item.salary=='2500199999'}">25000元/月以上</c:when>
										<c:otherwise>保密</c:otherwise>  
									</c:choose>
								</font>
								</span></b><b><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span></b><b><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></b></p>
							</td>
						</tr>
						<tr>
							<td width="719"  colspan="2" style="width:539.4500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								<c:if test="${item.industryName!=null && !item.industryName.equals('')}">
									${item.industryName}|
								</c:if>
								${item.jobTitle}
								</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
						<tr>
							<td width="79"  style="width:59.9500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								工作描述：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
							<td width="639"  style="width:479.5000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.workDesc.replace("<br />","")}
								</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
		
		<!-- ===============================项目经历============================= -->
		<c:if test="${projects!=null && projects.size()>0}">
			<br style="height:16px">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
						<td width="700">
							<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">项目经历</b>
						</td>
					</tr>
				</tbody>
			</table>
			<br style="height:16px">
			<c:forEach var="item" items="${projects}" varStatus="status">
				<table class="MsoTableGrid" width="700" style="border-collapse:collapse;line-height:25px;mso-table-layout-alt:fixed;mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;">
					<tbody>
						<tr>
							<td width="712"  colspan="2" style="width:534.1000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><b><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								<c:choose>  
									<c:when test="${item.startTime!=null && item.startTime.length()>=11}">${item.startTime.substring(0,11)}</c:when>
									<c:otherwise>${item.startTime}</c:otherwise>  
								</c:choose>
								&nbsp;-&nbsp;
								<font face="宋体">
								<c:choose>  
									<c:when test="${item.endTime!=null && item.endTime.length()>=11}">${item.endTime.substring(0,11)}</c:when>
									<c:when test="${item.endTime!=null && item.endTime.length()>=6}">${item.endTime}</c:when>
									<c:otherwise>至今</c:otherwise>  
								</c:choose>
								&nbsp;
								${item.projectName}
								</font></span></b><b><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span></b><b><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								</span></b><b><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;background:rgb(127,127,127);mso-shading:rgb(127,127,127);"><o:p></o:p></span></b></p>
							</td>
						</tr>
						<tr>
							<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								项目描述：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
							<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.projectDesc.replace("<br />","")}
								</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
						<c:if test="${item.responsibilityDesc!=null && !item.responsibilityDesc.equals('')}">
							<tr>
								<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									责任描述：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
								<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									${item.responsibilityDesc.replace("<br />","")}
									</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
							</tr>
						</c:if>
						<c:if test="${item.devTools!=null && !item.devTools.equals('')}">
							<tr>
								<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									开发工具：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
								<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									${item.devTools}
									</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
							</tr>
						</c:if>
						<c:if test="${item.hardware!=null && !item.hardware.equals('')}">
							<tr>
								<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									硬件环境：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
								<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									${item.devTools.replace("<br />","")}
									</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
							</tr>
						</c:if>
						<c:if test="${item.software!=null && !item.software.equals('')}">
							<tr>
								<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									软件环境：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
								<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
									<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
									${item.devTools.replace("<br />","")}
									</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
		
		<!--============================ 教育经历========================= -->
		<c:if test="${educations!=null && educations.size()>0}">
			<br style="height:16px">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
						<td width="700">
							<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">教育经历</b>
						</td>
					</tr>
				</tbody>
			</table>
			
			<br style="height:16px">
			<table class="MsoTableGrid" width="700" style="border-collapse:collapse;line-height:25px;mso-table-layout-alt:fixed;mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;">
				<tbody>
					<c:forEach var="item" items="${educations}" varStatus="status">
						<tr>
							<td width="150"  style="padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								<c:choose>  
									<c:when test="${item.startTime!=null && item.startTime.length()>=11}">${item.startTime.substring(0,11)}-</c:when>
									<c:otherwise>${item.startTime}-</c:otherwise>  
								</c:choose>
								<c:choose>  
									<c:when test="${item.endTime!=null && item.endTime.length()>=11}">${item.endTime.substring(0,11)}</c:when>
									<c:when test="${item.endTime!=null && item.endTime.length()>=6}">${item.endTime}</c:when>
									<c:otherwise>至今</c:otherwise>  
								</c:choose>
								&nbsp;</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
							<td width="586"  style="padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.schoolName} &nbsp;&nbsp;${item.specialty} &nbsp;&nbsp;
								<c:choose>  
									<c:when test="${item.education=='1'}">初中</c:when>
									<c:when test="${item.education=='2'}">中技</c:when>  
									<c:when test="${item.education=='3'}">高中</c:when>  
									<c:when test="${item.education=='4'}">中专</c:when>  
									<c:when test="${item.education=='5'}">大专</c:when>
									<c:when test="${item.education=='6'}"> 本科</c:when>
									<c:when test="${item.education=='7'}">硕士</c:when>
									<c:when test="${item.education=='8'}">MBA</c:when>
									<c:when test="${item.education=='9'}">EMBA</c:when>
									<c:when test="${item.education=='10'}"> 博士</c:when>
									<c:otherwise>其他</c:otherwise>  
								</c:choose>
								</span></p>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		
		<!--============================ 培训经历========================= -->
		<c:if test="${trainings!=null && trainings.size()>0}">
			<br style="height:16px">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
						<td width="700">
							<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">培训经历</b>
						</td>
					</tr>
				</tbody>
			</table>
			<br style="height:16px">
			<c:forEach var="item" items="${trainings}" varStatus="status">
				<table class="MsoTableGrid" width="700" style="border-collapse:collapse;line-height:25px;mso-table-layout-alt:fixed;mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;">
					<tbody>
						<tr>
							<td width="712"  colspan="2" style="width:534.1000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><b>
								<font face="Calibri">${item.trainName}</font></b><b><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:9.0000pt;mso-font-kerning:1.0000pt;background:rgb(127,127,127);mso-shading:rgb(127,127,127);"><o:p></o:p></span></b></p>
							</td>
						</tr>
						<tr>
							<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								培训机构：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
							<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.machinery}
								</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
						<tr>
							<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								培训地点：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
							<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.address}
								</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
						<tr>
							<td width="79"  style="width:59.3500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:none;;mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								所获证书：</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
							<td width="633"  style="width:474.7500pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.certificateName.replace("<br />","")}
								</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;">&nbsp;</span><span style="font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
		
		<!-- =======================语言能力========================== -->
		<c:if test="${languages!=null && languages.size()>0}">
			<br style="height:16px">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
						<td width="700">
							<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">语言能力</b>
						</td>
					</tr>
				</tbody>
			</table>
			<br style="height:16px">
			<table class="MsoTableGrid" width="700" style="border-collapse:collapse;line-height:25px;mso-table-layout-alt:fixed;mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;">
				<tbody>
					<c:forEach var="item" items="${languages}" varStatus="status">
						<tr>
							<td width="113"  style="width:84.8000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.languageName}
								</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;background:rgb(127,127,127);mso-shading:rgb(127,127,127);"><o:p></o:p></span></p>
							</td>
							<td width="146"  style="width:109.5000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								读写能力：${item.readWriteSkill}
								</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
							<td width="444"  style="width:333.1000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								听说能力:${item.hearSpeakSkill}
								</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		
		<!-- =======================掌握技能========================== -->
		<c:if test="${profeSkills!=null && profeSkills.size()>1}">
			<br style="height:16px">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr height="35" style="background:rgb(229,229,229);mso-shading:rgb(229,229,229);">
						<td width="700">
							<b style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;font-size:15.0000pt;mso-font-kerning:1.0000pt;padding-left: 15px;">掌握技能</b>
						</td>
					</tr>
				</tbody>
			</table>
			<br style="height:16px">
			<table class="MsoTableGrid" width="700" style="border-collapse:collapse;line-height:25px;mso-table-layout-alt:fixed;mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;">
				<tbody>
					<c:forEach var="item" items="${profeSkills}" varStatus="status">
						<tr>
							<td width="113"  style="width:84.8000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:1.0000pt solid rgb(0,0,0);mso-border-left-alt:0.5000pt solid rgb(0,0,0);border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								熟练程度：${item.masterDegree}
								</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;background:rgb(127,127,127);mso-shading:rgb(127,127,127);"><o:p></o:p></span></p>
							</td>
							<%-- <td width="146"  style="width:109.5000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								使用时间（月）:${item.usedMonths}
								</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td> --%>
							<td width="444"  style="width:333.1000pt;padding:0.0000pt 5.4000pt 0.0000pt 5.4000pt ;border-left:none;;mso-border-left-alt:none;;border-right:1.0000pt solid rgb(0,0,0);mso-border-right-alt:0.5000pt solid rgb(0,0,0);border-top:1.0000pt solid rgb(0,0,0);mso-border-top-alt:0.5000pt solid rgb(0,0,0);border-bottom:1.0000pt solid rgb(0,0,0);mso-border-bottom-alt:0.5000pt solid rgb(0,0,0);">
								<p class="MsoNormal"><span style="mso-spacerun:'yes';font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;">
								${item.skillName}
								</span><span style="font-family:宋体;font-size:9.0000pt;mso-font-kerning:1.0000pt;"><o:p></o:p></span></p>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<!--EndFragment-->
</body>
</html>