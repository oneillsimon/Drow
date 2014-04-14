package drow.document;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.text.StyledDocument;

import sl.docx.DocxDocument;
import drow.view.DocumentView;

public class PageCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<StyledDocument> pages;

	public PageCollection() {
		pages = new ArrayList<StyledDocument>();
	}
	
	public ArrayList<StyledDocument> getPages() {
		return pages;
	}

	public void setPages(DrowDocument doc) {
		for(int i = 0; i < doc.getPages().size(); i++) {
			pages.add(doc.getPages().get(i).getStyledDocument());
		}
	}
	
	public void applyToDocument(DocumentView docView) {
		
		for(int i = 0; i < pages.size(); i++) {
			docView.getDrowDocument().add(docView.getDrowDocument().newPage(docView));
			docView.getDrowDocument().getPages().get(i).setStyledDocumentf((DocxDocument)pages.get(i));
		}
	}
}
