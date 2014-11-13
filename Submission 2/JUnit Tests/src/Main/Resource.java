package Main;
import Login.User;
import RoomB.*;
import CabB.*;

import java.sql.SQLException;
import java.util.*;
import java.io.*;

public class Resource extends User
{
	protected Scanner scan = new Scanner(System.in);
	int choice;
	int cabChoice;		//to book a cab/cancel a cab
	int RoomChoice;		//to book a room or cancel a room
	Room room1;
	RoomA room2;
	protected RoomDB db1;
	Cab cab1;
	CabA cab2;
	CabDB db2;
	protected boolean avail;
	protected int newch, day, mon, year, hr, min, duration;
	
	//added by Aditya Sunil Joshi
	boolean cabFlag=false;
	boolean cabAFlag=false;
	boolean roomFlag=false;
	boolean roomAFlag=false;
	
	public Resource()
	{}
	
	public Resource(User user,int choice) throws SQLException
	{
		
		/*	for code
		System.out.println("Press:");
		System.out.println("1  -  Cab Sharing");
		System.out.println("2  -  Room Booking");
		choice = scan.nextInt();
		System.out.println("\n\n\n\n");
		*/
		if(choice == 1)
		{
			if(user.getAdmin() == true)
			{
				cabAFlag=true;
				//cab2 = new CabA();	commented for testing purpose 
			}
			else
			{
				cabFlag=true;
				//cab1 = new Cab();		commented for testing purpose
			}
		}
		else if(choice == 2)
		{
			if(user.getAdmin() == true)
			{
				roomAFlag=true;
				//room2 = new RoomA();		commented for testing purpose
			}
			else
			{
				roomFlag=true;
				//room1 = new Room();	commented for testing purpose
			}
		}
	}

}