package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
	private WebElement menuButton;
	
	@FindBy (id = "logout_sidebar_link")
	private WebElement LogoutButton;
	
	public  HomePage(WebDriver d)
	{
		PageFactory.initElements(d, this);
	}
	
	// User Logout 
	public void logout() {
		menuButton.click();
		LogoutButton.click();
	}
}
