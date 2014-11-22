package Main;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import Login.*;

public class System1
{	String usr,pwd;
	//LoginDetails lgn;
	UserDB udb;
	User user;
	Resource res;
	
	public System1() throws ClassNotFoundException, SQLException, ParseException
	{	Scanner scan = new Scanner(System.in);
		//usr = scan.next();
		//pwd = scan.next();
		String success = "Unsuccessful";
		while(success.matches("Unsuccessful")){
			//lgn = new LoginDetails();
			udb = new UserDB();
			success = udb.UserDBVerify(usr,pwd);
			if(success.matches("Unsuccessful")) System.out.println("Wrong login details"+"\n"+"Login Again....");
		}
		
		String choice="y";	
		//if(udb==null)return;
		user = udb.isVerified();
		//System.out.println(user.usr);
		while(choice.matches("y")){
			res = new Resource();
			res.ResourceInp(user);
			System.out.println("Do you want to do another activity?? ");
			System.out.println("Press 'y' to continue else 'n' to exit");
			choice=scan.next();
			choice.toLowerCase();
			if(choice.matches("n")) {
				System.out.println("Thanks for visiting us...");
				System.out.println("Exiting.......");
			}
			
		}
		
		
}
	
	
}