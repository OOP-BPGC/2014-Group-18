package Login;
//import databaseProg.*;

public class StudentDB
{
	String xusr, xpwd;
	String dbname, dbid, dbcontact;
	public Boolean adminlogin = null;
	
	public StudentDB(LoginDetails lgn)
	{	//Connect conn = new Connect();
		//interact with database and verify the account
		
	}
	
	public User isVerified()
	{
		User user = new User();
		user.name = dbname;
		user.id = dbid;
		user.contact = dbcontact;
		user.usr = xusr;
		user.pwd = xpwd;
		if(adminlogin == true)
			user.admin = true;
		else if(adminlogin == false)
			user.admin = false;
		return user;
	}
}