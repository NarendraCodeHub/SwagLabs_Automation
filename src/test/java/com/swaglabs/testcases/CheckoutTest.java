package com.swaglabs.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

	private final String userName = "standard_user";
	private final String password = "secret_sauce";

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
	public void tearDown() {
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
		assertEquals(actualURL, expectedCheckoutURL, "Checkout Step One URL should match");
	}

	@Test
	public void verifyCheckoutInfoFieldsPresence() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		cartPage.clickCheckoutButton();

		assertTrue(checkoutPage.getFirstNameField().isDisplayed(), "First Name field should be displayed");
		assertTrue(checkoutPage.getLastNameField().isDisplayed(), "Last Name field should be displayed");
		assertTrue(checkoutPage.getPostalCodeField().isDisplayed(), "Postal Code field should be displayed");
	}

	@Test
	public void verifyEmptyFieldValidationCheckout() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		cartPage.clickCheckoutButton();

		checkoutPage.clickContinueButton();
		assertTrue(checkoutPage.isErrorMessageFirstnameDisplayed(), "First name error message should be displayed");
		checkoutPage.enterFirstName("Narendra");

		checkoutPage.clickContinueButton();
		assertTrue(checkoutPage.isErrorMessageLastnameDisplayed(), "Last name error message should be displayed");
		checkoutPage.enterLastName("Kumar");

		checkoutPage.clickContinueButton();
		assertTrue(checkoutPage.isErrorMessagePostalcodeDisplayed(), "Postal code error message should be displayed");
		checkoutPage.enterPostalCode("201301");

		checkoutPage.clickContinueButton();
	}

	@Test
	public void verifyValidCheckoutFormSubmission() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		cartPage.clickCheckoutButton();

		checkoutPage.enterFirstName("Narendra");
		checkoutPage.enterLastName("Kumar");
		checkoutPage.enterPostalCode("201301");

		checkoutPage.clickContinueButton();

		assertTrue(checkoutPage.isTitleCheckoutOverviewDisplayed(), "Checkout Overview should be displayed.");
	}
}
