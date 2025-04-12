package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.swaglabs.utility.BrowserFactory;
import com.swaglabs.utility.ConfigDataProvider;

public class BaseTest {

	protected WebDriver driver;
	protected ConfigDataProvider config;
	protected String baseURL;
	protected String browser;

	@BeforeClass
	public void setUp() {
		// Initialize config provider
		config = new ConfigDataProvider();

		// Get values from config.properties
		browser = config.getBrowser();
		baseURL = config.getStagingUrl();

		System.out.println("[INFO] Starting browser: " + browser);
		System.out.println("[INFO] Navigating to URL: " + baseURL);

		// Start browser session
		driver = BrowserFactory.startApplication(driver, browser, baseURL);

		if (driver != null) {
			System.out.println("[INFO] WebDriver initialized successfully.");
		} else {
			System.err.println("[ERROR] Failed to initialize WebDriver.");
			throw new RuntimeException("WebDriver not initialized. Test aborted.");
		}
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			BrowserFactory.quitBrowser(driver);
			System.out.println("[INFO] Browser closed successfully.");
		}
	}
}
