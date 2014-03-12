package drow.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sl.docx.DocxDocument;
import drow.document.DrowDocument;
import drow.gui.TabbedGUI;
import drow.io.Filters;
import drow.manager.DrowDocumentManager;
import drow.styles.DrowStyles;

public class DocumentView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel backPanel;
	
	private String currentFileName;
	private boolean changed;
	
	private DrowDocumentManager docManager;
	
	private DrowDocument doc;

	public DocumentView() {
		
		Filters.setUp();
		
		backPanel = new JPanel();
		
		doc = new DrowDocument(backPanel);
		docManager = new DrowDocumentManager(this);
		
		currentFileName = "Untitled Document";
		changed = false;
		
		new TabbedGUI(this);
		
		this.setSize(500, 500);
		this.setTitle(getCurrentFileName());
		this.setVisible(true);
	}
	
	public DocumentView(DocxDocument styledDocument) {
		
		Filters.setUp();
		backPanel = new JPanel();
		
		doc = new DrowDocument(backPanel, styledDocument);
		docManager = new DrowDocumentManager(this);
		
		currentFileName = "Untitled Document";
		changed = false;
		
		new TabbedGUI(this);
		
		this.setSize(500, 500);
		this.setTitle(getCurrentFileName());
		this.setVisible(true);
	}
	
	public JPanel getBackPanel() {
		return backPanel;
	}
	
	public DrowDocumentManager getDrowDocumentManager() {
		return docManager;
	}
	
	public DrowDocument getDrowDocument() {
		return doc;
	}

	public String getCurrentFileName() {
		return currentFileName;
	}

	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

	public boolean getChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
}
