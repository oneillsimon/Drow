package drow.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class TestingGround extends JPanel {

	/**
	 * Create the panel.
	 */
	public TestingGround() {
		setLayout(new GridLayout(2, 9, 0, 0));
		
		JButton btnOpen = new JButton("Open");
		add(btnOpen);
		
		JButton btnNew = new JButton("New");
		add(btnNew);
		
		JLabel lblNull = new JLabel("null");
		add(lblNull);
		
		JButton btnBold = new JButton("Bold");
		add(btnBold);
		
		JButton btnItalic = new JButton("Italic");
		add(btnItalic);
		
		JButton btnFcol = new JButton("fCol");
		add(btnFcol);
		
		JLabel lblNull_2 = new JLabel("null");
		add(lblNull_2);
		
		JLabel lblNull_3 = new JLabel("null");
		add(lblNull_3);
		
		JButton btnLeft = new JButton("Left");
		add(btnLeft);
		
		JButton btnCenter = new JButton("Center");
		add(btnCenter);
		
		JButton btnRight = new JButton("Right");
		add(btnRight);
		
		JLabel lblNull_6 = new JLabel("null");
		add(lblNull_6);
		
		JComboBox comboBoxFontFamilies = new JComboBox();
		add(comboBoxFontFamilies);
		
		JButton btnSave = new JButton("Save");
		add(btnSave);
		
		JButton btnSaveAs = new JButton("Save As");
		add(btnSaveAs);
		
		JLabel lblNull_1 = new JLabel("Null");
		add(lblNull_1);
		
		JButton btnUnderline = new JButton("Underline");
		add(btnUnderline);
		
		JButton btnStrikethrough = new JButton("Strikethrough");
		add(btnStrikethrough);
		
		JButton btnHcol = new JButton("hCol");
		add(btnHcol);
		
		JLabel lblNull_4 = new JLabel("null");
		add(lblNull_4);
		
		JLabel lblNull_5 = new JLabel("null");
		add(lblNull_5);
		
		JButton btnSup = new JButton("Sup");
		add(btnSup);
		
		JButton btnSub = new JButton("Sub");
		add(btnSub);
		
		JToggleButton tglbtnDev = new JToggleButton("Dev");
		add(tglbtnDev);
		
		JLabel lblNull_7 = new JLabel("null");
		add(lblNull_7);
		
		JComboBox comboBoxFontSizes = new JComboBox();
		add(comboBoxFontSizes);

	}

}
