package drow.document;

import javax.swing.JTextPane;
import javax.swing.text.StyleContext;

import drow.spellchecker.DictionaryListener;
import sl.docx.DocxDocument;

public class DrowPage extends JTextPane {
	
	private static final long serialVersionUID = 1L;
	
	private StyleContext styleContext;
    private DocxDocument styledDocument;
	//private JTextPane textPane;
	
	private DictionaryListener dictionaryListener;
	
	public DrowPage(DrowDocument doc) {
		styleContext = new StyleContext();
		styledDocument = new DocxDocument(styleContext);
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));

		this.setStyledDocumentf(styledDocument);
		doc.add(this);
		
		dictionaryListener = new DictionaryListener(this);
		styledDocument.addDocumentListener(dictionaryListener);
	}
	
	public DrowPage(DrowDocument doc, DocxDocument styledDocument) {
		styleContext = new StyleContext();
		this.styledDocument = styledDocument;
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));
		this.setStyledDocumentf(this.styledDocument);

		//textPane = new JTextPane();
		//textPane.setStyledDocument(this.styledDocument);
		doc.add(this); 
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
		//this.textPane.setStyledDocument(styledDocument);
		this.setStyledDocument(styledDocument);
	}
}
