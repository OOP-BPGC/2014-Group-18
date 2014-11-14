package CabB;
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
    	String data = "INSERT INTO `oop`.`cab` (`Student`, `ID`, `Day`, `Month`, `Year`, `Hour`, `Minutes`, `Source`, `Destination`, `Duration`, `Fare`) VALUES ('"+user.name+"', '"+user.id+"', '"+ca.day+"', '"+ca.mon+"', '"+ca.year+"', '"+ca.hr+"', '"+ca.min+"', '"+ca.source+"', '"+ca.dest+"', '"+ca.duration+"', '"+ca.fare+"')";
    	ConnectCab cc = new ConnectCab();
    	cc.Connection(data,0);
    	System.out.println("The cab has been successfully booked");
    	SWD swd = new SWD();
    	swd.farecollector(user, ca.fare);
    }
	public void CabCheck(User user) throws SQLException
	{
		ConnectCab cc = new ConnectCab();
		System.out.println("Following are your cab bookings");
		String sql = "SELECT * FROM `oop`.`cab` WHERE `Student` = '"+user.name+"'";
		cc.Connection(sql, 1);
    	
	}
	
	public void cancel(int cabid) throws SQLException
	{	ConnectCab cc = new ConnectCab();
		//System.out.println(cabid);
		String sql = "DELETE FROM `oop`.`cab` WHERE `cab`.`AppNo` = "+cabid;
		cc.Connection(sql, 0);
		System.out.println("Cab booking deleted successfully");
		
	}
	
	public void route(){
		
	}

}