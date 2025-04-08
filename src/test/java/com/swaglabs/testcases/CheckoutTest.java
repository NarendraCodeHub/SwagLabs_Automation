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

	@Test
	public void verifyCheckoutInfoFieldsPresence() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		cartPage.clickCheckoutButton();

		assertTrue(checkoutPage.firstName.isDisplayed(), "First Name field should be displayed");
		assertTrue(checkoutPage.lastName.isDisplayed(), "Last Name field should be displayed");
		assertTrue(checkoutPage.postalCode.isDisplayed(), "Postal Code field should be displayed");
	}

	@Test
	public void verifyEmptyFieldValidationCheckout() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		cartPage.clickCheckoutButton();

		checkoutPage.clickcontinueButton();
		assertTrue(checkoutPage.isErrorMessageFirstnameDisplayed(), "First name error message should be displayed");
		checkoutPage.enterfirstName("Narendra");

		checkoutPage.clickcontinueButton();
		assertTrue(checkoutPage.isErrorMessageLastnameDisplayed(), "Last name error message should be displayed");
		checkoutPage.enterlastName("Kumar");

		checkoutPage.clickcontinueButton();
		assertTrue(checkoutPage.isErrorMessagePostalcodeDisplayed(), "Postal code error message should be displayed");
		checkoutPage.enterpostalCode("201301");

		checkoutPage.clickcontinueButton();
	}

}
