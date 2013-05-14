package com.tell.fetch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tell.data.NetEaseNewsArticleItem;

public class NetEaseLocalNewsFetcher extends FetchBase {

	final static Logger log = LoggerFactory.getLogger(NetEaseLocalNewsFetcher.class);
	final static String localNewsUrl = "http://c.3g.163.com/nc/article/local/";
	final static String startOffset = "0";
	static int count = 10;
	
	String locationCode = "5p2t5bee";//杭州
	String location = "杭州";//杭州
	
	public ArrayList<NetEaseNewsArticleItem> fetchLocalArticleItem() {
		return fetchLocalArticleItem(this.count);
	}
	
	/**
	 * get local news. this method is a litter different with the common news fetcher
	 * you can specified the news articles count you want 
	 * @param count
	 * @return
	 */
	public ArrayList<NetEaseNewsArticleItem> fetchLocalArticleItem(int count) {
		 ArrayList<NetEaseNewsArticleItem> news = new ArrayList<NetEaseNewsArticleItem>();
		 
		 String urlPostfix = "/" + startOffset + "-" + count + ".html";
		 String url = localNewsUrl + locationCode + urlPostfix;
		 news.addAll(NetEaseNewsArticleFetcher.fetchArticleItem(NetEaseNewsListFetcher.fetchListItem(url, location)));
		 return news;
	}
	
	/**
	 * set the location for local. it should be UTF-8 .e.g. 杭州
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
		this.locationCode = new sun.misc.BASE64Encoder().encode(location.getBytes());   
	}
	
	public String getLocation() {
		return location;
	}
	
	@Override
	public ArrayList<Object> fetch() {
		// TODO Auto-generated method stub
		return null;
	}

}
