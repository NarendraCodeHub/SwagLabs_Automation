package com.swaglabs.testcases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;

public class HomepageTest extends BaseTest {
	private static final Logger logger = LogManager.getLogger(HomepageTest.class);

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

	@AfterMethod
	public void closeTest() {
		hp.clickLogout();
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
	public void verifyMenuOptionsDisplayed() throws InterruptedException {
		Assert.assertTrue(hp.areMenuOptionsDisplayed(), "Menu options are not displayed correctly.");
		driver.wait(2000);
		hp.clickmenuCloseButton();
	}

	public void verifyAllItemMenuOption() {
		hp.clickMenuButton();
		hp.clickAllItem();

		String expectedURL = "https://www.saucedemo.com/inventory.html";
		String actualURL = driver.getCurrentUrl();

		if (!actualURL.equals(expectedURL)) {
			logger.error("Bug: Clicking 'All Items' does not navigate to the inventory page!");
		} else {
			logger.info("All Items menu option is working correctly.");
		}
	}

	@Test
	public void verifyAboutMenuOption() {
		// About Menu Option
		hp.clickMenuButton();
		hp.clickAbout();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement signBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Sign in']")));
		Assert.assertTrue(signBtn.isDisplayed(), "Sign-in button is not displayed on the page!");
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
	}

	@Test
	public void verifyLogoutMenuOption() {
		hp.clickLogout();
		Assert.assertTrue(lp.isUsernameVisible(), "UserName Text is not displayed on this page.");
	}

	@Test
	public void verifyResetAppStateMenuOption() {
		hp.clickMenuButton();
		hp.clickResetAppState();

		try {
			WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
			Assert.fail("Bug: Reset App State is not working! Cart badge is still visible.");
		} catch (NoSuchElementException e) {
			logger.info("Reset App State is working correctly.");
		}
	}
}
