package RoomB;
import java.sql.SQLException;

import Login.*;
import Main.Resource;
import Login.User;
import Main.Resource;
public class RoomA extends Resource
{
	public RoomA() throws SQLException
	{	super();
	}

	public void admin(User user) throws SQLException {
		System.out.println("Welcome "+user.name);
		Room ro = new Room();
		RoomDB db1 = new RoomDB();
		db1.decide(user);
		
		
	}
}
