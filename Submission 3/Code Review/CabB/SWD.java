package CabB;

import Login.User;

public class SWD
{
	public int farecalc(String source, String dest, User user, int duration)
	{
		int fare = 0;
		ConnectCab cc = new ConnectCab();
		String sql = "SELECT * FROM `cabroutes` WHERE `From` = '"+source+"'";
		fare=cc.cabRoute(sql, dest,0)*duration;
		return fare;
	}
	
	void farecollector(User user,int fare)
	{	int loan=0;
		String add = "SELECT SUM(Loan) FROM `oop`.`user` WHERE `ID` = '"+user.id+"'";
		ConnectCab cc = new ConnectCab();
		loan=cc.cabRoute(add, null, 1);	//returns present account balance
		//System.out.println(loan);
		loan = loan+fare;
		String amt = "UPDATE `oop`.`user` SET `Loan` = '"+loan+"' WHERE `ID` = '"+user.id+"' AND `user`.`Name` = '"+user.name+"'";
		cc.Connection(amt, 0);
	}
	
	void refund(User user,int cabid){
		ConnectCab cc = new ConnectCab();
		String amt = "SELECT * FROM `oop`.`cab` WHERE `cab`.`AppNo` = "+cabid;
		int amtr = cc.cabRoute(amt, null, 2);
		String add = "SELECT SUM(Loan) FROM `oop`.`user` WHERE `ID` = '"+user.id+"'";
		int loan=cc.cabRoute(add, null, 1);	//returns present account balance
		loan = loan-amtr;
		String latest = "UPDATE `oop`.`user` SET `Loan` = '"+loan+"' WHERE `ID` = '"+user.id+"' AND `user`.`Name` = '"+user.name+"'";
		cc.Connection(latest, 0);
	}
}
