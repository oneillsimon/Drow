package drow.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import drow.document.DrowDocument;
import drow.gui.TabbedGUI;
import drow.manager.DrowDocumentManager;
import drow.styles.DrowStyleActions;
import drow.styles.DrowStyles;

public class DocumentView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel background;
	
	private String currentFileName;
	private boolean changed;
	
	private DrowDocumentManager docManager;
	
	private DrowDocument doc;

	public DocumentView() {
		background = new JPanel();
		
		doc = new DrowDocument(background);
		docManager = new DrowDocumentManager(this);
		
		currentFileName = "Untitled Document";
		changed = false;
		
		new TabbedGUI(this);
		new DrowStyles();
		new DrowStyleActions(this);
		
		this.setSize(500, 500);
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
