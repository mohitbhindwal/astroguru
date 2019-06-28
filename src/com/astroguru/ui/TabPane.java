package com.astroguru.ui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.astroguru.panel.CPanel;

public class TabPane extends JTabbedPane {

	public TabPane() {
		init();
	}

	private void init() {
		setBounds(50, 50, 400, 400);
		System.out.println("@@@@@@@@"+getBounds().getSize());
		addTab("Basic", new CPanel(getWidth(), getHeight()));
		// setMnemonicAt(0, KeyEvent.VK_1);
	}

}
