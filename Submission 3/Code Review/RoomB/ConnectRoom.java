package RoomB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	String Search(String sql,int operation,Room ro){
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
		         String roomno  = rs.getString("RoomNo");
		         int day = rs.getInt("Day");
		         int month = rs.getInt("Month");
		         int year = rs.getInt("Year");
		         double start = rs.getDouble("Start");
		         double end = rs.getDouble("End");
		         int granted = rs.getInt("Granted");
		         long AppNo = rs.getLong("AppNo");
		        if(operation==1){
		        	if(day!=ro.day)continue;
		        	if(month!=ro.mon)continue;
		        	if(year!=ro.year)continue;
		        	if(ro.start>=start && ro.start<end) return "problem";
		        	
		        }
		        else { if(granted==0){
		        	System.out.println("Application No. "+AppNo+" Room "+roomno+" is queued");}
		        	else if(granted==1){
		        		System.out.println("Application No. "+AppNo+" Room "+roomno+" is  booked");
		        	}
		        	else System.out.println("Permission for Room "+roomno+" was denied");
		        	continue;
		        }
		        
		      }
		      //if(result==""){
		    	 //result = "N";
		      //}
		      rs.close();return "no problem";
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace(); return "no problem";
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();return "no problem";
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
		   
	}
	}


