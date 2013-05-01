package com.tell.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.jfree.util.Log;

import com.tell.fetch.NetEaseLocalNewsFetcher;
import com.tell.fetch.NetEaseNewsArticleFetcher;
import com.tell.fetch.NetEaseNewsArticleItem;
import com.tell.fetch.NetEaseNewsListFetcher;
import com.tell.fetch.NetEaseNewsListItem;
import com.tell.preprocess.SmartCN;
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
	/*	TellTimer tt = new TellTimer();
		DailyIterator di = new DailyIterator(13, 14, 0);
		
		tt.schedule(new SchedulerTask() {

			@Override
			public void run() {
				System.out.println("test timer");
				
			}			
		}, di);
		
		NetEaseNewsListFetcher listFetcher = new NetEaseNewsListFetcher();
		ArrayList<NetEaseNewsListItem> test = listFetcher.fetchListItem();
		//NetEaseNewsArticleFetcher articleFetcher = new NetEaseNewsArticleFetcher();
		NetEaseLocalNewsFetcher articleFetcher = new NetEaseLocalNewsFetcher();
		ArrayList<NetEaseNewsArticleItem> news = articleFetcher.fetchLocalArticleItem();
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(path + fileName));		
			for (NetEaseNewsArticleItem item : news) {
				item.setBody(SmartCN.parse(item.getBody()));
				out.writeObject(item);
//				System.out.println("link: " + item.getLink());
//				System.out.println("body: " + item.getBody());
//				System.out.println("title: " + item.getTitle());
//				System.out.println("digest: " + item.getDigest());
//				System.out.println("source: " + item.getSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null ) {
				out.close();
			}
		}
	
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

}
