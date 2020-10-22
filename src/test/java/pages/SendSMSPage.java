package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SendSMSPage 
{
	
	//properties for locating elements
	public RemoteWebDriver driver;
		
	@FindBy(how=How.NAME,using="toMoblie")
	public WebElement tombno ;
	
	@FindBy(how=How.NAME,using="message")
	public WebElement msg ;
	
	@FindBy(how=How.XPATH,using="//button[text()='Send Sms']")
	public WebElement send ;
	
	@FindBy(how=How.XPATH,using="//*[text()='Invalid Mobile Number']")
	public WebElement to_mbno_size_series_error ;
	
	@FindBy(how=How.XPATH,using="//*[text()='Enter Valid Mobile Number.']")
	public WebElement to_mbno_blank_error ;
	
	@FindBy(how=How.XPATH,using="//*[contains(text()='Please enter a message')]")
	public WebElement msg_blank_error ;
	
	@FindBy(how=How.XPATH,using="//*[text()='Message send Successfully' or text()='Paid SMS Stopped']")
	public WebElement output ;
	
	@FindBy(how=How.ID,using="closeRedirect")
	public WebElement cross_icon ;
	
	@FindBy(how=How.XPATH,using="//a[@class='logout']")
	public WebElement logout ;
	
	//constructor for connecting runner classes (via Association)
	public SendSMSPage(RemoteWebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
			
	//operational methods
	public void fillToMbno(String tomobileno)
	{
		tombno.sendKeys(tomobileno);
	}
				
	public void fillMsg(String message)
	{
		msg.sendKeys(message);
	}
		
	public void clickSendSms()
	{
		send.click();
	}

	public void clickCrossIcon()
	{
		cross_icon.click();
	}
	public void clickLogout()
	{
		logout.click();
	}
	

}
