package RoomB;
import java.sql.SQLException;

import Login.*;
import Main.Resource;

public class RoomA extends Resource
{
	public RoomA() throws SQLException
	{
	//	db1.decide();
	}
	public RoomA(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration,int decision) throws SQLException
	{
		db1.decide(userId, roomno, reason, day, mon, year, hr, min, duration, decision);
	}
}