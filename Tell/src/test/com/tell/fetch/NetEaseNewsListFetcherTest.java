package com.tell.fetch;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class NetEaseNewsListFetcherTest {

	@Test
	public void testFetch() {
		NetEaseNewsListFetcher fetcher = new NetEaseNewsListFetcher();
		ArrayList<NetEaseNewsListItem> test = fetcher.fetchListItem();
		assertTrue(test.size()>0);
//		System.out.println(test.size());
	}
}
