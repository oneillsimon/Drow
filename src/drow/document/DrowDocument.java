package drow.document;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import sl.docx.DocxDocument;
import drow.document.helpers.DrowDocumentHelper;
import drow.view.DocumentView;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static int FOCUSED_PAGE_NUMBER = 0;
	public static int BOTTOM_OF_LAST_PAGE;
	
	private ArrayList<DrowPage> pages;
	public int pageIndex = 0;
	
	public DrowDocument(DocumentView docView) {
		pages = new ArrayList<DrowPage>();
		this.setBackground(Color.darkGray);
		this.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowPage.BOTTOM_OF_LAST));
	}
	
	public DrowDocument(DrowDocument doc) {
		System.out.println(doc.getPages().size());
		pages = doc.getPages();
		this.setBackground(Color.darkGray);
		this.setPreferredSize(new Dimension(DrowPage.WIDTH, DrowPage.BOTTOM_OF_LAST));
	}
	
	public DrowPage newPage(DocumentView docView) {
		pages.add(new DrowPage(pageIndex));
		pages.get(pageIndex).requestFocusInWindow();
		setPreferredSize(new Dimension(DrowPage.WIDTH, DrowDocument.BOTTOM_OF_LAST_PAGE));
		determinePageX();
		repaint();
		
		return pages.get(pageIndex++);
	}
	
	public void determinePageX() {
		for(int i = 0; i < pages.size(); i++) {
			pages.get(i).determineX();
		}
	}
	
	public ArrayList<DrowPage> getPages() {
		return pages;
	}
	
	public DrowPage getCombinedPage() {
		DrowPage combinedPage = new DrowPage(0);
		
		for(int i = 0; i < pages.size(); i++) {
			try {
				DrowDocumentHelper.mergeDocument(pages.get(i).getStyledDocument(),
												 combinedPage.getStyledDocument());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
		return combinedPage;
	}
	
	public void removeAllPages() {
		pages.clear();
		pageIndex = 0;
		repaint();
	}
	
	public void removeListeners() {
		for(int i = 0; i < pages.size(); i++) {
			pages.get(i).removeListeners();
		}
	}
	
	public void addListeners() {
		for(int i = 0; i < pages.size(); i++) {
			pages.get(i).addListeners();
		}
	}
	
	public int getPageCount() {
		return pages.size();
	}
	
	public DrowPage getFocusedPage() {
		try {
			return pages.get(FOCUSED_PAGE_NUMBER);
		} catch (Exception e) {
			return new DrowPage();
		}
	}
	
	public int getDot() {
		return pages.get(FOCUSED_PAGE_NUMBER).getDot();
	}
	
	public int getMark() {
		return pages.get(FOCUSED_PAGE_NUMBER).getMark();
	}
	
	public int getLesser() {
		return pages.get(FOCUSED_PAGE_NUMBER).getCaretListener().getLesser();
	}
	
	public int getDiff() {
		return pages.get(FOCUSED_PAGE_NUMBER).getCaretListener().getDiff();
	}
}