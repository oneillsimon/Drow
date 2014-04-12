package drow.document;

import java.awt.Insets;
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
	
	private static final long serialVersionUID = 1L;
	
	private StyleContext styleContext;
    private DocxDocument styledDocument;
	
	private DictionaryListener dictionaryListener;
	private DrowCaretListener caretListener;
	
	private int number;
	
	public DrowPage(int pageNumber) {
		
		this.setPageDimensions(PageDimensions.A4);
		this.setBounds(X, Y_OFFSET + (HEIGHT * pageNumber) + (Y_OFFSET * pageNumber), WIDTH, HEIGHT);
		this.setMargin(new Insets(10, 10, 10, 10));
		
		styleContext = new StyleContext();
		styledDocument = new DocxDocument(styleContext);
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));

		this.setStyledDocumentf(styledDocument);
		
		caretListener = new DrowCaretListener();
		this.addCaretListener(caretListener);
		
		dictionaryListener = new DictionaryListener(this);
		styledDocument.addDocumentListener(dictionaryListener);
		
		this.number = pageNumber;
		
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
	
	public DrowPage(DocxDocument styledDocument) {
		styleContext = new StyleContext();
		this.styledDocument = styledDocument;
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));
		this.setStyledDocumentf(this.styledDocument);
	}
	
	public void determineX() {
		X = (DocumentView.WINDOW_WIDTH / 2) - (WIDTH / 2);
		setBounds(X, Y_OFFSET, WIDTH, HEIGHT);
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
}
