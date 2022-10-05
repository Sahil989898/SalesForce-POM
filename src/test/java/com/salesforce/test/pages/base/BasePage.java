package com.salesforce.test.pages.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.HasFullPageScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.salesforce.test.utility.Constants;
import com.salesforce.test.utility.GenerateReports;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static GenerateReports report;
	

	public BasePage(WebDriver driver) {
	this.driver=driver;
	
	PageFactory.initElements(driver, this); // this = login page and for login page all Webelements will be initialised
	report = GenerateReports.getInstance();
}
	public static String getPageTitle(){
	return driver.getTitle();	
	}

	public static void refreshPage() {
		driver.navigate().refresh();
		report.logTestInfo("page got refreshed");
	}
	@AfterMethod
	public static void tearDown() {
		report.logTestInfo("after method execution is started");
	
	}
	
	@AfterTest
	public static void teardownAfterTest() {
		//report.endReport();
	}
	/*@DataProvider (name = "SalesForce")
	public static readdata() {
		// logic to read all data from xl sheet data [][];
		// return data[][];
	}*/
	public static void setDriver(String browser) {

		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		default:
			break;
		}
	}
	
	
	
	public static void removePopup(WebElement tryLexDialogX, String objName) {
		if (tryLexDialogX.isDisplayed()) {
			clickElement(tryLexDialogX, "close");
			//report.logTestInfo("pass:" + objName + "element cleared");
		} else {
			report.logTestInfo("fail:" + objName + "element not displayed");
		}
	}
	public static void movetoelement(WebElement remember, String objName) {
		if (remember.isDisplayed()) {
			remember.click();
			//report.logTestInfo("pass:" + objName + "element clicked");
		} else {
			report.logTestInfo("fail:" + objName + "element not displayed");
		}
	}
	public static void replace(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			driver.close();
			//report.logTestInfo("pass:" + objName + "element clicked");
		} else {
			report.logTestInfo("fail:" + objName + "element not displayed");
		}
	}
	
	public static void pageLoad() {
		driver.manage().timeouts().getPageLoadTimeout();
	}

	

	public static void SelectByVisibleText() {
		Select ob = new Select(null);
		ob.selectByVisibleText(null);
	}
	public static void ImplicitWait() {
		WebDriverWait wait = new WebDriverWait(driver, null);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // implicit wait
	}
	public static void waitUntilvisibilityOfElementLocated(WebElement element, String objName) { // Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
		// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("")));
	}
// FluentWait : it has the polling time in it. it checks the DOM every 5 sec or so to check any element is present or not.
//	Wait Wait1 = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
	//		.ignoring(NoSuchElementException.class);

	
	@BeforeMethod
	public static void loginToSalesForce(){
		setDriver("chrome");
		driver.get("http://login.salesforce.com/");
		WebElement username = driver.findElement(By.className("username"));
		enterText(username, "johnabrahim@tekarch.com", "username");
		WebElement password = driver.findElement(By.id("password"));
		enterText(password,"Sahil@123" , "password");
		WebElement Login = driver.findElement(By.id("Login"));
		clickElement(Login, "Login"); 
	}
	
	@AfterTest
	public static void TestCompleted() {
		driver.close();
	}

	public static void maximize() {
		driver.manage().window().maximize();
	}
	public static void newWindow() {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}
	public static void selectByTextdata(WebElement element, String text, String objName) {
	Select selectcity = new Select(element);
	selectcity.selectByVisibleText(text);
	System.out.println(objName + "selected" + text);
	}

	public static void selectByindexdata(WebElement element, int index, String objName) {
	Select selectcity = new Select(element);
	selectcity.selectByIndex(index);
	}
	public static void selectByValuedata(WebElement element,  String text) {
	Select selectcity = new Select(element);
	selectcity.selectByValue(text);
	}

	public static void switchToWindowIpened(String mainWindowHandle) {
	Set<String> allWindowHandles = driver.getWindowHandles();
	for (String handle: allWindowHandles) {
		if(!mainWindowHandle.equalsIgnoreCase(handle));
		driver.switchTo().window(handle);
	}
	System.out.println("switch to new window");
			
	}
	public static WebElement selectFromList(List<WebElement> list, String text) {
	WebElement country=null;
	for(WebElement i:list) {
		if(i.getText().equalsIgnoreCase(text)) {
			System.out.println("selected=" +i.getText());
			country = i;
			break;
		}
	}
	return country;
	}
	
	public static void takeScreenShot(String fileName) {
		
		File screenshotFile = ((HasFullPageScreenshot) driver).getFullPageScreenshotAs(OutputType.FILE);
		String path = Constants.SCREENSHOTS_PATH+fileName;
		File destination = new File(path);
		try {
			FileUtils.copyFile(screenshotFile, destination);
		}catch (IOException e) {
			report.logTestFailedWithException(e);
		}
		report.logTestInfo("Screenshot taken");
		}

		public String readText(WebElement element, String objectName) {
			waitUntilVisible(element, objectName);
			String text = element.getText();
			return text;
		}
		protected void waitUntilVisible(WebElement element, String objectName) {
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		}
		

public static void validateButtons(boolean result, String objName) {
	if(result)
		report.logTestInfo(objName+" button checked");
	else {
		report.logTestInfo(objName+"button unchecked");
		String filename=objName.replace((char)32,'_');
		takeScreenShot(filename);
		report.logTestfailed(objName);
		//Assert.fail(objName+" button unchecked");
	}
	}public static void clickElement(WebElement remember, String objName) {
		if (remember.isDisplayed()) {
			remember.click();
			report.logTestInfo("pass:" + objName + "element clicked");
		} else {
			report.logTestInfo("fail:" + objName + "element not displayed");
		}
	}
	public static void enterText(WebElement element, String text, String objName) {
		if (element.isDisplayed()) {
			clearElement(element, objName);
			element.sendKeys(text);
			report.logTestInfo("text enterted in " + objName + "field");
		} else {
			report.logTestInfo("fail:" + objName + "element not displayed");
		}}
		public static void clearElement(WebElement element, String objName) {
			if (element.isDisplayed()) {
				element.clear();
				report.logTestInfo("pass:" + objName + "element cleared");
			} else {
				report.logTestInfo("fail:" + objName + "element not displayed");
			}
		}
		public static void clickButton(WebElement element, String objName) {
			if (element.isDisplayed()) {
				element.click();
				report.logTestInfo("Click-pass:" + objName + "field");
			} else {
				report.logTestInfo("click-fail:" + objName + "element not displayed");
			}
			}
		public static void reatText(WebElement element, String objName) {
			waitUntilvisibilityOfElementLocated(element, objName);
			String text = element.getText();
		}}

