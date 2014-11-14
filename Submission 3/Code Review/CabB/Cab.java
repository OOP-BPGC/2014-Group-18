package CabB;
import java.sql.SQLException;

import Login.User;
import Main.Resource;
import RoomB.Room;
import RoomB.RoomDB;

public class Cab extends Resource
{
	String dest, source;
	String confirm="y";
	int fare;
	SWD swd;
	
	public Cab()
	{	super();
		
	}
	
	public void Choice(User user) throws SQLException
	{
		System.out.println("Make a choice");
		System.out.println("1  -  New Booking");
		System.out.println("2  -  Cancel");
		newch = scan.nextInt();
		switch(newch)
		{
			case 1 : this.newCab(user);	break;
			case 2 : this.cancel(user);	break;
		}
	}
	
	public void newCab(User user) throws SQLException
	{	//String confirm="y";
		swd = new SWD();
		Cab ca = new Cab();
		CabDB db2 = new CabDB();
		System.out.println("Enter the details for booking the cab and please be case-sensitive");
		System.out.println("\n\n");
		System.out.println("Enter the starting point of journey (Bits,Vasco,Panjim,Airport,Madgaon)");
		ca.source = scan.next();
		System.out.println("Enter the ending point of journey  (Bits,Vasco,Panjim,Airport,Madgaon)");
		ca.dest = scan.next();
		System.out.println("Enter the date in the format DD MM YYYY");
		ca.day = scan.nextInt();
		ca.mon = scan.nextInt();
		ca.year = scan.nextInt();
		System.out.println("Enter the time in the format HH MM 24-HR format");
		ca.hr = scan.nextInt();
		ca.min = scan.nextInt();
		System.out.println("Press 1 for One Way and 2 for round trip");
		ca.duration = scan.nextInt();
		ca.fare=swd.farecalc(ca.source, ca.dest, user,ca.duration);
		System.out.println("Your fare for the journey would be "+ca.fare);
		System.out.println("Would you like to book a cab (y/n)");
		ca.confirm = scan.next();
		ca.confirm.toLowerCase();
		if(ca.confirm.matches("y")){
			db2.Book(user, ca);
		}
		else {System.out.println("Thanks for visiting us");return;}
		//System.out.println("The assigned cab id is " + db2.CabChk());
	}
		
	public void cancel(User user) throws SQLException
	{	Cab ca = new Cab();
		SWD swd = new SWD();
		CabDB db2 = new CabDB();
		db2.CabCheck(user);
		System.out.println("Enter the id of the cab which you want to cancel");
		int cabid = scan.nextInt();
		swd.refund(user, cabid);
		db2.cancel(cabid);
	}
	
}