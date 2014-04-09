package drow.document;

import javax.swing.JPanel;

import drow.view.DocumentView;
import sl.docx.DocxDocument;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private DocumentView docView;
	private DrowPage page;
	private DrowCaretListener caretListener;
	
	public DrowDocument(DocumentView view) {
		
		page = new DrowPage(this);
		docView = view;
		caretListener = new DrowCaretListener();
		page.addCaretListener(caretListener);
	}
	
	public DrowDocument(DocumentView view, DocxDocument styledDocument) {
		page = new DrowPage(this, styledDocument);
		caretListener = new DrowCaretListener();
		page.addCaretListener(caretListener);
	}
	
	public DocumentView getView() {
		return docView;
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
