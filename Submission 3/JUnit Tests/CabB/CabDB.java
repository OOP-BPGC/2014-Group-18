package CabB;

import java.util.*;
import java.io.*;

public class CabDB
{
	
	String[] entry1={"Bits","Panjim","adi","1","8","2014","17","30","2"};
	String[] entry2={"Vasco","Madgaon","shank","26","12","2015","16","55","1"};
	static ArrayList<String[]> data=new ArrayList<String[]>();
	
	public CabDB()
	{
		data.add(entry1);
		data.add(entry2);
	}
	
	public void update(String source,String dest,String id,int day,int mon,int year,int hr,int min,int duration)
	{
		String dayS=day+"";
		String monS=mon+"";
		String yearS=year+"";
		String hrS=hr+"";
		String minS=min+"";
		String durationS=duration+"";
		String[] dummy={source,dest,id,dayS,monS,yearS,hrS,minS,durationS};
		data.add(dummy);
	}
	
	public void remove(String source,String dest,String id,int day,int mon,int year,int hr,int min,int duration)
	{
		String dayS=day+"";
		String monS=mon+"";
		String yearS=year+"";
		String hrS=hr+"";
		String minS=min+"";
		String durationS=duration+"";
		String[] dummy={source,dest,id,dayS,monS,yearS,hrS,minS,durationS};
		for(int counter=0;counter<data.size();counter++)
		{
			String[] obj=data.get(counter);
			if(obj.equals(dummy));
			data.remove(counter);
			break;
		}
	}
	
	public boolean contains(String source,String dest,String id,int day,int mon,int year,int hr,int min,int duration)
	{	
		boolean res=false;
		String dayS=day+"";
		String monS=mon+"";
		String yearS=year+"";
		String hrS=hr+"";
		String minS=min+"";
		String durationS=duration+"";
		String[] dummy={source,dest,id,dayS,monS,yearS,hrS,minS,durationS};
		for(int counter=0;counter<data.size();counter++)
		{
			String[] obj=data.get(counter);
			if((obj[0].equals(dummy[0])) && (obj[1].equals(dummy[1])) && (obj[2].equals(dummy[2])) && (obj[3].equals(dummy[3])) && (obj[4].equals(dummy[4])) && (obj[5].equals(dummy[5])) && (obj[6].equals(dummy[6])) && (obj[7].equals(dummy[7])) && (obj[8].equals(dummy[8])))
				res=true;
		}
		return res;
	}
}