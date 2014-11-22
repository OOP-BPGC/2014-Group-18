package Login;
	
import java.sql.*;	
//import databaseProg.*;

public class UserDB 
{
	String xusr, xpwd,xcontact;
	String dbname, dbid;
	public Boolean adminlogin = false;
    Connection con;
    Statement stmt;
    int cnt;

	
	public String UserDBVerify(String usr, String pwd) throws SQLException
	{	Connect conn1 = new Connect();
	String sql = "SELECT * FROM `user` WHERE `User` = '"+usr+"' AND `Password` = '"+pwd+"'";
	
	String result = conn1.Connection(sql);
	//System.out.println(result);
	//System.out.println(result + " "+sql);
	if(result.matches("N")){
		return "Unsuccessful";
	}
	else{xusr=usr;
		xpwd=pwd;
		
		String contactlength = result.substring(1, 3);
		int cl = Integer.parseInt(contactlength);//System.out.println(cl);
		dbid = result.substring(3, 15);//ID
		xcontact = result.substring(15, 15+cl);//contact
		dbname = result.substring(15+cl);//name
		String result1 = result.substring(0,1);//admin login check
		if(result1.matches("1")) adminlogin = true;
		//System.out.println(xcontact);
		//System.out.println(dbname+" "+dbid+" "+adminlogin);
		return result1+"Successful";
	}
	
	}
	
	public User isVerified()
	{
		User user = new User();
		user.name = this.dbname;
		//System.out.println(user.name);
		if(user.name==null)System.exit(0);
		user.id = dbid;
		user.contact=xcontact;
		user.usr = xusr;
		user.pwd = xpwd;
		if(adminlogin == true)
			user.admin = true;
		else if(adminlogin == false)
			user.admin = false;
		return user;
	}
}	