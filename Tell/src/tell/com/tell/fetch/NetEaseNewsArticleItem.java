package com.tell.fetch;

import java.io.Serializable;


public class NetEaseNewsArticleItem implements Serializable {

	/**article object may store on the disk or some kind of DB
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static String urlBase = "http://c.3g.163.com/nc/article/";
	final static String urlPostfix = "/full.html";
//	final static String[] articleFieldsStr = {"body", "title", "source", "docis", "relative"};
	final static String CATEGORG = "category";
	final static String TITLE = "title";
	final static String DIGEST = "digest";
	final static String PTIME = "ptime";
	final static String BODY = "body";
	final static String REPLYCOUNT = "replyCount";
    final static String SOURCE = "source";
    final static String DOCID = "docid";
    final static String LINK = "link";
	
    public class LinkField { 
    	final static String REF = "ref";
    }

	String category;
	String title;
	String digest;
	String ptime;
	String body;
	String replyCount;
	String source;
	String docid;
	String link;
//	String vedio;
//	String img;
	
	public void setCategory(String category){
		this.category = category;
	}
	public void setDocid(String docid){
		this.docid = docid;
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
	public void setDigest(String digest){
		this.digest = digest;
	}
	public void setBody(String body){
		this.body = body;
	}
	public void setSource(String source){
		this.source = source;
	}
	public void setLink(String link){
		this.link = link;
	}
	
	public String getCategory(){
		return this.category;
	}
	public String getDocid(){
		return this.docid;
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
	public String getDigest(){
		return this.digest;
	}
	public String getBody(){
		return this.body;
	}
	public String getSource(){
		return this.source;
	}
	public String getLink(){
		return this.link;
	}
	
	public String toString() {
		return "title: " + title + "/n body: " + body;
	}
}
