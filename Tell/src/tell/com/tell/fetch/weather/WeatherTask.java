package com.tell.fetch.weather;

import com.tell.util.SchedulerTask;

public class WeatherTask extends SchedulerTask {

	@Override
	public void run() {
        WeatherInfoFetcher wi = new WeatherInfoFetcher();
		
		System.out.println(wi.fetchWeather("杭州").toString());
		WeatherItem i = wi.fetchWeather("杭州");
		String trigger = i.isTriggered();
		
		if(trigger != null && !trigger.isEmpty()) {
			//send the message to the queue. "trigger" is the message
		}

	}

}
