package com.tell.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the main for test. run as a back-end thread
 * 
 * @author brui
 *
 */
public class Starter {
	final static Logger log = LoggerFactory.getLogger(Starter.class);

	public static void main(String[] arg) {
		Teller teller = new Teller();
		teller.init();
		log.info("--------------------Teller started--------------------");
	}
}
