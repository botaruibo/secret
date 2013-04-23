package com.tell.fetch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NetEaseLocalNewsFetcherTest {

	@Test
	public void test() {
		NetEaseLocalNewsFetcher local = new NetEaseLocalNewsFetcher();
		local.setLocation("上海");
		
		assertTrue("5LiK5rW3".equals(local.locationCode));
	}
}
