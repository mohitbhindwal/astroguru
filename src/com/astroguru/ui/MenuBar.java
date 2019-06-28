package com.astroguru.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import freejyotish.gui.CalcPrefsDialog;

public class MenuBar extends JMenuBar {

	JMenu file, prefrences;
	JMenuItem i1, i2, i3, i4, i5;

	public MenuBar() {
		init();
	}

	private void init() {
		file = new JMenu("Menu");
		prefrences = new JMenu("Prefrences");
		i1 = new JMenuItem("Item 1");
		i2 = new JMenuItem("Item 2");
		i3 = new JMenuItem("Item 3");
		i4 = new JMenuItem("Prefrences");
		i5 = new JMenuItem("Item 5");
		file.add(i1);
		file.add(i2);
		file.add(i3);
		prefrences.add(i4);
		prefrences.add(i5);
		// file.add(prefrences); Sub Menu
		add(file);
		add(prefrences);
		i4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.dialog.showDialog();
			}
		});
	}

}
