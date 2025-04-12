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

	@Test(enabled = false)
	public void verifyProductConsistencyCheckout() {
		/*
		 * String productName = "Sauce Labs Backpack";
		 * 
		 * // Step 1: Get product details from Home Page String expectedProductName =
		 * homePage.getProductName(productName); String expectedProductPrice =
		 * homePage.getProductPrice(productName);
		 * 
		 * // Step 2: Add to cart and navigate to checkout
		 * homePage.clickAddToCartByProductName(productName); homePage.clickCart();
		 * cartPage.clickCheckoutButton();
		 * 
		 * // Step 3: Enter checkout info checkoutPage.enterFirstName("Narendra");
		 * checkoutPage.enterLastName("Kumar"); checkoutPage.enterPostalCode("201301");
		 * checkoutPage.clickContinueButton();
		 * 
		 * // Step 4: Validate product name and price in Checkout Overview String
		 * actualProductName = checkoutPage.getCheckoutItemNames(); String
		 * actualProductPrice = checkoutPage.getItemPrice();
		 * 
		 * assertTrue(actualProductName.contains(expectedProductName),
		 * "Product name should remain consistent throughout checkout");
		 * assertEquals(actualProductPrice, expectedProductPrice,
		 * "Product price should remain consistent throughout checkout");
		 */
	}

	@Test
	public void verifyOrderSummaryDetails() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		cartPage.clickCheckoutButton();

		checkoutPage.enterFirstName("Narendra");
		checkoutPage.enterLastName("Kumar");
		checkoutPage.enterPostalCode("201301");
		checkoutPage.clickContinueButton();

		// Assertions for item summary details
		assertTrue(checkoutPage.getCheckoutItemNames().contains("Sauce Labs Backpack"),
				"Item name should be displayed correctly");
		assertEquals(checkoutPage.getCartQuantity(), "1", "Cart quantity should be 1");
		assertTrue(checkoutPage.getItemPrice().startsWith("$"), "Item price should be displayed with $");

		// Subtotal, Payment Info, Shipping Info
		assertTrue(checkoutPage.getItemSubtotal().startsWith("Item total: $"),
				"Item subtotal should start with 'Item total:'");
		assertEquals(checkoutPage.getPaymentInformation(), "Payment Information:",
				"Payment label should be 'Payment Information'");
		assertTrue(checkoutPage.getPaymentInformationValue().length() > 0, "Payment value should not be empty");

		assertEquals(checkoutPage.getShippingInformation(), "Shipping Information:",
				"Shipping label should be 'Shipping Information'");
		assertTrue(checkoutPage.getShippingInformationValue().length() > 0, "Shipping value should not be empty");

		// Tax and Total
		assertTrue(checkoutPage.getTax().startsWith("Tax:"), "Tax label should start with 'Tax:'");
		assertTrue(checkoutPage.getTotal().startsWith("Total:"), "Total label should start with 'Total:'");
	}

}
