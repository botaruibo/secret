package com.tell.fetch.weather;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tell.fetch.FetchBase;

public class WeatherInfoFetcher extends FetchBase{
	final static Logger log = LoggerFactory.getLogger(WeatherInfoFetcher.class);
	
	final static String urlPrefix = "http://m.weather.com.cn/data/";
	final static String infoTitle = "weatherinfo";
	static {
		CityID.init();
	}
	
	/**
	 * get weather info by location
	 * @param location
	 * 			e.g. 杭州. must be Chinese character
	 */
	public WeatherItem fetchWeather(String location) {		
		String urlPostfix = CityID.get(location);
		if(urlPostfix == null) {
			log.error("the location is not crrect, chack again");
			return null;
		}
		urlPostfix = urlPostfix + ".html";
		String urlStr = urlPrefix + urlPostfix;
		String content = httpGet(urlStr);
		if(content == null || content.isEmpty()) {
			log.error("Weather Info fetch failed"); 
			return null;
		}
		WeatherItem item = null;
		try {
			JSONObject jsonObj = new JSONObject(content);
			if(jsonObj.getJSONObject(infoTitle) == null) {
				log.error("The weather info fetched from web is empoty. ");  
				return null;
			}
			JSONObject jsonWeatherItem = jsonObj.getJSONObject(infoTitle);
			item = new WeatherItem();
			
			String city = jsonWeatherItem.getString("city");
			if (!city.trim().equals(location)) {
				log.error("The weather we wish is not match with what we got. got city:" + city);
				return null;
			}
			item.setLocation(city);
			item.setTemp1(jsonWeatherItem.getString("temp1"));
			item.setTemp2(jsonWeatherItem.getString("temp2"));
			item.setWeather1(jsonWeatherItem.getString("weather1"));
			item.setWeather2(jsonWeatherItem.getString("weather2"));
			item.setWind1(jsonWeatherItem.getString("wind1"));
			item.setWind2(jsonWeatherItem.getString("wind2"));
		} catch (JSONException e) {
			log.warn("json paser faild");
		}		
	 return item;
	}

	@Override
	public ArrayList<Object> fetch() {
		// TODO Auto-generated method stub
		return null;
	}	
}
