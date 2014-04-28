package drow.gui;

import javax.swing.JPanel;

import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import drow.io.DrowIOActionManager;
import drow.view.DocumentView;

/**
 * <h1>DevHomePanel</h1>
 * This is the JPanel used in the home tab when developer mode is active.
 * <p>
 * @author Simon O'Neill
 * @author Graham Wolfe
 * <p>
 */
public class DevHomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/** JButton for opening a file. */
	private JButton btnOpen;
	
	/** JButton for saving a file. */
	private JButton btnSave;
	
	/** JButton for adding a new page to the document. */
	private JButton btnNew;
	
	/** JButton for swapping to word mode. */
	private JButton btnDev;
	
	/** DrowIOActionManager for manager IO actions in developer mode. */
	private DrowIOActionManager ioActionManager;
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - the view containing the the document.
	 */
	public DevHomePanel(DocumentView documentView) {
		setLayout(new GridLayout(2, 9, 0, 0));
		
		btnOpen = new JButton("Open");
		btnSave = new JButton("Save");
		btnNew = new JButton("New");
		btnDev = new JButton("Dev");
		
		ioActionManager = new DrowIOActionManager(documentView);
		
		btnSave.setAction(ioActionManager.saveAction());
		btnOpen.setAction(ioActionManager.openAction());
		btnNew.setAction(ioActionManager.newAction());
		btnDev.setAction(ioActionManager.wordModeAction());
		
		InputMap inputMap = btnSave.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke s = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
		inputMap.put(s, "S");
		btnSave.getActionMap().put("S", new ClickAction(btnSave));
		
		InputMap inputMap1 = btnOpen.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke o = KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK);
		inputMap1.put(o, "O");
		btnOpen.getActionMap().put("O", new ClickAction(btnOpen));

		add(btnOpen);
		add(btnNew);
		add(btnSave);
		add(btnDev);

	}
}
