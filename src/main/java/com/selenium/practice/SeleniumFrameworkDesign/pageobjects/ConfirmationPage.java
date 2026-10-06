package com.selenium.practice.SeleniumFrameworkDesign.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.practice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	
	public String getMessage() {
		return confirmMessage.getText();
		
	}
}
