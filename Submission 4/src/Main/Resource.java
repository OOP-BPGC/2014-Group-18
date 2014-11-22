package Main;
import Login.*;
import RoomB.*;
import CabB.*;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Resource
{
	protected Scanner scan = new Scanner(System.in);
	int choice;
	Room roomU;
	RoomA roomA;
	//protected RoomDB db1;
	Cab cabU;
	CabA cabA;
	//protected CabDB db2;
	protected boolean avail;
	protected int newch;
	public int day;
	public int mon;
	public int year;
	public int hr;
	public int min;
	public int duration;
	public double start,end;//start time of room booking and end time of booking
	public long AppNo;
	public Resource()
	{
		
	}
	
	public void ResourceInp(User user) throws SQLException, ParseException
	{	System.out.println("Welcome "+user.name);
		System.out.println("Press:");
		System.out.println("1  -  Cab Sharing");
		System.out.println("2  -  Room Booking");
		System.out.println("3  -  exit");
		choice = scan.nextInt();
		System.out.println("\n\n");
		if(choice == 1)
		{
			if(user.admin == true)
				{cabA = new CabA();
				 }
			else
				{cabU = new Cab();
				 cabU.Choice(user);}
		}
		else if(choice == 2)
		{	//System.out.println(user.admin);
			if(user.admin == true)
				{roomA = new RoomA();
				roomA.admin(user);}
			else
				{//System.out.println(user.admin);
				roomU = new Room();
				roomU.Choices(user);}
		}
		else return;
	}

}