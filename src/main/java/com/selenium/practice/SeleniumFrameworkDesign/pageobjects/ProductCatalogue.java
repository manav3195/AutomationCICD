package com.selenium.practice.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.practice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	By productsBy = By.cssSelector(".mb-3");
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By addCART =By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
	WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	return prod;
		
	}
	
	public void addToCART(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addCART).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear();
		
	}
	
	
	
	
	
	}

