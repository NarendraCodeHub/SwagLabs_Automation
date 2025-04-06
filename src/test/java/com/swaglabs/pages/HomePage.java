package com.swaglabs.pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	private static final Logger logger = LogManager.getLogger(HomePage.class);

	@FindBy(xpath = "//div[.='Swag Labs' and @class='app_logo']")
	private WebElement titleHomePage;

	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
	private WebElement menuButton;

	@FindBy(id = "inventory_sidebar_link")
	private WebElement AllItem;

	@FindBy(id = "about_sidebar_link")
	private WebElement About;

	@FindBy(id = "logout_sidebar_link")
	private WebElement LogoutButton;

	@FindBy(id = "reset_sidebar_link")
	private WebElement ResetAppState;

	@FindBy(id = "react-burger-cross-btn")
	private WebElement menuCloseButton;

	@FindBy(className = "bm-menu")
	private WebElement menuContainer;

	@FindBy(xpath = "//select")
	private WebElement filterOption;

	@FindBy(xpath = "//div[.='Sauce Labs Backpack']")
	private WebElement item_Backpack;

	@FindBy(xpath = "//div[.='Sauce Labs Bike Light']")
	private WebElement item_BikeLight;

	@FindBy(xpath = "//div[.='Sauce Labs Bolt T-Shirt']")
	private WebElement item_BoltTShirt;

	@FindBy(xpath = "//div[.='Sauce Labs Fleece Jacket']")
	private WebElement item_FleeceJacket;

	@FindBy(xpath = "//div[.='Sauce Labs Onesie']")
	private WebElement item_Onesie;

	@FindBy(xpath = "//div[.='Test.allTheThings() T-Shirt (Red)']")
	private WebElement item_TShirt_Red;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartIcon;

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	@FindBy(xpath = "//a[.='Twitter']")
	private WebElement socialTwitter;

	@FindBy(xpath = "//a[.='Facebook']")
	private WebElement socialFacebook;

	@FindBy(xpath = "//a[.='LinkedIn']")
	private WebElement socialLinkedIn;

	@FindBy(xpath = "//div[@data-test='inventory-item-name']")
	private WebElement productName;

	@FindBy(xpath = "//div[@data-test='inventory-item-desc']")
	private WebElement productDescription;

	@FindBy(xpath = "//div[@data-test='inventory-item-price']")
	private WebElement productPrice;

	@FindBy(id = "back-to-products")
	private WebElement backtoProductButton;

	@FindBy(id = "remove-sauce-labs-backpack")
	private WebElement removeSauceLabsBackpack;

	@FindBy(id = "remove-sauce-labs-bike-light")
	private WebElement removeSauceLabsBikeLight;

	@FindBy(id = "remove-sauce-labs-bolt-t-shirt")
	private WebElement removeSauceLabsBoltTShirt;

	@FindBy(id = "remove-sauce-labs-fleece-jacket")
	private WebElement removeSauceLabsFleeceJacket;

	@FindBy(id = "remove-sauce-labs-onesie")
	private WebElement removeSauceLabsOnesie;

	@FindBy(id = "remove-test.allthethings()-t-shirt-(red)")
	private WebElement removeTestAllTheThingsTShirtRed;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	private List<WebElement> productList;

	@FindBy(xpath = "//button[.='Remove']")
	public WebElement removeBtn;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addToCart_Backpack;

	@FindBy(id = "add-to-cart-sauce-labs-bike-light")
	private WebElement addToCart_BikeLight;

	@FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
	private WebElement addToCart_BoltTShirt;

	@FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
	private WebElement addToCart_FleeceJacket;

	@FindBy(id = "add-to-cart-sauce-labs-onesie")
	private WebElement addToCart_Onesie;

	@FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
	private WebElement addToCart_TestAllTheThingsRed;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isTitleHomePageDisplayed() {
		return titleHomePage.isDisplayed();
	}

	public void clickMenuButton() {
		menuButton.click();
	}

	// Method to Click All Item
	public void clickAllItem() {
		try {
			AllItem.click();
			logger.info("Clicked on 'All Items' successfully.");
		} catch (Exception e) {
			logger.error("Failed to click on 'All Items'. Exception: " + e.getMessage());
		}
	}

	// Method to Click About
	public void clickAbout() {
		About.click();
	}

	// Method to User Logout
	public void clickLogout() {
		try {
			menuButton.click();
			LogoutButton.click();
			logger.info("User logged out successfully.");
		} catch (Exception e) {
			logger.error("Failed to log out. Exception: " + e.getMessage());
		}
	}

	// Method to Click Reset App State
	public void clickResetAppState() {
		ResetAppState.click();
	}

	public void clickmenuCloseButton() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", menuCloseButton);
			logger.info("Menu close button clicked successfully using JavaScript.");
		} catch (Exception e) {
			logger.error("Failed to click on menu close button. Exception: " + e.getMessage());
		}
	}

	// Method to click Filter Option
	public void clickFilter() {
		filterOption.click();
	}

	// Method to choose filter option using switch-case
	public void chooseFilterOption(String filterType) {
		try {
			Select filterDropdown = new Select(filterOption);

			switch (filterType.toLowerCase()) {
			case "name (a to z)":
				filterDropdown.selectByVisibleText("Name (A to Z)");
				logger.info("Filter applied: Name (A to Z)");
				break;
			case "name (z to a)":
				filterDropdown.selectByVisibleText("Name (Z to A)");
				logger.info("Filter applied: Name (Z to A)");
				break;
			case "price (low to high)":
				filterDropdown.selectByVisibleText("Price (low to high)");
				logger.info("Filter applied: Price (low to high)");
				break;
			case "price (high to low)":
				filterDropdown.selectByVisibleText("Price (high to low)");
				logger.info("Filter applied: Price (high to low)");
				break;
			default:
				logger.warn("Invalid filter option provided: " + filterType);
			}
		} catch (Exception e) {
			logger.error("Failed to apply filter. Exception: " + e.getMessage());
		}
	}

	// Verify all products are displayed on homepage
	public boolean areAllProductsDisplayed() {
		try {
			boolean allDisplayed = true;
			for (WebElement product : productList) {
				if (!product.isDisplayed()) {
					allDisplayed = false;
					logger.warn("Product not displayed: " + product.getText());
				}
			}
			return allDisplayed;
		} catch (Exception e) {
			logger.error("Error verifying product display. Exception: " + e.getMessage());
			return false;
		}
	}

	// All product
	public void clickProduct(String productName) {
		switch (productName.toLowerCase()) {
		case "sauce labs backpack":
			item_Backpack.click();
			break;
		case "sauce labs bike light":
			item_BikeLight.click();
			break;
		case "Sauce Labs Bolt T-Shirt":
			item_BoltTShirt.click();
			break;
		case "Sauce Labs Fleece Jacket":
			item_FleeceJacket.click();
			break;
		case "Sauce Labs Onesie":
			item_Onesie.click();
			break;
		case "Test.allTheThings() T-Shirt (Red)":
			item_TShirt_Red.click();
			break;
		default:
			System.out.println("Invalid product name");
		}
	}

	public void clickAddToCartByProductName(String productName) {
		switch (productName.toLowerCase()) {
		case "sauce labs backpack":
			addToCart_Backpack.click();
			break;
		case "sauce labs bike light":
			addToCart_BikeLight.click();
			break;
		case "sauce labs bolt t-shirt":
			addToCart_BoltTShirt.click();
			break;
		case "sauce labs fleece jacket":
			addToCart_FleeceJacket.click();
			break;
		case "sauce labs onesie":
			addToCart_Onesie.click();
			break;
		case "test.allthethings() t-shirt (red)":
			addToCart_TestAllTheThingsRed.click();
			break;
		default:
			throw new IllegalArgumentException("Product not recognized: " + productName);
		}
	}

	public void clickRemoveButtonByProductName(String productName) {
		switch (productName.trim().toLowerCase()) {
		case "sauce labs backpack":
			removeSauceLabsBackpack.click();
			break;
		case "sauce labs bike light":
			removeSauceLabsBikeLight.click();
			break;
		case "sauce labs bolt t-shirt":
			removeSauceLabsBoltTShirt.click();
			break;
		case "sauce labs fleece jacket":
			removeSauceLabsFleeceJacket.click();
			break;
		case "sauce labs onesie":
			removeSauceLabsOnesie.click();
			break;
		case "test.allthethings() t-shirt (red)":
			removeTestAllTheThingsTShirtRed.click();
			break;
		default:
			throw new IllegalArgumentException("Invalid product name: " + productName);
		}
	}

	// Cart
	public boolean isCartIconVisible() {
		return cartIcon.isDisplayed();
	}

	public boolean isCartIconClickable() {
		return cartIcon.isEnabled();
	}

	public void clickCart() {
		cartIcon.click();
	}

	public int getCartCount() {
		try {
			return Integer.parseInt(cartBadge.getText());
		} catch (NoSuchElementException | NumberFormatException e) {
			return 0;
		}
	}

	public boolean areMenuOptionsDisplayed() {
		try {
			clickMenuButton();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			List<WebElement> menuOptions = Arrays.asList(AllItem, About, LogoutButton, ResetAppState);

			wait.until(ExpectedConditions.visibilityOfAllElements(menuOptions));

			for (WebElement option : menuOptions) {
				if (!option.isDisplayed()) {
					logger.warn("Menu option not displayed: " + option.getText());
					return false;
				}
			}

			logger.info("All menu options are displayed correctly.");
			return true;
		} catch (Exception e) {
			logger.error("Error verifying menu options. Exception: " + e.getMessage());
			return false;
		}
	}

	// Social Link
	public void clickSocialTwitter() {
		socialTwitter.click();
	}

	public void clickSocialFacebook() {
		socialFacebook.click();
	}

	public void clickSocialLinkedIn() {
		socialLinkedIn.click();
	}

	public void clickBackToProductButton() {
		backtoProductButton.click();
	}

	public boolean isMenuVisible() {
		return menuContainer.isDisplayed();
	}

	public String getProductName() {
		return productName.getText();
	}

	public String getProductDescription() {
		return productDescription.getText();
	}

	public String getProductPrice() {
		return productPrice.getText();
	}

	public void clickRemoveBtn() {
		removeBtn.click();
	}

}
