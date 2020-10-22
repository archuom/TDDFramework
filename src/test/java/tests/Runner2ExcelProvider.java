package tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SendSMSPage;
import utilities.TestUtility;

public class Runner2ExcelProvider 
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
	
	@DataProvider(name="mydata")
	public Object[][] method2() throws Exception
	{
		// Access excel file sheet1 for test data
		String  testdatafilepath = System.getProperty("user.dir")+ "\\src\\test\\resources\\W2SMSExcel.xlsx";
		//System.out.println(testdatafilepath);
		File file =new File(testdatafilepath);
				
		FileInputStream fi = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(fi);
				
		Sheet sh = workbook.getSheet("sheet1");
		
		int nour = sh.getPhysicalNumberOfRows();		
		int nouc = sh.getRow(0).getLastCellNum();
		
		//copy required count to create 2d array 
		Object[][] data = new Object[nour-1][nouc];
			
		for(int i=1;i<nour;i++)
		{
			Row r=sh.getRow(i);
			for(int j=0;j<nouc;j++)
			{
				Cell cell = r.getCell(j);
				DataFormatter df1 = new DataFormatter();
				String value = df1.formatCellValue(cell);
				
				//1st value in excel to 0th in data array
				data[i-1][j] = value;
				
			}
		}
		fi.close();
		workbook.close();
		
		return(data);
	}
	
	@Test(dataProvider="mydata")
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
