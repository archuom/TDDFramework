package examples;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class example2 
{
	@DataProvider(name="testdata")
	public Object[][] datamethod(ITestContext context)
	{
		Object[][] data=null;
		System.out.println("No of groups are"+context.getIncludedGroups().length);
		for(String str:context.getIncludedGroups())
		{
			System.out.println(str);
			if(str.equalsIgnoreCase("ArithematicOperation"))
			{
				data = new Object[][]
						{
							{10,20},
							{30,35}
						};
				break;
			}
			else if(str.equalsIgnoreCase("StringOperations"))
			{
				data = new Object[][]
						{
							{"Aryan","Burramukku"},
							{"Aakash","Burramukku"},
							
						};
				break;
				
			}
		}
		return (data);
		
	}
	
	@Test(groups= {"ArithematicOperation"},dataProvider="testdata")
	public void numbersAddition(int x, int y)
	{
		int a = x;
		int b = y;
		int c = b-a;
		System.out.println(c);
	}
	
	@Test(groups= {"StringOperations"},dataProvider="testdata")
	public void StringsAddition(String x, String y)
	{
		String str1 = x+ y;
		System.out.println(str1);
	}

}
