package com.swaglabs.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.CheckoutPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;

public class CheckoutTest extends BaseTest {

	private LoginPage loginPage;
	private HomePage homePage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;

	public String userName = "standard_user";
	public String password = "secret_sauce";

	@BeforeMethod
	public void setupTest() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);

		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
	}

	@AfterMethod
	public void closeTest() {
		homePage.clickLogout();
		driver.manage().deleteAllCookies();
	}

	@Test
	public void verifyCheckoutStepOneNavigation() {
		String expectedCheckoutURL = baseURL + "checkout-step-one.html";

		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		cartPage.clickCheckoutButton();

		String actualURL = driver.getCurrentUrl();
		assertEquals(expectedCheckoutURL, actualURL, "Checkout Step One URL should match");
	}

}
