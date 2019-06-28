package com.astroguru.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class CPanel extends JPanel {

	CHouse house1 = new CHouse();
	CHouse house2 = new CHouse();
	CHouse house3 = new CHouse();
	CHouse house4 = new CHouse();
	CHouse house5 = new CHouse();
	CHouse house6 = new CHouse();
	CHouse house7 = new CHouse();
	CHouse house8 = new CHouse();
	CHouse house9 = new CHouse();
	CHouse house10 = new CHouse();
	CHouse house11 = new CHouse();
	CHouse house12 = new CHouse();

	private boolean drag = false;
	private Point dragLocation = new Point();
	JLabel cursor_position = new JLabel();

	public CPanel(int x, int y) {
	
     	//setPreferredSize(((JSplitPane)getParent()).getComponent(0).getPreferredSize());
		setPreferredSize( new Dimension(x, y));
		setLayout(new GridLayout(1, 1));
		setBackground(Color.RED);
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
		house1.draw(g, getPreferredSize(), 1);
		house2.draw(g, getPreferredSize(), 2);
		house3.draw(g, getPreferredSize(), 3);
		house4.draw(g, getPreferredSize(), 4);
		house5.draw(g, getPreferredSize(), 5);
		house6.draw(g, getPreferredSize(), 6);
		house7.draw(g, getPreferredSize(), 7);
		house8.draw(g, getPreferredSize(), 8);
		house9.draw(g, getPreferredSize(), 9);
		house10.draw(g, getPreferredSize(), 10);
		house11.draw(g, getPreferredSize(), 11);
		house12.draw(g, getPreferredSize(), 12);
		 
		 
		 
	 

		

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
