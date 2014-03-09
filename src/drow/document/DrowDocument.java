package drow.document;

import javax.swing.JPanel;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private DrowPage page;
	private DrowCaretListener listener;
	
	public DrowDocument(JPanel view) {
		page = new DrowPage(this);
		listener = new DrowCaretListener();
		page.getTextPane().addCaretListener(listener);
	}
	
	public DrowPage getPage() {
		return page;
	}
	
	public int getDot() {
		return listener.getDot();
	}
	
	public int getMark() {
		return listener.getMark();
	}
	
	public int getLesser() {
		return listener.getLesser();
	}
	
	public int getDiff() {
		return listener.getDiff();
	}
}
