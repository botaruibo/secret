package com.tell.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tell.message.trigger.Trigger;
import com.tell.message.trigger.Triggerable;

public class WeatherItem implements Triggerable {
	final static Logger log = LoggerFactory.getLogger(WeatherItem.class);
	
	final static String sun = "晴";
	final static String rain = "雨";
	
	String location = "";
	String date_y = "";
	
	String temp1 = "";
	String temp2 = "";
	String weather1 = "";
	String weather2 = "";
	String wind1 = "";
	String wind2 = "";
	String triggerInfo = "";
	
	Trigger trigger;

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	
	public void setWeather1(String w1) {
		this.weather1 = w1;
	}
	
	public void setWeather2(String w2) {
		this.weather2 = w2;
	}
	
	public void setWind1(String wind1) {
		this.wind1 = wind1;
	}
	
	public void setWind2(String wind2) {
		this.wind2 = wind2;
	}
	
	public void setDate(String date) {
		date_y = date;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getTemp1() {
		return temp1;
	}
	
	public String getTemp2() {
		return temp2;
	}
	
	public String getWind1() {
		return wind1;
	}
	
	public String getWind2() {
		return wind2;
	}
	
	public String getWeather1() {
		return weather1;
	}
	
	public String getWeather2() {
		return weather2;
	}
	
	public String getDate() {
		return date_y;
	}
	
	/**
	 * show main information for today's weather
	 */
	public String toString() {
		String r = "temp1: " + temp1;
		r = r + "\ntemp2: " + temp2;
		r = r + "\nweather1: " + weather1;
		r = r + "\nweather2: " + weather2;
		r = r + "\nwind1: " + wind1;
		r = r + "\nwind2: " + wind2;
		return r;
	}
	
	public boolean isTriggered() {
		boolean todayIsSunny = !weather1.contains(rain);
		boolean tomorrowIsRain = weather2.contains(rain);
		String result = null;
		if(todayIsSunny && tomorrowIsRain) {
			result = "明日有雨，注意携带雨伞;";
		}
		int index = wind2.indexOf("级");
		String wind = index > 0 ? wind2.substring(index-1, index) : "0";
		
		if(Integer.parseInt(wind) > 4 && !wind2.contains("小于")) {
			result += "有大风（5级以上）;";
		}
		String todayH = temp1.split("~")[0];
		todayH = todayH.substring(0, todayH.length()-1);
		String tomorrowH = temp2.split("~")[0];
		tomorrowH = tomorrowH.substring(0, tomorrowH.length()-1); 
		if(Integer.parseInt(todayH) > 20 && Integer.parseInt(tomorrowH) < 15) {
			result += "有明显降温，注意加衣";
		}
		if(result == null) {
			return false;
		}
		this.triggerInfo = result;
		log.debug("trigger the conduction, trigger message: " + result);
		return true;
	}
	
	/**
	 * you will get a message that you should delivery if isTriggered() is true.
	 *  else, you get will empty string ""
	 * @return
	 */
	public String getTriggerInfo() {
		return trigger.getTriggerInfo();
	}
}
