package drow.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import drow.view.DocumentView;

public class DrowGui {

	private DocumentView docView;
	
	protected JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	
	protected HomePanel homePanel;
	protected InsertPanel insertPanel;
	protected LanguagePanel languagePanel;
	protected DevHomePanel modePanel;
	
	public DrowGui(DocumentView docView, JTabbedPane tabbedPane) {
		this.docView = docView;
		
		homePanel = new HomePanel(docView);
		insertPanel = new InsertPanel(docView);
		languagePanel = new LanguagePanel();
		modePanel = new DevHomePanel(docView);

		this.docView.setTitle("drow");
		this.docView.setIconImage(new ImageIcon("res/drow.png").getImage());
		
		docView.getDrowDocument().setLayout(null);
		
		scrollPane = new JScrollPane(docView.getDrowDocument(),
									 JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
									 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(null);
		this.tabbedPane = tabbedPane;
		
		docView.add(scrollPane, BorderLayout.CENTER);
		docView.add(tabbedPane, BorderLayout.NORTH);
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane) {
		docView.remove(this.tabbedPane);
		this.tabbedPane = tabbedPane;
		docView.add(this.tabbedPane, BorderLayout.NORTH);
		tabbedPane.revalidate();
		tabbedPane.repaint();
	}
}
