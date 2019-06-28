package com.astroguru.ui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.util.Date;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	static GraphicsConfiguration gc;
	MenuBar menu = new MenuBar();
	TabPane tabPane = new TabPane();
	ToolBar toolBar = new ToolBar();
	static PrefrenceDialog dialog;
	static public int WIDTH = 0;
	static public int HEIGHT = 0;

	public MainFrame() {
		super(gc);
		init();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Astroguru: " + new Date());
		setVisible(true);
		WIDTH = getWidth();
		HEIGHT = getHeight();

	}

	public static GraphicsConfiguration getGc() {
		return gc;
	}

	public static void setGc(GraphicsConfiguration gc) {
		MainFrame.gc = gc;
	}

	public MenuBar getMenu() {
		return menu;
	}

	public void setMenu(MenuBar menu) {
		this.menu = menu;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	private void init() {
		setJMenuBar(menu);
		add(tabPane);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		dialog = new PrefrenceDialog(this);
	}

}
