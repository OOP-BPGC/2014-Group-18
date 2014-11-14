package Login;
import java.util.Scanner;

public class LoginDetails
{
	String usr, pwd;
	Scanner scan;
	
	public LoginDetails(String user,String password)
	{
		//for testing part
		usr=user;
		pwd=password;
		/*			will be used in code
		scan = new Scanner(System.in);
		System.out.println("Enter Login Username");
		usr = scan.next();
		System.out.println("Enter Login Password");
		pwd = scan.next();
		*/
	}
}