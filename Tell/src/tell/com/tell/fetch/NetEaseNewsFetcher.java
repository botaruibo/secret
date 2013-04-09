package com.tell.fetch;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetEaseNewsFetcher implements FetchInterface {
	final static Logger log = LoggerFactory.getLogger(NetEaseNewsFetcher.class);

	@Override
	public ArrayList<Object> fetch() {
		// TODO Auto-generated method stub
		String urlStr = "http://c.3g.163.com/nc/article/headline/T1348647909107/0-10.html";
		String content = get(urlStr);
		if(content == null) {
			log.error("no content fetch from netEase"); 
			return null;
		}
		try {
			JSONObject jsonObj = new JSONObject(content);
			
			if(jsonObj.getJSONArray("T1348647909107") == null 
					|| jsonObj.getJSONArray("T1348647909107").length() < 1) {
				log.error("the content fetched from netEase is empoty"); 
				return null;
			}
			int length = jsonObj.getJSONArray("T1348647909107").length();
			JSONArray items = jsonObj.getJSONArray("T1348647909107");
			
			for(int i = 0; i < length; i++) {
				JSONObject item = (JSONObject)items.get(i);
				 String url_3w = item.getString("url_3w");
				 log.info("url_3w :" + url_3w);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
     
	private String get(String urlString) {
		try {
		URL url = new URL(urlString);
		HttpURLConnection connection = null;
		connection = (HttpURLConnection) url.openConnection();
		//connection.setConnectTimeout(CONNECTION_TIME_OUT);
		//connection.setReadTimeout(CONNECTION_TIME_OUT);
		connection.setDoOutput(true);
		InputStream in = url.openConnection().getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		for (int b = in.read(); b >= 0; b = in.read()) {
		outputStream.write((byte) b);
		}
		String response = new String(outputStream.toByteArray(), "UTF-8").trim();
		outputStream.close();
		in.close(); 
		return response;
		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}
}
