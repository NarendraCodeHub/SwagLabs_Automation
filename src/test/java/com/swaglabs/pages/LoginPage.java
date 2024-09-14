package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPage{
	
    private WebDriver d;

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	
	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement username;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;
	
	@FindBy (xpath = "//input[@id='login-button']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//h3[.='Epic sadface: Username and password do not match any user in this service']") 
    WebElement errorMessage;

    @FindBy(xpath = "//div[.='Swag Labs' and @class='app_logo']") //successful login
    WebElement inventoryList;

	
	public  LoginPage(WebDriver driver)
	{
		this.d = driver;
        PageFactory.initElements(d, this); // Ensure PageFactory is initialized
    }
	
	
	public void enterUsername(String user)
	{
		username.sendKeys(user);
	}
	
	public void enterPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		loginButton.click();
	}

    public boolean isLoginSuccessful() {
        try {
            return inventoryList.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("Login was not successful, inventory list not found.", e);
            return false;
        }
    }

    public boolean isLoginUnsuccessful() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("Login error message not found.", e);
            return false;
        }
    }
}
