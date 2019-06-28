package com.astroguru.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.astroguru.planets.Planet;
import com.astroguru.sign.Sign;

public class CHouse extends Polygon {

	protected ArrayList<Planet> planets = new ArrayList<Planet>();
	protected Sign sign;

	public void draw(Graphics g, Dimension d, int displayNumber) {
	//	System.out.println("$$$$$$$$$$$$$$" + planets);
		if (displayNumber == 1) {
			addPoint(d.width / 2, 0);
			addPoint(d.width / 4, d.height / 4);
			addPoint(d.width / 2, d.height / 2);
			addPoint(d.width / 2, d.height / 2);
			addPoint(d.width / 4 * 3, d.height / 4);
			g.drawString(sign !=null ? sign !=null ? String.valueOf(sign.getValue()):"":"", d.width / 2, d.height / 2 - 20);
			g.drawPolygon(this);
			

			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}

		} else if (displayNumber == 2) {
			addPoint(0, 0);
			addPoint(d.width / 4, d.height / 4);
			addPoint(d.width / 2, 0);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"", d.width / 4, d.height / 4 - 10);
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 3) {
			addPoint(0, 0);
			addPoint(0, d.height / 2);
			addPoint(d.width / 4, d.height / 4);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"", d.width / 4 -50 , d.height / 4 + 5 );
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 4) {
			addPoint(0, d.height / 2);
			addPoint(d.width / 4, d.height / 4);
			addPoint(d.width / 2, d.height / 2);
			addPoint(d.width / 4, d.height / 4 * 3);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 2 -50, d.height / 2);
			
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 5) {
			addPoint(0, d.height / 2);
			addPoint(0, d.height);
			addPoint(d.width / 4, d.height / 4 * 3);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 4  - 40, d.height / 4 * 3);
			
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 6) {
			addPoint(0, d.height);
			addPoint(d.width / 2, d.height);
			addPoint(d.width / 4, d.height / 4 * 3);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 4, d.height / 4 * 3+20);
			
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 7) {
			addPoint(d.width / 4, d.height / 4 * 3);
			addPoint(d.width / 2, d.height);
			addPoint(d.width / 4 * 3, d.height / 4 * 3);
			addPoint(d.width / 2, d.height / 2);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 2, d.height / 2 + 20 );
			
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 8) {
			addPoint(d.width / 2, d.height);
			addPoint(d.width, d.height);
			addPoint(d.width / 4 * 3, d.height / 4 * 3);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 4 * 3, d.height / 4 * 3 +20 );
			
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 9) {
			addPoint(d.width / 4 * 3, d.height / 4 * 3);
			addPoint(d.width, d.height);
			addPoint(d.width, d.height / 2);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 4 * 3 + 20, d.height / 4 * 3 + 5);
			
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(
							i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 10) {
			addPoint(d.width / 2, d.height / 2);
			addPoint(d.width / 4 * 3, d.height / 4 * 3);
			addPoint(d.width, d.height / 2);
			addPoint(d.width / 4 * 3, d.height / 4);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 2 + 25, d.height / 2+5 );
				
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 11) {
			addPoint(d.width / 4 * 3, d.height / 4);
			addPoint(d.width, d.height / 2);
			addPoint(d.width, 0);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 4 * 3 + 20 , d.height / 4 + 5 );
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		} else if (displayNumber == 12) {
			addPoint(d.width / 2, 0);
			addPoint(d.width / 4 * 3, d.height / 4);
			addPoint(d.width, 0);
			g.drawString(sign !=null ? String.valueOf(sign.getValue()):"",d.width / 4 * 3 , d.height / 4 - 20);
			
			g.drawPolygon(this);
			if (!planets.isEmpty() && planets.size() > 0) {
				for (int i = 0; i < planets.size(); i++) {
					g.drawString(planets.get(i).getPlanetName(), (int) getBounds2D().getCenterX()+ i*50,
							(int) getBounds2D().getCenterY());
				}
			}
		}

	}

	/**
	 * Description of the Method
	 *
	 * @param deg Description of the Parameter
	 * @return Description of the Return Value
	 */
	public static double toRadians(double deg) {
		return deg * (Math.PI / 180);
	}

}
