package com.selenium.practice.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.practice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement pass;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="div[class*='flyInOut']")
	WebElement error_message;
	
	public String getErrorMessage() {
		
		waitForElementToAppear(error_message);
		 return error_message.getText();
		
	}
	
	public ProductCatalogue loginApplication(String username,String password) {
		
		email.sendKeys(username);
		pass.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}	
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	}

