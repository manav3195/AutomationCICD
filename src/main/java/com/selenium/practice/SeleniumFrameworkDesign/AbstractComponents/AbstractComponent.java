package com.selenium.practice.SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.CartPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="button[routerlink*='cart']")
	WebElement cartCheckout;
	
	By orders =By.cssSelector("button[routerlink*='myorder']");

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	
	public void waitForElementToDisappear() throws InterruptedException {
	
		Thread.sleep(5000);
		
		/*
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));
		*/
	}
	
	public CartPage goToCART() {
		
		cartCheckout.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrders() {
		driver.findElement(orders).click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
