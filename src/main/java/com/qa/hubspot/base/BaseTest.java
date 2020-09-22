package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.hubspot.pages.LoginPage;

public class BaseTest {
	
	public Properties prop;
	public WebDriver driver;
	public BasePage basepage;
	public LoginPage loginpage;
	
	@BeforeTest
	public void loginSetup() {
		basepage = new BasePage();
		prop = basepage.init_properties();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
		
	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
