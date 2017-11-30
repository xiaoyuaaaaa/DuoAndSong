package com.cloud.fileupload.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.cloud.resume.model.NewResume;
import com.cloud.util.SysConfig;


/** 
 * @author tobber
 * @version 2016年9月18日
 */
public class ExportExcelResume {
	
	/**
	 * 生成Excle文件
	 * @param resumeList
	 * @param fileName 文件名 test.xls
	 */
	public static Boolean creatExcle(List<NewResume> resumeList,String fileName) {
		//第一步创建workbook  
        HSSFWorkbook wb = new HSSFWorkbook();  
        //第二步创建sheet  
        HSSFSheet sheet = wb.createSheet("云简历列表");  
        //第三步创建行row:添加表头0行  
        HSSFRow row = sheet.createRow(0);  
        HSSFCellStyle  style = wb.createCellStyle();      
        style.setWrapText(true);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
        
        HSSFCellStyle  titleStyle = wb.createCellStyle();      
        titleStyle.setWrapText(true);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置前景填充样式
        titleStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);//前景填充色
        
        //第四步创建单元格  
        HSSFCell cell = row.createCell(0);         //第一个单元格  
        cell.setCellValue("姓名");                  //设定值  
        cell.setCellStyle(style);                   //内容居中  
        cell = row.createCell(1);                   //第二个单元格     
        cell.setCellValue("性别");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);                   //第三个单元格    
        cell.setCellValue("年龄");  
        cell.setCellStyle(style);  
        cell = row.createCell(3);                   //第四个单元格    
        cell.setCellValue("手机号码");  
        cell.setCellStyle(style); 
        cell = row.createCell(4);                   //第四个单元格    
        cell.setCellValue("email");  
        cell.setCellStyle(style); 
        cell = row.createCell(5);                   //第四个单元格    
        cell.setCellValue("户口地");  
        cell.setCellStyle(style); 
        cell = row.createCell(6);                   //第四个单元格    
        cell.setCellValue("现居地");  
        cell.setCellStyle(style); 
        cell = row.createCell(7);                   //第四个单元格    
        cell.setCellValue("工作年限");  
        cell.setCellStyle(style); 
        cell = row.createCell(8);                   //第四个单元格    
        cell.setCellValue("求职岗位");  
        cell.setCellStyle(style); 
        cell = row.createCell(9);                   //第四个单元格    
        cell.setCellValue("期望城市");  
        cell.setCellStyle(style); 
        cell = row.createCell(10);                   //第四个单元格    
        cell.setCellValue("学历");  
        cell.setCellStyle(style); 
        cell = row.createCell(11);                   //第四个单元格    
        cell.setCellValue("专业");  
        cell.setCellStyle(style);
        cell = row.createCell(12);                   //第四个单元格    
        cell.setCellValue("毕业院校");  
        cell.setCellStyle(style); 
        
        //第五步插入数据  
        for (int i = 0; i < resumeList.size(); i++) {
        	NewResume resume = resumeList.get(i);
            //创建行  
            row = sheet.createRow(i+1);  
            //创建单元格并且添加数据  
            row.createCell(0).setCellValue(resume.getName()); //姓名
            if(resume.getSex()==1){
            	row.createCell(1).setCellValue("男"); 
            }else{
            	row.createCell(1).setCellValue("女"); 
            }
            row.createCell(2).setCellValue(resume.getAge()); 
            row.createCell(3).setCellValue(resume.getTelephone()); 
            row.createCell(4).setCellValue(resume.getEmail()); 
            if(resume.getHukouCity()!=null){
            	row.createCell(5).setCellValue(resume.getHukouCity()); 
            }else{
            	row.createCell(5).setCellValue("--"); 
            }
            if(resume.getCity()!=null){
            	row.createCell(6).setCellValue(resume.getCity()); 
            }else{
            	row.createCell(6).setCellValue("--"); 
            }
            row.createCell(7).setCellValue(resume.getJobYear());
            row.createCell(8).setCellValue(resume.getJobTitle()); 
            row.createCell(9).setCellValue(resume.getExpectCity());
            	if(resume.getEducation()==1){
            		row.createCell(10).setCellValue("初中");
            	}else if(resume.getEducation()==2){
            		row.createCell(10).setCellValue("中技");
            	}else if(resume.getEducation()==3){
            		row.createCell(10).setCellValue("高中");
            	}else if(resume.getEducation()==4){
            		row.createCell(10).setCellValue("中专");
            	}else if(resume.getEducation()==5){
            		row.createCell(10).setCellValue("大专");
            	}else if(resume.getEducation()==6){
            		row.createCell(10).setCellValue("本科");
            	}else if(resume.getEducation()==7){
            		row.createCell(10).setCellValue("硕士");
            	}else if(resume.getEducation()==8){
            		row.createCell(10).setCellValue("MBA");
            	}else if(resume.getEducation()==9){
            		row.createCell(10).setCellValue("EMBA");
            	}else if(resume.getEducation()==10){
            		row.createCell(10).setCellValue("博士");
            	}else{
            		row.createCell(10).setCellValue("其他");
            	}
            if(resume.getEduList().size()>0){
            	 row.createCell(11).setCellValue(resume.getEduList().get(0).getSpecialty());
            	 row.createCell(12).setCellValue(resume.getEduList().get(0).getSchoolName()); 
            }else{
            	 row.createCell(11).setCellValue("--");
            	 row.createCell(12).setCellValue("--");
            }
            creatExcleReusmeDetail(wb.createSheet((i+1)+"."+resume.getName()),resume,style,titleStyle);
        }  
        //第六步将生成excel文件保存到指定路径下  
        try {  
            FileOutputStream fout = new FileOutputStream(SysConfig.getValue("EXCEL_FILE_PATH")+fileName);  
            wb.write(fout);  
            fout.close();
            return true;
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return false; 
	}
	
	/**
	 * 在单个Sheet设置简历详情
	 * @param resumeList
	 * @param fileName 文件名 test.xls
	 */
	public static void creatExcleReusmeDetail(HSSFSheet detailSheet,NewResume resume,HSSFCellStyle style,HSSFCellStyle titleStyle) {
		//设置样式
		detailSheet.setColumnWidth(0, 4000);
		detailSheet.setColumnWidth(1, 15000);
		detailSheet.setDefaultColumnWidth(20);  
		detailSheet.setDefaultRowHeightInPoints(20);
		
		int rownum = 17;
        //第三步创建行row:添加表头0行  
        HSSFRow row = detailSheet.createRow(0); 
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("个人基本信息");
        cell.setCellStyle(titleStyle);
        CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 1);
        detailSheet.addMergedRegion(cra);
        
        HSSFRow row1 = detailSheet.createRow(1);  
        row1.createCell(0).setCellValue("姓名");
        row1.createCell(1).setCellValue(resume.getName());
        HSSFRow row2 = detailSheet.createRow(2);
        row2.createCell(0).setCellValue("性别");
        if(resume.getSex()==1){
        	 row2.createCell(1).setCellValue("男");
        }else{
        	 row2.createCell(1).setCellValue("女");
        }
        HSSFRow row3 = detailSheet.createRow(3);  
        row3.createCell(0).setCellValue("出生年月");
        if(resume.getBirthYear()!=null){
        	row3.createCell(1).setCellValue(resume.getBirthYear());
        }else{
        	row3.createCell(1).setCellValue("--");
        }
        HSSFRow row4 = detailSheet.createRow(4);  
        row4.createCell(0).setCellValue("现居地");
        if(resume.getCity()!=null){
        	row4.createCell(1).setCellValue(resume.getCity());
        }else{
        	row4.createCell(1).setCellValue("--");
        }
        HSSFRow row5 = detailSheet.createRow(5);  
        row5.createCell(0).setCellValue("工作经验");
        row5.createCell(1).setCellValue(resume.getJobYear()+"年工作经验");
        HSSFRow row6 = detailSheet.createRow(6);  
        row6.createCell(0).setCellValue("联系电话");
        row6.createCell(1).setCellValue(resume.getTelephone());
        HSSFRow row7 = detailSheet.createRow(7);  
        row7.createCell(0).setCellValue("E-mail");
        row7.createCell(1).setCellValue(resume.getEmail());
        
        HSSFRow row8 = detailSheet.createRow(8);  
        HSSFCell row8Cell = row8.createCell(0);
        row8Cell.setCellValue("求职意向");
        row8Cell.setCellStyle(titleStyle);
        CellRangeAddress selfCra=new CellRangeAddress(8, 8, 0, 1);//合并单元格
        detailSheet.addMergedRegion(selfCra);
        
        HSSFRow row9 = detailSheet.createRow(9);  
        row9.createCell(0).setCellValue("期望工作性质");
        if(resume.getExpectWorkType()==1){//-1 不限、2 全职、1 兼职、4 实习
        	row9.createCell(1).setCellValue("兼职");
        }else if(resume.getExpectWorkType()==2){
        	row9.createCell(1).setCellValue("全职");
        }else if(resume.getExpectWorkType()==4){
        	row9.createCell(1).setCellValue("实习");
        }else{
        	row9.createCell(1).setCellValue("不限");
        }
        
        HSSFRow row10 = detailSheet.createRow(10);  
        row10.createCell(0).setCellValue("期望从事职业");
        if(resume.getJobTitle()!=null && resume.getJobTitle().trim().length()>0){
        	row10.createCell(1).setCellValue(resume.getJobTitle());
        }else{
        	row10.createCell(1).setCellValue("--");
        }
        HSSFRow row11 = detailSheet.createRow(11);  
        row11.createCell(0).setCellValue("期望从事行业");
        if(resume.getExpectIndustry()!=null && resume.getExpectIndustry().trim().length()>0){
        	row11.createCell(1).setCellValue(resume.getExpectIndustry());
        }else{
        	row11.createCell(1).setCellValue("--");
        }
        HSSFRow row12 = detailSheet.createRow(12);  
        row12.createCell(0).setCellValue("期望工作地区");
        if(resume.getExpectCity()!=null){
        	row12.createCell(1).setCellValue(resume.getExpectCity());
        }else{
        	row12.createCell(1).setCellValue("--");
        }
        
        HSSFRow row13 = detailSheet.createRow(13);  
        row13.createCell(0).setCellValue("期望薪资");
        if(resume.getExpectSalary()!=null){
        	if(resume.getExpectSalary().equals("0000001000")){
        		row13.createCell(1).setCellValue("1000元/月以下");
        	}else if(resume.getExpectSalary().equals("0100002000")){
        		row13.createCell(1).setCellValue("1000-2000元/月");
        	}else if(resume.getExpectSalary().equals("0200104000")){
        		row13.createCell(1).setCellValue("2001-4000元/月");
        	}else if(resume.getExpectSalary().equals("0400106000")){
        		row13.createCell(1).setCellValue("4001-6000元/月");
        	}else if(resume.getExpectSalary().equals("0600108000")){
        		row13.createCell(1).setCellValue("6001-8000元/月");
        	}else if(resume.getExpectSalary().equals("0800110000")){
        		row13.createCell(1).setCellValue("8001-10000元/月");
        	}else if(resume.getExpectSalary().equals("1000115000")){
        		row13.createCell(1).setCellValue("10001-15000元/月");
        	}else if(resume.getExpectSalary().equals("1500125000")){
        		row13.createCell(1).setCellValue("15000-25000元/月");
        	}else if(resume.getExpectSalary().equals("2500199999")){
        		row13.createCell(1).setCellValue("25000元/月以上");
        	}else{
        		row13.createCell(1).setCellValue("面议");
        	}
        	
        }else{
        	row13.createCell(1).setCellValue("面议");
        }
        
        HSSFRow row14 = detailSheet.createRow(14);  
        row14.createCell(0).setCellValue("目前状况");
        if(resume.getJobState()==1){
        	row14.createCell(1).setCellValue("我目前处于离职状态，可立即上岗");
        }else if(resume.getJobState()==2){
        	row14.createCell(1).setCellValue("我目前在职，正考虑换个新环境（如有合适的工作机会，到岗时间一个月左右）");
        }else if(resume.getJobState()==3){
        	row14.createCell(1).setCellValue("目前暂无跳槽打算");
        }else if(resume.getJobState()==4){
        	row14.createCell(1).setCellValue("我对现有工作还算满意，如有更好的工作机会，我也可以考虑。（到岗时间另议）");
        }else if(resume.getJobState()==5){
        	row14.createCell(1).setCellValue("应届毕业生");
        }
        
        HSSFRow row15 = detailSheet.createRow(15); 
        HSSFCell selfTitle = row15.createCell(0);
        selfTitle.setCellValue("自我评价");
        selfTitle.setCellStyle(style);
        HSSFCell selfcell = row15.createCell(1);
        if(resume.getSelfEvaluate()!=null){
        	selfcell.setCellValue(resume.getSelfEvaluate().replace("<br />", "\r\n"));
        }else{
        	selfcell.setCellValue("--");
        }
        
        selfcell.setCellStyle(style);
        
        HSSFRow row16 = detailSheet.createRow(16); 
        HSSFCell workTitleCell = row16.createCell(0);
        workTitleCell.setCellValue("工作经历");
        workTitleCell.setCellStyle(titleStyle);
        
        CellRangeAddress workCra=new CellRangeAddress(16, 16, 0, 1);//合并单元格
        detailSheet.addMergedRegion(workCra);
        
        if(resume.getWorkList()!=null && resume.getWorkList().size()>0){
        	for (int i = 0; i < resume.getWorkList().size(); i++) {
        		HSSFRow workRow = detailSheet.createRow(rownum); 
        		rownum++;
        		if(resume.getWorkList().get(i).getEndTime()!=null && !resume.getWorkList().get(i).getEndTime().trim().equals("0")){
        			workRow.createCell(1).setCellValue(resume.getWorkList().get(i).getStartTime()+"~"+resume.getWorkList().get(i).getEndTime()+" "+resume.getWorkList().get(i).getCompName());
        		}else{
        			workRow.createCell(1).setCellValue(resume.getWorkList().get(i).getStartTime()+"~至今 "+resume.getWorkList().get(i).getCompName());
        		}
        		HSSFRow workJobRow = detailSheet.createRow(rownum);
        		rownum++;
        		if(resume.getWorkList().get(i).getSalary()!=null && !resume.getWorkList().get(i).getSalary().equals("0000000000")){
        			if(resume.getExpectSalary().equals("0000001000")){
        				workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 1000元/月以下");
                	}else if(resume.getExpectSalary().equals("0100002000")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 1000-2000元/月");
                	}else if(resume.getExpectSalary().equals("0200104000")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 2001-4000元/月");
                	}else if(resume.getExpectSalary().equals("0400106000")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 4001-6000元/月");
                	}else if(resume.getExpectSalary().equals("0600108000")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 6001-8000元/月");
                	}else if(resume.getExpectSalary().equals("0800110000")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 8001-10000元/月");
                	}else if(resume.getExpectSalary().equals("1000115000")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 10001-15000元/月");
                	}else if(resume.getExpectSalary().equals("1500125000")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 15000-25000元/月");
                	}else if(resume.getExpectSalary().equals("2500199999")){
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle()+" | 25000元/月以上");
                	}else{
                		workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle());
                	}
        		}else{
        			workJobRow.createCell(1).setCellValue(resume.getWorkList().get(i).getJobTitle());
        		}
        		HSSFRow workDesRow = detailSheet.createRow(rownum);
        		rownum++;
        		HSSFCell workCell = workDesRow.createCell(1);
        		if(resume.getWorkList().get(i).getWorkDesc()!=null){
        			workCell.setCellValue(resume.getWorkList().get(i).getWorkDesc().replace("<br />", "\r\n"));
        		}else{
        			workCell.setCellValue("--");
        		}
        		workCell.setCellStyle(style);
			}
        }
        HSSFRow projectRow = detailSheet.createRow(rownum); 
        HSSFCell proTitleCell = projectRow.createCell(0);
        proTitleCell.setCellValue("项目经历");
        proTitleCell.setCellStyle(titleStyle);
        
        CellRangeAddress proCra=new CellRangeAddress(rownum, rownum, 0, 1);//合并单元格
        detailSheet.addMergedRegion(proCra);
        rownum++;
        if(resume.getProjectList()!=null && resume.getProjectList().size()>0){
        	for (int i = 0; i < resume.getProjectList().size(); i++) {
        		HSSFRow proNameRow = detailSheet.createRow(rownum); 
        		rownum++;
        		proNameRow.createCell(0).setCellValue("项目名称");
        		if(resume.getProjectList().get(i).getEndTime()!=null && !resume.getProjectList().get(i).getEndTime().trim().equals("0")){
        			proNameRow.createCell(1).setCellValue(resume.getProjectList().get(i).getStartTime()+"~"+resume.getProjectList().get(i).getEndTime()+" "+resume.getProjectList().get(i).getProjectName());
        		}else{
        			proNameRow.createCell(1).setCellValue(resume.getProjectList().get(i).getStartTime()+"~至今 "+resume.getProjectList().get(i).getProjectName());
        		}
        		HSSFRow proDescRow = detailSheet.createRow(rownum);
        		rownum++;
        		HSSFCell proTiCell = proDescRow.createCell(0);
        		proTiCell.setCellValue("项目描述");
        		proTiCell.setCellStyle(style);
        		
        		HSSFCell proCell = proDescRow.createCell(1);
        		if(resume.getProjectList().get(i).getProjectDesc()!=null){
        			proCell.setCellValue(resume.getProjectList().get(i).getProjectDesc().replace("<br />", "\r\n"));
        		}else{
        			proCell.setCellValue("--");
        		}
        		proCell.setCellStyle(style);
        		HSSFRow workDesRow = detailSheet.createRow(rownum);
        		rownum++;
        		HSSFCell resCell0 = workDesRow.createCell(0);
        		resCell0.setCellValue("责任描述");
        		resCell0.setCellStyle(style);
        		HSSFCell resCell = workDesRow.createCell(1);
        		if(resume.getProjectList().get(i).getResponsibilityDesc()!=null){
        			resCell.setCellValue(resume.getProjectList().get(i).getResponsibilityDesc().replace("<br />", "\r\n"));
        		}else{
        			resCell.setCellValue("--");
        		}
        		resCell.setCellStyle(style);
			}
        }
        
        HSSFRow eduRow = detailSheet.createRow(rownum); 
        HSSFCell eduTitleCell = eduRow.createCell(0);
        eduTitleCell.setCellValue("教育经历");
        eduTitleCell.setCellStyle(titleStyle);
        CellRangeAddress eduCra=new CellRangeAddress(rownum, rownum, 0, 1);//合并单元格
        detailSheet.addMergedRegion(eduCra);
        rownum++;
        if(resume.getEduList()!=null && resume.getEduList().size()>0){
        	for (int i = 0; i < resume.getEduList().size(); i++) {
        		HSSFRow proNameRow = detailSheet.createRow(rownum);
        		rownum++;
        		StringBuffer eduBuffer= new StringBuffer();
        		if(resume.getEduList().get(i).getEndTime()!=null && !resume.getEduList().get(i).getEndTime().trim().equals("0")){
        			eduBuffer.append(resume.getEduList().get(i).getStartTime()+"~"+resume.getEduList().get(i).getEndTime()+" "+resume.getEduList().get(i).getSchoolName());
        		}else{
        			eduBuffer.append(resume.getEduList().get(i).getStartTime()+"~至今 "+resume.getEduList().get(i).getSchoolName());
        		}
        		eduBuffer.append(" "+resume.getEduList().get(i).getSpecialty()+" ");
        		if(resume.getEduList().get(i).getEducation()==1){
        			eduBuffer.append("初中");
            	}else if(resume.getEduList().get(i).getEducation()==2){
            		eduBuffer.append("中技");
            	}else if(resume.getEduList().get(i).getEducation()==3){
            		eduBuffer.append("高中");
            	}else if(resume.getEduList().get(i).getEducation()==4){
            		eduBuffer.append("中专");
            	}else if(resume.getEduList().get(i).getEducation()==5){
            		eduBuffer.append("大专");
            	}else if(resume.getEduList().get(i).getEducation()==6){
            		eduBuffer.append("本科");
            	}else if(resume.getEduList().get(i).getEducation()==7){
            		eduBuffer.append("硕士");
            	}else if(resume.getEduList().get(i).getEducation()==8){
            		eduBuffer.append("MBA");
            	}else if(resume.getEduList().get(i).getEducation()==9){
            		eduBuffer.append("EMBA");
            	}else if(resume.getEduList().get(i).getEducation()==10){
            		eduBuffer.append("博士");
            	}else{
            		eduBuffer.append("其他");
            	}
        		proNameRow.createCell(1).setCellValue(eduBuffer.toString());
			}
        }
        
        HSSFRow languageRow = detailSheet.createRow(rownum); 
        HSSFCell lanTitleCell = languageRow.createCell(0);
        lanTitleCell.setCellValue("语言能力");
        lanTitleCell.setCellStyle(titleStyle);
        
        CellRangeAddress lanCra=new CellRangeAddress(rownum, rownum, 0, 1);//合并单元格
        detailSheet.addMergedRegion(lanCra);
        rownum++;
        if(resume.getLanguagesList()!=null && resume.getLanguagesList().size()>0){
        	for (int i = 0; i < resume.getLanguagesList().size(); i++) {
        		HSSFRow lanNameRow = detailSheet.createRow(rownum); 
        		rownum++;
        		lanNameRow.createCell(1).setCellValue(resume.getLanguagesList().get(i).getLanguageName()+": 读写能力"+resume.getLanguagesList().get(i).getReadWriteSkill()+",听说能力"+resume.getLanguagesList().get(i).getHearSpeakSkill());
			}
        }
        
        HSSFRow skillRow = detailSheet.createRow(rownum); 
        HSSFCell skillTitleCell = skillRow.createCell(0);
        skillTitleCell.setCellValue("专业技能");
        skillTitleCell.setCellStyle(titleStyle);
        CellRangeAddress skillCra=new CellRangeAddress(rownum, rownum, 0, 1);//合并单元格
        detailSheet.addMergedRegion(skillCra);
        rownum++;
        if(resume.getSkillsList()!=null && resume.getSkillsList().size()>0){
        	for (int i = 0; i < resume.getSkillsList().size(); i++) {
        		HSSFRow skillNameRow = detailSheet.createRow(rownum); 
        		rownum++;
        		skillNameRow.createCell(1).setCellValue(resume.getSkillsList().get(i).getSkillName()+":"+resume.getSkillsList().get(i).getMasterDegree());
			}
        }
        
        HSSFRow trainRow = detailSheet.createRow(rownum); 
        HSSFCell traTitleCell = trainRow.createCell(0);
        traTitleCell.setCellValue("培训经历");
        traTitleCell.setCellStyle(titleStyle);
        CellRangeAddress traCra=new CellRangeAddress(rownum, rownum, 0, 1);//合并单元格
        detailSheet.addMergedRegion(traCra);
        rownum++;
        if(resume.getTrainList()!=null && resume.getTrainList().size()>0){
        	for (int i = 0; i < resume.getTrainList().size(); i++) {
        		HSSFRow machRow = detailSheet.createRow(rownum); 
        		rownum++;
        		machRow.createCell(0).setCellValue("培训机构");
        		machRow.createCell(1).setCellValue(resume.getTrainList().get(i).getMachinery());
        		HSSFRow addreRow = detailSheet.createRow(rownum); 
        		rownum++;
        		addreRow.createCell(0).setCellValue("培训地点");
        		addreRow.createCell(1).setCellValue(resume.getTrainList().get(i).getAddress());
        		HSSFRow cerRow = detailSheet.createRow(rownum); 
        		rownum++;
        		cerRow.createCell(0).setCellValue("所获证书");
        		cerRow.createCell(1).setCellValue(resume.getTrainList().get(i).getCertificateName());
        		HSSFRow nameRow = detailSheet.createRow(rownum); 
        		rownum++;
        		nameRow.createCell(0).setCellValue("培训内容");
        		nameRow.createCell(1).setCellValue(resume.getTrainList().get(i).getTrainName());
			}
        }
        
	}

}
