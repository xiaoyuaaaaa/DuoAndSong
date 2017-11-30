package com.cloud.resume.util.fileConverter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class WordConverter implements Runnable{
	private String folder;//保存的文件夹
	private String filename;//保存的名字
	private String baseurl;//请求的根路径
	
	public WordConverter(String folder, String filename,String baseurl) {
		this.folder = folder;
		this.filename = filename;
		this.baseurl = baseurl;
	}
	
	public WordConverter() {
		
	}
	
	/** 
     *  
     * @param folder 生成pdf后放在哪个目录 
     * @param filename pdf的名称 
     * @param baseurl 要生成pdf的url 
     * @param jspString 要把哪个生成pdf 
     * @return 
     */  
    public boolean processFile(String folder, String filename,String baseurl){  
        boolean res = false;  
        try{  
            File f = new File(folder);  
            if (f.isDirectory()) {  
                f.mkdir();  
            }  
              
            HttpClient client = HttpClients.createDefault();  
            //HttpGet h_request = new HttpGet("http://console.yifengjianli.com/resumeToHtml?userId=17");
            HttpGet h_request = new HttpGet(baseurl);
            HttpResponse h_response = client.execute(h_request); 
            writeWordFile(h_response.getEntity().getContent(), folder, filename);
            res = true;
        }catch(Exception e){  
            e.printStackTrace();  
        } 
        return res;  
          
    }
    
    /**
	 * 生成WORD
	 * @param in 
	 * @param filePath 存放了目录
	 * @param fileName 文件名
	 */
    public static void writeWordFile(InputStream in ,String filePath,String fileName){
           ByteArrayInputStream bais = null;
           FileOutputStream fos = null;
           //String path = "C:\\Users\\fangshaomin\\Desktop\\";  //根据实际情况写路径
           try {
	              if (!"".equals(filePath)) {
	                 File fileDir = new File(filePath);
	                 if (fileDir.exists()) {
	                    String content = readFile(in);
	                    byte b[] = content.getBytes("UTF-8");
	                    bais = new ByteArrayInputStream(b);
	                    POIFSFileSystem poifs = new POIFSFileSystem();
	                    DirectoryEntry directory = poifs.getRoot();
	                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
	                    fos = new FileOutputStream(filePath + fileName);
	                    poifs.writeFilesystem(fos);
	                    bais.close();
	                    fos.close();
	                 }
	              }
           } catch (Exception e) {
              e.printStackTrace();
           } finally {
        	   try{
        		   if(fos != null) fos.close();
                   if(bais != null) bais.close();
        	   }catch(Exception e){
        		   e.printStackTrace(); 
        	   }              
           }
    }
    
    /**
     * 读取html文件到字符串
     * @param filename
     * @return
     * @throws Exception
     */
    public static String readFile(InputStream in) throws Exception {
           StringBuffer buffer = new StringBuffer("");
           BufferedReader br = null;
           try {
        	   br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        	   String temp="";
        	   while((temp=br.readLine()) !=null){
        		   buffer.append(temp);
        	   }
           } catch (Exception e) {
              e.printStackTrace();
           } finally {
              if(br!=null) br.close();
           }
           return buffer.toString();
    }
	
	@Override
	public void run() {
		processFile(this.folder, this.filename, this.baseurl);		
	}

}
