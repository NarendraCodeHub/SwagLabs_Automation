package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	
	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
	private WebElement menuButton;
	
	@FindBy(id = "inventory_sidebar_link")
	private WebElement AllItem;
	
	@FindBy(id = "about_sidebar_link")
	private WebElement About;
	
	@FindBy (id = "logout_sidebar_link")
	private WebElement LogoutButton;
	
	@FindBy(id = "reset_sidebar_link")
	private WebElement ResetAppState;
	
	@FindBy(id = "react-burger-cross-btn")
	private WebElement menuCloseButton;
	
	@FindBy(xpath ="//select")
	private WebElement filterOption;
	
	public  HomePage(WebDriver d)
	{
		PageFactory.initElements(d, this);
	}
	
	//Method to Click All Item
	public void clickAllItem()
	{
		AllItem.click();
	}
	
	//Method to Click About 
	public void clickAbout() {
		About.click();
	}
	
	//Method to User Logout 
	public void logout() {
		menuButton.click();
		LogoutButton.click();
	}
	
	//Method to Click Reset App State 
	public void clickResetAppState() {
		ResetAppState.click();
	}
	
    // Method to close the menu
	public void clickmenuCloseButton() {
		menuCloseButton.click();
	}
	
	// Method to click Filter Option
	public void clickFilter() {
		filterOption.click();
	}

    // Method to choose filter option using switch-case
    public void chooseFilterOption(String filterType) {
        Select filterDropdown = new Select(filterOption);
        
        switch (filterType.toLowerCase()) {
            case "name (a to z)":
                filterDropdown.selectByVisibleText("Name (A to Z)");
                break;
            case "name (z to a)":
                filterDropdown.selectByVisibleText("Name (Z to A)");
                break;
            case "price (low to high)":
                filterDropdown.selectByVisibleText("Price (low to high)");
                break;
            case "price (high to low)":
                filterDropdown.selectByVisibleText("Price (high to low)");
                break;
            default:
                System.out.println("Invalid filter option");
        }
    }

	
	
}
