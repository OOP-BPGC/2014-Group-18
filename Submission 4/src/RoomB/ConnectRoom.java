package RoomB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Email.SendEmail;

public class ConnectRoom {
	public void Connection(String sql){
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
		      stmt.executeUpdate(sql);
		      
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
		
		   
	}
	public String Search(String sql,int operation,Room ro){ //operation 3 to send mail and operation 1 for checking availability
		//String result=""; 							operation 2 used for room admin
		// JDBC driver name and database URL
		   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL = "jdbc:mysql://localhost:3306/oop";

		   //  Database credentials
		   final String USER = "root";
		   final String PASS = "";
		   
		   //public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   long AppNo = 0;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		     // System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      //System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      //System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      //String sql = "SELECT * FROM `user` WHERE `User` = 'guptarohit9941'";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      //if(rs.next()==false){
		    	//  result=("no such user exists");
		      //}
		      
		      while(rs.next()){
		         //Retrieve by column name
		    	  String email = rs.getString("Email");
		    	  String reasondenied = rs.getString("Reason denied");
		         String roomno  = rs.getString("RoomNo");
		         int day = rs.getInt("Day");
		         int month = rs.getInt("Month");
		         int year = rs.getInt("Year");
		         double start = rs.getDouble("Start");
		         double end = rs.getDouble("End");
		         int granted = rs.getInt("Granted");
		         //System.out.println(granted);
		         AppNo = rs.getLong("AppNo");
		        if(operation==1){
		        	if(day!=ro.day)continue;
		        	if(month!=ro.mon)continue;
		        	if(year!=ro.year)continue;
		        	System.out.println(ro.start+" "+ro.end);
		        	System.out.println(start+" "+end);
		        	//System.out.println((ro.start>=start && ro.start<end) || ro.end>start);
		        	if(ro.end==start||ro.start==end){
		        		
		        		return "no problem";
		        	}
		        	if((ro.start>=start && ro.start<end) || ro.end>start) return "problem";
		        	
		        }
		        else if(operation==2) { if(granted==0){
		        	System.out.println("Application No. "+AppNo+" Room "+roomno+" is queued");}
		        	else if(granted==1){
		        		System.out.println("Application No. "+AppNo+" Room "+roomno+" is  booked");
		        	}
		        	else System.out.println("Permission for Room "+roomno+" was denied because "+reasondenied);
		        	continue;
		        }
		        else if(operation==3){
		        	String body="Your application for "+roomno+" has been ";
		        	String head = "Update on Room booking Application for Room "+roomno;
		        	if(granted==1){
		        		body = body+"approved for "+day+"-"+month+"-"+year+" at "+(int)start+":"+(int)((start-(int)start))*100+" for "+(int)(end-start)+" hours.";
		        	}
		        	else body = body+"denied because "+reasondenied+"\n"+" Details were "+day+"-"+month+"-"+year+" at "+(int)start+":"+(int)((start-(int)start))*100+" for "+(int)(end-start)+" hours."; 
		        	SendEmail se = new SendEmail();
		        	se.mail(email, body, head);
		        }
		        else;
		        
		      }
		      //if(result==""){
		    	 //result = "N";
		      //}
		      rs.close();//return "no problem";
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
		   if(AppNo==0){
			   return "0";
		   }
		   else{
			   return "no problem";
		   }
		
		   
	}
	
	public String[] getOldEntries(String sql){ //operation 3 to send mail and operation 1 for checking availability
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
		   long AppNo = 0;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		     // System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      //System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      //System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      //String sql = "SELECT * FROM `user` WHERE `User` = 'guptarohit9941'";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      //if(rs.next()==false){
		    	//  result=("no such user exists");
		      //}
		      
		      while(rs.next()){
		         //Retrieve by column name
		    	  String email = rs.getString("Email");
		    	  String reasondenied = rs.getString("Reason denied");
		         String roomno  = rs.getString("RoomNo");
		         int day = rs.getInt("Day");
		         int month = rs.getInt("Month");
		         int year = rs.getInt("Year");
		         double start = rs.getDouble("Start");
		         double end = rs.getDouble("End");
		         int granted = rs.getInt("Granted");
		         //System.out.println(granted);
		         AppNo = rs.getLong("AppNo");
		          
		         if(granted==0){
		        	results[count]=("Application No. "+AppNo+" Room "+roomno+" is queued");}
		        else if(granted==1){
		        	results[count]=("Application No. "+AppNo+" Room "+roomno+" is  booked");
		        	}
		        else results[count]=("Application No. "+AppNo+" for Room "+roomno+" was denied because "+reasondenied);
		        
		         count+=1;
		         continue;
		        
		       	        
		      }
		      if(count==0){
	    		  System.out.println("You do not have any bookings at this time"); return results;
	    	  }
		      rs.close();//return "no problem";
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
			         double start = rs.getDouble("Start");
			         double end = rs.getDouble("End");
			         int hour = (int)start;
			         int min = (int) ((start-hour)*100);
			         //int granted = rs.getInt("Granted");
			         AppNo = rs.getLong("AppNo");
			         //long fare = rs.getLong("Fare");
			         //String src = rs.getString("Source");
			         //String dst = rs.getString("Destination");
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
	
	public String getreason(String sql){ //operation 3 to send mail and operation 1 for checking availability
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
		   long AppNo = 0;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		     // System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      //System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      //System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      //String sql = "SELECT * FROM `user` WHERE `User` = 'guptarohit9941'";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      //if(rs.next()==false){
		    	//  result=("no such user exists");
		      //}
		      
		      while(rs.next()){
		         //Retrieve by column name
		    	  //String email = rs.getString("Email");
		    	  String reasondenied = rs.getString("Reason denied");
		        // String roomno  = rs.getString("RoomNo");
		         //int day = rs.getInt("Day");
		         //int month = rs.getInt("Month");
		        // int year = rs.getInt("Year");
		         //double start = rs.getDouble("Start");
		         //double end = rs.getDouble("End");
		         //int granted = rs.getInt("Granted");
		         //System.out.println(granted);
		         AppNo = rs.getLong("AppNo");
		          
		        String reason = rs.getString("Reason");
		        return reason; 
		       	        
		      }
		      rs.close();//return "no problem";
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
		   return "no";
		
		   
	}
	
	}


