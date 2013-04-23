package com.tell.fetch;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetEaseNewsListFetcher extends FetchBase {
	final static Logger log = LoggerFactory.getLogger(NetEaseNewsListFetcher.class);
	final static String startOffset = "0";
	final static String count = "10";


	/**
	 * access netease api to get json data. parse to object
	 * @return object list
	 */
	public static ArrayList<NetEaseNewsListItem> fetchListItem() {
		ArrayList<NetEaseNewsListItem> news = new ArrayList<NetEaseNewsListItem>();
		
		String urlPostfix = "/" + startOffset + "-" + count + ".html";
		String urlPrefix = NetEaseNewsListItem.urlBase.endsWith("/") ? NetEaseNewsListItem.urlBase : NetEaseNewsListItem.urlBase + "/" ;
		
        for(Entry<String, String> entry : NetEaseNewsListItem.categorysUrlMap.entrySet()){
        	String urlStr = urlPrefix + entry.getValue() + urlPostfix;
    		String content = httpGet(urlStr);
    		if(content == null) {
    			log.error("no content fetch from netEase"); 
    			continue;
    		}
    		try {
    			JSONObject jsonObj = new JSONObject(content);
    			String respCode = NetEaseNewsListItem.categorysCodeMap.get(entry.getKey());
    			if(jsonObj.getJSONArray(respCode) == null 
    					|| jsonObj.getJSONArray(respCode).length() < 1) {
    				log.error("the content fetched from netEase is empoty");
    				continue;
    			}
    			int length = jsonObj.getJSONArray(respCode).length();
    			JSONArray items = jsonObj.getJSONArray(respCode);
    			
    			for(int i = 0; i < length; i++) {
    				JSONObject item = (JSONObject)items.get(i);
    				//if the docid missed, this item should not be recode
    				if(item.getString(NetEaseNewsListItem.DOCID) == null) {
    					continue;
    				}
    				NetEaseNewsListItem newsItem = new NetEaseNewsListItem();
    				for(int j = 0; j < NetEaseNewsListItem.listFieldsStr.length; j++) {
    					String field = NetEaseNewsListItem.listFieldsStr[j];
    					newsItem.set(field, item.getString(field));    					
    				}
    				newsItem.setDocid(item.getString(NetEaseNewsListItem.DOCID).trim());
					newsItem.setDigest(item.getString(NetEaseNewsListItem.DIFEST));
					newsItem.setCategory(entry.getKey());
					news.add(newsItem);
    			}			
    			
    		} catch (JSONException e) {			
    			log.error("respose transfer to json error", e);
    		}    		
        }
		return news;
	}
     
	public static ArrayList<NetEaseNewsListItem> fetchListItem(String url, String keyWord) {
		ArrayList<NetEaseNewsListItem> news = new ArrayList<NetEaseNewsListItem>();

		if(url == null || url.isEmpty()) {
			return null;
		}
		
		String content = httpGet(url);
		if (content == null) {
			log.error("no content fetch from netEase");
			return null;
		}
		try {
			JSONObject jsonObj = new JSONObject(content);
			String respCode = keyWord;
			if (jsonObj.getJSONArray(respCode) == null
					|| jsonObj.getJSONArray(respCode).length() < 1) {
				log.error("the content fetched from netEase is empoty");
				return null;
			}
			int length = jsonObj.getJSONArray(respCode).length();
			JSONArray items = jsonObj.getJSONArray(respCode);

			for (int i = 0; i < length; i++) {
				JSONObject item = (JSONObject) items.get(i);
				// if the docid missed, this item should not be recode
				if (item.getString(NetEaseNewsListItem.DOCID) == null) {
					continue;
				}
				NetEaseNewsListItem newsItem = new NetEaseNewsListItem();				
				newsItem.setDocid(item.getString(NetEaseNewsListItem.DOCID)
						.trim());
				newsItem.setDigest(item.getString(NetEaseNewsListItem.DIFEST));
				newsItem.setCategory(keyWord);
				news.add(newsItem);
			}

		} catch (JSONException e) {
			log.error("respose transfer to json error", e);
		}

		return news;
	}
	
	@Override
	public ArrayList<Object> fetch() {
		// TODO Auto-generated method stub
		return null;
	}
}
