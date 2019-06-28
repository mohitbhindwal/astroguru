package com.astroguru.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.astroguru.panel.CHouse;
import com.astroguru.panel.House;
import com.astroguru.sign.Sign;

public class Chart extends JPanel{

	int rotatedNo = 0;
   
	LinkedList<House> houses = new LinkedList<House>() {
		@Override
		public int size() {
			return 12;
		}
	}; 
	
	
	public void setAscendentSign(int sign) {
		for (int i = 1; i <= 12; i++) {
			houses.add(new House(new Sign(sign++),i));
			if(sign > 12) {
				sign=1;
			}
		}
	}
	
	public House getHouseAsPerSign(int sign) {
		for (int i = 0; i < houses.size(); i++) {
			if(houses.get(i).getSign().getValue() == sign)
				return houses.get(i);
		}
		return null;
		
	}
	
	public House getHouse(int index) {
		return houses.get(index);
	}
	
	
	 

	private boolean drag = false;
	private Point dragLocation = new Point();
	JLabel cursor_position = new JLabel();

	public Chart(int x, int y) {
	
     	//setPreferredSize(((JSplitPane)getParent()).getComponent(0).getPreferredSize());
		setPreferredSize( new Dimension(x, y));
		setLayout(new GridLayout(1, 1));
		setBackground(Color.PINK);
		init();
	}
	
	public Chart( ) {
     	//setPreferredSize(((JSplitPane)getParent()).getComponent(0).getPreferredSize());
		setPreferredSize( new Dimension(200,200));
		setLayout(new GridLayout(1, 1));
		setBackground(Color.BLUE);
		init();
	}
	
	 
 

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		 g.clipRect(0, 0, getPreferredSize().width, getPreferredSize().height);
	//	JOptionPane.showMessageDialog(this, getPreferredSize()); 
		System.out.println("SSSSSSSSSSSSS"+ getPreferredSize());
		g.drawString("hello mohit", 100, 100);
		//((JSplitPane)getParent()).getComponent(0).getPreferredSize()
		//((JSplitPane)111getParent()).getComponent(1).getSize()
		
		for(int i = 0 ; i< houses.size();i++) {
			House house = houses.get(rotatedNo++);
			house.draw(g, getPreferredSize(), i+1);
			
			if(rotatedNo==12)
				rotatedNo=0;
		}
	}
	
	
	

	void init() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//	System.out.println(e.getLocationOnScreen());
			//	System.out.println(e.getComponent().getClass());

			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				cursor_position.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y);
				cursor_position.setText(e.getLocationOnScreen().getX() + "," + e.getLocationOnScreen().getY());
				//System.out.println(e.getLocationOnScreen());
				setToolTipText(e.getLocationOnScreen().getX() + "," + e.getLocationOnScreen().getY());
				 
			}
		});
	}

}
