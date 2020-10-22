package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	
	//properties for locating elements
	public RemoteWebDriver driver;
		
	//locators
	@FindBy(how=How.NAME,using="mobileNo")
	public WebElement mbno;
		
	@FindBy(how=How.NAME,using="password")
	public WebElement pwd;
		
	@FindBy(how=How.XPATH,using="(//button[contains(text(),'Login')])[1]")
	public WebElement login;
	
	@FindBy(how=How.XPATH,using="//*[text()='Enter your mobile number']")
	public WebElement mbnoblankerror ;
	
	@FindBy(how=How.XPATH,using="(//*[text()='Enter password'])[2]")
	public WebElement pwdblankerror ;
	
	@FindBy(how=How.XPATH,using="//*[text()='Enter valid mobile number']")
	public WebElement mbnosizeerror ;
	
	@FindBy(how=How.XPATH,using="//b[contains(text(),'not register') or text(),'Invalid Mobile Number']")
	public WebElement invalidmbnoerror ;
	
	@FindBy(how=How.XPATH,using="(//*[contains(text()='Incorrect number or password')])[1]")
	public WebElement invalidpwderror ;
	
	
	//constructor for connecting runner classes (via Association)
	public HomePage(RemoteWebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
		
	public void fillMbno(String mobileno)
	{
		mbno.sendKeys(mobileno);
	}
	
	//operational methods
	public void fillPwd(String password)
	{
		pwd.sendKeys(password);
	}
	
	public void clickLogin()
	{
		login.click();
	}

}
