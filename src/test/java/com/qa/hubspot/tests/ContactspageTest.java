package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.Contactspage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactspageTest extends BaseTest {
	
	HomePage homepage;
	Contactspage contactspage;
	
	
	@BeforeClass
	public void doContactsPageSetup() {
		homepage    = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactspage = homepage.gotoContactsPage();
			
	}
	
	@Test(priority=2,description="verify Contactspage title test")
	public void verifyContactsPageTitleTest() {
		String title = contactspage.getContactPageTitle();
		System.out.println("ContactsPage title is :"+title);
		Assert.assertEquals(title, Constants.CONTACTSPAGE_TITLE,"ContactsPage Title is not matched...");
		
		
	}
	@Test(priority=1,description="Verify contactspage header")
	public void verifycontactsPageHeaderTest() {
		String header = contactspage.getContactPageHeader();
		System.out.println("contactspage header is :"+header);
		Assert.assertEquals(header, Constants.CONTACTSPAGE_HEADER,"Homepage header is not correct..");
		
	}
	@DataProvider
	public Object[][] createContact() {
		Object[][] data = ExcelUtil.getData(Constants.TESTDATA_SHEET_NAME);
		return data;
		
	}
	
	@Test(priority=3,description="Create ContactPage test",dataProvider="createContact")
	public void createContactsPageTest(String email,String firstname,String lastname,String jobtitle) {
		contactspage.createContact(email,firstname,lastname,jobtitle);
		
	}

}
