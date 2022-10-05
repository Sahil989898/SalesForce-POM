package com.salesforce.test.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestExtent {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest Logger;
	

	public static void main(String[] args) {
		
		htmlReporter = new ExtentHtmlReporter("./extentReport.html");
		extent = new ExtentReports();
	
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("salesforce regression tests");

		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Sahil");
		
		
		
		Logger = extent.createTest("test1");
		
		Logger.log(Status.INFO, "info message");
		Logger.log(Status.PASS, MarkupHelper.createLabel("test1"+"is passTest", ExtentColor.GREEN));
		Logger.log(Status.FAIL, MarkupHelper.createLabel("test1"+"is failTest", ExtentColor.RED));
		
		Logger = extent.createTest("test2");
		
		Logger.log(Status.INFO, "info message");
		Logger.log(Status.PASS, MarkupHelper.createLabel("test2"+"is passTest", ExtentColor.GREEN));
		Logger.log(Status.FAIL, MarkupHelper.createLabel("test2"+"is failTest", ExtentColor.RED));
		
		extent.flush();
		System.out.println("completed");
		}
		
	}


