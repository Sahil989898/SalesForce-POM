package com.salesforce.test.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.test.pages.base.BasePage;

public class HomePage extends BasePage {

	@FindBy(id="userNavLabel" )WebElement userId;
	@FindBy(xpath="//a[contains(text(),'Logout')]" )WebElement logout;
	//@FindBy(id="Login" )WebElement loginButton;
	//@FindBy(id="error" )WebElement error;
	//@FindBy(id="rememberUn" )WebElement reminder;
	//@FindBy(id="forgot_password_link" )WebElement forgotpassword;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public static void clickElement(WebElement remember, String objName) {
		if (remember.isDisplayed()) {
			remember.click();
			report.logTestpassed("pass:" + objName + "element clicked");
		} else {
			report.logTestfailed("fail:" + objName + "element not displayed");
		}
	
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

	public void ClickuserId() {
		clickButton(userId, "userId");
	}
	public void Clicklogout() {
		clickButton(logout, "Logout");
	}
 
	public void homePage(WebElement userId, WebElement logout) {
	ClickuserId();
	Clicklogout();
}}
	