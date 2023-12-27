package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.Base;
import Pages.ChennaiUsedCars;
import Pages.HondaDetails;
import Pages.LoginPage;

public class AllTest extends Base{
	HondaDetails honda= new HondaDetails(); 
	ChennaiUsedCars chennai = new ChennaiUsedCars();
	LoginPage signup= new LoginPage();
	@Test
	public void test() throws InterruptedException, IOException {
		honda.openUrl();

		honda.closeLoginPopUp();
		honda.clickUpcomingBikes();
		honda.selectManufacturer();
		honda.viewMore();
		honda.printDetails();
		chennai.openUrl();
		chennai.clickUsedCars();
		chennai.clickPopularModels();
		signup.openUrl();
		signup.clickLogin();
		signup.clickGoogleSignIn();
		signup.captureErrorMessage();
		
		honda.closeBrowser();
	}
}
