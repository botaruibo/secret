package com.tell.fetch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetEaseNewsArticleFetcher extends FetchBase {
	final static Logger log = LoggerFactory.getLogger(NetEaseNewsArticleFetcher.class);

	/**
	 * fetch article content with the docid.
	 * @param newsList, the news list item. each itme contain docid/category and digest
	 * @return a list of article items--<NetEaseNewsArticleItem>
	 */
	public ArrayList<NetEaseNewsArticleItem> fetchArticleItem(List<NetEaseNewsListItem> newsList) {
        ArrayList<NetEaseNewsArticleItem> news = new ArrayList<NetEaseNewsArticleItem>();
		
		String urlPrefix = NetEaseNewsArticleItem.urlBase.endsWith("/") ? NetEaseNewsArticleItem.urlBase : NetEaseNewsArticleItem.urlBase + "/" ;
		if(newsList == null || newsList.isEmpty()) {
			log.warn("No news list item");
			return news;
		}
		int num = 0;
		for(NetEaseNewsListItem listItem : newsList) {
			String content = httpGet(urlPrefix + listItem.getDocid() + NetEaseNewsArticleItem.urlPostfix);
    		if(content == null || content.isEmpty()) {
    			log.error("Article content fetch failed, docid: " + listItem.getDocid()); 
    			continue;
    		}
    		try {
    			JSONObject jsonObj = new JSONObject(content);
    			if(jsonObj.getJSONObject(listItem.getDocid()) == null) {
    				log.error("The article fetched from netEase is empoty. docid: " + listItem.getDocid());  
    				continue;
    			}
    			JSONObject jsonArticleItem = jsonObj.getJSONObject(listItem.getDocid());
    			NetEaseNewsArticleItem articleItem = new NetEaseNewsArticleItem();
    			articleItem.setDocid(listItem.getDocid());
    			articleItem.setCategory(listItem.getCategory());
    			// digest must get from list item cause article item doesn't have this field
    			articleItem.setDigest(listItem.getDigest());
    			
    			articleItem.setLink(jsonArticleItem.getString(NetEaseNewsArticleItem.LINK));
    			String body = jsonArticleItem.getString(NetEaseNewsArticleItem.BODY);
    			body = preProcessBody(body, articleItem.getLink());
    			articleItem.setBody(body);
    			articleItem.setPtime(jsonArticleItem.getString(NetEaseNewsArticleItem.PTIME));
    			articleItem.setReplyCount(jsonArticleItem.getString(NetEaseNewsArticleItem.REPLYCOUNT));
    			articleItem.setSource(jsonArticleItem.getString(NetEaseNewsArticleItem.SOURCE));
    			articleItem.setTitle(jsonArticleItem.getString(NetEaseNewsArticleItem.TITLE));  
    			
    			news.add(articleItem);
    		} catch (JSONException e) {
    			num++;
    		}		
		}
		if(num > 0) {
			log.warn("There is a lot of article fetch failed, totle: " + num);
		}
		return news;
	}
	
	private String preProcessBody(String body, String link) {
		if(body == null || body.trim().isEmpty()){
			return body;
		}
		
		body = cleanP(body);
		
		//clean img/vedio
		body = cleanMedia(body);
		
		//clean link
		body = cleanLink(body, link);

		return body;
	}

	private String cleanLink(String body, String link) {
		try {
			JSONArray jsonAry = new JSONArray(link);
			
			//return if no link in this article
			if(jsonAry.length() < 1) { 
				return body;
			}
			int len = jsonAry.length();
			for(int i = 0; i < len; i++ ) {
				JSONObject linkItem = (JSONObject)jsonAry.get(i);
				body.replace(linkItem.getString(NetEaseNewsArticleItem.LinkField.REF), "");
			}			
		} catch (JSONException e) {
    		log.warn("Clean link have some problem, link: " + link);
    	}		
		return body;		
	}

	private String cleanMedia(String body) {
		//the img and video tag both like "<!--(.*)-->"
		//other tag like "<p>" "</p>" "<br/>"
		return body.replaceAll("<!--(.*)-->", "");		
		
	}

	private String cleanP(String body) {
		body = body.replace("<p>", "");
		body = body.replace("<P>", "");
		body = body.replace("</p>", "\n");
		body = body.replace("</P>", "\n");
		
		body = body.replace("<br/>", "\n");
		return body;		
		
	}

	@Override
	public ArrayList<Object> fetch() {
		// TODO Auto-generated method stub
		return null;
	}

}
