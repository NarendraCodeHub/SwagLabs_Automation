package com.swaglabs.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;

	private static final Logger logger = LogManager.getLogger(CartPage.class);

	@FindBy(id = "continue-shopping")
	private WebElement continueShoppingButton;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	@FindBy(xpath = "//button[.='Remove']")
	private WebElement removeButton;

	@FindBy(xpath = "//div[@class='cart_item']")
	private List<WebElement> cartItems;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickContinueShoppingButton() {
		continueShoppingButton.click();
	}

	public void clickCheckoutButton() {
		checkoutButton.click();
	}

	public void clickRemoveButton() {
		removeButton.click();
	}

	public boolean isVisibleCartItem() {
		try {
			return !cartItems.isEmpty();
		} catch (Exception e) {
			logger.error("Error checking cart items: " + e.getMessage());
			return false;
		}
	}

}
