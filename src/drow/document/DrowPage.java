package drow.document;

import javax.swing.JTextPane;
import javax.swing.text.StyleContext;

import drow.spellchecker.DictionaryListener;
import drow.spellchecker.MyDictionaryListener;
import sl.docx.DocxDocument;

public class DrowPage {
	
	private StyleContext styleContext;
    private DocxDocument styledDocument;
	private JTextPane textPane;
	
	private DictionaryListener dictionaryListener;
	private MyDictionaryListener myDictionaryListener;
	
	public DrowPage(DrowDocument doc) {
		styleContext = new StyleContext();
		styledDocument = new DocxDocument(styleContext);
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));
		textPane = new JTextPane(styledDocument);

		textPane = new JTextPane();
		textPane.setStyledDocument(styledDocument);
		doc.add(textPane);
		
		dictionaryListener = new DictionaryListener(textPane);
		myDictionaryListener = new MyDictionaryListener(textPane);
		//textPane.addKeyListener(dictionaryListener);
		styledDocument.addDocumentListener(myDictionaryListener);
	}
	
	public DrowPage(DrowDocument doc, DocxDocument styledDocument) {
		styleContext = new StyleContext();
		this.styledDocument = styledDocument;
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));
		textPane = new JTextPane(this.styledDocument);

		textPane = new JTextPane();
		textPane.setStyledDocument(this.styledDocument);
		doc.add(textPane); 
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

	public void setStyledDocument(DocxDocument styledDocument) {
		this.styledDocument = styledDocument;
		this.textPane.setStyledDocument(styledDocument);
	}
	
	public JTextPane getTextPane() {
		return textPane;
	}

	public void setTextPane(JTextPane readObject) {
		this.textPane = readObject;
	}
}
