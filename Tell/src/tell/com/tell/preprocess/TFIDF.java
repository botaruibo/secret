package com.tell.preprocess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TFIDF {
	final static Logger log = LoggerFactory.getLogger(TFIDF.class);
	
	/**
	 * words counter. it's a  linear time algorithm
	 * @param words
	 * 		a list of words waiting for counting
	 * @return
	 * 		the words and the count
	 */
	public static HashMap<String, Integer> wordsCount(ArrayList<String> words) {
		HashMap<String, Integer> counter = new HashMap<String, Integer>();
		for(String aWord : words) {
			if(counter.get(aWord) == null) {
				counter.put(aWord, 1);
			} else {
				counter.put(aWord, counter.get(aWord) + 1);
			}
		}
		return counter;
	}
	
	/**
	 * ascending sort the count of the words;
	 * 
	 * @param map
	 * 		map waiting for sort
	 * @return
	 * 		ascending sorted list Entry
	 * 
	 */
	public static ArrayList<Entry<String, Integer>> sortMap(HashMap<String, Integer> map) {
		ArrayList<Entry<String, Integer>> sortedResult = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(sortedResult, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		
		return sortedResult;		
	}

}
