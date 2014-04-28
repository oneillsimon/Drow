package drow.gui;

import javax.swing.JPanel;

import java.awt.Event;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import drow.io.DrowIOActionManager;
import drow.styles.DrowStyleActionManager;
import drow.view.DocumentView;

/**
 * <h1>HomePanel</h1>
 * This class contains the controls present on the HomeTab of the GUI.
 * It contains buttons to open, save and create new documents. It also has controls to change various aspects
 * about the text. Such as:
 * <br>-Bold.
 * <br>-Italic.
 * <br>-StrikeThrough.
 * <br>-Underline.
 * <br>-Font Colour.
 * <br>-BackGround Colour.
 * <br>-Full Screen.
 * <br>-SubScript.
 * <br>-SuperScript.
 * <br>-Justify Left.
 * <br>-Justify Centre.
 * <br>-Justify Right.
 * <br>-Font Family.
 * <br>-Font Size.
 * <p>
 * @author Graham Wolfe
 * <p>
 */
public class HomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private String[] fontFamilies;
	private String[] fontSizes;
	
	private JButton btnOpen;
	private JButton btnSave;
	private JButton btnSaveAs;
	private JButton btnNew;
	private JButton btnFullScreen;
	private JButton btnBold;
	private JButton btnItalic;
	private JButton btnUnderline;
	private JButton btnStrikethrough;
	private JButton btnFontColour;
	private JButton btnHighlightColour;
	private JButton btnSuperScript;
	private JButton btnSubScript;
	private JButton btnDev;
	private JButton btnJustifyLeft;
	private JButton btnJustifyCenter;
	private JButton btnJustifyRight;
	private JComboBox<String> comboBoxFontFamilies;
	private JComboBox<String> comboBoxFontSizes;
	private JLabel lblNull;
	private JLabel lblNull_1;
	private JLabel lblNull_2;
	private JLabel lblNull_3;
	private JLabel lblNull_4;
	private JLabel lblNull_5;
	private JLabel lblNull_6;
	private JLabel lblNull_7;
	private JLabel lblNull_8;
	
	/** Manager for controlling styles. */
	private DrowStyleActionManager styleActionManager;
	
	/** Manager for controlling IO actions. */
	private DrowIOActionManager ioActionManager;
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the document.
	 */
	public HomePanel(DocumentView documentView) {
		setLayout(new GridLayout(2, 9, 0, 0));
		
		fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontSizes = new String[] { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72" };
		
		btnOpen = new JButton("Open");
		btnSave = new JButton("Save");
		btnSaveAs = new JButton("Save As");
		btnNew = new JButton("New");
		btnFullScreen = new JButton("Fullscreen");
		btnBold = new JButton("Bold");
		btnItalic = new JButton("Italic");
		btnUnderline = new JButton("Underline");
		btnStrikethrough = new JButton("Strikethrough");
		btnFontColour = new JButton("fCol");
		btnHighlightColour = new JButton("hCol");
		btnJustifyLeft = new JButton("Left");
		btnJustifyCenter = new JButton("Center");
		btnJustifyRight = new JButton("Right");
		btnSuperScript = new JButton("Sup");
		btnSubScript = new JButton("Sub");
		btnDev = new JButton("Dev");
		
		comboBoxFontFamilies = new JComboBox<String>(fontFamilies);
		comboBoxFontSizes = new JComboBox<String>(fontSizes);
		comboBoxFontSizes.setEditable(true);
		
		lblNull = new JLabel("");
		lblNull_1 = new JLabel("");
		lblNull_2 = new JLabel("");
		lblNull_3 = new JLabel("");
		lblNull_4 = new JLabel("");
		lblNull_5 = new JLabel("");
		lblNull_6 = new JLabel("");
		lblNull_7 = new JLabel("");
		lblNull_8 = new JLabel("");
		
		styleActionManager = new DrowStyleActionManager(documentView);
		ioActionManager = new DrowIOActionManager(documentView);
		
		btnSave.setAction(ioActionManager.saveAction());
		btnOpen.setAction(ioActionManager.openAction());
		btnNew.setAction(ioActionManager.newAction());
		btnFullScreen.setAction(ioActionManager.fullScreenAction());
		btnBold.setAction(styleActionManager.boldAction());
		btnItalic.setAction(styleActionManager.italicAction());
		btnUnderline.setAction(styleActionManager.underlineAction());
		btnStrikethrough.setAction(styleActionManager.backgroundColorAction());
		btnJustifyLeft.setAction(styleActionManager.justifyLeftAction());
		btnJustifyCenter.setAction(styleActionManager.justifyCenterAction());
		btnJustifyRight.setAction(styleActionManager.justifyRightAction());
		btnFontColour.setAction(styleActionManager.foregroundColorAction());
		btnHighlightColour.setAction(styleActionManager.strikeThroughAction());
		btnSuperScript.setAction(styleActionManager.superScriptAction());
		btnSubScript.setAction(styleActionManager.subScriptAction());
		btnDev.setAction(ioActionManager.devModeAction());
		
		comboBoxFontFamilies.setAction(styleActionManager.fontFamilyAction());
		comboBoxFontSizes.setAction(styleActionManager.fontSizeAction());
		
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
		
		InputMap inputMap4 = btnUnderline.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke u = KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK);
		inputMap4.put(u, "U");
		btnUnderline.getActionMap().put("U", new ClickAction(btnUnderline));
		
		InputMap inputMap5 = btnFullScreen.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke fS = KeyStroke.getKeyStroke(KeyEvent.VK_F5, Event.CTRL_MASK);
		inputMap5.put(fS, "fS");
		btnFullScreen.getActionMap().put("fS", new ClickAction(btnFullScreen));

		add(btnOpen);
		add(btnNew);
		add(btnFullScreen);
		add(lblNull);
		add(btnBold);
		add(btnItalic);
		add(btnFontColour);
		add(lblNull_2);
		add(lblNull_3);
		add(btnJustifyLeft);
		add(btnJustifyCenter);
		add(btnJustifyRight);
		add(lblNull_6);
		add(comboBoxFontFamilies);
		add(btnSave);
		add(btnSaveAs);
		add(lblNull_8);
		add(lblNull_1);
		add(btnUnderline);
		add(btnStrikethrough);
		add(btnHighlightColour);
		add(lblNull_4);
		add(lblNull_5);
		add(btnSuperScript);
		add(btnSubScript);
		add(btnDev);
		add(lblNull_7);
		add(comboBoxFontSizes);
	}
}
