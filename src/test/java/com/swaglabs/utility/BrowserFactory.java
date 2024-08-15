package com.swaglabs.utility;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	public static WebDriver startApplication(WebDriver driver, String browserName, String weburl) {

		//Manage specific Browser - Chrome
		if (browserName.equalsIgnoreCase("Chrome")) {
		    WebDriverManager.chromedriver().setup();
		    ChromeOptions op = new ChromeOptions();
		    op.addArguments("--remote-allow-origins=*");
		    driver = new ChromeDriver(op);
		} else if (browserName.equalsIgnoreCase("Firefox")) {
		    WebDriverManager.firefoxdriver().setup();
		    FirefoxOptions options = new FirefoxOptions();
		    driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("Edge")) {
		    WebDriverManager.edgedriver().setup();
		    EdgeOptions options = new EdgeOptions();
		    driver = new EdgeDriver(options);
		} else {
		    System.out.println("We don't support this browser: " + browserName);
		}

		
		//Maximize the Browser
		driver.manage().window().maximize();
		//Enter the URL
		driver.get(weburl);
        // Wait for the page to fully load
        waitForPageLoad(driver);
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // Return Driver 
		return driver;
	}
	
	
	public static void waitForPageLoad(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
	    wait.until((ExpectedCondition<Boolean>) webDriver ->
	            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
}
