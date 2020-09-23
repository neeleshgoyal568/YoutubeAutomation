/*
 * @author Neelesh Goyal
 * 
 * @Description : In this class i haved implements ITestNGListener (interface) to monitor the run time event
 * 
 */

package com.youtube.generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.Reporter;


import com.google.common.io.Files;

public class MyTestNGListener implements ITestNGListener {
static int executionCount, passCount, failCount, skipCount=0;
	
	public void onStart(ITestContext context) {
		Reporter.log(context.getName()+" Suite execution is start " + new Date(),  true);
	}
	
	public void onTestStart(ITestResult result) {	
		executionCount++;
		Reporter.log(result.getName()+" Script execution is start" + new Date(), true);
	}
	
	public void onTestSuccess(ITestResult result) {
		passCount++;
		Reporter.log(result.getName()+" Script is passed", true);
	}
	
	public void onTestFailure(ITestResult result) {
		failCount++;
		Reporter.log(result.getName()+" Script is failed", true);
		TakesScreenshot ts=(TakesScreenshot)BaseLib.driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("/.screenshots" + result.getName()+ " .png");

		try {
			Files.copy(src,dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Reporter.log("screenshot has been taken", true);
	}
	
	public void onTestSkipped(ITestResult result) {
		skipCount++;
		Reporter.log(result.getName()+" Script is skipped", true);
	}
	
	public void onFinish(ITestContext context) {
		Reporter.log("Total Scripts are executed "+ executionCount);
		Reporter.log("Total Scripts are passed "+ passCount);
		Reporter.log("Total Scripts are failed "+ failCount);
		Reporter.log("Total Scripts are skipped "+ skipCount);
	}
}
