package com.selenium.practice.SeleniumFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	
	public static void main(String[] args) {
		
		String productName ="ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
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
		
		WebElement dropdown_select = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		dropdown_select.click();
		
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
		placeOrder.click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
