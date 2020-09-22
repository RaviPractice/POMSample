package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestAllureListener.class)

@Epic("TC - 101 : Checking the login page with functionalties..")
@Story("LoginPage Title,signup and logincredentials.")

public class LoginPageTest extends BaseTest {
	
	
	@Test(priority=2,description="verify loginpage title test")
	@Description("verify loginpage title test")
	@Severity(SeverityLevel.NORMAL)
	
	public void verifyLoginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		System.out.println("LoginPage title is :"+title);
		Assert.assertEquals(title, Constants.LOGINPAGE_TITLE,"LoginPage Title is not matched...");
		
		
	}
	
	@Test(priority=1,description="verify signup link test")
	@Description("verify loginpage signup link test")
	@Severity(SeverityLevel.CRITICAL)
	
	public void verifySignUpLinkTest() {
		boolean flag = loginpage.checkSignUpLink();
		Assert.assertTrue(flag);
		
	}
	
	@Test(priority=3,description="Verify LoginPage Test")
	@Description("verify loginpage login test")
	@Severity(SeverityLevel.BLOCKER)
	
	public void loginPageTest() {
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	

}
