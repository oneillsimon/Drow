package drow.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import drow.view.DocumentView;

public class TabbedGUI {
	
	private DocumentView docView;
	
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	
	public TabbedGUI(DocumentView docView) {
		
		this.docView = docView;

		this.docView.setTitle("drow");
		this.docView.setIconImage(new ImageIcon("res/drow.png").getImage());
		
		tabbedPane = new JTabbedPane();
		docView.getDrowDocument().setLayout(null);
		
		scrollPane = new JScrollPane(docView.getDrowDocument(),
												 JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(null);
		docView.add(scrollPane, BorderLayout.CENTER);
		docView.add(tabbedPane, BorderLayout.NORTH);
		
		//docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView));
		//docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView));
		//docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView));
		
		//tab names
		tabbedPane.addTab("Home", new HomePanel(docView));
		tabbedPane.addTab("Insert", new InsertPanel(docView));
		tabbedPane.addTab("Layout", new LayoutPanel());
		tabbedPane.addTab("Language", new LanguagePanel());
		tabbedPane.addTab("Mode", new ModePanel());
	}
}
