package CabB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectCab {
	public Long Connection(String sql,int operation){   //0 = update, 1 = execute
			String result="";
		// JDBC driver name and database URL
		   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL = "jdbc:mysql://localhost:3306/oop";

		   //  Database credentials
		   final String USER = "root";
		   final String PASS = "";
		   
		   //public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      //System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      //System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      stmt = conn.createStatement();
		      if(operation==0)  stmt.executeUpdate(sql);
		      else {	long AppNo=0;
		    	  ResultSet rs = stmt.executeQuery(sql);
		    	  while(rs.next()){
				         //Retrieve by column name
				         //String roomno  = rs.getString("RoomNo");
				         int day = rs.getInt("Day");
				         int month = rs.getInt("Month");
				         int year = rs.getInt("Year");
				         int hour = rs.getInt("Hour");
				         int min = rs.getInt("Minutes");
				         //int granted = rs.getInt("Granted");
				         AppNo = rs.getLong("AppNo");
				         long fare = rs.getLong("Fare");
				         String src = rs.getString("Source");
				         String dst = rs.getString("Destination");
				         //System.out.println("Cab no "+AppNo+" on "+day+":"+month+":"+year+" from "+src+" to "+dst+" time in booking is "+hour+":"+min+" fare was "+fare);
				        
				        
				      }
		    	  //System.out.println(AppNo);
		    	  if(AppNo==0){
		    		  //System.out.println("You do not have any bookings at this time"); 
		    		  return AppNo;
		    	  }
		    	  else{
		    		  return (long) 1;
		    	  }
		    	  //return null;
		    	  
		      }
		      //return null;
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return (long) 0;
		
		   
	}
	public int cabRoute(String sql,String dst,int operation){ //o = for fare calculator and 1 = getting loan and 2 = getting amount to be refunded and 3 = for changing fare of routes
		//String result="";
		// JDBC driver name and database URL
			int cost=0;
		   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL = "jdbc:mysql://localhost:3306/oop";

		   //  Database credentials
		   final String USER = "root";
		   final String PASS = "";
		   
		   //public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      //System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      //System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      //System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      if(operation==3){
		    	  stmt.executeUpdate(sql);
		    	  //System.out.println(sql);
		      }
		      else{
		    	  ResultSet rs = stmt.executeQuery(sql);
			      //STEP 5: Extract data from result set
			      
			      while(rs.next()){
			         //Retrieve by column name
			        if(operation==0){
			        	cost = rs.getInt(dst);
			        }
			        else if(operation==1) cost = rs.getInt("SUM(Loan)");
			        else cost = rs.getInt("Fare");
			        return cost;
			        		       
			      }
			      
			      rs.close();//return "no problem";
		      }
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace(); //return "no problem";
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();//return "no problem";
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();//return "no problem";
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();//return "no problem";
		      }catch(SQLException se){
		         se.printStackTrace();//return "no problem";
		      }//end finally try
		   }//end try
		//return result;
		return cost;
		   
	}
	

public String[] Connection2(String sql){   //0 = update, 1 = execute
	//String result="";
	int count=0;
	String[] results = new String[100];
// JDBC driver name and database URL
   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   final String DB_URL = "jdbc:mysql://localhost:3306/oop";

   //  Database credentials
   final String USER = "root";
   final String PASS = "";
   
   //public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      //System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      stmt = conn.createStatement();
      
       	long AppNo=0;
    	  ResultSet rs = stmt.executeQuery(sql);
    	  while(rs.next()){
		         //Retrieve by column name
		         //String roomno  = rs.getString("RoomNo");
		         int day = rs.getInt("Day");
		         int month = rs.getInt("Month");
		         int year = rs.getInt("Year");
		         int hour = rs.getInt("Hour");
		         int min = rs.getInt("Minutes");
		         //int granted = rs.getInt("Granted");
		         AppNo = rs.getLong("AppNo");
		         long fare = rs.getLong("Fare");
		         String src = rs.getString("Source");
		         String dst = rs.getString("Destination");
		         
		         results[count]=("Cab no "+AppNo+" on "+day+"-"+month+"-"+year+" "+((hour*100)+min)+" HRS From "+src+" to "+dst+" fare Rs."+fare);
		         //System.out.println(results[count]);
		         count +=1;
		        
		      }
    	  //System.out.println(AppNo);
    	  if(AppNo==0){
    		  System.out.println("You do not have any bookings at this time"); return results;
    	  }
    	  //return null;
    	  
      
      //return null;
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
return results;

   
}

public String getDate(String sql){   //0 = update, 1 = execute
	String result="";
	
// JDBC driver name and database URL
   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   final String DB_URL = "jdbc:mysql://localhost:3306/oop";

   //  Database credentials
   final String USER = "root";
   final String PASS = "";
   
   //public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      //System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      stmt = conn.createStatement();
      
       	long AppNo=0;
    	  ResultSet rs = stmt.executeQuery(sql);
    	  while(rs.next()){
		         //Retrieve by column name
		         //String roomno  = rs.getString("RoomNo");
		         int day = rs.getInt("Day");
		         int month = rs.getInt("Month");
		         int year = rs.getInt("Year");
		         int hour = rs.getInt("Hour");
		         int min = rs.getInt("Minutes");
		         //int granted = rs.getInt("Granted");
		         AppNo = rs.getLong("AppNo");
		         long fare = rs.getLong("Fare");
		         String src = rs.getString("Source");
		         String dst = rs.getString("Destination");
		         String y = Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+" "+Integer.toString(hour)+":"+Integer.toString(min);
		         return y;
    	  }
    	
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
return result;

   
}

public int getDues(String sql){   //0 = update, 1 = execute
	//String result="";
	
// JDBC driver name and database URL
   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   final String DB_URL = "jdbc:mysql://localhost:3306/oop";

   //  Database credentials
   final String USER = "root";
   final String PASS = "";
   
   //public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      //System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      stmt = conn.createStatement();
      
       	long AppNo=0;
    	  ResultSet rs = stmt.executeQuery(sql);
    	  while(rs.next()){
		         int due = rs.getInt("Loan");
		         return due;
    	  }
    	
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
return 0;

   
}
}


