package CabB;

import Main.Resource;

public class CabA extends Resource
{
	static int cablimit = 5;
	int ch;
	
	public CabA()
	{
		System.out.println("Do you want to change the cab limit ?");
		ch = scan.nextInt();
		if(ch == 1)
		{
			System.out.println("Enter the new limit");
			cablimit = scan.nextInt();
		}
	}
}