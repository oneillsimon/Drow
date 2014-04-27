package drow.gui;

/**
 * <h1>WordTabs</h1>
 * This class contains the JTabbedPane to be used while in word mode.
 */
import javax.swing.JTabbedPane;

import drow.view.DocumentView;

public class WordTabs extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the document.
	 */
	public WordTabs(DocumentView documentView) {
		this.addTab("Home", new HomePanel(documentView));
		this.addTab("Insert", new InsertPanel(documentView));
	}
}
