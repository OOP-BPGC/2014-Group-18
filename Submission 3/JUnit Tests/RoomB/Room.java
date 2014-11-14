package RoomB;
import java.sql.SQLException;

import Login.*;
import Main.Resource;

public class Room extends Resource
{
	String reason, roomno;
	int choice;
	String userId;
	
	public Room()
	{
		
	}
	
	public Room(String userId,int choice,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		switch(choice)
		{
			case 1 : newRoom(userId,roomno,reason,day,mon,year,hr,min,duration);	break;
			case 2 : check(userId,roomno,reason,day,mon,year,hr,min,duration);		break;
			case 3 : cancel(userId,roomno,reason,day,mon,year,hr,min,duration);		break;
		}
	}
	
	public void newRoom(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		/*for code
		System.out.println("Enter the details for booking the room");
		System.out.println("\n\n");
		System.out.println("Enter the date in the format DD MM YYYY");
		day = scan.nextInt();
		mon = scan.nextInt();
		year = scan.nextInt();
		System.out.println("Enter the time in the format HH MM");
		hr = scan.nextInt();
		min = scan.nextInt();

		System.out.println("Enter the duration for which the room is required");
		duration = scan.nextInt();
		System.out.println("Enter room number");
		roomno = scan.next(); 		
		*/
		if((avail = db1.RoomChk(userId,roomno,reason,day,mon,year,hr,min,duration)) == false)
		{
			System.out.println("The selected room is not free at the required time");
			return;
		}
		else
		{	/*for code
			System.out.println("Enter the reason for room requirement");
			reason = scan.nextLine();
			*/
			db1.queue(userId,roomno,reason,day,mon,year,hr,min,duration);
			//System.out.println("Your request has been queued");
		}
	}
	
	public void check(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		int chk = db1.checkPermission(userId,roomno,reason,day,mon,year,hr,min,duration);
		switch(chk)
		{
		case 1: System.out.println("Your request has been approved");
				break;
		case 2: System.out.println("Your request has been denied");
				break;
		case 0: System.out.println("Your request is being processed");
				break;
		}
	}
	
	public void cancel(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		RoomA admin=new RoomA(userId,roomno,reason,day,mon,year,hr,min,duration,2);
	}
	
}