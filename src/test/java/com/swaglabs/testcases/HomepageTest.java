package com.swaglabs.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;

public class HomepageTest extends BaseTest {

	private HomePage homePage;

	public WebDriver driver;

	LoginPage lp = new LoginPage(driver);
	HomePage hp = new HomePage(driver);

	public String userName = "standard_user";
	public String password = "secret_sauce";

	@Test
	public void isHomepageDisplayed() {
		if (hp.isTitleHomePageDisplayed()) {
			System.out.println("HomePage is Displyed !!!");
		} else {
			System.out.println("HomePage Not Displayed !!!");
		}
	}

}
