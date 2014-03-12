package drow.document;

import javax.swing.JPanel;

import sl.docx.DocxDocument;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private DrowPage page;
	private DrowCaretListener caretListener;
	
	public DrowDocument(JPanel view) {
		page = new DrowPage(this);
		caretListener = new DrowCaretListener();
		page.getTextPane().addCaretListener(caretListener);
	}
	
	public DrowDocument(JPanel view, DocxDocument styledDocument) {
		page = new DrowPage(this, styledDocument);
		caretListener = new DrowCaretListener();
		page.getTextPane().addCaretListener(caretListener);
	}
	
	public DrowPage getPage() {
		return page;
	}
	
	public int getDot() {
		return caretListener.getDot();
	}
	
	public int getMark() {
		return caretListener.getMark();
	}
	
	public int getLesser() {
		return caretListener.getLesser();
	}
	
	public int getDiff() {
		return caretListener.getDiff();
	}
}
