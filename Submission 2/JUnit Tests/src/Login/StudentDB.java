package Login;
	
import java.sql.*;	

public class StudentDB		//replace UserDB with StudentDB everywhere
{
	String xusr, xpwd;
	String dbname, dbid;
	String flag;
	public Boolean adminlogin = null;
    Connection con;
    Statement stmt;
    int cnt=-1;
    
    //database table of the form username,password,dbname,dbid,adminFlag
    
    String[][] data={{"Aditya","1234","aditya","adi","1"},{"Shankar","abc","shankar","shank","0"}};

	
	public StudentDB(LoginDetails lgn) throws SQLException
	{
		/*	when writing the code
		Driver myDriver = new oracle.jdbc.driver.OracleDriver();
		DriverManager.registerDriver( myDriver );
		con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","system","home");
        stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select count(*) from masterdb where username='"+lgn.usr+"' and password='"+lgn.pwd+"'");
        cnt = rs.getInt(0);        
        */
		for(int counter=0;counter<2;counter++)
		{
			if(data[counter][0].equals(lgn.usr) && data[counter][1].equals(lgn.pwd))
			{
				cnt=counter;
				xusr=lgn.usr;
				xpwd = lgn.pwd;
				if(data[counter][4].equals("1"))
					adminlogin=true;
				else
					adminlogin=false;
			}	
		}
		/* 	needed when code is implemented
        if(cnt!=-1)
        {
        	System.out.println("HI");
        	xusr = lgn.usr;
        	xpwd = lgn.pwd;
        	
        
        	rs=stmt.executeQuery("select Admin_flag from masterdb where username="+lgn.usr);
        	rs.next();
        	String flag = rs.getString(1);
        	rs=stmt.executeQuery("select Name from masterdb where username="+lgn.usr);
        	rs.next();
        	dbname = rs.getString(1);
        	rs=stmt.executeQuery("select ID from masterdb where username="+lgn.usr);
        	rs.next();
        	dbid = rs.getString(1);
        	
        	if (flag == "1")
        		adminlogin = true;
        	else if (flag == "0")
        		adminlogin = false;
        }
		*/
        //con.close();
		
	}
	
	public User isVerified()
	{
		User user = new User();
		user.name = dbname;
		user.id = dbid;
		user.usr = xusr;
		user.pwd = xpwd;
		if(adminlogin == true)
			user.admin = true;
		else if(adminlogin == false)
			user.admin = false;
		return user;
	}
}	