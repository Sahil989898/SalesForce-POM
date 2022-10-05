package com.salesforce.test.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.test.pages.base.BasePage;

public class LoginPage  extends BasePage{ 	
	
	@FindBy(className="username" )	 WebElement username;
	@FindBy(id="password" )	WebElement password;
	@FindBy(id="Login" )	WebElement loginButton;
	@FindBy(id="error" )	WebElement error;
	@FindBy(id="rememberUn" )	WebElement reminder;
	@FindBy(id="forgot_password_link" )	WebElement forgotpassword;
	@FindBy(id="continue") WebElement Continue; 
	@FindBy(xpath="//*[@id=\"un\"]") WebElement username1;
	

		public LoginPage(WebDriver driver) {
			super(driver);			
			
}
		public void enterUserName(String usrname) {
			waitUntilVisible(username, usrname);
			enterText(username, usrname, "username field");
		}
		public void enterPassword(String passWord) {
		enterText(password, passWord, "passWord field");
		}
		public void clickLoginButton() {
			clickElement(loginButton, "login button");
		}
		public void clickReminderButton() {
			clickButton(reminder, "Remember Me");
		}
		public void forgotPassword() {
			clickElement(forgotpassword, "forgot password");
		}
		public void Continue() {
			clickButton(Continue, "continue");
		}
		
		
		public void login(String usrname, String password) {
			enterUserName(usrname);
			enterPassword(password);
			clickLoginButton();
			clickReminderButton();
		}
		public void enterUserName1(String usrname1) {
			waitUntilVisible(username1, usrname1);
			enterText(username1, usrname1, "username field");
		
		/*public static void loginNoPassword(String password) {
			loginNoPassword login = new loginNoPassword(password);
`				
			
			
		}*/
			
		}}
             
			