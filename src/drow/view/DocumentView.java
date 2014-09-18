package drow.view;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import drow.document.DrowDocument;
import drow.document.DrowDocumentManager;
import drow.document.DrowPage;
import drow.gui.DrowGui;
import drow.gui.MouseMenu;
import drow.gui.WordTabs;
import drow.io.Filters;

/**
 * <h1>DocumentView</h1>
 * The main view, which contains the document.
 * <p>
 * @author Simon O'Neill
 * @author Graham Wolfe
 * <p>
 */
public class DocumentView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** The width of the frame. */
	public static int WINDOW_WIDTH = 750;
	
	/** The height of the frame. */
	public static int WINDOW_HEIGHT = 500;
	
	/** Is the GUI in developer mode. */
	public static boolean IS_IN_DEV_MODE = false;
	
	/** The document manager. */
	private DrowDocumentManager documentManager;
	
	/** The document. */
	private DrowDocument document;
	
	/** The GUI. */
	private DrowGui gui;

	/**
	 * <h1>Constructor</h1>
	 */
	public DocumentView() {
		
		/** Sets up the FileFilters. */
		Filters.setUp();
		
		document = new DrowDocument();
		
		documentManager = new DrowDocumentManager(this);
		
		gui = new DrowGui(this, new WordTabs(this));
		
		document.addMouseListener(new MouseMenu(this));
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle("Drow Word");
		this.setVisible(true);
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				WINDOW_WIDTH = getWidth();
				WINDOW_HEIGHT = getHeight();
				document.determinePageX();
				document.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowDocument.BOTTOM_OF_LAST_PAGE));
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				WINDOW_WIDTH = getWidth();
				WINDOW_HEIGHT = getHeight();
				document.determinePageX();
				document.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowDocument.BOTTOM_OF_LAST_PAGE));
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
		});
		
		document.determinePageX();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Gets the document manager.
	 * @return DrowDocumentManager - The manager for the documents.
	 */
	public DrowDocumentManager getDrowDocumentManager() {
		return documentManager;
	}
	
	/**
	 * Gets the document.
	 * @return DrowDocument - The document in the view.
	 */
	public DrowDocument getDrowDocument() {
		return document;
	}
	
	/**
	 * Gets the GUI.
	 * @return DrowGui - The GUI inside the JFrame.
	 */
	public DrowGui getGui() {
		return gui;
	}
}