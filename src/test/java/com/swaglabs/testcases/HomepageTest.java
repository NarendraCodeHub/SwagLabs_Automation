package com.swaglabs.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

	@Test
	public void verifyAllProductsAreDisplayed() {
		Assert.assertTrue(hp.areAllProductsDisplayed(), "Not all products are displayed on the homepage.");

	}

	@Test
	public void verifyMenuOptionsDisplayed() {
		Assert.assertTrue(hp.areMenuOptionsDisplayed(), "Menu options are not displayed correctly.");

	}

	@Test
	public void verifyAllItemMenuOption() {
		hp.clickMenuButton();
		hp.clickAllItem();
	}

	@Test
	public void verifyAboutMenuOption() {
		// About Menu Option
		hp.clickMenuButton();
		hp.clickAbout();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement signBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Sign in']")));
		Assert.assertTrue(signBtn.isDisplayed(), "Sign-in button is not displayed on the page!");
	}

	@Test
	public void verifyLogoutMenuOption() {
		hp.clickLogout();
		Assert.assertTrue(lp.isUsernameVisible(), "UserName Text is not displayed on this page.");
	}
}
