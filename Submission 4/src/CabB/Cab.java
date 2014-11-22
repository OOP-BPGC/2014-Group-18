package CabB;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

import Login.User;
import Main.Resource;
import RoomB.Room;
import RoomB.RoomDB;

public class Cab extends Resource
{
	public String dest, source;
	String confirm="y";
	public int fare;
	SWD swd;
	//Cab ca = new Cab();
	public Cab()
	{	super();
		
	}
	
	public void Choice(User user) throws SQLException, ParseException
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
	
	public void newCab(User user) throws SQLException, ParseException
	{	//String confirm="y";
		swd = new SWD();
		Cab ca = new Cab();
		CabDB db2 = new CabDB();
		System.out.println("Enter the details for booking the cab ");
		System.out.println("\n");
		System.out.println("Enter the starting point of journey");
		System.out.println(" (1. Bits, 2. Vasco, 3. Panjim, 4. Airport, 5. Madgaon)");
		System.out.println("enter only number");
		int x=1;
		x=scan.nextInt();
		switch(x){
		case 1 : ca.source="Bits";	break;
		case 2 : ca.source="Vasco";	break;
		case 3 : ca.source="Panjim";	break;
		case 4 : ca.source="Airport";	break;
		case 5 : ca.source="Madgaon";	break;
			
		}
		//ca.source = scan.next();
		System.out.println("Enter the ending point of journey");
		System.out.println(" (1. Bits, 2. Vasco, 3. Panjim, 4. Airport, 4. Madgaon)");
		System.out.println("enter only number");
		int y=2;
		y=scan.nextInt();
		switch(y){
		case 1 : ca.dest="Bits";	break;
		case 2 : ca.dest="Vasco";	break;
		case 3 : ca.dest="Panjim";	break;
		case 4 : ca.dest="Airport";	break;
		case 5 : ca.dest="Madgaon";	break;
		}
		boolean dateformat=true;  //true = correct data , false = wrong data
		while(dateformat){
			System.out.println("Enter the date in the format DD MM YYYY");
			ca.day = scan.nextInt();
			ca.mon = scan.nextInt();
			ca.year = scan.nextInt();
			System.out.println("Enter the time in the format HH MM 24-HR format");
			ca.hr = scan.nextInt();
			ca.min = scan.nextInt();
			Calendar cal = Calendar.getInstance();
	        Calendar cal1 = Calendar.getInstance();// creates calendar
	        cal.setTime(new Date()); // sets calendar time/date
	        cal.add(Calendar.HOUR_OF_DAY, 2); // adds one hour
	        cal1.set(ca.year, ca.mon-1, ca.day, ca.hr, ca.min);
			if(ca.day>30||ca.day<1) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(ca.mon>12||ca.mon<1) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(ca.hr>23||ca.hr<0) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(ca.min>59||ca.min<0) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(cal1.compareTo(cal)<0){ //checks whether cal>cal1
				dateformat=true;
				System.out.println("Err.....You  have book cab atleast 2 hours earlier....enter again");
			}
			else dateformat=false;
		}
		
		
		
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
		else {return;}
		//System.out.println("The assigned cab id is " + db2.CabChk());
	}
		
	public void cancel(User user) throws SQLException
	{	//Cab ca = new Cab();
		SWD swd = new SWD();
		CabDB db2 = new CabDB();
		long x = db2.CabCheck(user);
		if(x==0){
			return;
		}
		else{
			System.out.println("Enter the id of the cab which you want to cancel");
			System.out.println("You can press 0 to return");
			int cabid = scan.nextInt();
			if(cabid==0)return;
			else{
				swd.refund(user, cabid);
				db2.cancel(user,cabid);
			}
			
		}
		
	}
	
}