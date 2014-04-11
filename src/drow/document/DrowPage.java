package drow.document;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextPane;
import javax.swing.text.StyleContext;

import sl.docx.DocxDocument;
import drow.spellchecker.DictionaryListener;

public class DrowPage extends JTextPane {
	
	public static final int X = 100;
	public static final int Y_OFFSET = 10;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 300;
	
	private static final long serialVersionUID = 1L;
	
	private StyleContext styleContext;
    private DocxDocument styledDocument;
	
	private DictionaryListener dictionaryListener;
	private DrowCaretListener caretListener;
	
	private int number;
	
	public DrowPage(int pageNumber) {
		
		this.setBounds(X, Y_OFFSET + (HEIGHT * pageNumber) + (Y_OFFSET * pageNumber), WIDTH, HEIGHT);
		
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
				System.out.println("I lost focus");
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				DrowDocument.FOCUSED_PAGE_NUMBER = number;
				System.out.println("I have focus");
			}
		});
	}
	
	public DrowPage(DocxDocument styledDocument) {
		styleContext = new StyleContext();
		this.styledDocument = styledDocument;
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));
		this.setStyledDocumentf(this.styledDocument);
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
