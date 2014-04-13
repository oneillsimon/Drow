package drow.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import drow.view.DocumentView;

public class TabbedGUI {
	
	private DocumentView docView;
	
	private JTabbedPane tabbedPane;
	
	public TabbedGUI(DocumentView docView) {
		
		this.docView = docView;

		this.docView.setTitle("drow");
		this.docView.setIconImage(new ImageIcon("res/drow.png").getImage());
		
		tabbedPane = new JTabbedPane();
		docView.getDrowDocument().setLayout(null);
		
		JScrollPane sp = new JScrollPane(docView.getDrowDocument(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		docView.add(sp, BorderLayout.CENTER);
		docView.add(tabbedPane, BorderLayout.NORTH);
		
		docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView), BorderLayout.CENTER);
		
		//tab names
		tabbedPane.addTab("Home", new HomePanel(docView));
		tabbedPane.addTab("Insert", new InsertPanel(docView));
		tabbedPane.addTab("Layout", new LayoutPanel());
		tabbedPane.addTab("Language", new LanguagePanel());
		tabbedPane.addTab("Mode", new ModePanel());
	}
}
