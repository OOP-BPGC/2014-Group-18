package Login;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class StudentDBTest {

	@Test
	public void testStudentDB() throws SQLException 
	{
		LoginDetails lgn = new LoginDetails("Aditya","1234");
		LoginDetails lgn2 = new LoginDetails("aditya","1234");
		LoginDetails lgn3 = new LoginDetails("Shankar","abc");
		StudentDB db=new StudentDB(lgn);
		StudentDB db2=new StudentDB(lgn2);
		StudentDB db3=new StudentDB(lgn3);
		assertEquals(db.adminlogin,true);
		assertEquals(db2.adminlogin,null);
		assertEquals(db3.adminlogin,false);
	}

}
