package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;

public class CartTest extends BaseTest {

	private LoginPage loginPage;
	private HomePage homePage;
	private CartPage cartPage;

	public String userName = "standard_user";
	public String password = "secret_sauce";

	@BeforeMethod
	public void setupTest() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		cartPage = new CartPage(driver);

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
	public void verifyCartPageNavigation() {
		homePage.clickCart();
		String cartURL = baseURL + "cart.html";
		String CurrentURL = driver.getCurrentUrl();

		Assert.assertEquals(cartURL, CurrentURL);
	}

	@Test
	public void verifyProductsInCart() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		String productName = homePage.getProductName();
		Assert.assertEquals("Sauce Labs Backpack", productName);
	}

	@Test
	public void verifyCartProductDetails() {
		homePage.clickAddToCartByProductName("Sauce Labs Backpack");
		homePage.clickCart();
		String actualProductName = "Sauce Labs Backpack";
		String actualProductDesp = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
		String actualProductPrice = "$" + "29.99";

		String productName = homePage.getProductName();
		String productDescription = homePage.getProductDescription();
		String productPrice = homePage.getProductPrice();

		Assert.assertEquals(actualProductName, productName);
		Assert.assertEquals(actualProductDesp, productDescription);
		Assert.assertEquals(actualProductPrice, productPrice);

	}

	@Test
	public void verifyRemoveProductFromCart() {
		String productName = "Sauce Labs Backpack";
		homePage.clickAddToCartByProductName(productName);
		homePage.clickCart();
		cartPage.removeProductByName(productName);
		cartPage.verifyProductIsRemoved(productName);
	}

}
