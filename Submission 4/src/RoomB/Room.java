package RoomB;

import Login.*;

import java.io.BufferedReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Login.User;
import Main.Resource;

public class Room extends Resource
{
	public String reason, roomno;
	public String reasondenied;
	
	public Room() throws SQLException
	{	//super();
		
	}
	
	
	public void Choices(User user) throws SQLException, ParseException{
		//System.out.println(user.name+user.id);
		System.out.println("Make a choice");
		System.out.println("1  -  New Booking");
		System.out.println("2  -  Check Status");
		System.out.println("3  -  Cancellation");
		newch = scan.nextInt();
		switch(newch)
		{
			case 1 : this.newRoom(user);	break;
			case 2 : this.check(user);break;	
			case 3 : this.cancel(user);	break;
		}
	}
	
	public void newRoom(User user) throws SQLException, ParseException
	{	Room ro = new Room();
		RoomDB db1 = new RoomDB();
		System.out.println("Enter the details for booking the room");
		System.out.println("\n\n");
		
		boolean dateformat=true;  //true = correct data , false = wrong data
		while(dateformat){
			System.out.println("Enter the date in the format DD MM YYYY");
			ro.day = scan.nextInt();
			ro.mon = scan.nextInt();
			ro.year = scan.nextInt();
			System.out.println("Enter the time in the format HH MM 24-HR format");
			ro.hr = scan.nextInt();
			ro.min = scan.nextInt();
			Calendar cal = Calendar.getInstance();
	        Calendar cal1 = Calendar.getInstance();// creates calendar
	        cal.setTime(new Date()); // sets calendar time/date
	        cal.add(Calendar.DAY_OF_MONTH, 1); // adds one day
	        cal1.set(ro.year, ro.mon-1, ro.day, ro.hr, ro.min);
	        if(ro.day>30||ro.day<1) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(ro.mon>12||ro.mon<1) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(ro.hr>23||ro.hr<0) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(ro.min>59||ro.min<0) {
				dateformat=true;
				System.out.println("wrong data...enter again");
				continue;}
			else if(ro.min>0&&ro.hr==23) {
				System.out.println("Taking booking from 23:00 till 23:59 ....maximum possible entry");
			}
			else if(cal1.compareTo(cal)<0){ //checks whether cal>cal1
				dateformat=true;
				System.out.println("Err.....You have book rooms atleast 1 day earlier....enter again");
			}
			else dateformat=false;
		}
		  
				
		System.out.println("Enter the duration for which the room is required");
		ro.duration = scan.nextInt();//hours
		ro.start = ro.hr+(ro.min/100);//System.out.println(ro.start);
		ro.end = ro.start+ro.duration;//System.out.println(ro.end);
		if(ro.end>23.59) {
			ro.end = 23.59;
			duration = (int) (ro.end+0.01-ro.start);
			System.out.println("Taking booking till 23:59 ....maximum possible entry");
			
		}
		System.out.println("Enter room number");	
		ro.roomno = scan.next(); 		
		if((avail = db1.RoomChk(ro)) == false)
		{
			System.out.println("The selected room is not free at the required time");
			return;
		}
		else
		{
			System.out.println("Enter the reason for room requirement");
			
			ro.reason = scan.next()+scan.nextLine();
			db1.queue(ro,user);
			System.out.println("Your request has been queued");
			System.out.println("You can check status of your application later"+"\n");
			
		}
	}
	
	public String check(User user) throws SQLException
	{	
		RoomDB db1 = new RoomDB();
		
				String junk = db1.check(user);
				if(junk.matches("0")) System.out.println("You have no pending applications now");
				return junk;
	}
	
	public void cancel(User user) throws SQLException
	{	Room ro = new Room();
		RoomDB db1 = new RoomDB();
		System.out.println("Status of your applications");
		String junk = check(user);
		if(junk.matches("0")) {
			//System.out.println("You have no pending applications now");
			return;
		}
		else{
			System.out.println("Enter the Application No. for which you want to cancel application");
			System.out.println("You can also press 0 to return");
			int AppNo = scan.nextInt();
			if(AppNo==0) return;
			db1.Cancel(AppNo, user);
		}
		
	}
	
}