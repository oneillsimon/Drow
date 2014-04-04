package drow.gui;

import javax.swing.JTextField;

public class IntTextField extends JTextField {
	
	public IntTextField(int defval, int size) {
		super("" + defval, size);
	}
	
	public int getValue() {
		try {
			return Integer.parseInt(getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}	
