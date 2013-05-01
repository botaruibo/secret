package com.tell.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class RestrictedDailyIterator extends DailyIterator {

	private final int[] days;
	
	public RestrictedDailyIterator(int hourOfDay, int minute, int second, int[] days) {
		super(hourOfDay, minute, second);
		this.days = (int[]) days.clone();
		Arrays.sort(this.days);
		
	}
	
	public Date next() {
		do {
            calendar.add(Calendar.DATE, 1);
        } while (Arrays.binarySearch(days, calendar.get(Calendar.DAY_OF_WEEK)) < 0);
        return calendar.getTime();

    }

}
