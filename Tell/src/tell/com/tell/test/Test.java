package com.tell.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jfree.util.Log;

import com.tell.data.NetEaseNewsArticleItem;
import com.tell.fetch.NetEaseLocalNewsFetcher;
import com.tell.fetch.NetEaseNewsListFetcher;
import com.tell.fetch.NetEaseNewsListItem;
import com.tell.preprocess.SmartCN;
import com.tell.preprocess.TFIDF;
import com.tell.util.DailyIterator;
import com.tell.util.SchedulerTask;
import com.tell.util.TellTimer;

public class Test {

	final static String path = System.getProperty("java.io.tmpdir");
	final static String fileName = "tellTmpObject";
	final static int start = 5;
	final static int end = 9;
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException {
		
		NetEaseNewsListFetcher listFetcher = new NetEaseNewsListFetcher();
		ArrayList<NetEaseNewsListItem> test = listFetcher.fetchListItem();
		//NetEaseNewsArticleFetcher articleFetcher = new NetEaseNewsArticleFetcher();
		NetEaseLocalNewsFetcher articleFetcher = new NetEaseLocalNewsFetcher();
		ArrayList<NetEaseNewsArticleItem> news = articleFetcher.fetchLocalArticleItem();
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(path + fileName));		
//			for (NetEaseNewsArticleItem item : news) {
//				item.setBody(SmartCN.parseToString(item.getBody()));
//				out.writeObject(item);
//				System.out.println("link: " + item.getLink());
//				System.out.println("body: " + item.getBody());
//				System.out.println("title: " + item.getTitle());
//				System.out.println("digest: " + item.getDigest());
//				System.out.println("source: " + item.getSource());
//			}
			System.out.println("digest: " + SmartCN.parseToString(news.get(0).getBody()));
			System.out.println("digest: " + news.get(0).getDigest());
			HashMap<String, Integer> hm = TFIDF.wordsCount(SmartCN.parseToArray(news.get(0).getBody()));
			ArrayList<Entry<String, Integer>> sort = TFIDF.sortMap(hm);
			for(int i = 0; i < 8; i++) {
				System.out.println(sort.get(i).getKey() + "::::" + sort.get(i).getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null ) {
				out.close();
			}
		}
	/*
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(path + fileName));
			Object obj = null;
			for(int i = 0; i < (end - start); i++) {
				obj = in.readObject();
				NetEaseNewsArticleItem item = (NetEaseNewsArticleItem) obj;
				System.out.println("body: " + item.getBody());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				in.close();
			}
		}		
		deleteTmpFile(path + fileName); */
	}

	private static void deleteTmpFile(String file) {
		boolean success = (new File(file)).delete();
	    if (!success) {
	        Log.error("can't delete tmp file: " + file);
	    }
	}

	//@Test
	public void testAboutWeather() {
		int days[] = new int[] {
		        Calendar.MONDAY,
		        Calendar.TUESDAY,
		        Calendar.WEDNESDAY,
		        Calendar.THURSDAY,
		        Calendar.FRIDAY
		    }; //2,....6
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
		int a = Arrays.binarySearch(days, calendar.get(Calendar.DAY_OF_WEEK));
		
		System.out.println(a);
		a = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(a);
		
		/******test tell timer****/
		TellTimer tt = new TellTimer();
		DailyIterator di = new DailyIterator(13, 14, 0);
		
		tt.schedule(new SchedulerTask() {

			public void run() {
				System.out.println("test timer");
				
			}			
		}, di);
	}
}
