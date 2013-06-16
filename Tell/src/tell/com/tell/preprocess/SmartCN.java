package com.tell.preprocess;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.WordlistLoader;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartCN {
	final static Logger log = LoggerFactory.getLogger(SmartCN.class);

	private static CharArraySet stopwords;
	
	private static String STOPWORD_FILE = "conf/stopwords.txt";
	  
	private static final String STOPWORD_FILE_COMMENT = "//";
	  
	public   static   final  String[] STOP_WORDS = {  "a" ,  "an" ,  "and" ,
        "are" ,  "as" ,  "at" ,  "be" ,  "but" ,  "by" ,  "for" ,  "if" ,  "in" ,  "into" ,
        "is" ,  "it" ,  "no" ,  "not" ,  "of" ,  "on" ,  "or" ,  "s" ,  "such" ,  "t" ,
        "that" ,  "the" ,  "their" ,  "then" ,  "there" ,  "these" ,  "they" ,  "this" ,
        "to" ,  "was" ,  "will" ,  "with" ,  "我" ,  "我们" , "的", "是" };
	static {
		
		try {
			Reader rd = new FileReader(STOPWORD_FILE);
			stopwords = CharArraySet.unmodifiableSet(WordlistLoader.getWordSet(rd, Version.LUCENE_42));
		} catch (IOException e) {
			log.error("stopwords.txt cant find. inside stop words started", e);
			stopwords = StopFilter.makeStopSet(Version.LUCENE_42, STOP_WORDS, false);
		}
	}
	/**
	 * tokenize the src String. splite Chinese sentences to words use Lucene SmartCN component
	 * this component supported by ICTCLAS. the API link in lucene  <a href="http://lucene.apache.org/core/old_versioned_docs/versions/3_0_0/api/contrib-smartcn/">
	 * SmartCN API</a> 
	 *  
	 * @param src
	 * 		string before tonkenize
	 * @return
	 * 		string after tonkenize
	 * 
	 */
	public static String parseToString(String src) {
		StringReader sr = new StringReader(src);
		StringBuffer result = new StringBuffer();

		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_42);
		
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("", sr);

			while(ts.incrementToken()){
			  String aWord = ts.getAttribute(CharTermAttribute.class).toString();	
			  result.append(aWord + " ");
			}
		} catch (IOException e) {
			log.error("Tokenize the string faild, String: " + src, e);
			e.printStackTrace();
		} finally {
			if(ts != null) {
				try {
					ts.close();
				} catch (IOException e) {
					log.error("Close tokenStream faild! ", e);
					e.printStackTrace();
				}
			}
		}
        analyzer.close();
		return result.toString();
	}
	
	/**
	 * tokenize the src String. splite Chinese sentences to words use Lucene SmartCN component
	 * this component supported by ICTCLAS. the API link in lucene  <a href="http://lucene.apache.org/core/old_versioned_docs/versions/3_0_0/api/contrib-smartcn/">
	 * SmartCN API</a>
	 * 
	 * @param src
	 * 		string before tonkenize
	 * @return
	 * 		ArrayList after tokenize
	 * 
	 */
	public static ArrayList<String> parseToArray(String src) {
		StringReader sr = new StringReader(src);
		ArrayList<String> result = new ArrayList<String>();

		
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_42, stopwords);
		
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("", sr);
	
	//		 StopFilter sf = (StopFilter) ts

			while(ts.incrementToken()){
			  String aWord = ts.getAttribute(CharTermAttribute.class).toString();	
			  result.add(aWord);
			}
		} catch (IOException e) {
			log.error("Tokenize the string faild, String: " + src, e);
			e.printStackTrace();
		} finally {
			if(ts != null) {
				try {
					ts.close();
				} catch (IOException e) {
					log.error("Close tokenStream faild! ", e);
					e.printStackTrace();
				}
			}
		}
        analyzer.close();
		return result;
	}
}
