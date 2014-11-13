package Main;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import Login.*;

public class ResourceTest {

	@Test
	public void testResource() throws SQLException 
	{
		LoginDetails lgn = new LoginDetails("Aditya","1234");
		StudentDB db=new StudentDB(lgn);
		User u1=db.isVerified();
		int choice1=1;
		Resource r1=new Resource(u1,choice1);
		assertEquals(r1.cabFlag,false);
		assertEquals(r1.cabAFlag,true);
		assertEquals(r1.roomFlag,false);
		assertEquals(r1.roomAFlag,false);
		
	}

}
