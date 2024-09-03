package com.swaglabs.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;

public class HomepageTest {
	LoginPage loginPage;
    HomePage homePage;
	private WebDriver driver;

    @BeforeMethod
    public void setUpTest() {
    	
       //LoginPage loginPage = new LoginPage(driver);

		// Initialize Page Objects
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        
        // Log in to the application as a part of setup
        loginPage.enterUsername("standard_user"); 
        loginPage.enterPassword("secret_sauce");  
        loginPage.clickLogin();
    }
    
    @Test
    public void testFilterOption() {
        // Test script for filtering products
        homePage.chooseFilterOption("Price (low to high)");
        // Add assertions here
    }
    
    @AfterMethod
    public void tearDownTest() {
        homePage.clickLogout();
    }

}
