package drow.document;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextPane;
import javax.swing.text.StyleContext;

import sl.docx.DocxDocument;
import drow.spellchecker.DictionaryListener;
import drow.view.DocumentView;

/**
 * <h1>DrowPage</h1>
 * <p>
 * An extension of JTextPane, this class is the class that the user manipulates
 * and populates with text. It handles the styling of the text and the absolute positioning
 * of the page in the DrowDocument.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowPage extends JTextPane {
	
	private static final long serialVersionUID = 1L;
	
	/** The X coordinate of the page in the document. */
	public static int X;
	
	/** The Y coordinate of the page in the document. Initial value is 10. */
	public static int Y_OFFSET = 10;
	
	/** The width of the page. */
	public static int WIDTH;
	
	/** The height of the page. */
	public static int HEIGHT;
	
	/** The bottom of the last page. */
	public static int BOTTOM_OF_LAST;
	
	/** The stlyeContext of the page. */
	private StyleContext styleContext;
	
	/** The styledDocument of the page. This looks after the various styles applied to the page. */
    private DocxDocument styledDocument;
	
    /** The listener that will check word spelling. */
	private DictionaryListener dictionaryListener;
	
	/** The listener that will check the caret's position in the document. */
	private DrowCaretListener caretListener;
	
	/** The page's number in the document. */
	private int number;
	
	/**
	 * <h1>Default Constructor</h1>
	 * Creates an empty page which can be used logically but will never be styled or displayed.
	 */
	public DrowPage() {
		setPageDimensions(PageDimensions.A4);
		setBounds(X, Y_OFFSET + (HEIGHT * 0) + (Y_OFFSET * 0), WIDTH, HEIGHT);
		BOTTOM_OF_LAST = Y_OFFSET + (HEIGHT * 0) + (Y_OFFSET * 0) + HEIGHT + Y_OFFSET;
		DrowDocument.BOTTOM_OF_LAST_PAGE = BOTTOM_OF_LAST;
	}
	
	/**
	 * <h1>Constructor used most of the time</h1>
	 * Creates a page, sets its width and height and calculates it X and Y, all based on the pageNumber.
	 * @param pageNumber - The number of the page in the document.
	 */
	public DrowPage(int pageNumber) {
		
		setPageDimensions(PageDimensions.A4);
		setBounds(X, Y_OFFSET + (HEIGHT * pageNumber) + (Y_OFFSET * pageNumber), WIDTH, HEIGHT);
		BOTTOM_OF_LAST = Y_OFFSET + (HEIGHT * pageNumber) + (Y_OFFSET * pageNumber) + HEIGHT + Y_OFFSET;
		DrowDocument.BOTTOM_OF_LAST_PAGE = BOTTOM_OF_LAST;
		
		styleContext = new StyleContext();
		styledDocument = new DocxDocument(styleContext);
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));

		setStyledDocumentf(styledDocument);
		
		if(DocumentView.IS_IN_DEV_MODE) {
			addDevListeners();
		} else {
			addWordListeners();
		}
		
		number = pageNumber;
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				DrowDocument.FOCUSED_PAGE_NUMBER = number;
			}
		});
	}
	
	/**
	 * Calculates the page's X coordinate based on the JFrame width.
	 */
	public void determineX() {
		X = (DocumentView.WINDOW_WIDTH / 2) - (WIDTH / 2);
		setBounds(X, Y_OFFSET + (HEIGHT * getNumber()) + (Y_OFFSET * getNumber()), WIDTH, HEIGHT);
		repaint();
	}
	
	/**
	 * Sets the pages dimensions based on a rectangle.
	 * @param r - Rectangle to base the dimensions off of.
	 */
	public void setPageDimensions(Rectangle r) {
		WIDTH = r.width;
		HEIGHT = r.height;
	}
	
	/**
	 * Gets the page's stlyedDocument.
	 */
	public DocxDocument getStyledDocument() {
		return styledDocument;
	}

	/**
	 * Sets the page's styled document.
	 * @param styledDocument - StyledDocument to set.
	 */
	public void setStyledDocumentf(DocxDocument styledDocument) {
		this.styledDocument = styledDocument;
		this.setStyledDocument(styledDocument);
	}
	
	/**
	 * Gets the dot of the caret.
	 * @return int - The dot of the caret in the page.
	 */
	public int getDot() {
		return caretListener.getDot();
	}
	
	/**
	 * Gets the mark of the caret.
	 * @return int - The mark of the caret in the page.
	 */
	public int getMark() {
		return caretListener.getMark();
	}
	
	/**
	 * Gets the caret listener of the page.
	 * @return DrowCaretListener - The caret listener of the page.
	 */
	public DrowCaretListener getCaretListener() {
		return caretListener;
	}
	
	/**
	 * Gets the page's number in the document.
	 * @return int - The page's number.
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Removes all the listeners on the page.
	 */
	public void removeListeners() {
		removeCaretListener(caretListener);
		styledDocument.removeDocumentListener(dictionaryListener);
	}
	
	/**
	 * Adds the listeners for word processing, and a dictionary listener
	 * based on an English Dictionary.
	 */
	public void addWordListeners() {
		caretListener = new DrowCaretListener();
		addCaretListener(caretListener);
		
		dictionaryListener = new DictionaryListener(this, "englishDictionary.txt");
		styledDocument.addDocumentListener(dictionaryListener);
	}
	
	/**
	 * Adds the listeners for developing, and a dictionary based on Java keywords.
	 */
	public void addDevListeners() {
		caretListener = new DrowCaretListener();
		addCaretListener(caretListener);
		
		dictionaryListener = new DictionaryListener(this, "/dev/java.txt", "word", Color.cyan);
		styledDocument.addDocumentListener(dictionaryListener);
	}
}