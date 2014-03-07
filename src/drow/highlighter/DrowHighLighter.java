package drow.highlighter;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

public class DrowHighLighter extends DefaultHighlighter {
	
	private DrowHighlightPainter highlightPainter;
	
	public DrowHighLighter(DrowHighlightPainter highlightPainter) {
		this.highlightPainter = highlightPainter;
	}
	
	public Object addHighlight(int p0, int p1) throws BadLocationException {
		return addHighlight(p0, p1, highlightPainter);
	}
	
	public void setDrawsLayeredHighlights(boolean b) {
		super.setDrawsLayeredHighlights(b);
	}

}
