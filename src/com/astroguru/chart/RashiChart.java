package com.astroguru.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import com.astroguru.panel.House;

public class RashiChart extends Chart{

	public RashiChart(int x, int y) {
		super(x, y);
	}
	
	public RashiChart( ) {
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
