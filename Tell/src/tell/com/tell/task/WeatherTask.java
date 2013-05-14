package com.tell.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tell.data.WeatherItem;
import com.tell.fetch.weather.WeatherInfoFetcher;
import com.tell.fetch.weather.WeatherTrigger;
import com.tell.util.SchedulerTask;

public class WeatherTask extends SchedulerTask {
	final static Logger log = LoggerFactory.getLogger(WeatherTask.class);

	WeatherTrigger trigger = new WeatherTrigger();
	
	@Override
	public void run() {
        WeatherInfoFetcher wi = new WeatherInfoFetcher();
		
        String cityName = "杭州";
		WeatherItem i = wi.fetchWeather(cityName);
		log.info("The weather of city " + cityName + ": " + i.toString());

		trigger.setWeatherItem(i);
		
		if(trigger.isTriggered()) {
			//send the message to the queue. "trigger" is the message
			trigger.getTriggerInfo();
		}

	}

}
