package com.tell.util;

import org.junit.Test;

public class TellTimerTest {

	@Test
	public void test() {
		TellTimer tt = new TellTimer();
		DailyIterator di = new DailyIterator(13, 2 , 0);
		
		tt.schedule(new SchedulerTask() {

			@Override
			public void run() {
				System.out.println("test timer");
				
			}
			
		}, di);
	}
}
