package RoomB;
import java.sql.*;
import java.util.Scanner;

import Login.User;

public class RoomDB //extends Room
{	Scanner scan = new Scanner(System.in);
    Connection con;
    Statement stmt;
    int cnt;
    
    public RoomDB() throws SQLException
    {
    }

	public boolean RoomChk(Room ro) throws SQLException
	{	ConnectRoom crc = new ConnectRoom();
		String details = "SELECT * FROM `room` WHERE `RoomNo` = '"+ro.roomno+"'";
		String result = crc.Search(details, 1,ro); //operation 1 for checking availability
        
    	if (result.matches("problem"))
    		return false;
    	else return true;
		//return false;
	}
	
	public void queue(Room ro,User user) throws SQLException
	{	
		ConnectRoom cr = new ConnectRoom();
		String details = "INSERT INTO `oop`.`room` (`RoomNo`, `Student`, `ID`, `Day`, `Month`, `Year`, `Start`, `End`, `Duration`, `Reason`) VALUES ('"+ro.roomno+"', '"+user.name+"', '"+user.id+"', '"+ro.day+"', '"+ro.mon+"', '"+ro.year+"', '"+ro.start+"', '"+ro.end+"', '"+ro.duration+"', '"+ro.reason+"');";
		//System.out.println(details);
		cr.Connection(details);
		//stmt.executeUpdate("update roomdb set query = '1' where room = '" + roomno + "'");
	}

	public void check(User user) throws SQLException
	{	Room ro = new Room();
		ConnectRoom crc = new ConnectRoom();
		String details = "SELECT * FROM `room` WHERE `Student` = '"+user.name+"'";
		String junk = crc.Search(details, 2,ro);
    	
	}
	
	public void Cancel(Room ro,User user) throws SQLException
	{	
		ConnectRoom cr = new ConnectRoom();
		String details = "DELETE FROM `oop`.`room` WHERE `room`.`AppNo` = "+ro.AppNo;
		//System.out.println(details);
		cr.Connection(details);
    	
	}
	
	public void decide(User user) throws SQLException
	{	String continu = "y";
		while(continu.matches("y")){
			Room ro = new Room();
			ConnectRoom crc = new ConnectRoom();
			String details = "SELECT * FROM `room` WHERE `Granted` = '0'";
			String junk = crc.Search(details, 2,ro);
			System.out.println("Enter the Application No. for which you want to accept/deny");
			ro.AppNo = scan.nextLong();
			System.out.println("Enter 1 to accept and 2 to deny");
			long ans = scan.nextLong();
			String order = "UPDATE `oop`.`room` SET `Granted` = '"+ans+"' WHERE `room`.`AppNo` = "+ro.AppNo;
			crc.Connection(order);
			System.out.println("Do you want to continue (y/n)");
			continu = scan.next();
			continu.toLowerCase();
		}
		
	}
}