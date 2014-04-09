package drow.view;

import javax.swing.JFrame;

import sl.docx.DocxDocument;
import drow.document.DrowDocument;
import drow.gui.TabbedGUI;
import drow.io.Filters;
import drow.manager.DrowDocumentManager;

public class DocumentView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String currentFileName;
	private boolean changed;
	
	private DrowDocumentManager docManager;
	
	private DrowDocument doc;
	
	public DocumentView() {
		
		Filters.setUp();
		
		doc = new DrowDocument(this);
		docManager = new DrowDocumentManager(this);
		
		new TabbedGUI(this);
		
		currentFileName = "Untitled Document";
		changed = false;
		
		this.setSize(750, 500);
		this.setTitle(getCurrentFileName());
		this.setVisible(true);
	}
	
	public DocumentView(DocxDocument styledDocument) {
		
		Filters.setUp();
		
		doc = new DrowDocument(this, styledDocument);
		docManager = new DrowDocumentManager(this);
		
		currentFileName = "Untitled Document";
		changed = false;
		
		new TabbedGUI(this);
		
		this.setTitle(getCurrentFileName());
		this.setVisible(true);
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
