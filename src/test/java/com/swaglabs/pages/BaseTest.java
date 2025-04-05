package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.swaglabs.utility.BrowserFactory;
import com.swaglabs.utility.ConfigDataProvider;

public class BaseTest {

	/**
	 * Global Variable
	 */
	public WebDriver driver;
	public ConfigDataProvider config = new ConfigDataProvider();

	public String baseURL;
	public String browser;

	@BeforeClass
	public void setup() {
		browser = config.getBrowser();
		baseURL = config.getStagingUrl();

		// Debugging Logs
		System.out.println("Browser: " + browser);
		System.out.println("URL: " + baseURL);

		driver = BrowserFactory.startApplication(driver, browser, baseURL);

		if (driver != null) {
			System.out.println("Driver initialized successfully.");
		} else {
			System.out.println("Driver initialization failed.");
		}
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			BrowserFactory.quitBrowser(driver);
		}
	}
}
