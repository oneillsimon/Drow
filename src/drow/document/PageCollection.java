package drow.document;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.text.StyledDocument;

import sl.docx.DocxDocument;
import drow.view.DocumentView;

/**
 * <h1>PageCollection</h1>
 * This class takes the document and converts to a format to be serialized and exported.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class PageCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** A list of DrowPages. */
	private ArrayList<StyledDocument> pages;

	/**
	 * <h1>Constructor</h1>
	 */
	public PageCollection() {
		pages = new ArrayList<StyledDocument>();
	}
	
	/**
	 * Gets the pages in the collection.
	 * @return ArrayList<DrowPage> - The list of pages in the collection.
	 */
	public ArrayList<StyledDocument> getPages() {
		return pages;
	}

	/**
	 * Sets the pages in this collection to the pages in the document.
	 * @param document - The DrowDocument containing the pages.
	 */
	public void setPages(DrowDocument document) {
		for(int i = 0; i < document.getPages().size(); i++) {
			pages.add(document.getPages().get(i).getStyledDocument());
		}
	}
	
	/**
	 * Applies the pages in this collection to the document.
	 * @param documentView - The document view containing the document.
	 */
	public void applyToDocument(DocumentView documentView) {
		for(int i = 0; i < pages.size(); i++) {
			documentView.getDrowDocument().add(documentView.getDrowDocument().newPage());
			documentView.getDrowDocument().getPages().get(i).setStyledDocumentf((DocxDocument)pages.get(i));
		}
	}
}