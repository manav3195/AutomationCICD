package com.selenium.practice.SeleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.practice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.CartPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.LandingPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.OrderPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	//String productName ="ZARA COAT 3";
	
	@Test(dataProvider = "jsonData")
	public void Submitorder(HashMap<String, String> input) throws IOException, InterruptedException {
		
	
		String countryName="india";
				
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		
		ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"), input.get("password"));
		//ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addToCART(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCART();
		//CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyCartProductName(input.get("productName"));
		Assert.assertTrue(match);
		System.out.println("-------");
		CheckoutPage checkoutPage= cartPage.goToCheckout();
		 ConfirmationPage confirmationPage=checkoutPage.selectCountry(countryName);
		 String confirmMessage= confirmationPage.getMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 driver.close();
		 System.out.println("-----");
		
		
		/*
		WebElement email = driver.findElement(By.id("userEmail"));
		email.sendKeys("manavchaudhary3195@gmail.com");
		
		WebElement pass = driver.findElement(By.id("userPassword"));
		pass.sendKeys("Manav@123");
		
		WebElement login = driver.findElement(By.id("login"));
		login.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		WebElement cart_button = driver.findElement(By.cssSelector("button[routerlink*='cart']"));
		cart_button.click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
		checkout.click();
		
		WebElement selectCountry = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		Actions actions = new Actions(driver);
		actions.sendKeys(selectCountry,"india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		WebElement dropdown_select = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')][2]"));
		dropdown_select.click();
		
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
		placeOrder.click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		*/
		
	}
	
	
	@Test(dependsOnMethods = {"Submitorder"})
	public void OrderHistoryTest() {
		String productName ="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("manavchaudhary3195@gmail.com", "Manav@123");
		OrderPage orderPage=productCatalogue.goToOrders();
	    Assert.assertTrue(orderPage.verifyOrderName(productName));
		
		
		
	}
	
	@DataProvider
	public Object[][] getData() {
		
		return new Object[][] { {"manavchaudhary3195@gmail.com","Manav@123"},{"releulexefeu-1678@yopmail.com","Manav@123"} };
		
		
	}
	
	
	
	
	@DataProvider(name="hashMapData")
	public  Object[][] getDataHashMap() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "manavchaudhary3195@gmail.com");
		map.put("password", "Manav@123");
		map.put("productName", "ZARA COAT 3");
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "releulexefeu-1678@yopmail.com");
		map1.put("password", "Manav@123");
		map1.put("productName", "ZARA COAT 3");
		
		return new Object[][] { {map},{map1} };
	}
	
	@DataProvider(name="jsonData")
	public Object[][] getJsonData() throws IOException {
		
	List<HashMap<String, String>> data	=readDataFromJSON(System.getProperty("user.dir") + "//src//test//java//com//selenium//practice//SeleniumFrameworkDesign//Data//PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
		
	}
	

}
