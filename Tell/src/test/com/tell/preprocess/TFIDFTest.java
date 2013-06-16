package com.tell.preprocess;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;

public class TFIDFTest {

	@Test
	public void test(){
		TFIDF ti = new TFIDF();
		ArrayList<String> ts = new ArrayList<String>();
		HashMap<String, Integer> c = new HashMap<String, Integer>();
		
		ts.add("we");
		ts.add("are");
		ts.add("freind");
		ts.add("we");
		ts.add("like");
		ts.add("each");
		ts.add("other");
		c = ti.wordsCount(ts);
//		for(String w : c.keySet()){
//			System.out.println(w);
//			System.out.println(c.get(w));
//		}
		assertTrue(c.size() == 6);
		ArrayList<Entry<String, Integer>> s = ti.sortMap(c);
//		for(Entry<String,Integer> e : s) {   
//		       System.out.println(e.getKey() + "::::" + e.getValue());   
//		      }
		assertTrue(s.get(0).getKey().equals("we"));
	}
}
