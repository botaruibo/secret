package com.tell.fetch.weather;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CityID {
	final static Logger log = LoggerFactory.getLogger(CityID.class);
	
	final static String conf = "/conf/cityid.txt";
	final static Map citys = new HashMap<String, String>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void init() {
		InputStream in = null;
		
		try {
			Properties properties = new Properties();
			in = new FileInputStream(System.getProperty("user.dir") + conf);
			properties.load(in);
		
			Enumeration enums = properties.keys();
			while (enums.hasMoreElements()) {
				Object key = enums.nextElement();
				citys.put(properties.get(key), key);
			//	System.setProperty((String)key, (String) properties.get(key));
			}	
			log.info("load cityid successed");
		} catch (IOException e) {
			log.error("load cityid faild:", e);			
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 * get city id by Chinese character
	 * @param key
	 * 		Chinese city name. e.g. 杭州
	 * @return
	 * 		number code of the city
	 * 
	 */
	public static String get(String key) {
		if(key != null) {
			return (String)citys.get(key);
		}
		return null;
	}
	
	/**
	 * return all city code as a set
	 * @return
	 * 
	 */
	public static Set<String> getCityCode() {
		return citys.keySet();
	}
	
	/**
	 * return all city by name
	 * @return
	 * 
	 */
	public static Collection<String> getCityName() {
		return citys.values();
	}
}
