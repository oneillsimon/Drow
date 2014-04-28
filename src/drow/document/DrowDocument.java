package drow.document;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import drow.document.helpers.DrowDocumentHelper;

/**
 * <h1>Drow Document</h1>
 * <p>
 * This is the container that holds all the pages.
 * It also features many managerial and convenience methods
 * for manipulating the pages.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;

	/** Stores the number of the page that currently has focus. */
	public static int FOCUSED_PAGE_NUMBER = 0;
	
	/** Stores the lowest point of the document. */
	public static int BOTTOM_OF_LAST_PAGE;
	
	/** A list of all the DrowPages in the document. */
	private ArrayList<DrowPage> pages;
	
	/** The current index of the list. */
	public int pageIndex = 0;

	/**
	 * Default Constructor.
	 */
	public DrowDocument() {
		pages = new ArrayList<DrowPage>();
		this.setBackground(Color.darkGray);
		this.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowPage.BOTTOM_OF_LAST));
	}
	
	/**
	 * Constructor used when loading a .drow file.
	 * @param document - the document being loaded.
	 */
	public DrowDocument(DrowDocument document) {
		pages = document.getPages();
		this.setBackground(Color.darkGray);
		this.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowPage.BOTTOM_OF_LAST));
	}
	
	/**
	 * Creates a new page, adds it to the list, gives it focus and sets the dimensions.
	 * @return DrowPage - the new page.
	 */
	public DrowPage newPage() {
		pages.add(new DrowPage(pageIndex));
		pages.get(pageIndex).requestFocusInWindow();
		setPreferredSize(new Dimension(DrowPage.WIDTH, DrowDocument.BOTTOM_OF_LAST_PAGE));
		determinePageX();
		repaint();
		
		return pages.get(pageIndex++);
	}
	
	/**
	 * Determines the X values at which the page should be painted in the view.
	 */
	public void determinePageX() {
		
		if(pages == null)
			return;
		
		for(int i = 0; i < pages.size(); i++) {
			pages.get(i).determineX();
		}
	}
	
	/**
	 * Gets the list of pages.
	 * @return ArrayList<DrowPage> - the list of pages in the document.
	 */
	public ArrayList<DrowPage> getPages() {
		return pages;
	}
	
	/**
	 * Combines all DrowPages in the list into a single DrowPage.
	 * @return DrowPage - a combined DrowPage from all the pages in the list.
	 */
	public DrowPage getCombinedPage() {
		DrowPage combinedPage = new DrowPage(0);
		
		for(int i = 0; i < pages.size(); i++) {
			try {
				DrowDocumentHelper.mergeDocument(pages.get(i).getStyledDocument(),
												 combinedPage.getStyledDocument());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
		return combinedPage;
	}
	
	/**
	 * Deletes all pages in the document.
	 */
	public void removeAllPages() {
		pages.clear();
		pageIndex = 0;
		repaint();
	}
	
	/**
	 * Removes all listeners from the pages.
	 */
	public void removeListeners() {
		for(int i = 0; i < pages.size(); i++) {
			pages.get(i).removeListeners();
		}
	}
	
	/**
	 * Adds the appropriate listeners for word processing to each page.
	 */
	public void addWordListeners() {
		for(int i = 0; i < pages.size(); i++) {
			pages.get(i).addWordListeners();
		}
	}
	
	/**
	 * Adds the appropriate listeners for coding to each page.
	 */
	public void addDevListeners() {
		for(int i = 0; i < pages.size(); i++) {
			pages.get(i).addDevListeners();
		}
	}
	
	/**
	 * Wrapper method for removing coding listeners anding add word listeners.
	 */
	public void swapToWordListeners() {
		removeListeners();
		addWordListeners();
	}
	
	/**
	 * Wrapper method for removing word listeners anding coding word listeners.
	 */
	public void swapToDevListeners() {
		removeListeners();
		addDevListeners();
		System.out.println("here");
	}
	
	/**
	 * Gets the amount of pages in the document.
	 * @return int - the size of the list of pages.
	 */
	public int getPageCount() {
		return pages.size();
	}
	
	/**
	 * Gets the DrowPage that has focus.
	 * @return DrowPage - returns the DrowPage that currently has focus.
	 */
	public DrowPage getFocusedPage() {
		try {
			return pages.get(FOCUSED_PAGE_NUMBER);
		} catch (Exception e) {
			return new DrowPage();
		}
	}
	
	/**
	 * Gets the dot value of the caret in the focused page.
	 * @return int - the dot value of the caret.
	 */
	public int getDot() {
		return pages.get(FOCUSED_PAGE_NUMBER).getDot();
	}
	
	/**
	 * Gets the mark value of the caret in the focused page.
	 * @return int - the mark value of the caret.
	 */
	public int getMark() {
		return pages.get(FOCUSED_PAGE_NUMBER).getMark();
	}
	
	/**
	 * Gets the smallest value of the dot and mark in the focused page.
	 * @return int - the lower value of the dot and mark of the caret.
	 */
	public int getLesser() {
		return pages.get(FOCUSED_PAGE_NUMBER).getCaretListener().getLesser();
	}
	
	/**
	 * Gets the absolute value of the dot minus the mark in the focused page.
	 * @return int - the difference between the dot and mark of the caret.
	 */
	public int getDiff() {
		return pages.get(FOCUSED_PAGE_NUMBER).getCaretListener().getDiff();
	}
}