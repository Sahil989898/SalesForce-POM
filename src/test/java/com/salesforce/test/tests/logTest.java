package com.salesforce.test.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class logTest {

	private static Logger Logger = LogManager.getLogger(logTest.class);
	@Test 
	
	public void performSomeTask() {
		Logger.debug("This is a debug message");
		Logger.info("This is a info message");
		Logger.warn("This is a warn message");
		Logger.error("This is a error message");
		Logger.fatal("This is a fatal message");	
	}
	
	@Test 
	public void performSomeTask1() {
		Logger.debug("This is a debug message");
		Logger.info("This is a info message");
		Logger.warn("This is a warn message");
		Logger.error("This is a error message");
	    Logger.fatal("This is a fatal message");

	}
}