package com.tell.fetch;

import org.junit.Test;

public class NetEaseNewsFetcherTest {

	@Test
	public void testFetch() {
		NetEaseNewsFetcher fetcher = new NetEaseNewsFetcher();
		fetcher.fetch();
	}
}
