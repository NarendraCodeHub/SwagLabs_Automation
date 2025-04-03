package com.swaglabs.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;

public class HomepageTest extends BaseTest {

	private LoginPage lp;
	private HomePage hp;

	public String userName = "standard_user";
	public String password = "secret_sauce";

	@BeforeMethod
	public void setupTest() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);

		lp.enterUsername(userName);
		lp.enterPassword(password);
		lp.clickLogin();
	}

	@Test
	public void isHomepageDisplayed() {
		if (hp.isTitleHomePageDisplayed()) {
			System.out.println("HomePage is Displayed !!!");
		} else {
			System.out.println("HomePage Not Displayed !!!");
		}
	}

}
