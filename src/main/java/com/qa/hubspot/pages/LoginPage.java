package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	
	//1.ObjectRepo (OR) - By - Locators
	
	By Email = By.id("username");
	By Password = By.id("password");
	By LoginBtn = By.id("loginBtn");
	By SignUpLink = By.linkText("Sign up");
	
	// 2.create page class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}
	
	//3. page actions - in the form of methods
	
	@Step("Checking loginpageTitle test.")
	public String getLoginPageTitle() {
		return elementutil.waitForTitleToBePresent(Constants.LOGINPAGE_TITLE, 10);
		
	}
	
	@Step("Checking signuplink test.")
	public boolean checkSignUpLink() {
		return elementutil.doIsDisplayed(SignUpLink) ;
	}
	
	@Step("User login with username:{0} and password :{1}")
	public HomePage doLogin(String email,String password) {
		elementutil.waitForElementPresent(this.Email, 10);
		elementutil.doSendKeys(this.Email, email);
		elementutil.doSendKeys(this.Password, password);
		elementutil.waitForElementPresent(this.LoginBtn, 10);
		elementutil.doClick(LoginBtn);
		
		return new HomePage(driver);
		
	}
	
	

}
