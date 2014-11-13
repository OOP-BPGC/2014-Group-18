package CabB;
import java.sql.SQLException;

import Main.Resource;

public class Cab extends Resource
{
	String dest, source;
	String userId;
	int choice;
	static CabDB cabdb=new CabDB();
	public Cab(int choice,String source,String dest,String id,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		if(choice==1)
			newCab(source,dest,id,day,mon,year,hr,min,duration);
		if(choice==2)
			cancel(source,dest,id,day,mon,year,hr,min,duration);
		/*for code
		System.out.println("Make a choice");
		System.out.println("1  -  New Booking");
		System.out.println("2  -  Cancel");
		newch = scan.nextInt();
		switch(newch)
		{
			case 1 : newCab();	break;
			case 2 : cancel();	break;
		}
		*/
	}
	
	public void newCab(String source,String dest,String id,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		this.source=source;
		this.dest=dest;
		userId=id;
		this.day=day;
		this.mon=mon;
		this.year=year;
		this.hr=hr;
		this.min=min;
		this.duration=duration;
		cabdb.update(source,dest,id,day,mon,year,hr,min,duration);
		farecalc();
		/*for code
		System.out.println("Enter the details for booking the cab");
		System.out.println("\n\n");
		System.out.println("Enter the starting point of journey");
		source = scan.next();
		System.out.println("Enter the ending point of journey");
		dest = scan.next();
		System.out.println("Enter the date in the format DD MM YYYY");
		day = scan.nextInt();
		mon = scan.nextInt();
		year = scan.nextInt();
		System.out.println("Enter the time in the format HH MM");
		hr = scan.nextInt();
		min = scan.nextInt();
		System.out.println("Press 1 for One Way and 2 for round trip");
		duration = scan.nextInt();
		*/
		
	}
		
	public void cancel(String source,String dest,String id,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		cabdb.remove(source,dest,id,day,mon,year,hr,min,duration);
	}
	
	public void farecalc()
	{
		
	}
}