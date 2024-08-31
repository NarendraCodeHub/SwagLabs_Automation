package com.swaglabs.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout {

	@FindBy(id = "first-name")
	private WebElement firstName;
	
	@FindBy(id = "last-name")
	private WebElement lastName;
	
	@FindBy(id = "postal-code")
	private WebElement postalCode;
	
	@FindBy(id = "cancel")
	private WebElement cancelButton;
	
	@FindBy(id = "continue")
	private WebElement continueButton;
	
	@FindBy(xpath = "//h3[.='Error: First Name is required']")
	private WebElement errorMessageFirstname;
	
	@FindBy(xpath = "//h3[.='Error: Last Name is required']")
	private WebElement errorMessageLastname;
	
	@FindBy(xpath = "//h3[.='Error: Postal Code is required']")
	private WebElement errorMessagePostalcode;
	
	//Checkout: Overview
	
	@FindBy(xpath = "//span[.='Checkout: Overview']")
	private WebElement titleCheckoutOverview;
	
	@FindBy(className = "cart_quantity")
	private WebElement cartQuantity;
	
	@FindBy(xpath = "//div[@class='inventory_item_name']")
	private List<WebElement> checkoutItemNames;
	
	@FindBy(xpath = "//div[@class='inventory_item_price' and @data-test='inventory-item-price']")
	private WebElement itemPrice;
	
	@FindBy(xpath = "//div[@class='summary_info_label' and @data-test='payment-info-label']")
	private WebElement PaymentInformation;
	
	@FindBy(xpath = "//div[@class='summary_value_label' and @data-test='payment-info-value']")
	private WebElement PaymentInformationValue;
	
	@FindBy(xpath = "//div[@class='summary_info_label' and @data-test='shipping-info-label']")
	private WebElement ShippingInformation;
	
	@FindBy(xpath = "//div[@class='summary_value_label' and @data-test='shipping-info-value']")
	private WebElement  ShippingInformationValue;
	
	@FindBy(xpath = "//div[@class='summary_info_label' and @data-test='total-info-label']")
	private WebElement itemSubtotal;
	
	@FindBy(xpath = "//div[@class='summary_tax_label' and @data-test='tax-label']")
	private WebElement Tax;
	
	@FindBy(xpath = "//div[@class='summary_total_label' and @data-test='total-label']")
	private WebElement Total;
	
	@FindBy(id = "cancel")
	private WebElement cancelCheckoutButton;
	
	@FindBy(id = "finish")
	private WebElement finishButton;

}
