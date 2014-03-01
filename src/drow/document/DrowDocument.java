package drow.document;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

import drow.styles.DrowStyles;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private StyleContext styleContext;
    private DefaultStyledDocument styledDocument;
	private JTextPane textPane;
	
	
	public DrowDocument(JPanel view) {
		
		new DrowStyles();
		
		styleContext = new StyleContext();
		styledDocument = new DefaultStyledDocument(styleContext);
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

	public DefaultStyledDocument getStyledDocument() {
		return styledDocument;
	}

	public void setStyledDocument(DefaultStyledDocument styledDocument) {
		this.styledDocument = styledDocument;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}
}
