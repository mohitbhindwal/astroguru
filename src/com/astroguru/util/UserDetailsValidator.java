package com.astroguru.util;
import freejyotish.main.BirthData;
/**
 * @author Michael W. Taft
 * 16 May, 2003
 * 
 */
public class UserDetailsValidator {
	///CURRENTLY CHECKS A BIRTH DATA OBJECT FOR BASIC DATA BOUNDARY VIOLATIONS
	public static void checkBirthDataValidity(BirthData inputData)
		throws Exception {
		try {
			checkLongitude_Deg(inputData.getLongitude_Deg());
			checkLatitude_Deg(inputData.getLatitude_Deg());
			checkMinSec(inputData.getLongitude_Min());
			checkMinSec(inputData.getLatitude_Min());
			checkMinSec(inputData.getLongitude_Sec());
			checkMinSec(inputData.getLatitude_Sec());
			checkDSTOffset(inputData.getDSTOffset());
			checkTimeZoneOffset(inputData.getTimeZoneOffset());
			compareTZandLongitude(
				inputData.getTimeZoneOffset(),
				inputData.getLongitude_Deg());
		} catch (Exception fje) {
			System.out.println(fje);
		}
	}
	private static void checkLongitude_Deg(int longitude_Deg)
		throws Exception {
		String errorMsg = "Longitude Degree value out of bounds.";
		if (-180 > longitude_Deg || longitude_Deg > 180)
			throw new Exception(errorMsg);
	}
	private static void checkLatitude_Deg(int latitude_Deg)
		throws Exception {
		String errorMsg = "Latitude Degree value out of bounds.";
		if (-90 > latitude_Deg || latitude_Deg > 90)
			throw new Exception(errorMsg);
	}
	private static void checkMinSec(int minSec)
		throws Exception {
		String errorMsg =
			"Longitude/Latitude minutes or seconds value out of bounds.";
		if (0 > minSec || minSec > 60)
			throw new Exception(errorMsg);
	}
	private static void checkDSTOffset(int dstOffset)
		throws Exception {
		String errorMsg = "Daylight Savings Time value out of bounds.";
		if (0 > dstOffset && dstOffset > 2)
			throw new Exception(errorMsg);
	}
	private static void checkTimeZoneOffset(double tzOffset)
		throws Exception {
		String errorMsg = "Time Zone value out of bounds.";
		if (-13.0 > tzOffset || tzOffset > 13.0)
			throw new Exception(errorMsg);
	}
	public static void compareTZandLongitude(
		double tzOffset,
		int longitude_Deg)
		throws Exception {
		//Sign on value must be + or - for both. They cannot be different signs.
		String errorMsg = "Time Zone and Longitude are mismatched.";
		if (0 <= longitude_Deg && 0.0 <= tzOffset)
			return;
		else if (0 >= longitude_Deg && 0.0 >= tzOffset)
			return;
		else
			throw new Exception(errorMsg);
	}
	public static void main(String[] args) {
	}
}
