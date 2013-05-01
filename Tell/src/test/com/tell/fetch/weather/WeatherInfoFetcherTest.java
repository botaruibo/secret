package com.tell.fetch.weather;

import org.junit.Test;

public class WeatherInfoFetcherTest {

	@Test
	public void test(){
	/*	WeatherInfoFetcher wi = new WeatherInfoFetcher();
		
		System.out.println(wi.fetchWeather("杭州").toString());
		WeatherItem i = wi.fetchWeather("杭州");
		
		String todayH = i.temp1.split("~")[0];
		todayH = todayH.substring(0, todayH.length()-1);
		String tomorrowH = i.temp2.split("~")[0];
		tomorrowH = tomorrowH.substring(0, tomorrowH.length()-1);
		System.out.println("th: " + todayH + " tmoH: " + tomorrowH);*/
		String wind2 = "北风3-5级";
		
		int index = wind2.indexOf("级");
		String wind = index > 0 ? wind2.substring(index-1, index) : "0";
		
		if(Integer.parseInt(wind) > 4 && !wind2.contains("小于")) {
			System.out.println("wind: " + wind);
		}
		
		
	}
}
