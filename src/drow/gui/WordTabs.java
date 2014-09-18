package drow.gui;

import javax.swing.JTabbedPane;

import drow.view.DocumentView;

/**
 * <h1>WordTabs</h1>
 * This class contains the JTabbedPane to be used while in word mode.
 * <p>
 * @author Simon O'Neill
 * @author Graham Wolfe
 * <p>
 */
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