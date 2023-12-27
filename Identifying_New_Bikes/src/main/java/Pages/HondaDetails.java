package Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class HondaDetails extends Base {
	By nbikes = By.linkText("New Bikes");
	By ubikes = By.xpath("//span[text()='Upcoming Bikes']");
	By smanuf = By.id("makeId");
	By lclose = By.id("alternate-login-close");
	By viewButton = By.xpath("//span[@class='zw-cmn-loadMore']");
	By BikeNames = By.xpath("//strong[@class='lnk-hvr block of-hid h-height']");
	By BikePrices = By.xpath("//div[@class='b fnt-15']");
	By BikeLaunch = By.xpath("//div[@class='clr-try fnt-14']");
	By closePopup = By.id("feedClose");
	int count = 0, count1 = 0;

	public void clickUpcomingBikes() // Method to click Upcoming_bikes
	{
		logger = report.createTest("Upcoming Bikes");
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
			WebElement w1 = driver.findElement(nbikes);
			Actions act = new Actions(driver);
			act.moveToElement(w1).perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(ubikes));
			driver.findElement(ubikes).click();
			takeScreenshot();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol//li/span[text()='Upcoming Bikes']")));
			String str = driver.findElement(By.xpath("//ol//li/span[text()='Upcoming Bikes']")).getText();
			if (str.equals("Upcoming Bikes"))
				reportPass("Upcoming bikes has been opened");
		} catch (Exception e) {
			reportFail(e.getMessage());
			
		}
	}

	public void selectManufacturer() // Method to select the Manufacturer
	{
		logger = report.createTest("Honda Manufacturer");
		try {
			WebElement drop = driver.findElement(smanuf);
			Select select = new Select(drop);
			select.selectByValue("53");
			takeScreenshot();
			String str1 = driver.findElement(By.xpath("(//ol//li/span)[3]")).getText();
			if (str1.contains("Honda Bikes"))
				reportPass("Manufacturer is HONDA");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void closeLoginPopUp() throws IOException // Method to close the login-popup
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(lclose));
		driver.findElement(lclose).click();
		if(verifyObjectDisplayed(closePopup)) {
		driver.findElement(closePopup).click();
		}
		takeScreenshot();
	}

	public void viewMore() throws IOException // Method to click viewmore
	{
		logger = report.createTest("Accessing View More");
		try {
			WebElement element = driver.findElement(viewButton);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			reportPass("View More is clicked");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}

	public void printDetails() // Method to print details on the console
	{
		logger = report.createTest("Obtaining bike prices");
		List<WebElement> bikeNames = driver.findElements(BikeNames);
		List<WebElement> bikePrices = driver.findElements(BikePrices);
		List<WebElement> bikeLaunch = driver.findElements(BikeLaunch);
		count = bikeNames.size();
		String priceTxt;
		System.out.println("*******************************************************");
		System.out.println("              Upcoming Bike Details:");
		System.out.println("*******************************************************");
		float price=0;
		try {
			for (int i = 0; i < count; i++) {
				priceTxt = bikePrices.get(i).getText();
				if(priceTxt.contains("Lakh")) {
					price = Float.parseFloat(priceTxt.replaceAll("Rs. ", "").replaceAll(" Lakh", ""));
				}else {
					priceTxt=priceTxt.concat(".00");
//					price = Float.valueOf(priceTxt.replaceAll(",", ""));
				}
				if (price < 4 || !(priceTxt.contains("Lakh"))) {
					System.out.println(bikeNames.get(i).getText() + "\t" + bikePrices.get(i).getText() + "\t"
							+ bikeLaunch.get(i).getText());

				}
			}
			reportPass("Bike Prices are Obtained");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
}
