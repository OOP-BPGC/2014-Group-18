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

	
	public UserDB(LoginDetails lgn) throws SQLException
	{	Connect conn1 = new Connect();
	String sql = "SELECT * FROM `user` WHERE `User` = '"+lgn.usr+"' AND `Password` = '"+lgn.pwd+"'";
	String result = conn1.Connection(sql);
	//System.out.println(result);
	if(result.matches("N")){
		System.out.println("No such user found");return;
	}
	else{xusr=lgn.usr;
		xpwd=lgn.pwd;
		dbid = result.substring(1, 13);
		xcontact = result.substring(13, 23);
		dbname = result.substring(23);
		String result1 = result.substring(0,1);
		if(result1.matches("1")) adminlogin = true;
		//System.out.println(dbname+" "+dbid+" "+adminlogin);
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