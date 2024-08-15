package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement username;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;
	
	@FindBy (xpath = "//input[@id='login-button']")
	private WebElement login_Btn;
	
	public  Login(WebDriver d)
	{
		PageFactory.initElements(d, this);
	}
	
	public void username(String user)
	{
		username.sendKeys(user);
	}
	
	public void password(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void login_Btn()
	{
		login_Btn.click();
	}

}
