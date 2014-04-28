package drow.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import drow.view.DocumentView;

/**
 * <h1>MouseMenu</h1>
 * This class is for a pop up menu when a user right 
 * clicks around the page.
 * <p>
 * @author Graham Wolfe
 * <p> 
 */
public class MouseMenu extends MouseAdapter {
	
	private JPopupMenu popup;
	private AbstractAction cutAction;
	private AbstractAction copyAction;
	private AbstractAction pasteAction;
	private AbstractAction undoAction;
	private AbstractAction selectAllAction;


	private JTextComponent textComponent;
	private String savedString = "";
	private Actions lastActionSelected;

	private enum Actions { UNDO, CUT, COPY, PASTE, SELECT_ALL };

	/**
	 * <h1>Constructor</h1> 
	 * @param documentView - The view containing the document.
	 */
	public MouseMenu(final DocumentView documentView) {
		
		popup = new JPopupMenu();
		
		/** The undo action. */
		undoAction = new AbstractAction("Undo") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				textComponent = documentView.getDrowDocument().getFocusedPage();
				textComponent.replaceSelection(savedString);
				lastActionSelected = Actions.UNDO;
			}
		};

		popup.add(undoAction);
		popup.addSeparator();

		/** The cut action. */
		cutAction = new AbstractAction("Cut") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.CUT;
				savedString = textComponent.getText();
				textComponent.cut();
			}
		};
		
		popup.add(cutAction);

		/** The copy action. */
		copyAction = new AbstractAction("Copy") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.COPY;
				textComponent.copy();
			}
		};

		popup.add(copyAction);

		/** The paste action. */
		pasteAction = new AbstractAction("Paste") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.PASTE;
				savedString = textComponent.getText();
				textComponent.paste();
			}
		};

		popup.add(pasteAction);
		popup.addSeparator();

		/** The select all action. */
		selectAllAction = new AbstractAction("Select All") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.SELECT_ALL;
				textComponent.selectAll();
			}
		};

		popup.add(selectAllAction);
	}

	/**
	 * Handles when the mouse button is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			if (!(e.getSource() instanceof JTextComponent)) {
				return;
			}

			textComponent = (JTextPane) e.getSource();
			textComponent.requestFocus();
			
			boolean enabled = textComponent.isEnabled();
			boolean editable = textComponent.isEditable();
			boolean nonempty = !(textComponent.getText() == null || textComponent.getText().equals(""));
			boolean marked = textComponent.getSelectedText() != null;

			boolean pasteAvailable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).isDataFlavorSupported(DataFlavor.stringFlavor);

			undoAction.setEnabled(enabled && editable && (lastActionSelected == Actions.CUT || lastActionSelected == Actions.PASTE));
			cutAction.setEnabled(enabled && editable && marked);
			copyAction.setEnabled(enabled && marked);
			pasteAction.setEnabled(enabled && editable && pasteAvailable);
			selectAllAction.setEnabled(enabled && nonempty);

			int nx = e.getX();

			if (nx > 500) {
				nx = nx - popup.getSize().width;
			}
			popup.show(e.getComponent(), nx, e.getY() - popup.getSize().height);
		}
	}
	
	/**
	 * Handles when the mouse button is released.
	 */
	public void mouseReleased(MouseEvent e) {
		showPopup(e); 
	}
	
	/**
	 * Handles when the mouse button is pressed.
	 */
	public void mousePressed(MouseEvent e) {
		showPopup(e);
	}
	
	/**
	 * Shows the pop up menu.
	 */
	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger() && popup != null) {
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
