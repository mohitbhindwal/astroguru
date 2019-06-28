package com.astroguru.chart;

import com.astroguru.panel.House;

public class D9 extends Chart{

	public D9(int x, int y) {
		super(x, y);
	}
	
	public D9( ) {
	}
	
	public void addHouse(House house){
		houses.add(house);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		for(House house : houses) {
			str = str + " " +houses.toString();
		}
		System.out.println("!!!!!!!!"+str);
		return str;
	}
	
		
}


