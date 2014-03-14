package drow.highlighter;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

public class DrowHighLighter extends DefaultHighlighter {
	
	private DrowHighlightPainter highlightPainter;
	
	public DrowHighLighter(DrowHighlightPainter highlightPainter) {
		this.highlightPainter = highlightPainter;
	}
	
	public Object addHighlight(int offset, int length) throws BadLocationException {
		return addHighlight(offset, length, highlightPainter);
	}
	
	public void setDrawsLayeredHighlights(boolean b) {
		super.setDrawsLayeredHighlights(b);
	}

}
