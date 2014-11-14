package RoomB;

import Login.*;

import java.sql.SQLException;

import Login.User;
import Main.Resource;

public class Room extends Resource
{
	String reason, roomno;
	
	public Room() throws SQLException
	{	super();
		
	}
	
	
	public void Choices(User user) throws SQLException{
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
	
	public void newRoom(User user) throws SQLException
	{	Room ro = new Room();
		RoomDB db1 = new RoomDB();
		System.out.println("Enter the details for booking the room");
		System.out.println("\n\n");
		System.out.println("Enter the date in the format DD MM YYYY");
		ro.day = scan.nextInt(); //System.out.println(ro.day);
		ro.mon = scan.nextInt();
		ro.year = scan.nextInt();
		System.out.println("Enter the time in the format HH MM 24-HR format");
		ro.hr = scan.nextInt();//System.out.println(ro.hr);
		ro.min = scan.nextInt();//System.out.println(ro.min);

		System.out.println("Enter the duration for which the room is required");
		ro.duration = scan.nextInt();//hours
		ro.start = ro.hr+(ro.min/100);//System.out.println(ro.start);
		ro.end = ro.start+ro.duration;//System.out.println(ro.end);
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
		}
	}
	
	public void check(User user) throws SQLException
	{	
		RoomDB db1 = new RoomDB();
		
				db1.check(user);
		
	}
	
	public void cancel(User user) throws SQLException
	{	Room ro = new Room();
		RoomDB db1 = new RoomDB();
		System.out.println("Following is the status of your applications");
		check(user);
		System.out.println("Enter the Application No. for which you want to cancel application");
		ro.AppNo = scan.nextLong();
		db1.Cancel(ro, user);
	}
	
}