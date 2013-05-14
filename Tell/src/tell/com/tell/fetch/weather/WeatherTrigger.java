package com.tell.fetch.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tell.data.WeatherItem;
import com.tell.message.trigger.Trigger;

/**
 * this kind of command pattern
 * 
 * @author brui
 *
 */
public class WeatherTrigger extends Trigger {
	final static Logger log = LoggerFactory.getLogger(WeatherTrigger.class);

	protected WeatherItem item;
	
	public WeatherTrigger(WeatherItem weather) {
		this.item = weather;
	}
	
	public WeatherTrigger() {
		
	}
	
	public void setWeatherItem(WeatherItem weather) {
		this.item = weather;
	}
	
	@Override
	public boolean isTriggered() {
		if(item.isTriggered()) {
			this.setTriggerInfo(item.getTriggerInfo());
			return true;
		}
		return false;
	}

}
