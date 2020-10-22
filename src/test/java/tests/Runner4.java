package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Runner4 
{
	@Test(priority=1)
	public void method1() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		
		WebElement we = driver.findElement(By.xpath("//input[@value='Go']"));
		File src = we.getScreenshotAs(OutputType.FILE);
		File dest = new File("E:\\amazonsearchscrnshot.png");
		FileHandler.copy(src, dest);
		
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dest1 = new File("E:\\amazonpage.png");
		FileHandler.copy(src1, dest1);
	}	
	
	@Test(priority=2)
	public void method2()
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.yahoo.co.in");
	}
}
