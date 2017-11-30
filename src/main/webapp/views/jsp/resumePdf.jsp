<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>简历云-简历详情</title>
	<style type="text/css">
    	body,pre{font-family:"KaiTi_GB2312"}
		body{font-size:14px}
		table{border-collapse:collapse}
		pre{white-space:pre-wrap;line-height: 30px;font-size: 12px;}
		.content2 .content2-tr3,.mr-span-wrap span,.mr-wrap *{margin-right:12px}
		.content1{height:60px;font-size:16px;background-color:#fff}
		.content1 .time{border-left:1px solid #ddd;padding-left:10px}
		.content2{color:#333;height:100px;font-size:14px}
		.content2 .content2-td td{height: 34px;}
		.content2 .content2-ico{position:relative;top:2px}
		.content3-wrap{padding:25px 0px 10px;background:#fff;color:#333}
		.content3-wrap .mail-title{position:relative;border-bottom: 1px solid #e5e6e6;}
		.content3-wrap .mail-title span{color: #fff;background-color: #13c5a5;width: 75px;text-align: center;line-height: 32px;margin-left: 30px;display: inline-block;}
		.content3-wrap .main-wrap{padding:0 0 20px 20px}
		.content3-wrap .main-wrap .main{margin-top:20px}
		.content3-wrap .main-wrap .main td{height:30px}
		.content3-wrap .main-wrap .main.min-main td{height:20px}
		.list-wrap p{color:#333;float:left;border:1px solid #e5e5e5;border-radius:3px;padding:5px 10px;margin:10px 20px 10px 0;list-style: none;}
		.list-wrap p.clear{float:none;clear:both;padding:0;border:0}
    </style>
</head>

<body>
	<table border="0" cellpadding="0" cellspacing="0" style="margin: 0 auto;border: 1px solid #ddd;" width="100%">
		<tr>
			<td style="border-bottom: 3px solid #13c5a5;">
				<table class="content1" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="padding: 0 10px 0 25px;">
							<img src="/img/detail/animate-sz.png"/>
						</td>
						<td>
							<span class="time">更新时间：${resume.lastTime}</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="padding: 25px 0;">
				<table class="content2" border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td>
							<c:choose>  
							    <c:when test="${resume.sex=='1'}">
									<img src="/img/detail/avatar_b.png" style="margin-left: 25px;"/>
							    </c:when>  
							    <c:otherwise>
							    	<img src="/img/detail/avatar_g.png" style="margin-left: 25px;"/>
							    </c:otherwise>  
							</c:choose>
						</td>
						<td>
							<table class="content2-td" border="0" cellspacing="0" cellpadding="0">
								<tr class="content2-tr1" style="border-collapse: collapse;">
									<td>
										<c:if test="${resume.jotTitleName!=null && !resume.jotTitleName.equals('')}">
											${resume.jotTitleName}
										</c:if>
										<c:if test="${resume.jotTitleName==null || resume.jotTitleName.equals('')}">
											无
										</c:if>
									</td>
								</tr>
								<tr class="content2-tr2">
									<td>
										<img class="content2-ico" src="/img/detail/dreducation_sz.png"/>
										<span>${resume.schoolName}</span>
										<span style="color: #D9D9D9;"> | </span>
										<span>${resume.specialty}</span>
										<span style="color: #D9D9D9;"> | </span>
										<span>
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
											</c:choose>
										</span>
										<span style="margin-left: 12px;">3年工作经验</span>
									</td>
								</tr>
								<tr class="content2-tr3">
									<td class="mr-span-wrap">
										<img class="content2-ico" src="/img/detail/druser_sz.png"/>
										<span>${resume.name}</span>
										<span>
											<c:choose>  
											    <c:when test="${resume.sex=='1'}">男</c:when>  
											    <c:otherwise>女</c:otherwise>  
											</c:choose>
										</span>
										<span>
											<c:choose>  
											    <c:when test="${resume.maritalStatus=='1'}">未婚</c:when>  
											    <c:when test="${resume.maritalStatus=='2'}">已婚</c:when> 
											    <c:when test="${resume.maritalStatus=='3'}">离异</c:when> 
											    <c:otherwise>未填写</c:otherwise>  
											</c:choose>
										</span>
										<span>${resume.age}岁</span>
										<img class="content2-ico" src="/img/detail/drtel_sz.png"/>
										<span>${resume.telephone}</span>
										<img class="content2-ico" src="/img/detail/dremail_sz.png"/>
										<span>${resume.email}</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="content3-wrap">
				<table class="content3" border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td class="mail-title"><span>求职意向</span></td>
					</tr>
					<tr>
						<td class="main-wrap">
							<table class="main" border="0" cellspacing="0" cellpadding="0" width="100%">
								<tr>
									<td>期待工作性质：
										<c:choose>  
										    <c:when test="${resume.expectWorkType==-1}">不限</c:when> 
										    <c:when test="${resume.expectWorkType==1}">兼职</c:when>  
										    <c:when test="${resume.expectWorkType==2}">全职</c:when>  
										    <c:when test="${resume.expectWorkType==4}">实习</c:when>  
										    <c:otherwise>不限</c:otherwise>  
										</c:choose>
									</td>
									<td>期待城市：
										<c:if test="${resume.expectCityName!=null && !resume.expectCityName.equals('')}">
											${resume.expectCityName}
										</c:if>
									</td>
								</tr>
								<tr>
									<td>期待月薪：
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
									</td>
									<td>目前状况：
										<c:choose>  
										    <c:when test="${resume.jobState=='1'}">我目前处于离职状态，可立即上岗</c:when>
										    <c:when test="${resume.jobState=='2'}">我目前在职，正考虑换个新环境</c:when>  
										    <c:when test="${resume.jobState=='3'}">目前暂无跳槽打算</c:when>  
										    <c:when test="${resume.jobState=='4'}">我对现有工作还算满意,可以考虑新环境。</c:when>  
										    <c:when test="${resume.jobState=='5'}">应届毕业生</c:when>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td colspan="2">期待从事职业：
										<c:if test="${resume.jotTitleName!=null && !resume.jotTitleName.equals('')}">
											${resume.jotTitleName}
										</c:if>
										<c:if test="${resume.jotTitleName==null || resume.jotTitleName.equals('')}">
											无
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="mail-title"><span>自我评价</span></td>
					</tr>
					<tr>
						<td class="main-wrap">
							<table class="main" border="0" cellspacing="0" cellpadding="0" width="100%">
								<tr>
									<td>
										<pre>
											<c:if test="${resume.selfEvaluate!=null && !resume.selfEvaluate.equals('')}">
												${resume.selfEvaluate.replace("<br />","")}
											</c:if>
											<c:if test="${resume.selfEvaluate==null || resume.selfEvaluate.equals('')}">
												暂无
											</c:if>
										</pre>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${workEx!=null && workEx.size()>0}">
						<tr>
							<td class="mail-title"><span>工作经历</span></td>
						</tr>
						<tr>
							<td class="main-wrap">
								<c:forEach var="item" items="${workEx}" varStatus="status">
									<table class="main" border="0" cellspacing="0" cellpadding="0" width="100%">
										<tr>
											<td>
												<b style="margin-right: 12px;">${item.compName}</b>
												<span>
													<c:choose>  
														<c:when test="${item.startTime!=null && item.startTime.length()>=11}">${item.startTime.substring(0,11)}</c:when>
														<c:otherwise>${item.startTime}</c:otherwise>  
													</c:choose>
												</span>
												<span>—</span>
												<span>
													<c:choose>  
														<c:when test="${item.endTime!=null && item.endTime.length()>=11}">${item.endTime.substring(0,11)}</c:when>
														<c:when test="${item.endTime!=null && item.endTime.length()>=6}">${item.endTime}</c:when>
														<c:otherwise>至今</c:otherwise>  
													</c:choose>|
												</span>
											</td>
										</tr>
										<tr>
											<td>
												<span>
													<c:if test="${item.industryName!=null && !item.industryName.equals('')}">
														${item.industryName}|
													</c:if>
												</span>
												<span>${item.jobTitle}| </span>
												<span>
													<c:choose>  
														<c:when test="${item.compProperty=='1'}">国企</c:when>
														<c:when test="${item.compProperty=='2'}">外商独资</c:when>
														<c:when test="${item.compProperty=='3'}">代表处</c:when>
														<c:when test="${item.compProperty=='4'}">合资</c:when>
														<c:when test="${item.compProperty=='5'}">民营</c:when>
														<c:when test="${item.compProperty=='6'}">国家机关</c:when>
														<c:when test="${item.compProperty=='7'}">其它</c:when>
														<c:when test="${item.compProperty=='8'}">股份制企业</c:when>
														<c:when test="${item.compProperty=='9'}">上市公司</c:when>
														<c:when test="${item.compProperty=='10'}">事业单位</c:when>
														<c:otherwise>其它</c:otherwise>  
													</c:choose>
												</span>
												<span>
													<c:choose>  
														<c:when test="${item.compSize=='1'}">20人以下</c:when>
														<c:when test="${item.compSize=='2'}">20-99人</c:when>
														<c:when test="${item.compSize=='3'}">100-499人</c:when>
														<c:when test="${item.compSize=='4'}">500-999人</c:when>
														<c:when test="${item.compSize=='5'}">1000-9999人</c:when>
														<c:when test="${item.compSize=='6'}">10000人以上</c:when>
														<c:otherwise>20人以下</c:otherwise>  
													</c:choose>
												</span>
											</td>
										</tr>
										<tr>
											<td>
												<b>工作描述：</b>
											</td>
										</tr>
										<tr>
											<td>
												<pre>${item.workDesc.replace("<br />","")}</pre>
											</td>
										</tr>
									</table>
								</c:forEach>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${projects!=null && projects.size()>0}">
						<tr>
							<td class="mail-title"><span>项目经历</span></td>
						</tr>
						<tr>
							<td class="main-wrap">
								<c:forEach var="item" items="${projects}" varStatus="status">
									<table class="main" border="0" cellspacing="0" cellpadding="0" width="100%">
										<tr>
											<td>
												<b style="margin-right: 12px;">${item.projectName}</b>
												<span>
													<c:choose>  
														<c:when test="${item.startTime!=null && item.startTime.length()>=11}">${item.startTime.substring(0,11)}</c:when>
														<c:otherwise>${item.startTime}</c:otherwise>  
													</c:choose>
												</span>
												<span>—</span>
												<span>
													<c:choose>  
														<c:when test="${item.endTime!=null && item.endTime.length()>=11}">${item.endTime.substring(0,11)}</c:when>
														<c:when test="${item.endTime!=null && item.endTime.length()>=6}">${item.endTime}</c:when>
														<c:otherwise>至今</c:otherwise>  
													</c:choose>
												</span>
											</td>
										</tr>
										<tr>
											<td>
												<b>责任描述：</b>
											</td>
										</tr>
										<tr>
											<td>
												<pre>${item.responsibilityDesc.replace("<br />","")}</pre>
											</td>
										</tr>
										<tr>
											<td>
												<b>项目描述：</b>
											</td>
										</tr>
										<tr>
											<td>
												<pre>${item.projectDesc.replace("<br />","")}</pre>
											</td>
										</tr>
									</table>
								</c:forEach>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${educations!=null && educations.size()>0}">
						<tr>
							<td class="mail-title"><span>教育经历</span></td>
						</tr>
						<tr>
							<td class="main-wrap">
								<c:forEach var="item" items="${educations}" varStatus="status">
									<table class="main min-main" border="0" cellspacing="0" cellpadding="0" width="100%">
										<tr>
											<td>
												<span>
													<c:choose>  
														<c:when test="${item.startTime!=null && item.startTime.length()>=11}">${item.startTime.substring(0,11)}-</c:when>
														<c:otherwise>${item.startTime}</c:otherwise>  
													</c:choose>
												</span>
												<span>—</span>
												<span>
													<c:choose>  
														<c:when test="${item.endTime!=null && item.endTime.length()>=11}">${item.endTime.substring(0,11)}</c:when>
														<c:when test="${item.endTime!=null && item.endTime.length()>=6}">${item.endTime}</c:when>
														<c:otherwise>至今</c:otherwise>  
													</c:choose>
												</span>
												<span>${item.schoolName} > </span>
												<span>${item.specialty} > </span>
												<span>
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
												</span>
											</td>
										</tr>
									</table>
								</c:forEach>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${languages!=null && languages.size()>0}">
						<tr>
							<td class="mail-title"><span>语言能力</span></td>
						</tr>
						<tr>
							<td class="main-wrap">
								<c:forEach var="item" items="${languages}" varStatus="status">
									<table class="main min-main" border="0" cellspacing="0" cellpadding="0" width="100%">
										<tr>
											<td class="mr-wrap">
												<span>${item.languageName}</span>
												<span>读写能力 ${item.readWriteSkill}</span>
												<span>听说能力${item.hearSpeakSkill}</span>
											</td>
										</tr>
									</table>
								</c:forEach>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${trainings!=null && trainings.size()>0}">
						<tr>
							<td class="mail-title"><span>培训经历</span></td>
						</tr>
						<tr>
							<td class="main-wrap">
								<c:forEach var="item" items="${trainings}" varStatus="status">
									<table class="main" border="0" cellspacing="0" cellpadding="0" width="100%">
										<tr>
											<td>
												<span>
													<c:choose>  
														<c:when test="${item.startTime!=null && item.startTime.length()>=11}">${item.startTime.substring(0,11)}-</c:when>
														<c:otherwise>${item.startTime}</c:otherwise>  
													</c:choose>
												</span>
												<span>—</span>
												<span>
													<c:choose>  
														<c:when test="${item.endTime!=null && item.endTime.length()>=11}">${item.endTime.substring(0,11)}</c:when>
														<c:when test="${item.endTime!=null && item.endTime.length()>=6}">${item.endTime}</c:when>
														<c:otherwise>至今</c:otherwise>  
													</c:choose>
												</span>
												<span>${item.trainName}</span>
											</td>
										</tr>
										<tr>
											<td class="mr-wrap">
												<span>${item.machinery}</span>
												<span>${item.certificateName.replace("<br />","")}</span>
												<span>${item.address}</span>
											</td>
										</tr>
									</table>
								</c:forEach>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${profeSkills!=null && profeSkills.size()>1}">
						<tr>
							<td class="mail-title"><span>掌握技能</span></td>
						</tr>
						<tr>
							<td class="main-wrap">
								
								<table class="main" border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td class="list-wrap">
											<c:forEach var="item" items="${profeSkills}" varStatus="status">
												<p>
													<span>${item.skillName}</span>
													<span>${item.usedMonths}个月</span>
													<span>${item.masterDegree}</span>
												</p>
											</c:forEach>
											<p class="clear"></p>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:if>
				</table>
			</td>
		</tr>
	</table>
</body>

</html>