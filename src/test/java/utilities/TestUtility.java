package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtility 
{
	//properties
	public RemoteWebDriver driver;
	
	//constructor  
	//it is optional as by default the driver object is null at the time of creation 
	public TestUtility()
	{
		driver=null;
	}
	
	//operational methods
	public RemoteWebDriver openBrowser(String bname)
	{
		if(bname.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");
			
			//down casting- as RemoteWebDriver is being initialize by its child class ChromeDriver
			//it is also called as dynamic binding as this assignment of child to parent is done at
			//Runtime
			//bypass ssl certificate error
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
			ChromeOptions co = new ChromeOptions();
			co.merge(dc);
			driver = new ChromeDriver(co);
			
		}
		else if(bname.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();			
		}
		else if(bname.equalsIgnoreCase("opera"))
		{
			System.setProperty("webdriver.opera.driver",
					"E:\\Archana\\selenium_softwares\\operadriver_win64\\operadriver_win64\\operadriver.exe");
			OperaOptions op=new OperaOptions();
			op.setBinary("C:\\Users\\user\\AppData\\Local\\Programs\\Opera\\69.0.3686.95\\opera.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			op.merge(capabilities);
			
			driver = new OperaDriver(op);			
		}
		else if(bname.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();			
		}
		else
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();			
		}
		return driver;
	}
	
	public void launchsite(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public String screenshot() throws Exception
	{
		//take screenshot with date and time
		File src=driver.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yy-hh-mm-ss");
		Date dt=new Date();
		String fname="scrnshot"+ sf.format(dt)+".png";
		File dest = new File(fname);
		FileHandler.copy(src, dest);
		return (dest.getAbsolutePath());
	}

	public void closeSite()
	{
		driver.quit();
	}
	
	public Properties accessProperties() throws Exception
	{
		String pfpath = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
		FileReader fr = new FileReader(pfpath);
		BufferedReader br = new BufferedReader(fr);
		Properties p = new Properties();
		p.load(br);
		return p;		
	}
}
