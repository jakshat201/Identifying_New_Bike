package Pages;

import java.io.IOException;

import org.openqa.selenium.By;

import Base.Base;

public class LoginPage extends Base {
	By lclose = By.id("alternate-login-close");
	By login = By.id("des_lIcon");
	By googleSignIn = By.xpath("(//span[text()='Google'])");
	By email = By.xpath("(//input[@type='email'])");
	By submit = By.xpath(
			"//span[text()='Next']");
	By error = By.xpath("(//div[@class='o6cuMc Jj6Lae'])");

	public void clickLogin() // Method to click Login
	{
		logger = report.createTest("Login button functionality");
		try {
			driver.findElement(login).click();
			
			Thread.sleep(2000);
			String login1 = "Login/Register to";
			String ver = driver.findElement(By.xpath(
					"//span[text()='Google']"))
					.getText();
			if (ver.contains(login1))
				reportPass("Working properly");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void clickGoogleSignIn() throws InterruptedException // Method to click Login
, IOException
	{
		logger = report.createTest("Error Checking after signup");
		driver.findElement(googleSignIn).click();
		takeScreenshot();
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
		}
		driver.findElement(email).sendKeys("azx@gmal.com");
		takeScreenshot();
		driver.findElement(submit).click();
	}

	public void captureErrorMessage() throws IOException // Method to capture error message
, InterruptedException
	{
		
		System.out.println("*******************************************************");
		System.out.println("              Error Obtained during Signup:");
		System.out.println("*******************************************************");
		Thread.sleep(800);
		String errorMessage = driver.findElement(error).getText();
		System.out.println("Error Message = " + errorMessage);
		takeScreenshot();

	}

}
