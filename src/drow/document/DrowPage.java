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

public class DrowPage extends JTextPane {
	
	public static int X;
	public static int Y_OFFSET = 10;
	public static int WIDTH;
	public static int HEIGHT;
	public static int BOTTOM_OF_LAST;
	
	private static final long serialVersionUID = 1L;
	
	private StyleContext styleContext;
    private DocxDocument styledDocument;
	
	private DictionaryListener dictionaryListener;
	private DrowCaretListener caretListener;
	
	private int number;
	
	public DrowPage() {
		setPageDimensions(PageDimensions.A4);
		setBounds(X, Y_OFFSET + (HEIGHT * 0) + (Y_OFFSET * 0), WIDTH, HEIGHT);
		BOTTOM_OF_LAST = Y_OFFSET + (HEIGHT * 0) + (Y_OFFSET * 0) + HEIGHT + Y_OFFSET;
		DrowDocument.BOTTOM_OF_LAST_PAGE = BOTTOM_OF_LAST;
	}
	
	public DrowPage(int pageNumber) {
		
		setPageDimensions(PageDimensions.A6);
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
	
	public void determineX() {
		X = (DocumentView.WINDOW_WIDTH / 2) - (WIDTH / 2);
		setBounds(X, Y_OFFSET + (HEIGHT * getNumber()) + (Y_OFFSET * getNumber()), WIDTH, HEIGHT);
		repaint();
	}
	
	public void setPageDimensions(Rectangle r) {
		WIDTH = r.width;
		HEIGHT = r.height;
	}
	
	public StyleContext getStyleContext() {
		return styleContext;
	}

	public void setStyleContext(StyleContext styleContext) {
		this.styleContext = styleContext;
	}

	public DocxDocument getStyledDocument() {
		return styledDocument;
	}

	public void setStyledDocumentf(DocxDocument styledDocument) {
		this.styledDocument = styledDocument;
		this.setStyledDocument(styledDocument);
	}
	
	public int getDot() {
		return getDot();
	}
	
	public int getMark() {
		return getMark();
	}
	
	public DrowCaretListener getCaretListener() {
		return caretListener;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void removeListeners() {
		removeCaretListener(caretListener);
		styledDocument.removeDocumentListener(dictionaryListener);
	}
	
	public void addWordListeners() {
		caretListener = new DrowCaretListener();
		addCaretListener(caretListener);
		
		dictionaryListener = new DictionaryListener(this, "englishDictionary.txt");
		styledDocument.addDocumentListener(dictionaryListener);
	}
	
	public void addDevListeners() {
		caretListener = new DrowCaretListener();
		addCaretListener(caretListener);
		
		dictionaryListener = new DictionaryListener(this, "/dev/java.txt", "word", Color.blue);
		styledDocument.addDocumentListener(dictionaryListener);
	}
}
