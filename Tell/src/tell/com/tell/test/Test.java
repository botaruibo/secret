package com.tell.test;

import java.util.ArrayList;

import com.tell.fetch.NetEaseNewsArticleFetcher;
import com.tell.fetch.NetEaseNewsArticleItem;
import com.tell.fetch.NetEaseNewsListFetcher;
import com.tell.fetch.NetEaseNewsListItem;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NetEaseNewsListFetcher listFetcher = new NetEaseNewsListFetcher();
		ArrayList<NetEaseNewsListItem> test = listFetcher.fetchListItem();
		NetEaseNewsArticleFetcher articleFetcher = new NetEaseNewsArticleFetcher();
		ArrayList<NetEaseNewsArticleItem> news = articleFetcher.fetchArticleItem(test.subList(5, 9));
		for (NetEaseNewsArticleItem item : news) {
			System.out.println("link: " + item.getLink());
			System.out.println("body: " + item.getBody());
			System.out.println("title: " + item.getTitle());
			System.out.println("digest: " + item.getDigest());
			System.out.println("source: " + item.getSource());
		}
	}

}
