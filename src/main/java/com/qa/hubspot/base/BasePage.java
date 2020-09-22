package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.Optionsmanager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	WebDriver driver;
	public Properties prop;
	public ElementUtil elementutil;
	public Optionsmanager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();	
	}
	
	
	/**
	 * this method is used to initilize webdriver and launching browser
	 * @param prop
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName = null;
		
		if(System.getProperty("browser") == null) {
			browserName = prop.getProperty("browser");
		} else {
			browserName = System.getProperty("browser");
		}
		
		System.out.println("Running on ----->"+ browserName  + " browser ");
		
		optionsManager = new Optionsmanager(prop);
		
		//String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		} else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			tlDriver.set(new InternetExplorerDriver());
			
		} 
		else {
			System.out.println("please enter valid browser name ---->"+ browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
	}
	/**
	 * this method is used to read the properties from config.properties file
	 * @return
	 */
	
	public Properties init_properties() {
		
		prop = new Properties();
		String path = null;
		String env = null;
		
		try {
			env = System.getProperty("env");
			System.out.println("Enviornment is ---->"+env);
			
			if(env == null) {
				path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
			}else {
				
				switch(env) {
				
				case "qa" :
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
					break;
					
				case "stg" :
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\stg.config.properties";
					break;
					
				case "dev" :
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\dev.config.properties";
					break;
					
					default :
						System.out.println("Please enter valid enviornment----->"+env);
						break;
					
				}
			}
			
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * this method is used to take the screenshot
	 * @return
	 */
	public String getScreenshot() {
		
		File source = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}

}
