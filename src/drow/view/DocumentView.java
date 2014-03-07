package drow.view;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import drow.document.DrowDocument;
import drow.gui.DrowGui;
import drow.gui.TabbedGUI;
import drow.manager.DrowDocumentManager;

public class DocumentView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel background;
	private String currentFileName;
	private boolean changed;
	private DrowGui gui;
	private DrowDocumentManager docManager;
	private TabbedGUI tabbedGUI;
	private DrowDocument doc;

	public DocumentView() {
		background = new JPanel();
		
		doc = new DrowDocument(background);
		docManager = new DrowDocumentManager(this);
		
		currentFileName = "Untitled Document";
		changed = false;
		
		background.setBackground(Color.GRAY);
		
		
		doc = new DrowDocument(background);

		tabbedGUI = new TabbedGUI(this);
		background.setBackground(Color.cyan);
		
		
		//this.setTitle(getCurrentFileName());
		this.setVisible(true);
	}
	
	public DrowGui getDrowGui() {
		return gui;
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
