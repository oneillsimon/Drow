package drow.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import drow.view.DocumentView;

/**mouse menu is the class for a popup menu when a user right 
 * clicks around the page. the right click currently doesn't work 
 * on the page because of the styled document we use.
 * 
 * **/
public class MouseMenu extends MouseAdapter {
	/** 
	 * creating new popup menu
	 */
	private JPopupMenu popup = new JPopupMenu();

	//creating new abstract actions instead
	private AbstractAction cutAction;
	private AbstractAction copyAction;
	private AbstractAction pasteAction;
	private AbstractAction undoAction;
	private AbstractAction selectAllAction;


	private JTextComponent textComponent;
	private String savedString = "";
	private Actions lastActionSelected;

	//define different actions which are passed into each action method
	private enum Actions { UNDO, CUT, COPY, PASTE, SELECT_ALL };

	/**
	 * each method holds an action and the method takes in the parameter of docView 
	 * @param docView
	 */
	//undo action in popup menu
	public MouseMenu(final DocumentView docView) {
		undoAction = new AbstractAction("Undo") {
			//each method has an if action is performed and a task associated with it
			@Override
			public void actionPerformed(ActionEvent ae) {
				textComponent = docView.getDrowDocument().getFocusedPage();
				textComponent.replaceSelection(savedString);
				lastActionSelected = Actions.UNDO;
			}
		};

		//add undo to popup and put a seperator underneath it
		popup.add(undoAction);
		popup.addSeparator();

		//cut method
		cutAction = new AbstractAction("Cut") {
			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.CUT;
				savedString = textComponent.getText();
				textComponent.cut();
			}
		};
		//add cut action to popup
		
		popup.add(cutAction);

		copyAction = new AbstractAction("Copy") {

			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.COPY;
				textComponent.copy();
			}
		};
		//add copy action to popup
		popup.add(copyAction);

		pasteAction = new AbstractAction("Paste") {

			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.PASTE;
				savedString = textComponent.getText();
				textComponent.paste();
			}
		};
		//add paste action to popup and put seperator beneath it
		popup.add(pasteAction);
		popup.addSeparator();

		selectAllAction = new AbstractAction("Select All") {

			@Override
			public void actionPerformed(ActionEvent ae) {
				lastActionSelected = Actions.SELECT_ALL;
				textComponent.selectAll();
			}
		};
		//add select action to popup
		popup.add(selectAllAction);
	}

	/**
	 * mouse event handling, this is for when the mouse button is clocked and released
	 * and also for when anything is picked off the popup menu to be used
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			if (!(e.getSource() instanceof JTextComponent)) {
				return;
			}

			textComponent = (JTextPane) e.getSource();
			textComponent.requestFocus();
			
			//enable all the popup menu components
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

			//setting size of popup menu.
			int nx = e.getX();

			if (nx > 500) {
				nx = nx - popup.getSize().width;
			}
			popup.show(e.getComponent(), nx, e.getY() - popup.getSize().height);
		}
	}
	public void mouseReleased(MouseEvent e) {
		showPopup(e); 
	}
	public void mousePressed(MouseEvent e) {
		showPopup(e);
	}
	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger() && popup != null) {
			//DrowGUI.setText(e.toString());
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
