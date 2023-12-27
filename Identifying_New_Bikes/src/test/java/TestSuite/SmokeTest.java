package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import Pages.HomePage;


public class SmokeTest 
{
	@Test
	public void testing() throws IOException
	{
		HomePage hd= new HomePage();
		hd.openUrl();
		hd.clickUpcomingBikes();
		hd.selectManufacturer();
	}

}
