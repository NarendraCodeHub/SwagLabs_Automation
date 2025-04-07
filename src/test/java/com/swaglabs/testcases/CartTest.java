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

	private LoginPage lp;
	private HomePage hp;
	private CartPage cp;

	public String userName = "standard_user";
	public String password = "secret_sauce";

	@BeforeMethod
	public void setupTest() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		cp = new CartPage(driver);

		lp.enterUsername(userName);
		lp.enterPassword(password);
		lp.clickLogin();
	}

	@AfterMethod
	public void closeTest() {
		hp.clickLogout();
		driver.manage().deleteAllCookies();
	}

	@Test
	public void verifyCartPageNavigation() {
		hp.clickCart();
		String cartURL = baseURL + "cart.html";
		String CurrentURL = driver.getCurrentUrl();

		Assert.assertEquals(cartURL, CurrentURL);
	}

	@Test
	public void verifyProductsInCart() {
		hp.clickAddToCartByProductName("Sauce Labs Backpack");
		hp.clickCart();
		String productName = hp.getProductName();
		Assert.assertEquals("Sauce Labs Backpack", productName);
	}

}
