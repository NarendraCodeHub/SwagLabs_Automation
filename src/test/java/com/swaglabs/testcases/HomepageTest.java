package com.swaglabs.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.HomePage;

public class HomepageTest extends BaseTest {
	
	LoginPage loginPage = new LoginPage(driver);
	HomePage homePage = new HomePage(driver);
	
	@BeforeMethod
	public void setupmethod() {
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickLogin();
	}
	
	@Test
	public void validateHomePage() {
		
	}
   
}
