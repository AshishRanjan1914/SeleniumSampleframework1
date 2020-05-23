package com.nics.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nics.base.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;

public class BasePage extends BaseTest {

	public int waitTime = 90;
	
	public BasePage(WebDriver driver, ExtentTest reporter) {
		this.reporter = reporter;
		this.driver = driver;
	}

	public boolean WaitForEelementVisible_boolean(By locator) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if (element != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;

		}

	}

	public boolean WaitForEelementDisappear(By locator) {
		boolean flag = false;
		try {

			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			flag = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

		} catch (Exception e) {
			return false;
		}

		return flag;
	}
	
	public boolean WaitForEelementDisappear(By locator,int waittime) {
		boolean flag = false;
		try {

			WebDriverWait wait = new WebDriverWait(driver, waittime);
			flag = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

		} catch (Exception e) {
			return false;
		}

		return flag;
	}

	public boolean IsElementDisplayed(By locator) {
		boolean flag = false;
		try {

			flag = driver.findElement(locator).isDisplayed();

		} catch (Exception e) {
			return false;
		}

		return flag;
	}

	public WebElement WaitForEelementVisible(By locator) {
		List<WebElement> element = null;
		WebElement elevisible = null;
		try {

			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

			for (WebElement ele : element) {
				if (ele.isDisplayed()) {
					elevisible = ele;
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			elevisible = null;
			//
		}
		return elevisible;
	}
		
		public WebElement WaitForEelementVisible(By locator,int waittime) {
			List<WebElement> element = null;
			WebElement elevisible = null;
			try {

				WebDriverWait wait = new WebDriverWait(driver, waittime);
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

				for (WebElement ele : element) {
					if (ele.isDisplayed()) {
						elevisible = ele;
						break;
					}
				}

			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
				elevisible = null;
				//
			}

		return elevisible;
	}

	public WebElement WaitForEelementClickable(By locator) {
		WebElement element = null;
		try {

			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			element = null;
		}

		return element;
	}

	public void Click(By locator) {

		try {
			
		WebElement ele =WaitForEelementClickable(locator);
		highlightElement(ele);
		ele.click();
		
		
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;//
		}

	}

	public void EnterText(By locator, String text) {

		try {

			WebElement ele = WaitForEelementVisible(locator);
			ele.clear();
			highlightElement(ele);
			ele.sendKeys(text);

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}

	}

	public void waitForPageLoad() {

		new WebDriverWait(driver, waitTime).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
		System.out.println("Page Loaded.");
	}

	public void MandateWWait5sec() {

		waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void MandateWWait30sec() {

		waitForPageLoad();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void MandateWWait2sec() {

		waitForPageLoad();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ClickByJs(By locator) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", WaitForEelementClickable(locator));

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}

	}

	public void ClickTheDisplayedElementFromElements(By locator) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			List<WebElement> Clickable = driver.findElements(locator);
			for (WebElement ele : Clickable) {
				if (ele.isDisplayed()) {
					js.executeScript("arguments[0].click()", ele);
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}

	}

	public void ClickByJsNoWait(By locator) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", driver.findElement(locator));

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}

	}

	public void SelectDropdownByText(By locator, String text) {

		try {

			WebElement ele = WaitForEelementVisible(locator);
			Select eleselect = new Select(ele);
			eleselect.selectByVisibleText(text);

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}

	}

	public void SelectDropdownByIndex(By locator, int index) {

		try {

			WebElement ele = WaitForEelementVisible(locator);
			Select eleselect = new Select(ele);
			eleselect.selectByIndex(index);

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}

	}

	public String SelectedElement(By locator) {

		try {

			WebElement ele = WaitForEelementVisible(locator);
			Select eleselect = new Select(ele);
			return eleselect.getFirstSelectedOption().getText();

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}

	}

	public void HoverandClick(By parent, By child) {

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(parent)).click(driver.findElement(child)).build().perform();
	}

	public void WaitForURL(String url) {
		int count = 0;
		do {
			count++;
			MandateWWait2sec();
		} while ((!driver.getCurrentUrl().toLowerCase().contains(url.toLowerCase())) && count < 10);
	}
	
	public String getText(By locator) {
		String error;
		try {
			error=driver.findElement(locator).getText();

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			throw e;
		}
		return error;

	}
	
	public void navigate(String url)
	{
		driver.get(url);
		waitForPageLoad();
	}
	
	public String getPageTitle() {
			return driver.getTitle();

	}
	
	 public void highlightElement(WebElement ele) {
		        //Create object of a JavascriptExecutor interface
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		        //use executeScript() method and pass the arguments 
		        //Here i pass values based on css style. Yellow background color with solid red color border. 
		 js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
		 }
}
