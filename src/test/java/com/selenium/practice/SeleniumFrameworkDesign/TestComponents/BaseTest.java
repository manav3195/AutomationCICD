package com.selenium.practice.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes.Name;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selenium.practice.SeleniumFrameworkDesign.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		
		Properties properties = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//selenium//practice//SeleniumFrameworkDesign//resources//GlobalData.properties");
		properties.load(fs);
		String browserName= System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser"); 
		
		if(browserName.toLowerCase().contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--start-maximized");
				
			}
			
			driver = new ChromeDriver(options);
			if (browserName.equalsIgnoreCase("chrome")) {
				driver.manage().window().maximize();
			}

			//driver.manage().window().setSize(new Dimension(1920, 1080));
		}
			
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			
			//firefox code
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			
			//edge code
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			
		}
		
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);
		 return System.getProperty("user.dir") + "//Reports//" + testCaseName +".png";
		
		
		
	}
	
	
	
		
	public List<HashMap<String, String>> readDataFromJSON(String filepath) throws IOException {
		
	String jsonContent=	FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data =mapper.readValue(jsonContent, new TypeReference <List<HashMap<String, String>>>(){
	});
	
	return data;
	}
}
	
	

