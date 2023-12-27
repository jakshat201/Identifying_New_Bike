package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import Utils.ExtentReportManager;


public class Base {
	public static WebDriver driver;
	public static Properties prop;

	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public static WebDriverWait wait;
	
	
	@BeforeSuite
	public void driverSetup() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/java/Config/Config.properties")); // Loading the properties
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (prop.getProperty("browserName").matches("chrome")) {
			driver = new ChromeDriver(); // Initializing the new chrome driver
		}
		if (prop.getProperty("browserName").matches("firefox")) {
			driver = new FirefoxDriver(); // Initializing the new firefox driver
		}
		driver.manage().window().maximize(); // To maximize the window
	}
	
//	@Test(groups={"smoke"})
	public void openUrl() throws IOException // Method to open URL for smoke test
	{
		logger = report.createTest("Opening Url");
		try {
			driver.get(prop.getProperty("url"));

			reportPass("URL opened, URL is :" + prop.getProperty("url"));
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}

	public void reportFail(String report) {
		logger.log(Status.FAIL, report);
		takeScreenShotOnFailure();
	}

	// Function to show the passed test cases in the report
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}

	public void Screenshoot(String fileName) throws IOException {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/Screenshot/" + fileName + ".png");
		Files.copy(srcFile, destFile);
	}

	// To take Screenshot when test gets failed
	public void takeScreenShotOnFailure() {

		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File src = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Screenshot/FailedCases/Screenshot.png");
		try {
			FileUtils.copyFile(src, dest);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshot/FailedCases/Screenshot.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void takeScreenshot() throws IOException {
		//Taking the Screenshot And save it the Screenshot folder
		
	TakesScreenshot scrshot=(TakesScreenshot)driver;
	File ScrFile=scrshot.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(ScrFile,new File(System.getProperty("user.dir")+"\\screenshot\\"+System.currentTimeMillis()+".png"));
	}

	@SuppressWarnings("deprecation")
	@AfterSuite
	public void closeBrowser() // method to close the browser
	{
		driver.quit(); // To close the browser
		report.flush(); // To save the reports
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (Exception e) {
		}
	}
	
	/*
	 * *************************Method: getObject *** *************Description:
	 * Get Object in Pages**
	 */

	public WebElement getObject(By by) {
		return driver.findElement(by);

	}
	
	/*
	 * *************************Method: verifyObjectDisplayed ***
	 * *************Description: Verify Object Displayed**
	 */

	public boolean verifyObjectDisplayed(By by) {

		try {
			if (getObject(by).isDisplayed()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}
}
