package com.salesforce.test.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.salesforce.BaseTest.BaseTest;
import com.salesforce.test.pages.home.HomePage;
import com.salesforce.test.pages.login.LoginPage;
import com.salesforce.test.utility.CommonUtilities;

public class SalesforceAutomation extends BaseTest{
	
		
	@Test
	public static void LoginErrorMessage() {
	    //String expected = "Login To Sales Force";
		report.logTestInfo("test case-error message01");
		
	    CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
		String username = CU.getApplicationProperty("username", applicationPropertiesFile);
		String password = CU.getApplicationProperty("wrongPassword", applicationPropertiesFile);
		LoginPage login = new LoginPage(driver);
		login.login(username, password);
	    HomePage homepage = new HomePage(driver);
	    //String actual = homepage.GetTextFromSalesForce();  // check lastline
	    //Assert.assertEquals(actual, expected);
	    report.logTestInfo("testscript execution completed");
	    
	}
	@Test 
	public static void LoginToSalesForce() {
		report.logTestInfo("test case-error message01");
	    CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
		String username = CU.getApplicationProperty("username", applicationPropertiesFile);
		String password = CU.getApplicationProperty("password", applicationPropertiesFile);
		LoginPage login = new LoginPage(driver);
		login.login(username, password);
		waitUntilPageLoads();
		HomePage homepage = new HomePage(driver);
	    //String actual = homepage.GetTextFromSalesForce();  // check lastline
	    //Assert.assertEquals(actual, expected);
	    report.logTestInfo("testscript execution completed");
	    }
	  	    

	@Test
	public static void CheckRememberMe(){
	report.logTestInfo("test case-error message01");
	String expected = "username";
    CommonUtilities CU = new CommonUtilities();
	Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
	String username = CU.getApplicationProperty("username", applicationPropertiesFile);
	String password = CU.getApplicationProperty("password", applicationPropertiesFile);
	LoginPage login = new LoginPage(driver);
	login.enterUserName(username);
	login.enterPassword(password);
	login.clickLoginButton();
	waitUntilPageLoads();
	HomePage homePage = new HomePage(driver);
	homePage.ClickuserId();
	homePage.Clicklogout();
	waitUntilPageLoads();
	report.logTestInfo("testscript execution completed");
	}
	
	@Test
	public static void Chrome4A() throws InterruptedException {
	report.logTestInfo("test case-error message01");
	String expected = "username";
    CommonUtilities CU = new CommonUtilities();
	Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
	String username = CU.getApplicationProperty("username", applicationPropertiesFile);
	String password = CU.getApplicationProperty("password", applicationPropertiesFile);
	LoginPage login = new LoginPage(driver);
	login.forgotPassword();
	String username1 = CU.getApplicationProperty("username1", applicationPropertiesFile);
	login.enterUserName1(username1);
	login.Continue();
	waitUntilPageLoads();
	HomePage homePage = new HomePage(driver);
}

	@Test
	public static void ValidateLoginErrorMessage() throws InterruptedException {
	report.logTestInfo("test case-error message01");
	String expected = "username";
    CommonUtilities CU = new CommonUtilities();
	Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
	String usrName = CU.getApplicationProperty("usrName", applicationPropertiesFile);
	String pasWord = CU.getApplicationProperty("pasWord", applicationPropertiesFile);
	LoginPage login = new LoginPage(driver);
	login.enterUserName(usrName);
	login.enterPassword(pasWord);
	login.clickLoginButton();
	waitUntilPageLoads();
	HomePage homePage = new HomePage(driver);
	}

	@Test
	public static void TC05() throws InterruptedException {
	report.logTestInfo("test case-error message01");
	String expected = "My Profile";
    CommonUtilities CU = new CommonUtilities();
	Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
	String username = CU.getApplicationProperty("username", applicationPropertiesFile);
	String password = CU.getApplicationProperty("password", applicationPropertiesFile);
	LoginPage login = new LoginPage(driver);
	login.enterUserName(username);
	login.enterPassword(password);
	login.clickLoginButton();
	HomePage homePage = new HomePage(driver);
	homePage.ClickuserId();
	
	}
}
