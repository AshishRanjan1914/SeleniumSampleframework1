package com.nics.test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.nics.base.BaseTest;
import com.nics.base.Configuration;
import com.nics.base.Constant;
import com.nics.utils.BasePage;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class CreateEvalautionDeal extends BaseTest{
	
	BasePage actions;
	
  @Test
  public void EvaluationAgreementTest () {
	  try
	  {
		
		 actions = new BasePage(driver, reporter);
		// Login to salesforce app
	 
	    actions.navigate(Constant.Url);
	    reporter.log(LogStatus.INFO, "User navigated to url -"+ Constant.Url,reporter.addScreenCapture(getAscreenshot()));
	    
	    actions.EnterText(By.id("username"), Constant.username);
	    reporter.log(LogStatus.INFO, "Entered Username",Constant.username);
	    
	    actions.EnterText(By.id("password"), Constant.password);
	    reporter.log(LogStatus.INFO, "Entered Password",Constant.password);
	    
	    actions.Click(By.id("Login"));
	    reporter.log(LogStatus.INFO, "Clicked Login");
	    
	    actions.MandateWWait5sec();
	    actions.waitForPageLoad();
	    
	  
	    reporter.log(LogStatus.INFO, "Navigated to Opportunity",reporter.addScreenCapture(getAscreenshot()));
	    
	    actions.Click(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a"));
	    reporter.log(LogStatus.INFO, "Select first Opportunity");
	    actions.waitForPageLoad();

	    actions.Click(By.xpath("//*[@id=\"topButtonRow\"]/input[@value='Create Agreement']"));
	    actions.waitForPageLoad();
	    
	    actions.SelectDropdownByText(By.xpath("//*[@id=\"j_id0:j_id5:j_id10:idRecordType\"]/select"),"Evaluation-AG");
	    actions.Click(By.xpath("//input[@value='Continue']"));
	    
	    
	    actions.waitForPageLoad();
	   
        actions.Click(By.xpath("//input[@value='Continue']"));
	    actions.waitForPageLoad();
	    reporter.log(LogStatus.INFO, "Navigated to Agreement",reporter.addScreenCapture(getAscreenshot()));
	    
	    
	    
	    
	    // Add Primary contact
	    actions.EnterText(By.xpath("//label[text()='Agreement Primary Contact']/..//following-sibling::td//span//input"),"test test1");
	    actions.Click(By.xpath("//*[contains(@id,\"autoCompleteRowId_0\")]"));
	    
	    actions.SelectDropdownByText(By.xpath("//label[text()='Evaluation Term']/../..//following-sibling::td//select"), "forty-five (45)");
	       
	    actions.Click(By.name("save"));
	    actions.waitForPageLoad();
	    
	    reporter.log(LogStatus.INFO, "Agreement saved",reporter.addScreenCapture(getAscreenshot()));
	    
	    
	    String agreementId =    actions.WaitForEelementVisible(By.xpath("//td[text()='Agreement ID']/following-sibling::td//div")).getText();
	    reporter.log(LogStatus.INFO, "Reporter Id",agreementId);
	    reporter.log(LogStatus.INFO, "Agreement saved",reporter.addScreenCapture(getAscreenshot()));
	    
	    actions.Click(By.xpath("//td[text()='Configure Products']/following-sibling::td//a//img"));
	    reporter.log(LogStatus.INFO, "Click configure product");
	    
	    actions.waitForPageLoad();
	    actions.WaitForEelementVisible_boolean(By.xpath("//input[@type='search']"));
	    reporter.log(LogStatus.INFO, "Configure Products Loaded",reporter.addScreenCapture(getAscreenshot()));
	    
	    actions.Click(By.xpath("//div[@class='main-product-catalog__product-block']//a[text()='Add Miscellaneous']"));
	    actions.Click(By.xpath("//label[@for='checkbox-all']"));
	    actions.MandateWWait2sec();
	    actions.Click(By.xpath("//span[text()='Add to Cart']"));
	    actions.MandateWWait5sec();
	    actions.Click(By.xpath("//span[text()='Go to Pricing']"));
	    actions.MandateWWait5sec();
	    actions.WaitForEelementVisible(By.xpath("//div[@class='loading']"));
	    actions.WaitForEelementDisappear(By.xpath("//div[@class='loading']"));
	    actions.Click(By.xpath("//span[text()='Finalize']"));
	    
	    actions.MandateWWait5sec();
	    actions.WaitForEelementVisible(By.xpath("//center"));
	    actions.WaitForEelementDisappear(By.xpath("//center"));
	    
	    actions.MandateWWait5sec();
	    actions.Click(By.xpath("//input[@title='Submit Agreement Approvals']"));
	    actions.waitForPageLoad();
	    
	    actions.EnterText(By.xpath("//textarea"), "Test Agreement");
	    actions.Click(By.xpath("//input[@value='Submit']"));
	    actions.WaitForEelementVisible(By.xpath("//center"));
	    actions.WaitForEelementDisappear(By.xpath("//center"));
	    actions.ClickByJs(By.xpath("//input[@value='Return']"));
	    actions.waitForPageLoad();
	    reporter.log(LogStatus.INFO, "Submitted for approval",reporter.addScreenCapture(getAscreenshot()));
	    
	    
	    Configuration config= new Configuration();
        WebDriver driver1 = config.LoadBrowser("Chrome");
        
        BasePage  newactions = new BasePage(driver1, reporter);
		// Login to salesforce app
	 
        newactions.navigate(Constant.Url);
	    reporter.log(LogStatus.INFO, "User navigated to url -"+ Constant.Url);
	    
	    newactions.EnterText(By.id("username"), Constant.approver1username);
	    reporter.log(LogStatus.INFO, "Entered Username",Constant.approver1username);
	    
	    newactions.EnterText(By.id("password"), Constant.password);
	    reporter.log(LogStatus.INFO, "Entered Password",Constant.password);
	    
	    newactions.Click(By.id("Login"));
	    reporter.log(LogStatus.INFO, "Clicked Login");
	    
	    newactions.MandateWWait5sec();
	    newactions.waitForPageLoad();
	    
	  	    
	    newactions.EnterText(By.id("phSearchInput"), agreementId);
	    newactions.Click(By.id("phSearchButton"));
	    
	    newactions.waitForPageLoad();
	    newactions.Click(By.xpath("//div[contains(@id,'Agreement') and @class='pbBody']//tr[2]//th"));
	    
	    newactions.waitForPageLoad();
	    newactions.Click(By.xpath("//a[text()='Approve / Reject']"));
	    
	    newactions.waitForPageLoad();
	    newactions.EnterText(By.xpath("//textarea"), "Approved");
	    newactions.Click(By.xpath("//*[@value=\"Approve\"]"));
	    
	    newactions.waitForPageLoad();
	    driver1.close();  //CommOps - RMS Asset Ops Queue
	    
	    
	    Configuration config1= new Configuration();
        WebDriver driver2 = config1.LoadBrowser("Chrome");
        
        BasePage  newactions1 = new BasePage(driver2, reporter);
		// Login to salesforce app
	 
        newactions1.navigate(Constant.Url);
	    reporter.log(LogStatus.INFO, "User navigated to url -"+ Constant.Url);
	    
	    newactions1.EnterText(By.id("username"), Constant.approver2username);
	    reporter.log(LogStatus.INFO, "Entered Username",Constant.approver2username);
	    
	    newactions1.EnterText(By.id("password"), Constant.password);
	    reporter.log(LogStatus.INFO, "Entered Password",Constant.password);
	    
	    newactions1.Click(By.id("Login"));
	    reporter.log(LogStatus.INFO, "Clicked Login");
	    
	    newactions1.MandateWWait5sec();
	    newactions1.waitForPageLoad();
	    
	   
	    
	    newactions1.EnterText(By.id("phSearchInput"), agreementId);
	    newactions1.MandateWWait2sec();
	    newactions1.Click(By.id("phSearchButton"));
	    
	    newactions1.waitForPageLoad();
	    newactions1.Click(By.xpath("//div[contains(@id,'Agreement') and @class='pbBody']//tr[2]//th"));
	    
	    newactions1.waitForPageLoad();
	    newactions1.Click(By.xpath("//a[text()='Approve / Reject']"));
	    
	    newactions1.waitForPageLoad();
	    newactions1.EnterText(By.xpath("//textarea"), "Approved");
	    newactions1.Click(By.xpath("//*[@value=\"Approve\"]"));
	    
	    newactions1.waitForPageLoad();
	    driver2.close();
	    
	    
	    driver.navigate().refresh();
	    
	    actions.Click(By.xpath("//img[@alt='Preview']"));
	    actions.waitForPageLoad();
	    
	    actions.Click(By.xpath("//input[@value='Generate']"));
	    actions.waitForPageLoad();
	    actions.MandateWWait2sec();
	    actions.MandateWWait5sec();
	    actions.WaitForEelementDisappear(By.xpath("//center"));
	    actions.Click(By.xpath("//input[@value='Return']"));
	    actions.waitForPageLoad();
	    
	    
	    actions.Click(By.xpath("//img[@alt='Generate']"));
	    actions.waitForPageLoad();
	    
	    actions.Click(By.xpath("//input[@value='Generate']"));
	    actions.waitForPageLoad();
	    actions.MandateWWait5sec();
	    actions.WaitForEelementDisappear(By.xpath("//center"));
	    actions.Click(By.xpath("//input[@value='Return']"));
	    actions.waitForPageLoad();
	    
	    actions.Click(By.xpath("//img[@alt='Send For Review']"));
	    actions.waitForPageLoad();
	    actions.Click(By.xpath("//input[@type='checkbox']"));
	    actions.MandateWWait5sec();
	    actions.ClickByJs(By.xpath("//input[@value='Next']"));
	    actions.MandateWWait5sec();
	    actions.waitForPageLoad();
	    actions.Click(By.xpath("//input[@type='radio']"));
	    actions.MandateWWait5sec();
	    actions.Click(By.xpath("//input[@value='Next']"));
	    actions.MandateWWait5sec();
	    actions.waitForPageLoad();
	    
	    actions.Click(By.xpath("//img[@class='lookupIcon']"));
	    String currentwinHandle= driver.getWindowHandle();
	    
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		driver.switchTo().frame("resultsFrame");
		actions.MandateWWait2sec();
		driver.findElement(By.xpath("//a[text()='test test1']")).click();
		
		driver.switchTo().window(currentwinHandle);
		actions.Click(By.xpath("//input[@value='Send']"));		
		actions.waitForPageLoad();
		
		actions.Click(By.xpath("//img[@alt='Walk-In Signature']"));
		actions.waitForPageLoad();
		actions.Click(By.xpath("//input[@value='Return to Agreement']"));
		 actions.MandateWWait5sec();
	    actions.waitForPageLoad();
	    actions.Click(By.xpath("//img[@alt='Activate']"));
	    actions.waitForPageLoad();
	    actions.Click(By.xpath("//select//option"));
	    actions.Click(By.xpath("//input[@value='Next']"));
	    actions.MandateWWait5sec();
	    actions.waitForPageLoad();
	    actions.Click(By.xpath("//input[@value='Activate']"));
	    actions.MandateWWait5sec();
	    actions.WaitForEelementVisible(By.xpath("//center"));
	    actions.WaitForEelementDisappear(By.xpath("//center"));
	    
	    actions.waitForPageLoad();
	    reporter.log(LogStatus.INFO, "Agreement approved",reporter.addScreenCapture(getAscreenshot()));
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	  
	  }
	  catch(Exception e)
	  {
		  reporter.log(LogStatus.FATAL, "Script failed with the Issue", e.getMessage());
		  Assert.assertTrue(false);
	  }
	  
  }
}
