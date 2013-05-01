package com.tell.main;

import com.tell.fetch.weather.WeatherTask;
import com.tell.util.DailyIterator;
import com.tell.util.SchedulerTask;
import com.tell.util.TellTimer;

public class Starter {

	public static void main(String[] arg) {
		TellTimer tTimer = new TellTimer();
		//run at 6:00pm everyday
		DailyIterator di = new DailyIterator(18, 0, 0);
		SchedulerTask task = new WeatherTask();
		
		tTimer.schedule(task, di);
	}
}
