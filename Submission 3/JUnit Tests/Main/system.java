package Main;
import java.sql.SQLException;

import Login.*;
import java.util.*;
import java.io.*;

public class system
{
	LoginDetails lgn;
	StudentDB sdb;
	User user;
	Resource res;
	//added by Aditya Sunil Joshi
	Scanner in=new Scanner(System.in);
	String userName,password;
	int choice;
	
	public system() throws ClassNotFoundException, SQLException
	{
		userName=in.next();
		password=in.next();
		choice=in.nextInt();
		lgn = new LoginDetails(userName,password);
		sdb = new StudentDB(lgn);
		
		if(sdb.adminlogin == null)
			return;

		user = sdb.isVerified();
		res = new Resource(user,choice);
		
}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		system sys = new system();
	}
}