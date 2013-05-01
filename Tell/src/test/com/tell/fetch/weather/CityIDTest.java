package com.tell.fetch.weather;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CityIDTest {

	@Test
	public void test() {
		CityID.init();
		assertTrue(CityID.get("杭州") != null);
	}
}
