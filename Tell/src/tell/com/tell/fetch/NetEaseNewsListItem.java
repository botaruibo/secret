package com.tell.fetch;

import java.util.HashMap;
import java.util.Map;

public class NetEaseNewsListItem {

	final static String urlBase = "http://c.3g.163.com/nc/article/";
	public final static Map<String, String> categorysUrlMap = new HashMap<String, String>();
	static {
		categorysUrlMap.put("headline", "headline/T1348647909107");
		categorysUrlMap.put("fun", "list/T1348648517839");
		categorysUrlMap.put("sport", "list/T1348649079062");
		categorysUrlMap.put("finance", "list/T1348648756099");
		categorysUrlMap.put("IT", "list/T1348649580692");
		categorysUrlMap.put("other", "list/T1348654756909");
		
//		categorysMap.put("local", "local");		
	}
	
	public final static Map<String, String> categorysCodeMap = new HashMap<String, String>();
	static {
		categorysCodeMap.put("headline", "T1348647909107");
		categorysCodeMap.put("fun", "T1348648517839");
		categorysCodeMap.put("sport", "T1348649079062");
		categorysCodeMap.put("finance", "T1348648756099");
		categorysCodeMap.put("IT", "T1348649580692");
		categorysCodeMap.put("other", "T1348654756909");
	}
	final static String DOCID = "docid";
	final static String DIFEST = "digest";
	
//	final static String resposeFeildName = "";
	final static String[] listFieldsStr = {"url_3w", "docid", "title", "replyCount", "digest", "ptime", "url"};
	
	final  Map<String, String> listFieldsMap = new HashMap<String, String>();
	String category;
	String docid;
	String digest;
	/*	String url_3w;	
	String title;
	String replyCount;
	String ptime;
	String url;	

	public void setUrl3W(String url3w){
		this.url_3w = url3w;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setReplyCount(String replyCount){
		this.replyCount = replyCount;
	}
	public void setPtime(String ptime){
		this.ptime = ptime;
	}
	public void setUrl(String url){
		this.url = url;
	}
	*/
	public void setDocid(String docid){
		this.docid = docid;
	}
	public void setDigest(String digest){
		this.digest = digest;
	}
	public void setCategory(String category){
		this.category = category;
	}
		
	public void set(String key, String value){
		listFieldsMap.put(key, value);
		
	}
	/*
	public String getUrl3W(){
		return this.url_3w;
	}
	public String getTitle(){
		return this.title;
	}
	public String getReplyCount(){
		return this.replyCount;
	}
	public String getPtime(){
		return this.ptime;
	}
	public String getUrl(){
		return this.url;
	}*/
	public String get(String key){
		return listFieldsMap.get(key);
	}
	public String getDocid(){
		return this.docid;
	}
	public String getDigest(){
		return this.digest;
	}
	public String getCategory(){
		return this.category;
	}
	
}
