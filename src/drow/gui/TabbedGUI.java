package drow.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
										   docView.getDrowDocument());
		
		docView.getDrowDocument().setLayout(null);
		docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView), BorderLayout.CENTER);
		//docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView), BorderLayout.CENTER);
		//docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView), BorderLayout.CENTER);
		
		//tab names
		tabbedPane.addTab("Home", new HomePanel(docView));
		tabbedPane.addTab("Insert", new InsertPanel(docView));
		tabbedPane.addTab("Layout", new LayoutPanel());
		tabbedPane.addTab("Language", new LanguagePanel());
		tabbedPane.addTab("Mode", new ModePanel());

		docView.getContentPane().add(verticalSplitPane);
		verticalSplitPane.setDividerSize(0);
	}

}
