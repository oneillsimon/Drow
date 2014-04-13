package drow.gui;

import java.awt.Event;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import drow.io.DrowIOActionManager;
import drow.styles.DrowStyleActionManager;
import drow.view.DocumentView;

public class HomePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private GridBagLayout gridBagLayout;
	
	private String[] fontFamilies;
	private String[] fontSizes;
	
	private JButton btnSave;
	private JButton btnOpen;
	private JButton btnBold;
	private JButton btnItalic;
	private JButton btnUnderline;
	private JButton btnStrikethrough;
	private JButton btnJustifyLeft;
	private JButton btnJustifyCenter;
	private JButton btnJustifyRight;
	private JButton btnFontColour;
	private JButton btnHighlightColour;
	private JButton btnSuperScript;
	private JButton btnSubScript;
	
	GridBagConstraints gbc_btnSave;
	GridBagConstraints gbc_btnOpen;
	GridBagConstraints gbc_btnBold;
	GridBagConstraints gbc_btnItalic;
	GridBagConstraints gbc_btnUnderline;
	GridBagConstraints gbc_btnStrikethrough;
	GridBagConstraints gbc_btnJustifyLeft;
	GridBagConstraints gbc_btnJustifyCenter;
	GridBagConstraints gbc_btnJustifyRight;
	GridBagConstraints gbc_btnFontColour;
	GridBagConstraints gbc_btnHighlightColour;
	GridBagConstraints gbc_btnSuperScript;
	GridBagConstraints gbc_btnSubScript;
	
	private JComboBox<String> comboBoxFontFamily;
	private JComboBox<String> comboBoxFontSize;
	
	GridBagConstraints gbc_comboBoxFontFamily;
	GridBagConstraints gbc_comboBoxFontSize;
	
	private DrowStyleActionManager styleActionManager;
	private DrowIOActionManager ioActionManager;
	
	public HomePanel(final DocumentView docView) {
		gridBagLayout = new GridBagLayout();
		
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{5, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 5.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);
		
		fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontSizes = new String[] { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72" };
		
		btnSave = new JButton(new ImageIcon("res/save.gif"));
		btnOpen = new JButton(new ImageIcon("res/open.gif"));
		btnBold = new JButton(new ImageIcon("res/bold.png"));
		btnItalic = new JButton(new ImageIcon("res/italic.png"));
		btnUnderline = new JButton(new ImageIcon("res/underline.png"));
		btnStrikethrough = new JButton("s");
		btnJustifyLeft = new JButton("JL");
		btnJustifyCenter = new JButton("JC");
		btnJustifyRight = new JButton("JR");
		btnFontColour = new JButton("Fcol");
		btnHighlightColour = new JButton("Hcol");
		btnSuperScript = new JButton("sS");
		btnSubScript = new JButton("Ss");
		
		gbc_btnSave = new GridBagConstraints();
		gbc_btnOpen = new GridBagConstraints();
		gbc_btnBold = new GridBagConstraints();
		gbc_btnItalic = new GridBagConstraints();
		gbc_btnUnderline = new GridBagConstraints(); 
		gbc_btnStrikethrough = new GridBagConstraints();
		gbc_btnJustifyLeft = new GridBagConstraints();
		gbc_btnJustifyCenter = new GridBagConstraints();
		gbc_btnJustifyRight = new GridBagConstraints();
		gbc_btnFontColour = new GridBagConstraints();
		gbc_btnHighlightColour = new GridBagConstraints();
		gbc_btnSuperScript = new GridBagConstraints();
		gbc_btnSubScript = new GridBagConstraints();
		
		comboBoxFontFamily = new JComboBox<String>(fontFamilies);
		comboBoxFontSize = new JComboBox<String>(fontSizes);
		comboBoxFontSize.setEditable(true);
		
		gbc_comboBoxFontFamily = new GridBagConstraints();
		gbc_comboBoxFontSize = new GridBagConstraints();
		
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 2;
		
		gbc_btnOpen.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpen.gridx = 0;
		gbc_btnOpen.gridy = 1;
		
		gbc_btnBold.insets = new Insets(0, 0, 5, 5);
		gbc_btnBold.gridx = 3;
		gbc_btnBold.gridy = 1;
		
		gbc_btnUnderline.insets = new Insets(0, 0, 0, 5);
		gbc_btnUnderline.gridx = 3;
		gbc_btnUnderline.gridy = 2;
		
		gbc_btnItalic.insets = new Insets(0, 0, 5, 5);
		gbc_btnItalic.gridx = 4;
		gbc_btnItalic.gridy = 1;
		
		gbc_btnStrikethrough.insets = new Insets(0, 0, 5, 5);
		gbc_btnStrikethrough.gridx = 5;
		gbc_btnStrikethrough.gridy = 1;
		
		gbc_btnJustifyLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnJustifyLeft.gridx = 7;
		gbc_btnJustifyLeft.gridy = 1;
		
		gbc_btnJustifyCenter.insets = new Insets(0, 0, 5, 5);
		gbc_btnJustifyCenter.gridx = 8;
		gbc_btnJustifyCenter.gridy = 1;
		
		gbc_btnJustifyRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnJustifyRight.gridx = 9;
		gbc_btnJustifyRight.gridy = 1;
		
		gbc_btnFontColour.insets = new Insets(0, 0, 0, 5);
		gbc_btnFontColour.gridx = 4;
		gbc_btnFontColour.gridy = 2;
		
		gbc_btnHighlightColour.insets = new Insets(0, 0, 0, 5);
		gbc_btnHighlightColour.gridx = 5;
		gbc_btnHighlightColour.gridy = 2;
		
		gbc_btnSuperScript.insets = new Insets(0, 0, 0, 5);
		gbc_btnSuperScript.gridx = 8;
		gbc_btnSuperScript.gridy = 2;
		
		gbc_btnSubScript.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubScript.gridx = 9;
		gbc_btnSubScript.gridy = 2;
		
		styleActionManager = new DrowStyleActionManager(docView);
		ioActionManager = new DrowIOActionManager(docView);
		
		btnSave.setAction(ioActionManager.saveAction());
		btnOpen.setAction(ioActionManager.openAction());
		btnBold.setAction(styleActionManager.boldAction());
		btnItalic.setAction(styleActionManager.italicAction());
		btnUnderline.setAction(styleActionManager.underlineAction());
		btnStrikethrough.setAction(styleActionManager.strikeThroughAction());
		btnJustifyLeft.setAction(styleActionManager.justifyLeftAction());
		btnJustifyCenter.setAction(styleActionManager.justifyCenterAction());
		btnJustifyRight.setAction(styleActionManager.justifyRightAction());
		btnFontColour.setAction(styleActionManager.foregroundColorAction());
		btnHighlightColour.setAction(styleActionManager.backgroundColorAction());
		btnSuperScript.setAction(styleActionManager.superScriptAction());
		//btnSubScript.setAction(styleActionManager.subScriptAction());
		
		btnSubScript.setAction(new AbstractAction("New page") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView));
			}
		});

		comboBoxFontFamily.setAction(styleActionManager.fontFamilyAction());
		comboBoxFontSize.setAction(styleActionManager.fontSizeAction());
		
		gbc_comboBoxFontFamily.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFontFamily.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFontFamily.gridx = 10;
		gbc_comboBoxFontFamily.gridy = 1;
		
		gbc_comboBoxFontSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFontSize.gridx = 10;
		gbc_comboBoxFontSize.gridy = 2;
		
		InputMap inputMap = btnSave.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke s = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
		inputMap.put(s, "S");
		
		btnSave.getActionMap().put("S", new ClickAction(btnSave));
		InputMap inputMap1 = btnOpen.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke o = KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK);
		inputMap1.put(o, "O");
		
		btnOpen.getActionMap().put("O", new ClickAction(btnOpen));
		InputMap inputMap2 = btnBold.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke b = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
		inputMap2.put(b, "B");
		btnBold.getActionMap().put("B", new ClickAction(btnBold));
		
		InputMap inputMap3 = btnItalic.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke i = KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK);
		inputMap3.put(i, "I");
		btnItalic.getActionMap().put("I", new ClickAction(btnItalic));
			
		this.add(btnSave, gbc_btnSave);
		this.add(btnOpen, gbc_btnOpen);
		this.add(btnBold, gbc_btnBold);
		this.add(btnItalic, gbc_btnItalic);
		this.add(btnUnderline, gbc_btnUnderline);
		this.add(btnStrikethrough, gbc_btnStrikethrough);
		this.add(btnJustifyLeft, gbc_btnJustifyLeft);
		this.add(btnJustifyCenter, gbc_btnJustifyCenter);
		this.add(btnJustifyRight, gbc_btnJustifyRight);
		this.add(btnFontColour, gbc_btnFontColour);
		this.add(btnHighlightColour, gbc_btnHighlightColour);
		this.add(btnSuperScript, gbc_btnSuperScript);
		this.add(btnSubScript, gbc_btnSubScript);
		
		this.add(comboBoxFontFamily, gbc_comboBoxFontFamily);
		this.add(comboBoxFontSize, gbc_comboBoxFontSize);
	}
	
	public class ClickAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		private JButton button;

		public ClickAction(JButton button) {
		    this.button = button;
		}

		public void actionPerformed(ActionEvent e) {
		    button.doClick();
		}
	}
}
