package com.tell.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.jfree.util.Log;

import com.tell.fetch.NetEaseNewsArticleFetcher;
import com.tell.fetch.NetEaseNewsArticleItem;
import com.tell.fetch.NetEaseNewsListFetcher;
import com.tell.fetch.NetEaseNewsListItem;

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
		NetEaseNewsArticleFetcher articleFetcher = new NetEaseNewsArticleFetcher();
		ArrayList<NetEaseNewsArticleItem> news = articleFetcher.fetchArticleItem(test.subList(start, end));
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(path + fileName));		
			for (NetEaseNewsArticleItem item : news) {
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
		deleteTmpFile(path + fileName);
	}

	private static void deleteTmpFile(String file) {
		boolean success = (new File(file)).delete();
	    if (!success) {
	        Log.error("can't delete tmp file: " + file);
	    }
	}

}
