package drow.gui;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import drow.view.DocumentView;

public class TabbedGUI {
	
	private DocumentView docView;
	
	private JTabbedPane tabbedPane;
	private JSplitPane verticalSplitPane;
	
	public TabbedGUI(DocumentView docView) {
		
		this.docView = docView;

		this.docView.setTitle("drow");
		this.docView.setIconImage(new ImageIcon("res/drow.png").getImage());
		
		tabbedPane = new JTabbedPane();
		verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
										   tabbedPane,
										   docView.getDrowDocument().getPage().getTextPane());
		
		//tab names
		tabbedPane.addTab("Home", new HomePanel(this.docView));
		tabbedPane.addTab("Insert", new InsertPanel());
		tabbedPane.addTab("Layout", new LayoutPanel());
		tabbedPane.addTab("Language", new LanguagePanel());
		tabbedPane.addTab("Format", new FormatPanel());

		docView.getContentPane().add(verticalSplitPane);
		docView.setSize(500,200);
		verticalSplitPane.setDividerLocation(docView.getHeight()/2);
		verticalSplitPane.setDividerSize(0);
	}

}
