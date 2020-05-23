package com.nics.base;

import java.io.File;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.javascript.host.html.Enumerator;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Configuration {

	private WebDriver driver;
	public ExtentReports extent;
	public ExtentTest reporter;
	

	public WebDriver LoadBrowser(String browser) {
		DesiredCapabilities capabilities;
		String driverpath = System.getProperty("user.dir") + "\\Drivers";
		switch (browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", driverpath + "\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case "Mozilla":
			System.setProperty("webdriver.gecko.driver", driverpath + "\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			
			System.setProperty("webdriver.chrome.driver", driverpath + "\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		}
		;
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	public ExtentTest loadReport(ExtentReports extent, String testname) {
		reporter = extent.startTest(testname);
		return reporter;
	}

	public ExtentReports getExtentReport() {
		String filename = System.getProperty("user.dir") + "\\Reports" +"TestRun_"+ System.currentTimeMillis() + ".html";
		extent = new ExtentReports(filename);
		String filename_config = System.getProperty("user.dir") + "\\drivers\\" + "extent-config.xml";
		this.extent.loadConfig(new File(filename_config));
		return extent;
	}
	
	

}
