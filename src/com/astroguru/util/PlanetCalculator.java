package com.astroguru.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.prefs.Preferences;

import com.astroguru.chart.D1;
import com.astroguru.chart.D9;
import com.astroguru.chart.RashiChart;
import com.astroguru.panel.HouseData;
import com.astroguru.planets.AllPlanets;
import com.astroguru.planets.Planet;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;

public class PlanetCalculator {

	private static final int iflag_SID = SweConst.SEFLG_SIDEREAL + SweConst.SEFLG_NONUT + SweConst.SEFLG_SPEED;
	private static final int iflag_TROP = SweConst.SEFLG_SPEED;
	SwissEph sw;
	private UserDetails nativeInfo;
	private Preferences prefs;
	private double tjd_ut;
	private String zodiac;
	private int iflag;
	private char houseSystem;
	private AllPlanets allPlanets;
	private HouseData houseData;
	private VargaData vargadata;

	public PlanetCalculator(UserDetails nativeInfo, Preferences prefs) {
		sw = new SwissEph();
		this.nativeInfo = nativeInfo;
		this.prefs = prefs;
		this.tjd_ut = getTJD_UT(nativeInfo);
		this.zodiac = prefs.get("mode", "sidereal");

		if (zodiac.equals("tropical"))
			this.iflag = iflag_TROP;
		else
			this.iflag = iflag_SID;
		String houseString = prefs.get("houseType", "Shripati");
		if (houseString.equals("Shripati"))
			houseSystem = 'O';
		else if (houseString.equals("Koch"))
			houseSystem = 'K';
		else if (houseString.equals("Placidus"))
			houseSystem = 'P';
		else if (houseString.equals("Alcabitus"))
			houseSystem = 'B';
		else if (houseString.equals("Regiomontanus"))
			houseSystem = 'R';
		else if (houseString.equals("Campanus"))
			houseSystem = 'C';
		else
			houseSystem = 'O'; // IF ALL ELSE FAILS, USE SHRIPATI HOUSES
		allPlanets = calculateAllPlanets(prefs);
		houseData = calculateHouseData(nativeInfo, allPlanets, prefs);
		vargadata = VargaDataCalculator.calculateVargaData(allPlanets, houseData.getAscendant());
	}

	public AllPlanets getAllPlanets() {
		return allPlanets;
	}

	public void setAllPlanets(AllPlanets allPlanets) {
		this.allPlanets = allPlanets;
	}

	public HouseData getHouseData() {
		return houseData;
	}

	public void setHouseData(HouseData houseData) {
		this.houseData = houseData;
	}

	public VargaData getVargadata() {
		return vargadata;
	}

	public void setVargadata(VargaData vargadata) {
		this.vargadata = vargadata;
	}

	public AllPlanets calculateAllPlanets(Preferences prefs) {

		AllPlanets planets = new AllPlanets();
		int ayanamsa = prefs.getInt("ayanamsa", 1);
		String node = prefs.get("node", "true");

		long iflgret;
		String snam = null;
		String retro = "";
		StringBuffer serr = new StringBuffer();
		double x2[] = new double[6];
		Planet planet;
		int p;
		int nd;
		if (zodiac.equals("sidereal")) {
			sw.swe_set_sid_mode(ayanamsa, 0, 0);
		}

		if (node.equals("true"))
			nd = 11;
		else
			nd = 10;
		System.out.println("node = " + nd);

		for (p = SweConst.SE_SUN; p <= nd; p++) {
			if (nd == 11 && p == 10) {
				continue;
			}

			// System.out.println("Calculating p#: " + p);
			iflgret = sw.swe_calc_ut(tjd_ut, p, (int) iflag, x2, serr);

			if (iflgret < 0) {
				System.out.print("error: " + serr.toString() + "\n");
			} else if (iflgret != iflag) {
				System.out.print("warning: iflgret != iflag. " + serr.toString() + "\n");
			}

			if (p <= 10) {
				snam = Constants.planetLongNames[p];
				planet = new Planet(snam, p);
				planet.setLongitude(x2[0]);// longitude
				planet.setLatitude(x2[1]);// latitude
				planet.setVelocity(x2[3]);// velocity in longitude
			} else {
				snam = "Rahu";
				planet = new Planet(snam, p);
				planet.setLongitude(x2[0]);// longitude
				planet.setLatitude(x2[1]);// latitude
				planet.setVelocity(x2[3]);// velocity in longitude
			}
			planets.setPlanet(planet);

		}
		snam = "Ketu";
		planet = new Planet(snam, p);
		double rahuLong = planets.getPlanet("Rahu").getLongitude();
		double ketuLong = (rahuLong + 180) % 360;
		planet.setLongitude(ketuLong);
		planet.setLatitude(planets.getPlanet("Rahu").getLatitude());
		planet.setVelocity(planets.getPlanet("Rahu").getVelocity());
		planets.setPlanet(planet);
		return planets;

	}

