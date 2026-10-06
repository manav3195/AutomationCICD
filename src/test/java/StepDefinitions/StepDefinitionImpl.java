package StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.practice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.CartPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.LandingPage;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce login Webpage")
	public void I_landed_on_Ecommerce_Webpage() throws IOException {
		
		landingPage = launchApplication();		
		
	}
	
	@Given("^Logged in username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password) {
		
		productCatalogue=landingPage.loginApplication(username, password);
		
	}
	
	@When("^I Add the product (.+) from CART$")
	public void I_add_the_productName_from_CART(String productName) throws InterruptedException {
		
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addToCART(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {
		
		cartPage=productCatalogue.goToCART();
		Boolean match = cartPage.verifyCartProductName(productName);
		Assert.assertTrue(match);
		checkoutPage= cartPage.goToCheckout();
		
	}
	
	@Then("Success message {string} should come")
	public void sucess_message_come(String string) {
		
		confirmationPage=checkoutPage.selectCountry("india");
		 String confirmMessage= confirmationPage.getMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		
	}
	
	@Then("Message {string} should come")
	public void Login_page_error_message(String string) {
		
		Assert.assertEquals(landingPage.getErrorMessage(),string);
	}
}
