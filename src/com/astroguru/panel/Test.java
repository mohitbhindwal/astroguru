package com.astroguru.panel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import freejyotish.FreeJyotish;
import freejyotish.gui.FJSplit;
import freejyotish.main.AllPlanets;
import freejyotish.main.Chart;
import freejyotish.main.ChartCenter;
import freejyotish.main.FJConstants;
import freejyotish.main.IChartCenter;
import freejyotish.main.Planet;
import freejyotish.main.calc.VargaData;

public class Test {

	static int screen_width = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration().getBounds().width;
	static int screen_height = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration().getBounds().height;

	public static void main(String[] args) {
		
		createAndShowGUI();
		
		if(true )
return;
		FreeJyotish jyotish = new FreeJyotish();
		// HashMap map =
		// jyotish.getStorage().importAllCharts("F:\\myworkspace\\AstroGuru\\charts");
		// System.out.println(map);

		ChartCenter center = (ChartCenter) jyotish.getCenter();

		Chart chart = center.getChart("Mohit Bhindwal");

		System.out.println("!!!!!!!!!!!" + chart.getPlanetInfo().getAllPlanetsMaP());
		AllPlanets allPlanets = chart.getPlanetInfo();
		VargaData vargaData = chart.getVargaData();
		int[][] vargaDataArray = vargaData.getVargaData();
		System.out.println("!!!!!!!!!!!" + chart.getVargaData());

		for (int i = 0; i < allPlanets.getSize(); i++) {
			Planet p = allPlanets.getPlanet(FJConstants.planetLongNames[i]);
			System.out.print(p + "Rasi Positions: " + vargaDataArray[0][i] + "\n");
		}
		System.out.print("Ascendent : " + vargaDataArray[0][13] + "\n");

		// createAndShowGUI();

	}

	private static void resizePanel(JSplitPane sp2) {

		// System.out.println(propertyChangeEvent.getNewValue() + "EEEEEEEEEEEEEEE" +
		// sp2.getWidth());
		// System.out.println(propertyChangeEvent.getOldValue() + "TTTTTTTTT" +
		// sp2.getAlignmentX());
		sp2.setRightComponent(new CPanel(sp2.getWidth() - sp2.getDividerLocation(), sp2.getHeight()));
		sp2.setLeftComponent(new Button("ghj"));

	}

	private static void createAndShowGUI() {
		System.out.println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread());
		JFrame f = new JFrame("Swing Paint Demo");
		// f.setLayout(new FlowLayout());

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(screen_width, screen_height));
		f.setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel();
		CPanel panel = new CPanel(100, 100);
		panel.add(new JButton("adasda"));
		// JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, new
		// CPanel(100,100));
		JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(sp2, BorderLayout.CENTER);

		BasicSplitPaneUI basicSplitPaneUI = (BasicSplitPaneUI) sp2.getUI();
		BasicSplitPaneDivider basicSplitPaneDivider = basicSplitPaneUI.getDivider();

		f.add(centerPanel, BorderLayout.CENTER);
		resizePanel(sp2);

		sp2.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				resizePanel(sp2);
			}
		});

		/*
		 * sp2.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new
		 * PropertyChangeListener() {
		 * 
		 * @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
		 * {
		 * 
		 * 
		 * System.out.println(propertyChangeEvent.getNewValue() + "EEEEEEEEEEEEEEE" +
		 * sp2.getDividerLocation());
		 * System.out.println(propertyChangeEvent.getOldValue() + "TTTTTTTTT" +
		 * sp2.getAlignmentX()); System.out.println("$$$$$$$$$$$$$$$$" + sp2.getSize());
		 * // JOptionPane.showMessageDialog(null, sp2.getRightComponent().toString());
		 * 
		 * // sp2.remove(sp2.getRightComponent()); sp2.remove(sp2.getLeftComponent());
		 * sp2.remove(sp2.getRightComponent());
		 * 
		 * 
		 * JPanel centerPanel = new JPanel(); CPanel panelLeft = new CPanel(100, 100);
		 * panelLeft.add(new JButton("adasda")); sp2.setLeftComponent(panelLeft);
		 * sp2.repaint(); CPanel panel = new CPanel(200, 200); BasicSplitPaneUI
		 * basicSplitPaneUI = (BasicSplitPaneUI) sp2.getUI(); BasicSplitPaneDivider
		 * basicSplitPaneDivider = basicSplitPaneUI.getDivider();
		 * System.out.println("asdadassdsdsadsasad" +
		 * basicSplitPaneDivider.getDividerSize()); panel.setPreferredSize( new
		 * Dimension(sp2.getSize().width - sp2.getDividerLocation(),
		 * sp2.getSize().height)); sp2.setRightComponent(panel);
		 * sp2.getUI().installUI(panel); // panel.repaint(); // sp2.revalidate(); //
		 * sp2.repaint();
		 * 
		 * } });
		 */
		// sp2.firePropertyChange(JSplitPane.DIVIDER_LOCATION_PROPERTY, true, false);
		// sp2.revalidate();
		// sp2.repaint();

		// f.add(new CPanel(500,500));
		f.setLayout(new GridLayout(1, 1));
		f.pack();
		f.setVisible(true);
	}

}
