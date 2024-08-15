package com.swaglabs.testcases;

import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;

public class Testcase extends BaseTest {
	
	@Test
	void testcheck() throws InterruptedException {
		BaseTest obj = new BaseTest();
		Thread.sleep(2000);
	}

}
