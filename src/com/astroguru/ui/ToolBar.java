package com.astroguru.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	public ToolBar() {
		init();
	}

	private void init() {
		setRollover(true);
		JButton button = new JButton("File");
		add(button);
		addSeparator();
		add(new JButton("Edit"),BorderLayout.WEST);
	}

}
