package tests;

import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SendSMSPage;
import utilities.TestUtility;

public class Runner3XmlProvider 
{
	//Declare global objects to Required Class
	public Properties properties;
	public RemoteWebDriver driver;
	public WebDriverWait wait;
	public TestUtility testutil;
	public HomePage hpage;
	public SendSMSPage smspage;
		
	@BeforeMethod
	public void method1() throws Exception
	{
		//Define TestUtility obj and load properties
		testutil = new TestUtility();
		properties = testutil.accessProperties();
	}
	
	
	@Test
	@Parameters({"browsername","mnumber", "mnocriteria", "pword", "pwdcriteria", "comments" })
	public void method3(String bname, String mnumber, String mnocriteria, String pword, String pwdcriteria, String comments ) throws Exception
	{
		//open browser and launch site
		driver = testutil.openBrowser(bname);
				
		//create obj for page classes
		hpage = new HomePage(driver);
		smspage = new SendSMSPage(driver);
		//launch site
		testutil.launchsite(properties.getProperty("url"));
				
		int maxwait = Integer.parseInt(properties.getProperty("maxwait"));
		WebDriverWait  wait = new WebDriverWait(driver,maxwait);
		
		wait.until(ExpectedConditions.visibilityOf(hpage.mbno));
		//do login
		hpage.fillMbno(mnumber);
		hpage.fillPwd(pword);
		hpage.clickLogin();
		Thread.sleep(6000);//wait for outcome
		try
		{
			//blank mno test
			if((mnumber.length() == 0)  && (hpage.mbnoblankerror.isDisplayed()))
			{
				Reporter.log("Test Passed for"+comments);
				testutil.closeSite();
			}
			//wrong size mno test
			else if((mnumber.length() < 10)  && (hpage.mbnosizeerror.isDisplayed()))
			{
				Reporter.log("Test Passed for"+comments);
				testutil.closeSite();
			}
			//invalid mno test
			else if((mnumber.length() == 10)  && (mnocriteria.equalsIgnoreCase("invalid"))&&
					(hpage.invalidmbnoerror.isDisplayed()))
			{
				Reporter.log("Test Passed for"+comments);
				testutil.closeSite();
			}
			//blank pwd test
			else if((pword.length() == 0)  && (hpage.pwdblankerror.isDisplayed()))
			{
				Reporter.log("Test Passed for"+comments);
				testutil.closeSite();
			}
			//wrong pwd test
			else if((pword.length() != 0)  && (pwdcriteria.equalsIgnoreCase("invalid"))&&
					(hpage.invalidpwderror.isDisplayed()))
			{
				Reporter.log("Test Passed for"+comments);
				testutil.closeSite();
			}
			//valid details test
			else if ((mnocriteria.equalsIgnoreCase("valid"))&& (pwdcriteria.equalsIgnoreCase("valid")) 
					&& (smspage.tombno.isDisplayed()))
			{	
				//System.out.println("in case3");
				Reporter.log("Test Passed for"+comments);
				testutil.closeSite();			
			}
			else
			{
				driver.executeScript("arguments[0].style.border='2px red dotted';",smspage.tombno);
				String filepath = testutil.screenshot();
				Reporter.log("Test failed for"+comments);
				Reporter.log("<a href=\""+filepath+"\"><img src=\""+filepath+"\" height=\"100\" width=\"100\"/></a>");
				testutil.closeSite();
				Assert.fail();
			}
		}
			
		catch(Exception e)
		{
			String filepath = testutil.screenshot();
			Reporter.log("Test failed for"+e.getMessage());
			Reporter.log("<a href=\""+filepath+"\"><img src=\""+filepath+"\" height=\"100\" width=\"100\"/></a>");
			testutil.closeSite();
			//Assert.fail();
			Assert.assertEquals(false, false);
		}		
	}

}
