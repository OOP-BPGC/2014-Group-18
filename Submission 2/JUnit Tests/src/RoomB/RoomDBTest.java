package RoomB;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class RoomDBTest 
{

	@Test
	public void testRoomChk() throws SQLException 
	{
		String[] entry1={"adi","C401","For fun","24","8","2015","21","30","1","1"};
		RoomDB db=new RoomDB();
		assertEquals(true,db.RoomChk("adi", "C401", "For fun", 24, 8, 2015, 21, 30, 1));
		assertEquals(false,db.RoomChk("adi", "C401", "For fun", 24, 8, 2015, 21, 15, 1));
	}

	@Test
	public void testQueue() throws SQLException 
	{
		RoomDB db=new RoomDB();
		String[] entry1={"adi","C401","For fun","24","8","2015","21","30","1"};
		db.queue("adi", "C401", "For fun", 24, 8, 2015, 21, 30, 1);
		String dayS="24";
		String monS="8";
		String yearS="2015";
		String hrS="21";
		String minS="30";
		String durationS="1";
		String[] dummy={"adi","C401","For fun",dayS,monS,yearS,hrS,minS,durationS};
		int ans=1;
		for(int counter=0;counter<db.Requestdata.size();counter++)
		{
			String[] obj=db.Requestdata.get(counter);
			if(obj[0].equals(dummy[0])&&obj[1].equals(dummy[1])&&obj[2].equals(dummy[2])&&obj[3].equals(dummy[3])&&obj[4].equals(dummy[4])&&obj[5].equals(dummy[5])&&obj[6].equals(dummy[6])&&obj[7].equals(dummy[7])&&obj[8].equals(dummy[8]))
			{
				ans=Integer.parseInt(obj[9]);
				break;
			}
			
		}
		assertEquals(0,ans);
	}
	
	@Test
	public void testCheckPermission() throws SQLException 
	{
		RoomDB db=new RoomDB();
		int ans=db.checkPermission("adi","C401","For fun",24,8,2015,21,30,1);
		assertEquals(0,ans);
	}
	
	@Test
	public void testDecide() throws SQLException 
	{
		RoomDB db=new RoomDB(); 
		db.decide("adi","C401","For fun",24,8,2015,21,30,1,1);
		int ans=0;
		String dayS="24";
		String monS="8";
		String yearS="2015";
		String hrS="21";
		String minS="30";
		String durationS="1";
		String[] dummy={"adi","C401","For fun",dayS,monS,yearS,hrS,minS,durationS};
		for(int counter=0;counter<db.Requestdata.size();counter++)
		{
			String[] obj=db.Requestdata.get(counter);
			if(obj[0].equals(dummy[0])&&obj[1].equals(dummy[1])&&obj[2].equals(dummy[2])&&obj[3].equals(dummy[3])&&obj[4].equals(dummy[4])&&obj[5].equals(dummy[5])&&obj[6].equals(dummy[6])&&obj[7].equals(dummy[7])&&obj[8].equals(dummy[8]))
			{
				ans=Integer.parseInt(obj[9]);
				break;
			}
		}
		assertEquals(1,ans);
	}

}
