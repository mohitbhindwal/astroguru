package com.astroguru.sign;

import com.astroguru.util.Constants;

public class Sign {

	int sign ;
	String name;
	String shortName ;
	
	public Sign(int sign) {
		 this.sign=sign;
		 name=Constants.zodiacFullNames[sign-1]; 
		 shortName=Constants.zodiacShortNames[sign-1];
	}
	
	public int getValue() {
		return sign;
	}
	
	@Override
	public String toString() {
		return "Sign [sign=" + sign + ", name=" + name + ", shortName=" + shortName + "]";
	}

	public Sign() {
		if(true)
		throw new RuntimeException();
		 this.sign=sign;
		 name=Constants.zodiacFullNames[sign-1]; 
		 shortName=Constants.zodiacShortNames[sign-1];
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Sign && this.sign==((Sign)obj).getSign();
	}
	
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
		//Names of Signs
		/**
		 *  Description of the Field
		 */
		public static final int ARIES = 1;
		/**
		 *  Description of the Field
		 */
		public static final int TAURUS = 2;
		/**
		 *  Description of the Field
		 */
		public static final int GEMINI = 3;
		/**
		 *  Description of the Field
		 */
		public static final int CANCER = 4;
		/**
		 *  Description of the Field
		 */
		public static final int LEO = 5;
		/**
		 *  Description of the Field
		 */
		public static final int VIRGO = 6;
		/**
		 *  Description of the Field
		 */
		public static final int LIBRA = 7;
		/**
		 *  Description of the Field
		 */
		public static final int SCORPIO = 8;
		/**
		 *  Description of the Field
		 */
		public static final int SAGITTARIUS = 9;
		/**
		 *  Description of the Field
		 */
		public static final int CAPRICORN = 10;
		/**
		 *  Description of the Field
		 */
		public static final int AQUARIUS = 11;
		/**
		 *  Description of the Field
		 */
		public static final int PISCES = 12;


	
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
