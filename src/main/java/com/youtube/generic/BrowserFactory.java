/*
 * @author Neelesh Goyal
 * 
 * @Description : I have used this class to launch the respective browser according to the parameter value
 * 
 */

package com.youtube.generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
static WebDriver driver;
	
	public static WebDriver launchBrowser(String browserName, String baseUrl) {
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			Reporter.log("Chrome Launch", true);
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			Reporter.log("Firefox Launch", true);
		}
		
		else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			Reporter.log("IE Launch",true);
		}

		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			Reporter.log("Edge Launch", true);
		}
		return driver;
	}
}
