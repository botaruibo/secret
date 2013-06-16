package com.tell.preprocess;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.WordlistLoader;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class SmartCNTest {

	@Test
	public void testParseToString() throws IOException {
		String s = "外企也就那么会儿事。";

		SmartCN sc = new SmartCN();
//		System.out.println(sc.parse(s));
		assertTrue(sc.parseToString(s).contains("外企 也 就 那么 会儿 事"));
	}
	@Test
	public void testParseToArray() throws IOException {
		String s = "外企也就那么会儿事。";

		SmartCN sc = new SmartCN();
		ArrayList<String> words = new ArrayList<String>();
		words = sc.parseToArray(s);
		System.out.println(words.toString());
		assertTrue(words.size() == 3);
	}
	
//	@Test
	public void test() throws Exception{
		Reader rd = new FileReader("conf/stopwords.txt");
		CharArraySet stopwords = CharArraySet.unmodifiableSet(WordlistLoader.getWordSet(rd, Version.LUCENE_42));
	}
}
