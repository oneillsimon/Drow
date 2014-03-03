package drow.document;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyleContext;

import sl.docx.DocxDocument;
import drow.styles.DrowStyles;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private StyleContext styleContext;
    private DocxDocument styledDocument;
	private JTextPane textPane;
	
	public DrowDocument(JPanel view) {
		
		new DrowStyles();
		
		styleContext = new StyleContext();
		styledDocument = new DocxDocument(styleContext);
		styleContext.addStyle("MainStyle", styleContext.getStyle(StyleContext.DEFAULT_STYLE));
		textPane = new JTextPane(styledDocument);
		
		view.add(textPane);
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
