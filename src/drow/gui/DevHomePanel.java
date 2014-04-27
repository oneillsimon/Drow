package drow.gui;

import javax.swing.JPanel;

import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import drow.io.DrowIOActionManager;
import drow.view.DocumentView;

public class DevHomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnOpen;
	private JButton btnSave;
	private JButton btnSaveAs;
	private JButton btnNew;
	private JButton btnDev;
	
	private DrowIOActionManager ioActionManager;
	
	
	public DevHomePanel(DocumentView docView) {
		setLayout(new GridLayout(2, 9, 0, 0));
		
		btnOpen = new JButton("Open");
		btnSave = new JButton("Save");
		btnSaveAs = new JButton("Save As");
		btnNew = new JButton("New");
		btnDev = new JButton("Dev");
		
		ioActionManager = new DrowIOActionManager(docView);
		
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
		add(btnSaveAs);
		add(btnDev);

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
