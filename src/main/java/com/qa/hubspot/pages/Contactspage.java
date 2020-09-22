package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class Contactspage extends BasePage {
	private WebDriver driver;
	
	//1.ObjectRepository - OR Locators
	
	By CreateContactPrimary = By.xpath("//span[text()='Create contact']");
	By ContactHeader = By.xpath("//span[text()='Contacts']");
	By Email = By.xpath("//input[@data-field='email']");
	By FirstName = By.xpath("//input[@data-field='firstname']");
	By Lastname = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	By CreateContactSecondary = By.xpath("(//span[text()='Create contact'])[2]");
	
	By BackToContacts = By.xpath("(//*[text()='Contacts'])[2]");
	
	//2.create constructor for contacts page
	public Contactspage (WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}
	
	//3.page actions in the form of methods 
	
	public String getContactPageTitle() {
		return elementutil.waitForTitleToBePresent(Constants.CONTACTSPAGE_TITLE,10);
		
		
	}
	public String getContactPageHeader() {
		if(elementutil.doIsDisplayed(ContactHeader)) {
		return elementutil.doGetText(ContactHeader);
		}
		return null;
		
	}
	
	public void createContact(String email,String firstname,String lastname,String jobTitle) {
		
		elementutil.clickWhenReady(this.CreateContactPrimary, 10);
		
		elementutil.waitForElementToBeVisible(this.Email,10);
		elementutil.doSendKeys(this.Email,email );
		elementutil.doSendKeys(this.FirstName,firstname );
		elementutil.doSendKeys(this.Lastname,lastname );
		elementutil.waitForElementToBeVisible(this.jobTitle,5);
		elementutil.doSendKeys(this.jobTitle,jobTitle );
		
		elementutil.waitForElementToBeVisible(this.CreateContactSecondary, 10);
		elementutil.doClick(CreateContactSecondary);
		
		elementutil.waitForElementToBeVisible(this.BackToContacts, 10);
		elementutil.doActionsClick(BackToContacts);

		
	}
	
	

}
