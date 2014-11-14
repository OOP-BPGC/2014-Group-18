package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	public String Connection(String sql){
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
		      //System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      //String sql = "SELECT * FROM `user` WHERE `User` = 'guptarohit9941'";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      
		      while(rs.next()){
		         //Retrieve by column name
		         String id  = rs.getString("ID");
		         int admin = rs.getInt("Admin");
		         String name = rs.getString("Name");
		         String password = rs.getString("Password");
		         String contact = rs.getString("Contact");

		        
		         result = admin+id+contact+name;
		         //System.out.println(result);
		      }
		      if(result==""){
		    	  result = "N";
		      }
		      rs.close();
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

}
