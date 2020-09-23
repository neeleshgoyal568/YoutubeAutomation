/*
 * @author Neelesh Goyal
 * 
 * @Description : In this i have used @BM, @AM and @parameter 
 * 		@BM will exeute before every tetng script
 * 		@AM will exeute after every testng script
 * 
 */
package com.youtube.generic;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseLib {
	
public static WebDriver driver;
	
	@BeforeMethod
	@Parameters({"browserName", "baseUrl"})
	public void preCond(String browserName, String baseUrl) {
		driver=BrowserFactory.launchBrowser(browserName, baseUrl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(baseUrl);
		Reporter.log("URL is navigated", true);
	}
	
	@AfterMethod
	public void postCond() {
		driver.quit();
		Reporter.log("Browser is closed", true);
	}
}
