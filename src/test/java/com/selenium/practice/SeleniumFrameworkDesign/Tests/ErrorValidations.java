package com.selenium.practice.SeleniumFrameworkDesign.Tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import com.selenium.practice.SeleniumFrameworkDesign.TestComponents.Retry;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.CartPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.LandingPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;


public class ErrorValidations extends BaseTest {
	
	
	@Test(groups = {"ErrorValidations"},retryAnalyzer = Retry.class)
	public void ErrorValidations() {
		
		landingPage.loginApplication("manavchaudhary3195@gmail.com", "abc@123");
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
		System.out.println("-----");
		
		
	}
	
	@Test(groups= {"ErrorValidations"})
	public void ProductErrorValidation() throws InterruptedException {
		
		String productName ="ZARA COAT 3";
		String countyName="india";
				
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ProductCatalogue productCatalogue=landingPage.loginApplication("manavchaudhary3195@gmail.com", "Manav@123");
		productCatalogue.addToCART(productName);
		CartPage cartPage=productCatalogue.goToCART();
		Boolean match=cartPage.verifyCartProductName("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
	}
	

}
