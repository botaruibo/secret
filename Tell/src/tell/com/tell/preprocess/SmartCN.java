package com.tell.preprocess;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartCN {
	final static Logger log = LoggerFactory.getLogger(SmartCN.class);

	public static String parse(String src) {
		StringReader sr = new StringReader(src);
		StringBuffer result = new StringBuffer();

		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_42);
		
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("", sr);

			while(ts.incrementToken()){
			  String aWord = ts.getAttribute(CharTermAttribute.class).toString();	
			  result.append(aWord + " ");
			//  System.out.println(aWord);
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
}
