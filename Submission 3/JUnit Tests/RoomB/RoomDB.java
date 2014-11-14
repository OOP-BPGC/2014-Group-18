package RoomB;
import java.sql.*;
import java.util.ArrayList;

public class RoomDB extends Room
{
    Connection con;
    Statement stmt;
    int cnt;
    /*data table format- 
    Available
    userId,roomno,reason,day,mon,year,hr,min,duration,status
    
    Request
    userId,roomno,reason,day,mon,year,hr,min,duration,permission
    */
    static ArrayList<String[]> Availabledata=new ArrayList<String[]>();
    static ArrayList<String[]> Requestdata=new ArrayList<String[]>();
    public RoomDB() throws SQLException
    {	
    	String[] entry1={"adi","C401","For fun","24","8","2015","21","30","1","1"};		//last bit tells available status 1-yes 0-no
    	Availabledata.add(entry1);
    	/*for code
		Driver myDriver = new oracle.jdbc.driver.OracleDriver();
		DriverManager.registerDriver( myDriver );
		con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","system","home");
        stmt=con.createStatement();
        */
    }

	public boolean RoomChk(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		boolean res=false;
		String dayS=day+"";
		String monS=mon+"";
		String yearS=year+"";
		String hrS=hr+"";
		String minS=min+"";
		String durationS=duration+"";
		String[] dummy={userId,roomno,reason,dayS,monS,yearS,hrS,minS,durationS};

		for(int counter=0;counter<Availabledata.size();counter++)
		{
			String[] obj=Availabledata.get(counter);
			if(obj[0].equals(dummy[0])&&obj[1].equals(dummy[1])&&obj[2].equals(dummy[2])&&obj[3].equals(dummy[3])&&obj[4].equals(dummy[4])&&obj[5].equals(dummy[5])&&obj[6].equals(dummy[6])&&obj[7].equals(dummy[7])&&obj[8].equals(dummy[8]))
			{
				if(obj[9].equals("1"))
					res=true;
				if(obj[9].equals("0"))
					res=false;
				break;
			}	
		}
		return res;
		/* for code
        ResultSet rs=stmt.executeQuery("select Avail from roomdb where room='"+roomno+"'");
    	rs.next();
    	String avail = rs.getString(1);
    	if (avail == "1")
    		return true;
    	else if (avail == "0")
    		return false;
		return false;
		*/
	}
	
	public void queue(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		String dayS=day+"";
		String monS=mon+"";
		String yearS=year+"";
		String hrS=hr+"";
		String minS=min+"";
		String durationS=duration+"";
		String[] dummy={userId,roomno,reason,dayS,monS,yearS,hrS,minS,durationS,"0"};
		Requestdata.add(dummy);
		
		//stmt.executeUpdate("update roomdb set query = '1' where room = '" + roomno + "'");
	}

	public int checkPermission(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration) throws SQLException
	{
		/*
        ResultSet rs=stmt.executeQuery("select Req from roomdb where room='"+roomno+"'");
    	rs.next();
    	String req = rs.getString(1);
    	if (req == "0")
    	{
            rs=stmt.executeQuery("select Avail from roomdb where room='"+roomno+"'");
        	rs.next();
        	req = rs.getString(1);
        	if(req == "0")
        		return 1;
        	else if(req == "1")
        		return 2;
    	}
		return 0;
		*/
		String dayS=day+"";
		String monS=mon+"";
		String yearS=year+"";
		String hrS=hr+"";
		String minS=min+"";
		String durationS=duration+"";
		String[] dummy={userId,roomno,reason,dayS,monS,yearS,hrS,minS,durationS,"0"};
		
		for(int counter=0;counter<Requestdata.size();counter++)
		{
			String[] obj=Requestdata.get(counter);
			if(obj[0].equals(dummy[0])&&obj[1].equals(dummy[1])&&obj[2].equals(dummy[2])&&obj[3].equals(dummy[3])&&obj[4].equals(dummy[4])&&obj[5].equals(dummy[5])&&obj[6].equals(dummy[6])&&obj[7].equals(dummy[7])&&obj[8].equals(dummy[8]))
			{
				return Integer.parseInt(dummy[9]);
			}
		}
		return -1;	//to show error
	}
	
	public void decide(String userId,String roomno,String reason,int day,int mon,int year,int hr,int min,int duration,int decision) throws SQLException
	{//final variable tells accepted or rejected
		/*
		int dec;
        ResultSet rs=stmt.executeQuery("select room from roomdb where query='1'");
    	while(rs.next())
    	{
    		String req = rs.getString(1);
    		System.out.println("Room is " + req);
    		System.out.println("Press 1 to accept and 0 to deny");
    		dec = scan.nextInt();
    		if(dec == 1)
    			stmt.executeUpdate("update roomdb set query = '0', Avail = '0' where room = '" + roomno + "'");
    		else if(dec == 0)
    			stmt.executeUpdate("update roomdb set query = '0', Avail = '1' where room = '" + roomno + "'");
    	}
    	*/
		String dayS=day+"";
		String monS=mon+"";
		String yearS=year+"";
		String hrS=hr+"";
		String minS=min+"";
		String durationS=duration+"";
		String[] dummy={userId,roomno,reason,dayS,monS,yearS,hrS,minS,durationS,"1"};
		for(int counter=0;counter<Requestdata.size();counter++)
		{
			String[] obj=Requestdata.get(counter);
			if(obj[0].equals(dummy[0])&&obj[1].equals(dummy[1])&&obj[2].equals(dummy[2])&&obj[3].equals(dummy[3])&&obj[4].equals(dummy[4])&&obj[5].equals(dummy[5])&&obj[6].equals(dummy[6])&&obj[7].equals(dummy[7])&&obj[8].equals(dummy[8]))
			{
				obj[9]=decision+"";
				break;
			}
		}
		if(decision==2)
			Availabledata.add(dummy);
	}
}