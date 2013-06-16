package com.tell.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tell.util.ScheduleIterator;
import com.tell.util.SchedulerTask;
import com.tell.util.TellTimer;

/**
 * the main thread. init all task that specified in configuration file.
 * 
 * @author brui
 *
 */
public class Teller {
	Logger log = LoggerFactory.getLogger(Teller.class);

	final static String conf = "/conf/tell.properties";
	final static String PARAM_TASK = "tasks";
	static TellTimer tTimer = new TellTimer();
	
	final Map<String, String> tasksClass = new HashMap<String, String>();
	final Map<String, String> schedulesClass = new HashMap<String, String>();
	final Map<String, SchedulerTask> tasks = new HashMap<String, SchedulerTask>();
	final Map<String, ScheduleIterator> schedules = new HashMap<String, ScheduleIterator>();
	
	public void init() {
		//now, all configuration info is in tell.properties. port to XML format is in our plan
		//XML is more flexible for configuration
		loadConfig();
		
		loadTask();
		
		scheduleTask();
	}
	
	/**
	 * schedule task by map {tasks} and {schedules}
	 * 
	 */
	private void scheduleTask() {
		for(String task : tasks.keySet()) {
			SchedulerTask sTask = tasks.get(task);
			ScheduleIterator schedule = schedules.get(task);
			if(sTask != null && schedules != null) {
				tTimer.schedule(sTask, schedule);
			}
		}
		
	}

	/**
	 * instance those class defined in property file. those class name get by loadCofig().
	 * task and schedule objects will be stored in maps 
	 * 
	 */
	private void loadTask() {
		if(tasksClass.size() < 1) {
			log.info("Task list is empty. load tasks: 0");
		}
		for(String task : tasksClass.keySet()) {
			try {
				SchedulerTask sTask = (SchedulerTask)Class.forName(tasksClass.get(task)).newInstance();
				ScheduleIterator schedule = (ScheduleIterator)Class.forName(schedulesClass.get(task)).newInstance();
				tasks.put(task, sTask);
				schedules.put(task, schedule);
			} catch (Exception e) {				
				log.error("Failed to instance class: " + tasksClass.get(task));
			}			
		}
		
	}

	/**
	 * load properties and read class to map. we will change the configuration file to XML in future
	 * 
	 */
	private void loadConfig() {
		InputStream in = null;

		try {
			Properties properties = new Properties();
			in = new FileInputStream(System.getProperty("user.dir") + conf);
			properties.load(in);

			String tasksStr = (String) properties.get(PARAM_TASK);
			String[] tasksArray = tasksStr.split(",");

			for (int i = 0; i < tasksArray.length; i++) {
				tasksClass.put(tasksArray[i], (String)properties.get(tasksArray[i]));
				schedulesClass.put(tasksArray[i], (String)properties.get(tasksArray[i] + ".ScheduleIterator"));
				log.info("load task name: " + tasksArray[i] + " class: " + tasksClass.get(tasksArray[i]));
				log.info("load taskSchedule for task: " + tasksArray[i] + "schedule class: " + schedulesClass.get(tasksArray[i]));
			}								
		} catch (IOException e) {
			log.error("load tell.properties faild:", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
