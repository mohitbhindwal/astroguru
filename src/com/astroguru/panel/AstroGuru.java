package com.astroguru.panel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import com.astroguru.chart.D1;
import com.astroguru.chart.D9;
import com.astroguru.planets.AllPlanets;
import com.astroguru.ui.MainFrame;
import com.astroguru.ui.PrefrenceDialog;
import com.astroguru.util.FileUtil;
import com.astroguru.util.PlanetCalculator;
import com.astroguru.util.UserDetails;
import com.astroguru.util.VargaData;
import com.astroguru.util.VargaDataCalculator;
 

public class AstroGuru {

	// public static final String planetLongNames[] =
	// {"Sun", "Moon", "Mercury", "Venus", "Mars", "Jupiter", "Saturn", "Uranus",
	// "Neptune", "Pluto", "Rahu", "Ketu"};
	// 8, 5, 7, 7, 11, 11, 8, 8, 9, 7, 12, 6, 0, 7],
	
	static PrefrenceDialog pd;

	public static void main(String[] args) {
		FileUtil fileUtill = new FileUtil();
		fileUtill.readNativeFile("F:\\myworkspace\\AstroGuru\\charts", "natives.txt");
		System.out.println(fileUtill.getNatives());
		UserDetails user =  fileUtill.getNatives().get("Mohit");
		System.out.println(user.getTimeZoneOffset());
	 
		
		
		
		MainFrame frame = new MainFrame();
		
		
		
		
		  pd = new PrefrenceDialog(frame);
		pd.setDefaultCalculationPreferences();
		pd.displayPrefrences();
		
		long strtTime = System.currentTimeMillis();
		PlanetCalculator pc = new PlanetCalculator(user, pd.prefs);
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time for calculations =" + (endTime - strtTime));
		int[][] vargaData =   pc.getVargadata().getVargaData();
		
		for (int i = 0; i < vargaData[0].length; i++) {
			System.out.println(vargaData[0][i]);
		}
		 
		D1 d1 = pc.calculateD1Chart(pc.getAllPlanets(), vargaData, pc.getHouseData().getAscendant());
		D9 d9 = pc.calculateD9Chart(pc.getAllPlanets(), vargaData, pc.getHouseData().getAscendant());
			
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+d9);
	//	d1.setPreferredSize(new Dimension(frame.getTabPane().getComponentAt(0).getWidth(), frame.getTabPane().getComponentAt(0).getHeight()));
		d9.setPreferredSize(new Dimension(frame.getTabPane().getComponentAt(0).getWidth(), frame.getTabPane().getComponentAt(0).getHeight()));
		
		System.out.println(frame.getTabPane().getComponentAt(0).getWidth());
		System.out.println(frame.getTabPane().getComponentAt(0).getHeight());
		
		int width = frame.getTabPane().getComponentAt(0).getWidth();
		int height = frame.getTabPane().getComponentAt(0).getHeight();
		
		
		System.out.println(d1);
		
	 
		 
		JTextArea   t1 = new JTextArea(10, 10); 
		
		
		
		CPanel cpanel = new CPanel(frame.getTabPane().getComponentAt(0).getWidth()/2, frame.getTabPane().getComponentAt(0).getHeight()/2);
		JSplitPane sp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,cpanel,d9);
		sp1.setLeftComponent(cpanel);
		//sp1.setRightComponent(t1);
		sp1.setResizeWeight(.50);
		//resizePanel(sp1,d1);
		sp1.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				resizePanel(sp1,d1);
			}
		});
		
		
		JPanel panel = new JPanel(new BorderLayout());
		//panel.setPreferredSize(new Dimension(width, height));
		JPanel upper = new JPanel( );
		upper.add(sp1);
	  
		panel.add(sp1,BorderLayout.CENTER);
		
		frame.getTabPane().setComponentAt(0, panel);
	//	resizePanel(sp1,d1);
	//	frame.getTabPane().setComponentAt(0, d1);
	//	frame.getTabPane().setComponentAt(0, d9);
			
		
		
		
		  
		
		/*FreeJyotish jyotish = new FreeJyotish();

		ChartCenter center = (ChartCenter) jyotish.getCenter();

		Chart chart = center.getChart("Mohit Bhindwal");
		System.out.println("!!!!!!!!!!!" + chart.getPlanetInfo().getAllPlanetsMaP());
		System.out.println("!!!!!!!!!!!" + chart.getVargaData());

	*/}
	
	private static void resizePanel(JSplitPane sp2,JPanel cpanel) {

		// System.out.println(propertyChangeEvent.getNewValue() + "EEEEEEEEEEEEEEE" +
		// sp2.getWidth());
		// System.out.println(propertyChangeEvent.getOldValue() + "TTTTTTTTT" +
		// sp2.getAlignmentX());
	//	sp2.setRightComponent(new JPanel());
		
		
		FileUtil fileUtill = new FileUtil();
		fileUtill.readNativeFile("F:\\myworkspace\\AstroGuru\\charts", "natives.txt");
		System.out.println(fileUtill.getNatives());
		UserDetails user =  fileUtill.getNatives().get("Mohit");
		PlanetCalculator pc = new PlanetCalculator(user, pd.prefs);
		long endTime = System.currentTimeMillis();
		int[][] vargaData =   pc.getVargadata().getVargaData();
		
		for (int i = 0; i < vargaData[0].length; i++) {
			System.out.println(vargaData[0][i]);
		}
		 
		D1 d1 = pc.calculateD1Chart(pc.getAllPlanets(), vargaData, pc.getHouseData().getAscendant());

	
	 
	//	cpanel.getGraphics().clearRect(0, 0, 200, 200);
		//sp2.setRightComponent(new CPanel(sp2.getWidth() - sp2.getDividerLocation(), sp2.getHeight()));
		
		d1.setPreferredSize(new Dimension(sp2.getWidth() - sp2.getDividerLocation(), sp2.getHeight()));
		sp2.setRightComponent(d1);
		
	System.out.println("@@@@@@@@@@aaaaaaaaaaaa"+ (sp2.getWidth() - sp2.getDividerLocation()));
		d1.repaint();
	    //   sp2.setRightComponent(d1);
		 

	}

}
