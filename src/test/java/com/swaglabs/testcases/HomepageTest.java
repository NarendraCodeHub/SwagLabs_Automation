package com.swaglabs.testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

	@Test
	public void verifyCloseButtonMenuOption() {

		hp.clickMenuButton();

		hp.clickmenuCloseButton();

		Assert.assertFalse(!hp.isMenuVisible(), "Menu should be closed after clicking the Close button.");
	}

	@Test
	public void verifyFilter_ZtoA() {

		hp.chooseFilterOption("Name (Z to A)");

		List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item_name"));

		List<String> actualProductNames = new ArrayList<>();
		for (WebElement product : products) {
			actualProductNames.add(product.getText());
		}

		List<String> expectedProductNames = new ArrayList<>(actualProductNames);
		expectedProductNames.sort(Comparator.reverseOrder());

		Assert.assertEquals(actualProductNames, expectedProductNames,
				"Products are not sorted correctly in Z to A order.");
	}

	@Test
	public void verifyFilter_AtoZ() {

		hp.chooseFilterOption("Name (A to Z)");

		List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item_name"));

		List<String> actualProductNames = new ArrayList<>();
		for (WebElement product : products) {
			actualProductNames.add(product.getText());
		}

		List<String> expectedProductNames = new ArrayList<>(actualProductNames);
		expectedProductNames.sort(Comparator.naturalOrder());

		Assert.assertEquals(actualProductNames, expectedProductNames,
				"Products are not sorted correctly in A to Z order.");
	}

	@Test
	public void verifyFilter_LowToHigh() {

		hp.chooseFilterOption("Price (low to high)");

		List<WebElement> priceElements = driver.findElements(By.cssSelector(".inventory_item_price"));

		List<Double> actualPrices = new ArrayList<>();
		for (WebElement price : priceElements) {
			String priceText = price.getText().replace("$", "").trim();
			actualPrices.add(Double.parseDouble(priceText));
		}

		List<Double> expectedPrices = new ArrayList<>(actualPrices);
		expectedPrices.sort(Comparator.naturalOrder());

		Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by price (low to high).");
	}

	@Test
	public void verifyFilter_HighToLow() {

		hp.chooseFilterOption("Price (high to low)");

		List<WebElement> priceElements = driver.findElements(By.cssSelector(".inventory_item_price"));

		List<Double> actualPrices = new ArrayList<>();
		for (WebElement price : priceElements) {
			String priceText = price.getText().replace("$", "").trim();
			actualPrices.add(Double.parseDouble(priceText));
		}

		List<Double> expectedPrices = new ArrayList<>(actualPrices);
		expectedPrices.sort(Comparator.reverseOrder());

		Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by Price (high to low).");
	}

}
