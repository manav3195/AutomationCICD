package com.selenium.practice.SeleniumFrameworkDesign.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportStandaloneTest {
	
	ExtentReports extentReports;
	
	@BeforeTest
	public void config() {
		
		String path = System.getProperty("user.dir") + "//Reports//" +"index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Extent Reports");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester", "Manav");
		
		
	}

	
	@Test
	public void initialDemo() {
		
		ExtentTest test =extentReports.createTest("Initial Demo Test case");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		driver.close();
		test.fail("Results do not match");
		extentReports.flush();
		
		
	}
	
}
