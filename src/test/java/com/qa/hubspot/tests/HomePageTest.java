package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest extends BaseTest{
	
	HomePage homepage;
	
	@BeforeClass
	public void homepageSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=2,description="verify Homepage title test")
	public void verifyHomePageTitleTest() {
		String title = homepage.getHomePageTitle();
		System.out.println("HomePage title is :"+title);
		Assert.assertEquals(title, Constants.HOMEPAGE_TITLE,"HomePage Title is not matched...");
		
		
	}
	@Test(priority=1,description="Verify homepage header")
	public void verifyHomePageHeaderTest() {
		String header = homepage.verifyHomePageHeader();
		System.out.println("Homepage header is :"+header);
		Assert.assertEquals(header, Constants.HOMEPAGE_HEADER,"Homepage header is not correct..");
		
	}
	
	
	
	

}
