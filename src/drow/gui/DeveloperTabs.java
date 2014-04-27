package drow.gui;

import javax.swing.JTabbedPane;

import drow.view.DocumentView;

public class DeveloperTabs extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	public DeveloperTabs(DocumentView docView) {
		this.addTab("Home", new DevHomePanel(docView));
	}
}
