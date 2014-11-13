package CabB;

import static org.junit.Assert.*;

import org.junit.Test;

public class CabDBTest {

	@Test
	public void testUpdate() {
		String[] entry1={"Bits","Panjim","adi","5","8","2014","17","30","2"};
		CabDB db=new CabDB();
		db.update("Bits","Panjim","adi",5,8,2014,17,30,2);
		/*for(int counter=0;counter<db.data.size();counter++)
		{
			String[] obj=db.data.get(counter);
			System.out.println(obj[0]+" "+obj[1]+" "+obj[2]+" "+obj[3]+" "+obj[4]+" "+obj[5]+" "+obj[6]+" "+obj[7]+" "+obj[8]);
		}
		*/
		assertEquals(true,db.contains("Bits","Panjim","adi",5,8,2014,17,30,2));
		assertEquals(false,db.contains("Zari","Madgaon","adi",5,8,2014,17,30,2));
		
	}

	@Test
	public void testRemove() 
	{
		CabDB db=new CabDB();
		db.remove("Bits","Panjim","adi",1,8,2014,17,30,2);
		assertEquals(false,db.contains("Bits","Panjim","adi",1,8,2014,17,30,2));
	}

}
