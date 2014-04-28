package drow.gui;

import javax.swing.JTabbedPane;

import drow.view.DocumentView;

/**
 * <h1>DeveloperTabs</h1>
 * This class contains the JTabbedPane to be used while in developer mode.
 * <p>
 * @author Simon O'Neill
 * @author Graham Wolfe
 * <p>
 */
public class DeveloperTabs extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The document view containing the document.
	 */
	public DeveloperTabs(DocumentView documentView) {
		this.addTab("Home", new DevHomePanel(documentView));
	}
}