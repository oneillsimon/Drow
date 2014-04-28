package drow.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * <h1>ClickAction</h1>
 * Used for triggering actions tied to keyboard macros.
 * <p>
 * @author Graham Wolfe
 * <p>
 */
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