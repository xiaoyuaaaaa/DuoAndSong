package com.cloud.resume.util.fileConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.zefer.pd4ml.PD4ML;

public class PDFConverter implements Runnable {
	
	private String folder;//保存的文件夹
	private String filename;//保存的名字
	private String baseurl;//请求的根路径
	
	/**
	 * 构造函数
	 * @param folder pdf文件保存路径
	 * @param filename pdf文件名称
	 * @param baseurl 生成pdf页面的URL
	 */
	public PDFConverter(String folder, String filename,String baseurl) {
		this.folder = folder;
		this.filename = filename;
		this.baseurl = baseurl;
	}
	
	public PDFConverter() {
		
	}

    /** 
     *  
     * @param folder 生成pdf后放在哪个目录 
     * @param filename pdf的名称 
     * @param baseurl 要生成pdf的url 
     * @return 
     */  
    public boolean processFile(String folder, String filename,String baseurl){  
        boolean res = false;  
        InputStreamReader isr = null;  
        try{  
            File f = new File(folder);  
            if (f.isDirectory()) {  
                f.mkdir();  
            }  
            String fullfilename = folder + filename;  
              
            HttpClient client = HttpClients.createDefault();  
            HttpGet h_request = new HttpGet(baseurl);  
            HttpResponse h_response = client.execute(h_request);  
            isr = new InputStreamReader(h_response.getEntity().getContent(), "UTF-8");  
            //PDFConverter converter = new PDFConverter();  
            res = generatePDF(isr, fullfilename, baseurl);  
              
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            try{
            	isr.close();
            	isr=null;
            }catch(Exception e){
            	return false;
            }  
        }  
  
        return res;  
          
    }  
    /** 
     * 生成pdf 
     * @param isr 
     * @param pdfFilename 
     * @param baseurl 
     * @return 
     */  
    public boolean generatePDF(InputStreamReader isr, String pdfFilename, String baseurl){  
        FileOutputStream fos = null;  
        boolean res = false;  
  
        try{  
            fos = new FileOutputStream(new File(pdfFilename));  
            PD4ML pd4ml = new PD4ML();  
            pd4ml.setPageSize(PD4ML.A4);
			pd4ml.setPageInsets(new java.awt.Insets(20, 50, 20, 50));
			pd4ml.enableImgSplit(false);
            pd4ml.useTTF("java:com/cloud/resume/util/fileConverter", true);
            pd4ml.enableDebugInfo(); 
            pd4ml.render(isr, fos, new URL(baseurl));  
            res = true;  
              
            //res = baos.toByteArray();  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            try{
            	fos.close();
            	fos=null;
            	}catch(Exception ee){
            		return false;
            	}  
        }  
  
        return res;  
    }

	@Override
	public void run() {
		processFile(this.folder, this.filename, this.baseurl);  
	}  
}
