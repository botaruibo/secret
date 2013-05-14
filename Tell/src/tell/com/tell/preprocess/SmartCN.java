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

	/**
	 * tokenize the src String. splite Chinese sentences to words use Lucene SmartCN component
	 * this component supported by ICTCLAS. the API link in lucene  <a href="http://lucene.apache.org/core/old_versioned_docs/versions/3_0_0/api/contrib-smartcn/">
	 * SmartCN API</a> 
	 *  
	 * @param src
	 * 		string efore tonkenize
	 * @return
	 * 		string after tonkenize
	 * 
	 */
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
