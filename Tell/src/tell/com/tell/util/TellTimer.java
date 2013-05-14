package com.tell.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TellTimer {
	final static Logger log = LoggerFactory.getLogger(TellTimer.class);

	class SchedulerTimerTask extends TimerTask {
        private SchedulerTask schedulerTask;
        private ScheduleIterator iterator;
        
        public SchedulerTimerTask(SchedulerTask schedulerTask,
                ScheduleIterator iterator) {
            this.schedulerTask = schedulerTask;
            this.iterator = iterator;
        }
        
        public void run() {
            schedulerTask.run();
            reschedule(schedulerTask, iterator);
        }
    }

    private final Timer timer = new Timer();

    public TellTimer() {
    }

    public void cancel() {
        timer.cancel();
    }

    /**
     * schedule a task by calling the Timer.schedule(*)
     * 
     * @param schedulerTask
     * 		schedule task. run the real task
     * @param iterator
     * 		a iterator to define when this task should run
     * 
     */
    public void schedule(SchedulerTask schedulerTask,
            ScheduleIterator iterator) {

        Date time = iterator.next();
        if (time == null) {
            schedulerTask.cancel();
        } else {
            synchronized(schedulerTask.lock) {
                if (schedulerTask.state != SchedulerTask.VIRGIN) {
                  throw new IllegalStateException("Task already  scheduled " + "or cancelled");
                }
                schedulerTask.state = SchedulerTask.SCHEDULED;
                schedulerTask.timerTask =
                    new SchedulerTimerTask(schedulerTask, iterator);
                timer.schedule(schedulerTask.timerTask, time);
            }
        }
    }

    private void reschedule(SchedulerTask schedulerTask,
            ScheduleIterator iterator) {

        Date time = iterator.next();
        if (time == null) {
            schedulerTask.cancel();
        } else {
            synchronized(schedulerTask.lock) {
                if (schedulerTask.state != SchedulerTask.CANCELLED) {
                    schedulerTask.timerTask =
                        new SchedulerTimerTask(schedulerTask, iterator);
                    timer.schedule(schedulerTask.timerTask, time);
                }
            }
        }
    }
}
