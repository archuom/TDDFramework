package examples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WaitForPageExample1 
{
	RemoteWebDriver driver;
	
	@BeforeTest
	public void setup() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://www.google.com");
	    
	}
	
	@Test
	public void clickSearch() 
    {
		CheckPageIsReady();
		System.out.println("page is ready start working");
    }
		
	public void CheckPageIsReady()
	{
		if(driver.executeScript("return document.readyState").toString().equals("complete"))
		{
			System.out.println("page is ready ");
		}
	}
	
}