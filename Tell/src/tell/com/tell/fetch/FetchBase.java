package com.tell.fetch;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public abstract class FetchBase {

	abstract ArrayList<Object> fetch();
	
	/**
	 * http get method request for urlString. 
	 * @param urlString
	 * @return response string
	 */
	protected String httpGet(String urlString) {
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
