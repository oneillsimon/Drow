package drow.gui;

import javax.swing.JTabbedPane;

import drow.view.DocumentView;

public class WordTabs extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	public WordTabs(DocumentView docView) {
		this.addTab("Home", new HomePanel(docView));
		this.addTab("Insert", new InsertPanel(docView));
	}
}
