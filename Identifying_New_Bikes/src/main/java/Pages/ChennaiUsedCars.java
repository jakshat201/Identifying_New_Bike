package Pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class ChennaiUsedCars extends Base
{
	By ucars=By.linkText("Used Cars");
	By chennai=By.xpath("//span[text()='Chennai']");
	By lclose=By.id("alternate-login-close");
	By popularmodels=By.xpath("//ul[contains(@class,'usedCarMakeModelList')]");
	By list=By.tagName("li");
	
	public void clickUsedCars()  // Method to click used_cars
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		logger = report.createTest("Used Cars and Popular Model");
		try{
			WebElement w1=driver.findElement(ucars);
		Actions act=new Actions(driver);
		act.moveToElement(w1).perform();
		takeScreenshot();
		driver.findElement(chennai).click();
		takeScreenshot();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='usedcarttlID']")));
		String usedCars = driver.findElement(By.xpath("//h1[@id='usedcarttlID']")).getText();
		if (usedCars.contains("Used Cars in Chennai")) 
		reportPass("Used Cars in chennai are displayed");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	public void clickPopularModels() // Method to click popular_models
	{
		logger = report.createTest("Obtaining Popular Models");
		try {
		WebElement w1=driver.findElement(popularmodels);
		System.out.println("*******************************************************");
		System.out.println("            Popular Used Cars in Chennai:");
		System.out.println("*******************************************************");
		List<WebElement> ls= w1.findElements(list);
		for(int i=0;i<ls.size();i++)
		{
			System.out.println(ls.get(i).getText());
		}
		reportPass("Popular models are printed");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}


}
