package  com.astroguru.util;

import java.util.Arrays;

/**
 *  Description of the Class
 *
 *@author     Michael W. Taft
 *@created    June 5, 2002
 */
public class VargaData
{
	private int[][] vargaData;
	
	public VargaData(int[][] data)
	{
		vargaData = data;
	}
	
	public void setVargaData(int [][] data)
	{
		vargaData = data;
	}
	public int[][] getVargaData()
	{
		return vargaData;
	}

	@Override
	public String toString() {
		return "VargaData [vargaData=" + Arrays.deepToString(vargaData) + "]";
	}
	
	

}






