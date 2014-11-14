package Login;
import RoomB.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Resource1
{
	protected Scanner scan = new Scanner(System.in);
	int choice;
	Room room1;
	
	public Resource1() throws SQLException
	{
		System.out.println("Press:");
		System.out.println("1  -  Cab Sharing");
		System.out.println("2  -  Room Booking");
		choice = scan.nextInt();
		System.out.println("\n\n");
		if(choice == 1) ;
		else if(choice == 2);
			room1 = new Room();
		//else return;
	}

}
