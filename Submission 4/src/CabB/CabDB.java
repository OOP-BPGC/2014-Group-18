package CabB;
import Email.*;
import java.sql.*;

import Login.User;

public class CabDB extends Cab
{
    Connection con;
    Statement stmt;
    int cnt;
    
    public CabDB() throws SQLException
    {
		
    }
    
    public void Book(User user,Cab ca)throws SQLException{
    	SendEmail se = new SendEmail();
    	String data = "INSERT INTO `oop`.`cab` (`Student`, `ID`, `Day`, `Month`, `Year`, `Hour`, `Minutes`, `Source`, `Destination`, `Duration`, `Fare`) VALUES ('"+user.name+"', '"+user.id+"', '"+ca.day+"', '"+ca.mon+"', '"+ca.year+"', '"+ca.hr+"', '"+ca.min+"', '"+ca.source+"', '"+ca.dest+"', '"+ca.duration+"', '"+ca.fare+"')";
    	ConnectCab cc = new ConnectCab();
    	cc.Connection(data,0);
    	System.out.println("The cab has been successfully booked");
    	String head = "cab has been successfully booked";
    	String message = "Ticket from "+ca.source+" to "+ca.dest+" on "+ca.day+":"+ca.mon+":"+ca.year+" "+((ca.hr*100)+ca.min)+" HRS has been successfully booked with fare Rs."+ca.fare+" ("+ca.duration+") rounds";
    	se.mail(user.contact, message, head);
    	SWD swd = new SWD();
    	swd.farecollector(user, ca.fare);
    }
	public Long CabCheck(User user) throws SQLException
	{
		ConnectCab cc = new ConnectCab();
		//System.out.println("Following are your cab bookings");
		String sql = "SELECT * FROM `oop`.`cab` WHERE `Student` = '"+user.name+"'";
		long noOfEntries = cc.Connection(sql, 1);
		//System.out.println("@cabdb "+noOfEntries);
		return noOfEntries;
    	
	}
	
	public void cancel(User user,int cabid) throws SQLException
	{	ConnectCab cc = new ConnectCab();
		SendEmail se = new SendEmail();
		System.out.println(cabid);
		String sql = "DELETE FROM `oop`.`cab` WHERE `cab`.`AppNo` = "+cabid;
		cc.Connection(sql, 0);
		System.out.println("Cab booking deleted successfully");
		String head = "cab has been successfully cancelled";
    	//String message = "Ticket from "+ca.source+" to "+ca.dest+" on "+ca.day+":"+ca.mon+":"+ca.year+" "+ca.hr+":"+ca.min+" has been successfully cancelled with fare Rs."+ca.fare+" ("+ca.duration+") rounds";
    	String message = "Ticket has been successfully cancelled and amount has been refunded";
		//System.out.println(user.contact);
    	se.mail(user.contact, message, head);
    	System.out.println("Fare has also been refunded");
		
	}
	
	public void route(){
		
	}

}