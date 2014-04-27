package drow;

import javax.swing.UIManager;

import drow.view.DocumentView;

/**
 * <h1>Drow worD Processor</h1>
 * <p>
 * This is Drow, the basic word processor from Team Full-Duplex.
 * Coded in 3 months for our Team Project.
 * <p>
 * @author Simon O'Neill
 * @author Lee Mc Donald
 * @author Graham Wolfe
 * <p>
 * Abandon hope all ye who venture past this point,
 * for this be the land of undocumented libraries and Shitty Swing code.
 */
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