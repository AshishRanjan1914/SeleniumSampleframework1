package com.nics.base;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;


public class BaseTest {

	public  WebDriver driver;
	public  ExtentReports extent;
	public ExtentTest reporter; 
	public  Properties property;
	public String driverpath;
	String filename;
	public String imagepath = System.getProperty("user.dir")+"\\Screenshot";
   	public String imagefilename;
	public  String url;
	public  String env;
	

	

	

	public void loadConfiguration() throws IOException {
		
		Configuration config= new Configuration();
		extent = config.getExtentReport();
		
		
	}
	
	
	

	public void generaretReport(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				
			} else if (result.getStatus() == ITestResult.FAILURE) {
				
				reporter.log(LogStatus.FAIL, reporter.addScreenCapture(getscreenshot()));
			} else if (result.getStatus() == ITestResult.SKIP) {
				reporter.log(LogStatus.SKIP, "Test Script skipped");
			}
		} catch (Exception e) {
			System.out.println("\nLog Message::@AfterMethod: Exception caught");
			e.printStackTrace();
		}
		driver.quit();
		extent.endTest(reporter);
		extent.flush();
	}

	public String getscreenshot() throws IOException {
		
		imagefilename = imagepath + "\\screenshot_" + System.currentTimeMillis() + ".png";
		Screenshot shot = new AShot().takeScreenshot(driver);
		ImageIO.write(shot.getImage(), "PNG", new File(imagefilename));
		return imagefilename;
	}
	
	@BeforeSuite
	public void AssemblyInit() throws IOException {

		loadConfiguration();
	}

	@BeforeMethod
	public void TestInit(Method method) throws IOException {
        Configuration config= new Configuration();
        driver = config.LoadBrowser("Chrome");
		reporter = config.loadReport(extent,method.getName());
      
	}

	@AfterMethod 
	public void afterTest(ITestResult result) {

		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				reporter.log(LogStatus.PASS, "Test Script passed");

			} else if (result.getStatus() == ITestResult.FAILURE) {

				reporter.log(LogStatus.FAIL, "Test Script failed ");
				reporter.log(LogStatus.FAIL, reporter.addScreenCapture(getscreenshot()));

			}

			else if (result.getStatus() == ITestResult.SKIP) {
				reporter.log(LogStatus.SKIP, "Test Script skipped");

			}
		} catch (Exception e) {
			System.out.println("\nLog Message::@AfterMethod: Exception caught");
			e.printStackTrace();
		}
		driver.quit();
		extent.endTest(reporter);
	}

	@AfterSuite
	public void AssemblyCleanup() {

		extent.flush();
	}
	
	public String getAscreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		imagefilename = imagepath + "\\screenshot_" + System.currentTimeMillis() + ".png";
		FileUtils.copyFile(scrFile, new File(imagefilename));
		return imagefilename; 
	}
	
	
}
