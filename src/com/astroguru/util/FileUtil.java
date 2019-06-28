package com.astroguru.util;

import freejyotish.main.BirthData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
*	Created 15 April, 2003 by Michael W. Taft
*/

public class FileUtil
{
	private HashMap<String,UserDetails> natives;
	private BufferedReader in;
	private UserDetails nativeInfo;
	
	public void readNativeFile(String path, String filename)
		{	
			File file = new File(path + File.separator + filename);
			System.out.println
       (file + (file.exists() ? " exists." : " does not exist."));
       
			try {
				
					in = new BufferedReader(new FileReader(path + File.separator + filename));
					natives = new HashMap<String,UserDetails>();
					readData(in);
				
			
			} catch (java.io.IOException e)
				{
					System.out.println(e);
				}
		}
	
	public void readData(BufferedReader in)throws java.io.IOException
		{
			natives = new HashMap<String,UserDetails>();
			
			
			while (in.read()!= -1)
			{
                try {
                    String s = in.readLine();
                    StringTokenizer st = new StringTokenizer(s, "|");
                    nativeInfo = new UserDetails();
                    nativeInfo.setFirstName(st.nextToken()); 
                    nativeInfo.setLastName(st.nextToken());
                    nativeInfo.setBirthCity(st.nextToken());
                    nativeInfo.setBirthState(st.nextToken());
                    nativeInfo.setBirthCountry(st.nextToken());
                    nativeInfo.setLongitude_Deg(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLongitude_Min(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLongitude_Sec(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLatitude_Deg(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLatitude_Min(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLatitude_Sec(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthYear(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthMonth(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthDate(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthHour(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthMinute(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthSecond(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setTimeZoneOffset(Double.valueOf(st.nextToken()).doubleValue());
                    nativeInfo.setDSTOffset(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setSex(Integer.valueOf(st.nextToken()).intValue());
                    natives.put(nativeInfo.getFirstName(), nativeInfo);
                    System.out.println("@@@@@@@@"+natives);
                } catch( NoSuchElementException nsee ) {
                    //One of the above required element not found.
                    //skip.
                }
		 catch( java.lang.NumberFormatException nfe ) 
		 {
			 System.out.println("NumberFormatException for " + nativeInfo.toString());
			  System.out.println(nfe);
                    continue;
                }
			}
		}
	
	public HashMap<String,UserDetails> getNatives()
	{
		return natives;
	}

	////TESTING ONLY//////
	public static void main (String[] test)
	{
		FileUtil rnf = new FileUtil();
		String path = "F:\\myworkspace\\AstroGuru\\charts";
		String filename = "natives.txt";
		rnf.readNativeFile(path, filename);
		/*NativeFileWriter wnf = new NativeFileWriter();
		wnf.writeAllNatives(rnf.getNatives(), path, "nativesCopy.txt");
		rnf.readNativeFile(path, "nativesCopy.txt");
		wnf.writeAllNatives(rnf.getNatives(), path, "nativesCopyAgain.txt");*/
	}
	


	private PrintWriter os;
	
	public void writeNative(BirthData ni) throws IOException
	{
		
			try {
					os = new PrintWriter(new FileWriter("natives.txt"));
					} catch (Exception ex)
						{
							System.out.print(ex);
						}
			String output = createNativeInfoString(ni);	
			os.print(output);
			os.close();
		
		
	}
	
	public void writeAllNatives(HashMap natives, String path, String filename) 
	{
		
			try {
					os = new PrintWriter(new FileWriter(path + File.separator + filename + ".txt"));
					
			Iterator iter = natives.values().iterator();
			while (iter.hasNext())
			{
			BirthData nativeInfo = (BirthData) iter.next();
			String output = createNativeInfoString(nativeInfo);	
			os.println(output);
			}
			os.close();
		} catch (IOException ex)
						{
							System.out.print(ex);
						}
		
	}

	public String createNativeInfoString(BirthData ni)
	{
		StringBuffer construct = new StringBuffer("|" + ni.getFirstName() +  "|");
		construct.append(ni.getLastName() + "|");
		construct.append(ni.getBirthCity() + "|");
		construct.append(ni.getBirthState() + "|");
		construct.append(ni.getBirthCountry() + "|");
		construct.append(ni.getLongitude_Deg() + "|");
		construct.append(ni.getLongitude_Min() + "|");
		construct.append(ni.getLongitude_Sec() + "|");
		//construct.append(ni.getIsEast() + "|");
		construct.append(ni.getLatitude_Deg() + "|");
		construct.append(ni.getLatitude_Min() + "|");
		construct.append(ni.getLatitude_Sec() + "|");
		//construct.append(ni.getIsNorth() + "|");
		construct.append(ni.getBirthYear() + "|");
		construct.append(ni.getBirthMonth() + "|");
		construct.append(ni.getBirthDate() + "|");
		construct.append(ni.getBirthHour() + "|");
		construct.append(ni.getBirthMinute() + "|");
		construct.append(ni.getBirthSecond() + "|");
		construct.append(ni.getTimeZoneOffset() + "|");
		construct.append(ni.getDSTOffset() + "|");
		construct.append(ni.getSex() + "|");
		System.out.println(construct);
		return construct.toString();
	}



	
}
