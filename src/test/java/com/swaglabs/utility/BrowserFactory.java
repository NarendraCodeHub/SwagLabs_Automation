package com.swaglabs.utility;

import java.util.concurrent.TimeUnit;

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
		if (browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(op);
		}
		// FireFox Browser
		else if (browserName.equals("Firefox")) {
		    WebDriverManager.firefoxdriver().setup();
		    FirefoxOptions options = new FirefoxOptions();
		    driver = new FirefoxDriver(options);
		} 
		//Edge Browser 
		else if (browserName.equals("Edge")) {
		    WebDriverManager.edgedriver().setup();
		    EdgeOptions options = new EdgeOptions();
		    driver = new EdgeDriver(options);
		}
		else {
			System.out.println("We don't support this browser!!!");
		}
		
		//Maximize the Browser
		driver.manage().window().maximize();
		//Enter the URL
		driver.get(weburl);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	    waitForPageLoad(driver);
	    //Return Driver 
		return driver;
	}
	
	
//	public static void waitForPageLoad(WebDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, 200);
//        wait.until((ExpectedCondition<Boolean>) webDriver ->
//                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//    }
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
}
