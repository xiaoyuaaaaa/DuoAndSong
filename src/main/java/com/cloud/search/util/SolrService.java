package com.cloud.search.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;


public class SolrService {
    private static SolrService solrService = null;
    private static SolrClient  solrClient=null;
    private static String url="http://112.74.27.226:8080/solr/resume";
    
    public static synchronized SolrService getInstance() {
        if (solrService==null){
        	solrService=new SolrService();
        }
        return solrService;
    }
    public static SolrClient getsolrClient(){
         try {
        	 if(solrClient==null){
        		 solrClient = new HttpSolrClient(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solrClient;
    }
    
    public static SolrClient getsolrClient(String URL){
    	SolrClient atsClicent = null;
        try {
       	 if(atsClicent==null){
       		atsClicent = new HttpSolrClient(URL);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return atsClicent;
   }
    
    /**
     * solr查询单条数据
     * querStr 查询条件,格式 jobTitle:Andorid 
     * resultField 查询返回的field字段名
     * @throws IOException 
     * @throws SolrServerException 
     */
    public static SolrDocument getSolrResult(String queryStr,String[] resultField) throws SolrServerException, IOException{
		SolrClient solr = SolrService.getsolrClient();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(queryStr);
		solrQuery.setFields(resultField);
		QueryResponse resp = solr.query(solrQuery);
		SolrDocumentList docsList = resp.getResults();
		SolrDocument solrDocument = null;
		if(docsList.size()>0){
			while (true) {
				solrDocument = docsList.get(0);
				break;
			}
		}
		return solrDocument;
    }
    
    /**
     * 查询指定服务器索引库中总条数
     * querStr 查询条件,格式 jobTitle:Andorid 
     * resultField 查询返回的field字段名
     * @throws IOException 
     * @throws SolrServerException 
     */
    public static long getSolrResult(SolrClient solrClient,String queryStr,String[] resultField) throws SolrServerException, IOException{
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(queryStr);
		solrQuery.setFields(resultField);
		QueryResponse resp = solrClient.query(solrQuery);
		SolrDocumentList docsList = resp.getResults();
		long numFound = docsList.getNumFound();
		return numFound;
    }
    
    /**
     * solr查询多条数据
     * sort 排序字段
     * order 排序方式，0.升序、1.降序
     * querStr 查询条件,格式 jobTitle:Andorid 
     * resultField 查询返回的field字段名
     * @throws IOException 
     * @throws SolrServerException 
     */
    public static SolrDocumentList getSolrList(String queryStr,String sort,int order,String[] resultField,int page,int pageSize) throws SolrServerException, IOException{
		SolrClient solr = SolrService.getsolrClient();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setRequestHandler("/select");
		solrQuery.setRows(pageSize);
		solrQuery.setStart(page);
		solrQuery.setQuery(queryStr);
		solrQuery.setFields(resultField);
		if(sort!=null){
			if(order==1){
				solrQuery.setSort(sort,ORDER.desc);
			}else{
				solrQuery.setSort(sort,ORDER.asc);
			}
			
		}
		QueryResponse resp = solr.query(solrQuery);
		SolrDocumentList docsList = resp.getResults();
		return docsList;
    }
    
    /**
     * solr 设置搜索条件
     * querStr 查询条件,格式 jobTitle:Andorid 
     * resultField 查询返回的field字段名
     * @throws IOException 
     * @throws SolrServerException 
     */
    public static String setSolrQ(Map<String, Object> params) throws SolrServerException, IOException{
    	
    	StringBuffer buff = new StringBuffer();
    	Set<String> keySet = params.keySet();
    	for(String key:keySet) {
    		buff.append(" AND "+key+":"+params.get(key));
    	}
		return buff.toString().substring(5);
    }
    
    public static void buildIndex(List<SolrInputDocument> list) throws Exception {
		if(!list.isEmpty()) {
			//SystemDefaultHttpClient cl = new SystemDefaultHttpClient();   
			SolrClient solrClient = new HttpSolrClient(url);
			try {
				solrClient.add(list);
				solrClient.commit();
				list = null;
				solrClient.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}
    
    public static void deleteIndex(List<String> list,String type) throws Exception {
		if(!list.isEmpty()) {
			SolrClient solrClient = null;
			solrClient = new HttpSolrClient(url);
			try {
				solrClient.deleteById(list);
				solrClient.commit();
				list = null;
				solrClient.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
