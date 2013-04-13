package com.tell.fetch;

import java.util.ArrayList;

import org.junit.Test;

public class NetEaseNewsListFetcherTest {

	@Test
	public void testFetch() {
		NetEaseNewsListFetcher fetcher = new NetEaseNewsListFetcher();
		ArrayList<NetEaseNewsListItem> test = fetcher.fetchListItem();
		System.out.println(test.size());
	}
}
