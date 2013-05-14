package com.tell.util;

import java.util.Calendar;
import java.util.Date;

public class DailyIterator implements ScheduleIterator {
	protected final int hourOfDay, minute, second;
    protected final Calendar calendar = Calendar.getInstance();

    /**
     * a no parameter constructor with default time. this constructor just for instance this class by reflect 
     * we will config this class in a XML file in the future as well as it's parameters. we config class name 
     * in tell.properties at present. 
     * 
     */
    public DailyIterator() {
    	this(21, 21, 0, new Date());
    }
    
    public DailyIterator(int hourOfDay, int minute, int second) {
        this(hourOfDay, minute, second, new Date());
    }

    public DailyIterator(int hourOfDay, int minute, int second, Date date) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        if (!calendar.getTime().before(date)) {
            calendar.add(Calendar.DATE, -1);
        }
    }

    public Date next() {
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

}
