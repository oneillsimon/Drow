package drow.document;

import javax.swing.JTextPane;
import javax.swing.text.StyleContext;

import sl.docx.DocxDocument;

public class DrowPage {
	
	private StyleContext styleContext;
    private DocxDocument styledDocument;
	private JTextPane textPane;
	
	public DrowPage(DrowDocument doc) {
		styleContext = new StyleContext();
		styledDocument = new DocxDocument(styleContext);
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));
		textPane = new JTextPane(styledDocument);

		textPane = new JTextPane();
		textPane.setStyledDocument(styledDocument);
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
	}
	
	public JTextPane getTextPane() {
		return textPane;
	}

	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}
}
