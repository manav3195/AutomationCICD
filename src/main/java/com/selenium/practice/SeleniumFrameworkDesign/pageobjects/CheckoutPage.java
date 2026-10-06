package com.selenium.practice.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.selenium.practice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement dropdown_select;
	
	@FindBy(css=".action__submit")
	WebElement place_order;
	
	
	public ConfirmationPage selectCountry(String countryName) {
		
		Actions actions = new Actions(driver);
		actions.sendKeys(Country,"india").build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		dropdown_select.click();
		place_order.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
		
	}
	

}
