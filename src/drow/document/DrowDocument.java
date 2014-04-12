package drow.document;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import sl.docx.DocxDocument;
import drow.view.DocumentView;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static int FOCUSED_PAGE_NUMBER = 0;
	
	private ArrayList<DrowPage> pages;
	public int pageIndex = 0;
	
	public DrowDocument(DocumentView docView) {
		
		pages = new ArrayList<DrowPage>();
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.darkGray);
	}
	
	public DrowDocument(DocumentView view, DocxDocument styledDocument) {
		pages = new ArrayList<DrowPage>();
		pages.add(new DrowPage(styledDocument));
	}
	
	public DrowPage newPage(DocumentView docView) {
		pages.add(new DrowPage(pageIndex));
		repaint();
		pages.get(pageIndex).requestFocusInWindow();
		
		return pages.get(pageIndex++);
	}
	
	public void determinePageX() {
		for(DrowPage page : pages) {
			page.determineX();
		}
	}
	
	public ArrayList<DrowPage> getPages() {
		return pages;
	}
	
	public int getPageCount() {
		return pages.size();
	}
	
	public DrowPage getFocusedPage() {
		return pages.get(FOCUSED_PAGE_NUMBER);
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