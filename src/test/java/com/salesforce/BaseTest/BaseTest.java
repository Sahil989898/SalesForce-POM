package com.salesforce.BaseTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.HasFullPageScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.salesforce.test.utility.CommonUtilities;
import com.salesforce.test.utility.Constants;
import com.salesforce.test.utility.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static Logger Logger = LogManager.getLogger(BaseTest.class);
	public static GenerateReports report;
	public static ExtentReports extent;
	public static ExtentTest extentLog;

	ExtentHtmlReporter htmlReporter;
	
	
	@BeforeTest 
	public static void SetUpBeforeTest() {
		report = GenerateReports.getInstance();
		report.startExtentReport();
	}
	@AfterTest
	public static void teardownAfterTest() {
		report.endReport();
	}
	
	@Parameters({"browsername"})
	@BeforeMethod
	public static void setUp(String browsername, Method m) {
		System.out.println("before execution started");
		report.startSingleTestReport(m.getName());  // test name to enter in place of test1
		setDriver(browsername);
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
		String url=CU.getApplicationProperty("url", applicationPropertiesFile);
		gotoUrl(url);
		waitUntilPageLoads();		
		}
	
	public static void WaitDriver() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	public static void getDriver() {
		driver.get("chrome");		
	}
	
	/*@AfterMethod
	logger.info("After method start execution");
	report.logTestInfo("After method start execution");
	driver.close();*/
	
	
	/*@AfterMethod
	public static void tearDown() {
		Logger.info("after method execution is started");
		closeAllbrowser();
	}*/
	
	/*@AfterTest
	public static void teatdownAfterTest() {
		report.endReport();
	}*/
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
	public static void gotoUrl(String url) {
		 driver.get(url);
	}
	public static void waitUntilPageLoads() {
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));	
	}
	public static void closeAllbrowser() {
		driver.quit();
	}
	public static void enterText(WebElement element, String text, String objName) {
		if (element.isDisplayed()) {
			clearElement(element, objName);
			element.sendKeys(text);
			report.logTestInfo("text enterted in " + objName + "field");
		} else {
			report.logTestInfo("fail:" + objName + "element not displayed");
		}
	}
	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			Logger.info("pass:" + objName + "element cleared");
		} else {
			Logger.error("fail:" + objName + "element not displayed");
		}
	}
	public static void clickElement(WebElement remember, String objName) {
		if (remember.isDisplayed()) {
			remember.click();
			Logger.info("pass:" + objName + "element clicked");
		} else {
			Logger.error("fail:" + objName + "element not displayed");
		}
	}
	public static void removePopup(WebElement tryLexDialogX, String objName) {
		if (tryLexDialogX.isDisplayed()) {
			clickElement(tryLexDialogX, "close");
			Logger.info("pass:" + objName + "element cleared");
		} else {
			Logger.error("fail:" + objName + "element not displayed");
		}
	}
	public static void movetoelement(WebElement remember, String objName) {
		if (remember.isDisplayed()) {
			remember.click();
			Logger.info("pass:" + objName + "element clicked");
		} else {
			Logger.error("fail:" + objName + "element not displayed");
		}
	}
	public static void replace(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			driver.close();
			Logger.info("pass:" + objName + "element clicked");
		} else {
			Logger.error("fail:" + objName + "element not displayed");
		}
	}
	public static void close() {
		driver.close();
		Logger.info("closed");
	}
	public static void pageLoad() {
		driver.manage().timeouts().getPageLoadTimeout();
	}

	public static void close1() {
		driver.close();
	}

	public static void url() {
		driver.get(null);
	}

	public static void SelectByVisibleText() {
		Select ob = new Select(null);
		ob.selectByVisibleText(null);
	}
	public static void ImplicitWait() {
		WebDriverWait wait = new WebDriverWait(driver, null);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // implicit wait
	}
	public static void waitUntilvisibilityOfElementLocated(By locator, String objName) { // Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
		// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("")));
	}
// FluentWait : it has the polling time in it. it checks the DOM every 5 sec or so to check any element is present or not.
//	Wait Wait1 = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
	//		.ignoring(NoSuchElementException.class);

	
	
	/*
	@AfterTest*/
	/*public static void TestCompleted() {
		driver.close();
	}*/

	/*public static void maximize() {
		driver.manage().window().maximize();
	}*/
	/*public static void newWindow() {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}*/
	/*public static void selectByTextdata(WebElement element, String text, String objName) {
	Select selectcity = new Select(element);
	selectcity.selectByVisibleText(text);
	report.logTestInfo(objName + "selected" + text);
	}

	public static void selectByindexdata(WebElement element, int index, String objName) {
	Select selectcity = new Select(element);
	selectcity.selectByIndex(index);
	}
	public static void selectByValuedata(WebElement element,  String text) {
	Select selectcity = new Select(element);
	selectcity.selectByValue(text);
	}*/

	public static void switchToWindowopened(String mainWindowHandle) {
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
	public static void waitUntilVisible(WebElement element, String objName) {
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void waitUntilalertIsPresent(WebElement element, String objName) {
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	public static void waitUntilElementtobeClickable(By locator, String objName) {
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
	public static void validateText(String actualText, String expectedText, String objName) throws InterruptedException{
		Assert.assertEquals(actualText, expectedText, objName);
		report.logTestpassed(objName);
		
		}
	
}



