package com.tell.preprocess;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class SmartCNTest {

	@Test
	public void test() throws IOException {
		String s = "外企也就那么会儿事。";

		SmartCN sc = new SmartCN();
//		System.out.println(sc.parse(s));
		assertTrue(sc.parse(s).contains("外企 也 就 那么 会儿 事"));
	}
}