	public D1 calculateD1Chart(AllPlanets allplanets, int[][] vargaDataArray, double asc) {
		int[] d1signArrray = vargaDataArray[0];
		d1signArrray[13] = getRasi(asc);
		int rashiascsign = d1signArrray[13];
		D1 d1 = new D1();
		d1.setAscendentSign(rashiascsign);
		for (int i = 0; i < allPlanets.getSize(); i++) {
			d1signArrray[i] = getRasi(allPlanets.getPlanet(Constants.planetLongNames[i]).getLongitude());
			d1.getHouseAsPerSign(vargaDataArray[0][i]).addPlanet(allPlanets.getPlanet(Constants.planetLongNames[i]));
			System.out.print(allPlanets.getPlanet(Constants.planetLongNames[i]) + "Rasi Positions: "
					+ vargaDataArray[0][i] + "\n");
		}
		return d1;
	}
	
	
	public D9 calculateD9Chart(AllPlanets allplanets, int[][] vargaDataArray, double asc) {
		int[] d1signArrray = vargaDataArray[7];
		d1signArrray[13] = getNavamsa(asc);
		int rashiascsign = d1signArrray[13];
		D9 d9 = new D9();
		d9.setAscendentSign(rashiascsign);
		for (int i = 0; i < allPlanets.getSize(); i++) {
			d1signArrray[i] = getNavamsa(allPlanets.getPlanet(Constants.planetLongNames[i]).getLongitude());
			d9.getHouse(i).addPlanet(allPlanets.getPlanet(Constants.planetLongNames[i]));
			System.out.print(allPlanets.getPlanet(Constants.planetLongNames[i]) + "Rasi Positions: "
					+ vargaDataArray[0][i] + "\n");
		}
		return d9;
	}
	
	//Navamsa
		/**
		 *  Gets the navamsa attribute of the Vargas class
		 *
		 *@param  deg  Description of the Parameter
		 *@return      The navamsa value
		 */
		public static int getNavamsa(double deg)
		{
			//calculates V9 positions

			int house = 0;
			int x = 0;
			double nav = 3.33333333333;
			double signLoc = deg % 30;
			//gives it's location within a 30 degree arc
			int startSign = (int) (deg / 30) + 1;
			//tells which sign it's in
			for (int i = 0; i < 30; i++)
			{

				if ((i * nav) <= signLoc && signLoc < (i + 1) * nav)
				{
					x = (int) i;
					//System.out.println("X = " + x);
					break;
				}
			}
			switch (startSign)
			{
							case 1:
							case 4:
							case 7:
							case 10:
								house = startSign + x;
								break;
							case 2:
							case 5:
							case 8:
							case 11:
								house = startSign + 8 + x;
								break;
							case 3:
							case 6:
							case 9:
							case 12:
								house = startSign + 4 + x;
								break;
			}
			house = house % 12;
			if (house == 0)
			{
				house = 12;
			}
			return house;

		}


	public static int getRasi(double deg) {
		int house = (int) (deg / 30) + 1;
		return house;

	}

	public HouseData calculateHouseData(UserDetails nativeInfo, AllPlanets allPlanets, Preferences prefs) {

		double lon = getDecimalLongitude(nativeInfo);
		double lat = getDecimalLatitude(nativeInfo);
		double[] cusp = new double[13];
		double[] ascmc = new double[10];

		System.out.println("House System = " + houseSystem);////////// TESTING ONLY

//		the actual calculation
		sw.swe_houses(tjd_ut, iflag, lat, lon, houseSystem, cusp, ascmc);

		// System.out.println("Ascendant = " + FJUtility.zodiacDMS(ascmc[0]));
		HouseData houseData = new HouseData(ascmc, cusp);
		houseData.setHouseSystem(new Character(houseSystem).toString());
		System.out.println(houseData);
		return houseData;

	}

	public static double getTJD_UT(UserDetails nativeInfo) // converts local time to the Julian Date of GMT
	{
		int year = nativeInfo.getBirthYear();
		int mon = nativeInfo.getBirthMonth();
		int day = nativeInfo.getBirthDate();
		;
		int hour = nativeInfo.getBirthHour();
		;
		int min = nativeInfo.getBirthMinute();
		int sec = nativeInfo.getBirthSecond();
		double offset = nativeInfo.getTimeZoneOffset();
		int dst = nativeInfo.getDSTOffset();
		Calendar birth = new GregorianCalendar(year, mon - 1, day, hour, min, sec);
		birth.add(Calendar.SECOND, (int) (-3600 * (offset + dst)));
		// Changes time to UTC
		double UTHour = (double) birth.get(Calendar.HOUR_OF_DAY);
		double UTMin = (double) birth.get(Calendar.MINUTE);
		double UTSec = (double) birth.get(Calendar.SECOND);
		double ut = UTHour + UTMin / 60 + UTSec / 3600;
		SweDate sd = new SweDate(birth.get(Calendar.YEAR), birth.get(Calendar.MONTH) + 1, birth.get(Calendar.DATE), ut);
		return sd.getJulDay();
	}

	private static double getDecimalLongitude(UserDetails nativeInfo) {
		double longitude = (double) Math.abs(nativeInfo.getLongitude_Deg())
				+ ((double) nativeInfo.getLongitude_Min()) / 60 + ((double) nativeInfo.getLongitude_Sec()) / 3600;
		if (nativeInfo.getLongitude_Deg() < 0)
			longitude = -1 * longitude;
		// System.out.println("Longitude = " + longitude);///TEST CODE
		return longitude;
	}

	private static double getDecimalLatitude(UserDetails nativeInfo) {
		double latitude = (double) Math.abs(nativeInfo.getLatitude_Deg()) + ((double) nativeInfo.getLatitude_Min()) / 60
				+ ((double) nativeInfo.getLatitude_Sec()) / 3600;
		if (nativeInfo.getLatitude_Deg() < 0)
			latitude = -1 * latitude;
		// System.out.println("Latitude = " + latitude);///TEST CODE
		return latitude;
	}

}
