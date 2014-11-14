package Main;
import java.sql.SQLException;

import Login.*;

public class System1
{
	LoginDetails lgn;
	UserDB udb;
	User user;
	Resource res;
	
	public System1() throws ClassNotFoundException, SQLException
	{
		lgn = new LoginDetails();
		udb = new UserDB(lgn);
		
		
		//if(udb==null)return;
		user = udb.isVerified();
		//System.out.println(user.usr);
		res = new Resource();
		res.ResourceInp(user);
		
}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		System1 sys = new System1();
	}
}