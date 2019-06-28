package com.astroguru.panel;

import java.util.ArrayList;
import java.util.Iterator;

import com.astroguru.planets.Planet;
import com.astroguru.sign.Sign;

public class House extends CHouse implements Iterator<House> {

	int house = -1;
	private House next = null;
	private House previous = null;


	public House(int house, House next, House previous, Sign sign) {
		this.house = house;
		this.next = next;
		this.previous = previous;
		this.sign = sign;
	}
	
	
	@Override
	public String toString() {
		return "House [house=" + house + ", sign=" + sign + ", planets=" + planets + "]";
	}


	public  void  addPlanet(Planet planet) {
		planets.add(planet);
	}

	public House(Sign sign,int house) {
		this.sign = sign;
		this.house=house;
	}

	public Sign getSign() {
		return sign;
	}
	
	
	@Override
	public boolean hasNext() {
		return this.house != 12;
	}

	@Override
	public House next() {
		return next;
	}

	public House getHouse(int number) {
		House house = null;
		while (number-- > 1) {
			house = this.next;
		}
		return house;
	}

	public House getPreviousHouse(int number) {
		House house = null;
		while (number-- > 1) {
			house = this.previous;
		}
		return house;
	}

	public ArrayList<House> getNHouseAhead(int nth) {
		ArrayList<House> list = new ArrayList<House>(nth);
		while (nth-- > 1) {
			list.add(this.next);
		}
		return list;
	}

	public ArrayList<House> getNHousePrevious(int nth) {
		ArrayList<House> list = new ArrayList<House>(nth);
		while (nth-- > 1) {
			list.add(this.previous);
		}
		return list;
	}

}
