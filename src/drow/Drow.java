package drow;

import javax.swing.UIManager;

import drow.view.DocumentView;

public class Drow {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (Exception e) {
			System.err.println("Look and feel not set.");
			System.out.println(e.getStackTrace() + "\n");
			System.out.println(e.getCause());
		}
		
		new DocumentView();
	}
}