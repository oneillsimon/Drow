package drow.gui;

import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import drow.io.DrowIOActionManager;
import drow.styles.DrowStyleActionManager;
import drow.view.DocumentView;

public class HomePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private String[] fontFamilies;
	private String[] fontSizes;
	
	private JButton btnSave;
	private JButton btnOpen;
	private JButton btnBold;
	private JButton btnItalic;
	private JButton btnUnderline;
	
	private JComboBox<String> comboBoxFontFamily;
	private JComboBox<String> comboBoxFontSize;
	
	private DrowStyleActionManager styleActionManager;
	private DrowIOActionManager ioActionManager;
	
	public HomePanel(DocumentView docView) {
		fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontSizes = new String[] { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72" };
		
		btnSave = new JButton("Save");
		btnOpen = new JButton("Open");
		btnBold = new JButton("B");
		btnItalic = new JButton("I");
		btnUnderline = new JButton("U");
		
		comboBoxFontFamily = new JComboBox<String>(fontFamilies);
		comboBoxFontSize = new JComboBox<String>(fontSizes);
		comboBoxFontSize.setEditable(true);
		
		styleActionManager = new DrowStyleActionManager(docView);
		ioActionManager = new DrowIOActionManager(docView);
		
		btnSave.addActionListener(ioActionManager.exportListener());
		btnOpen.addActionListener(ioActionManager.importListener());
		btnBold.setAction(styleActionManager.boldAction());
		btnItalic.setAction(styleActionManager.subScriptAction());
		btnUnderline.setAction(styleActionManager.underlineAction());
		
		comboBoxFontFamily.setAction(styleActionManager.fontFamilyAction());
		comboBoxFontSize.setAction(styleActionManager.fontSizeAction());
		
		this.add(btnSave);
		this.add(btnOpen);
		this.add(btnBold);
		this.add(btnItalic);
		this.add(btnUnderline);
		
		this.add(comboBoxFontFamily);
		this.add(comboBoxFontSize);
	}
}
