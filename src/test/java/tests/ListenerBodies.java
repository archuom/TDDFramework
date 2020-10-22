package tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenerBodies implements ITestListener
{
	@Override
	public void onFinish(ITestContext Result)
	{
		System.out.println("Testing completed");
	}
	
	@Override
	public void onStart(ITestContext Result)
	{
		System.out.println("Testing started");
	}
	
	@Override
	public void onTestFailure(ITestResult Result)
	{
		Reporter.log("The failed testcase is "+Result.getName());
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
	{
		System.out.println("Test"+ Result.getName()+"failed but met success percentaged");
	}
	
	@Override
	public void onTestSkipped(ITestResult Result)
	{
		System.out.println("Test"+ Result.getName()+"skipped for testing ");
	}
	
	@Override
	public void onTestStart(ITestResult Result)
	{
		System.out.println( Result.getName()+" Testcase started  ");
	}
	
	@Override
	public void onTestSuccess(ITestResult Result)
	{
		System.out.println("The name of testcase that passed id "+ Result.getName());
	}
		
}
