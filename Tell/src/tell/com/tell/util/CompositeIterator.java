package com.tell.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CompositeIterator implements ScheduleIterator {

	@SuppressWarnings("rawtypes")
	private List orderedTimes = new ArrayList();
    @SuppressWarnings("rawtypes")
	private List orderedIterators = new ArrayList();

    public CompositeIterator(ScheduleIterator[] scheduleIterators) {
        for (int i = 0; i < scheduleIterators.length; i++) {
            insert(scheduleIterators[i]);
        }
    }

    @SuppressWarnings("unchecked")
	private void insert(ScheduleIterator scheduleIterator) {
        Date time = scheduleIterator.next();
        if (time == null) {
            return;
        }
        int index = Collections.binarySearch(orderedTimes, time);
        if (index < 0) {
            index = -index - 1;
        }
        orderedTimes.add(index, time);
        orderedIterators.add(index, scheduleIterator);
    }

    public synchronized Date next() {
        Date next = null;
        while (!orderedTimes.isEmpty() && (next == null || next.equals((Date) orderedTimes.get(0)))) {
            next = (Date) orderedTimes.remove(0);
            insert((ScheduleIterator) orderedIterators.remove(0));
        }
        return next;
    }

}
