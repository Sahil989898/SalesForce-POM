package com.salesforce.test.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateReports {

	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest Logger;
	public static GenerateReports ob;
	public GenerateReports() {                     
																
		}
	public static GenerateReports getInstance() {       // SingletonClass
		if(ob==null) {
			ob=new GenerateReports();
		}
		return ob;
	}

	public void startExtentReport(){
		
		htmlReporter = new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Sahil");
		
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("salesforce regression tests");
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	    public void startSingleTestReport(String testcasename) {
	    	Logger = extent.createTest(testcasename);
	    }
	    
	    public void logTestInfo(String message) {
	    Logger.log(Status.INFO, message);
	    }
	    public void logTestpassed(String testcaseName) {
	    	Logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + "ispassTest", ExtentColor.GREEN));
	    }
	    
	    public void logTestfailed(String testcaseName) {
	    	Logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + "isfailedTest", ExtentColor.RED));
	    }
		public void logTestFailedWithException(Exception e) {
			Logger.log(Status.ERROR, e);
		}
		public void logTestSkipped(String testcaseName) {
			Logger.log(Status.SKIP, MarkupHelper.createLabel(testcaseName + "skipped the test", ExtentColor.YELLOW));
		}
		public void endReport() {
			extent.flush();
		}
		
		}
