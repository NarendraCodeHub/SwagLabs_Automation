package com.swaglabs.testcases;

import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.Login;

public class Testcase extends BaseTest {
	
	@Test
	void testcheck() throws InterruptedException {
		
		Login login_page = new Login(driver);
		
		login_page.username("standard_user");
		login_page.password("secret_sauce");
		login_page.login_Btn();
		
		Thread.sleep(2000);
		
	}

}
