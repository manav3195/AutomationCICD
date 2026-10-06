package com.selenium.practice.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.practice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By orders_list = By.xpath("//tr//td[2]");
	
	public Boolean verifyOrderName(String productName) {
		
		List<WebElement> orders_names  =driver.findElements(orders_list);
		Boolean match=orders_names.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		
		
	}
	
	

}
