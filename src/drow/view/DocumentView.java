package drow.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sl.docx.DocxDocument;
import drow.document.DrowDocument;
import drow.document.DrowDocumentManager;
import drow.document.DrowPage;
import drow.gui.TabbedGUI;
import drow.io.Filters;

public class DocumentView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static int WINDOW_WIDTH = 750;
	public static int WINDOW_HEIGHT = 500;
	
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
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle(getCurrentFileName());
		this.setVisible(true);
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				WINDOW_WIDTH = getWidth();
				WINDOW_HEIGHT = getHeight();
				doc.determinePageX();
				doc.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowDocument.BOTTOM_OF_LAST_PAGE));
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				WINDOW_WIDTH = getWidth();
				WINDOW_HEIGHT = getHeight();
				doc.determinePageX();
				doc.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowDocument.BOTTOM_OF_LAST_PAGE));
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
		});
		
		doc.determinePageX();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
