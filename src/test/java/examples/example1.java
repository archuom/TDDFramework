package examples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class example1
{
	@Test (priority=1, groups= {"smoketest"})
	public void openbrowser()
	{
		System.out.println("launch browser");
		Assert.assertTrue(false);
	}
	
	@Test(priority=2, dependsOnMethods= {"openbrowser"}, groups= {"smoketest"})
	public void login()
	{
		System.out.println("do login");
	}
	
	@Test(priority=2, dependsOnGroups= {"smoketest"})
	public void sendmail()
	{
		System.out.println("do login");
	}
	

}
