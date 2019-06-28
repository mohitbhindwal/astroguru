package com.astroguru.ui;

import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.astroguru.util.DefaultFormBuilder;
import com.astroguru.util.I18NManager;
import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.layout.FormLayout;

import swisseph.SweConst;

public class PrefrenceDialog extends JDialog {

	private static final String MEAN_NODE = "mean"; //$NON-NLS-1$
	private static final String TRUE_NODE = "true"; //$NON-NLS-1$
	private static final String SIDEREAL_MODE = "sidereal"; //$NON-NLS-1$
	private static final String TROPICAL_MODE = "tropical"; //$NON-NLS-1$
	private static final String NODE_PREF_KEY = "node"; //$NON-NLS-1$
	private static final String MODE_PREF_KEY = "mode"; //$NON-NLS-1$
	private static final String AYANAMSA_PREF_KEY = "ayanamsa"; //$NON-NLS-1$
	private static final String HOUSE_SYSTEM_PREF_KEY = "houseType";//$NON-NLS-1$

	private JButton okButton, cancelButton;
	private JComboBox ayanamasaCombo, houseTypeCombo;
//	private CalculationPreferences calcPrefs;
	private JRadioButton tropicalRadioButton, siderealRadioButton, trueRadioButton, meanRadioButton;
//m	private IChartCenter chartCenter;
	public static Preferences prefs = Preferences.userRoot().node("/freejyotish");
	private Hashtable prefsMap;
	public static String[] ayanamsaStringsNew = { 
			I18NManager.getString("calcprefsdialog.ayanamsa.fagan"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.lahiri"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.deluce"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.raman"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.ushashashi"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.krishnamurti"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.dhwajkhul"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.yukteshwar"), //$NON-NLS-1$
			I18NManager.getString("calcprefsdialog.ayanamsa.jnbhasin") }; //$NON-NLS-1$

	public PrefrenceDialog(JFrame frame) {
		super(frame);
		// preferences = Preferences.userNodeForPackage(FreeJyotish.class);
		System.out.println("!!!@@@" + prefs);

		setTitle(I18NManager.getString("calcprefsdialog.title")); //$NON-NLS-1$
		initComponents();

		FormLayout layout = new FormLayout("pref,6dlu,pref:grow,3dlu,pref", ""); //$NON-NLS-1$ //$NON-NLS-2$
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.appendSeparator(I18NManager.getString("calcprefsdialog.separator.title")); //$NON-NLS-1$
		builder.append(I18NManager.getString("calcprefsdialog.zodiactype.label"), siderealRadioButton, //$NON-NLS-1$
				tropicalRadioButton);
		builder.nextLine();
		builder.append(I18NManager.getString("calcprefsdialog.ayanamsa.label")); //$NON-NLS-1$
		builder.append(createAyanamasaCombo(), 3);
		builder.nextLine();
		builder.append(I18NManager.getString("calcprefsdialog.nodetype.label"), trueRadioButton, meanRadioButton); //$NON-NLS-1$
		builder.nextLine();
		builder.append(I18NManager.getString("calcprefsdialog.housetype.label")); //$NON-NLS-1$
		builder.append(createHouseTypeCombo(), 3);
		builder.nextLine();
		builder.appendSeparator();
		builder.append(buildButtonPanel(), 5);
		getContentPane().add(builder.getPanel());
		pack();
		setLocationRelativeTo(frame);

	}

	public void setPreference(String name, Object o) {
		if (prefsMap == null)
			prefsMap = new Hashtable();
		prefsMap.put(name, o);
	}

	public Object getPreference(String name) {
		if (prefsMap == null)
			return null;
		return prefsMap.get(name);
	}

	public Hashtable getAllPreferences() {
		if (prefsMap == null)
			return null;
		return prefsMap;
	}

	private String zodiac;
	private int ayanamsa;
	private String node;
	private char houseSystem;
	private static final int iflag_SID = SweConst.SEFLG_SIDEREAL + SweConst.SEFLG_NONUT + SweConst.SEFLG_SPEED;
	private static final int iflag_TROP = SweConst.SEFLG_SPEED;
	private static int iflag;

	public void setDefaultCalculationPreferences() {

		zodiac = prefs.get("mode", "sidereal");
		ayanamsa = prefs.getInt("ayanamsa", 1);
		node = prefs.get("node", "true");
		if (zodiac.equals("tropical"))
			iflag = iflag_TROP;
		else
			iflag = iflag_SID;
		String houseString = prefs.get("houseType", "Shripati");
		if (houseString.equals("Shripati"))
			houseSystem = 'O';
		else if (houseString.equals("Koch"))
			houseSystem = 'K';
		else if (houseString.equals("Placidus"))
			houseSystem = 'P';
		else if (houseString.equals("Alcabitus"))
			houseSystem = 'B';
		else if (houseString.equals("Regiomontanus"))
			houseSystem = 'R';
		else if (houseString.equals("Campanus"))
			houseSystem = 'C';
		else
			houseSystem = 'O'; // IF ALL ELSE FAILS, USE SHRIPATI HOUSES
	}

	void initComponents() {
		tropicalRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.mode.tropical")); //$NON-NLS-1$
		siderealRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.mode.sidereal")); //$NON-NLS-1$
		ButtonGroup zodiacTypeGroup = new ButtonGroup();
		zodiacTypeGroup.add(tropicalRadioButton);
		zodiacTypeGroup.add(siderealRadioButton);

		trueRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.node.true")); //$NON-NLS-1$
		meanRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.mean.true")); //$NON-NLS-1$
		ButtonGroup nodeTypeGroup = new ButtonGroup();
		nodeTypeGroup.add(trueRadioButton);
		nodeTypeGroup.add(meanRadioButton);

		okButton = new JButton(new SavePreferencesAction());
		cancelButton = new JButton(new CancelAction());

	}

	private JComboBox createHouseTypeCombo() {
		String[] houseTypes = { I18NManager.getString("calcprefsdialog.housetype.shripati"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.housetype.koch"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.housetype.placidus"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.housetype.alcabitus"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.housetype.regiomontanus"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.housetype.campanus") };
		houseTypeCombo = new JComboBox(houseTypes);
		return houseTypeCombo;
	}

	private JComboBox createAyanamasaCombo() {
/*		String[] ayanamsaStrings = { 
				I18NManager.getString("calcprefsdialog.ayanamsa.fagan"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.lahiri"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.deluce"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.raman"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.ushashashi"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.krishnamurti"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.dhwajkhul"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.yukteshwar"), //$NON-NLS-1$
				I18NManager.getString("calcprefsdialog.ayanamsa.jnbhasin") }; //$NON-NLS-1$
*/		ayanamasaCombo = new JComboBox(ayanamsaStringsNew);
		return ayanamasaCombo;
	}

	private JPanel buildButtonPanel() {
		ButtonBarBuilder builder = new ButtonBarBuilder();
		builder.addGlue();
		builder.addGridded(okButton);
		builder.addRelatedGap();
		builder.addGridded(cancelButton);
		return builder.getPanel();
	}

	public void showDialog() {
		String modePreference = prefs.get(MODE_PREF_KEY, SIDEREAL_MODE);
		if (modePreference.equals(TROPICAL_MODE))
			tropicalRadioButton.setSelected(true);
		else
			siderealRadioButton.setSelected(true);

		int ayanamsaPreference = prefs.getInt(AYANAMSA_PREF_KEY, 0);
		ayanamasaCombo.setSelectedIndex(ayanamsaPreference);

		String nodePreference = prefs.get(NODE_PREF_KEY, TRUE_NODE);

		if (nodePreference.equals(MEAN_NODE))
			meanRadioButton.setSelected(true);
		else
			trueRadioButton.setSelected(true);

		String houseSystemPreference = prefs.get(HOUSE_SYSTEM_PREF_KEY, "Shripati");
		houseTypeCombo.setSelectedItem((Object) houseSystemPreference);

		setVisible(true);
	}

	public void setCalculationPreferences() {
		String selectedMode = tropicalRadioButton.isSelected() ? TROPICAL_MODE : SIDEREAL_MODE;
		prefs.put(MODE_PREF_KEY, selectedMode);

		int selectedAyanamasa = ayanamasaCombo.getSelectedIndex();
		prefs.putInt(AYANAMSA_PREF_KEY, selectedAyanamasa);

		String selectedNode = trueRadioButton.isSelected() ? TRUE_NODE : MEAN_NODE;
		prefs.put(NODE_PREF_KEY, selectedNode);

		String selectedHouseSystem = (String) houseTypeCombo.getSelectedItem();
		prefs.put(HOUSE_SYSTEM_PREF_KEY, selectedHouseSystem);

		// calcPrefs.setPreference("house_system", (Object) new String ("" +
		// comboA.getSelectedIndex()));

	}

	public static void displayPrefrences() {
		try {
			String[] allEntries = prefs.keys();
			for (String key : prefs.keys()) {
				System.out.println( key + " : " + prefs.get(key, "notfound"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
/*	public static String getAynamsa() {
		return a


				zodiac = prefs.get("mode", "sidereal");
				ayanamsa = prefs.getInt("ayanamsa", 1);
				node = prefs.get("node", "true");
				if (zodiac.equals("tropical"))
					iflag = iflag_TROP;
				else
					iflag = iflag_SID;
				String houseString = prefs.get("houseType", "Shripati");
				if (houseString.equals("Shripati"))
					houseSystem = 'O';
				else if (houseString.equals("Koch"))
					houseSystem = 'K';
				else if (houseString.equals("Placidus"))
					houseSystem = 'P';
				else if (houseString.equals("Alcabitus"))
					houseSystem = 'B';
				else if (houseString.equals("Regiomontanus"))
					houseSystem = 'R';
				else if (houseString.equals("Campanus"))
					houseSystem = 'C';
				else
					houseSystem = 'O'; // IF ALL ELSE FAILS, USE SHRIPATI HOUSES
			
	}*/

	class SavePreferencesAction extends AbstractAction {
		public SavePreferencesAction() {
			super(I18NManager.getString("calcprefsdialog.savepreferencesaction.label")); //$NON-NLS-1$
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("Preferences changed.");
			JOptionPane.showMessageDialog(null, "Test");
			setCalculationPreferences();
			setVisible(false);
            displayPrefrences();
			/*
			 * System.out.println("Preferences change heard.");
			 * chartCenter.saveCalculationPreferences();
			 * chartCenter.createChart(chartCenter.getCurrentChart().getNativeInfo2());
			 */
		}
	}

	class CancelAction extends AbstractAction {
		public CancelAction() {
			super(I18NManager.getString("calcprefsdialog.cancelpreferencesaction.label")); //$NON-NLS-1$
		}

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

}
