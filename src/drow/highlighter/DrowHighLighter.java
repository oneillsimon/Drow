package drow.highlighter;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 * <h1>DrowHighLighter</h1>
 */
public class DrowHighLighter extends DefaultHighlighter {
	
	private DrowHighlightPainter highlightPainter;
	
	public DrowHighLighter(DrowHighlightPainter highlightPainter) {
		this.highlightPainter = highlightPainter;
	}
	
	public Object addHighlight(int offset, int length) throws BadLocationException {
		return super.addHighlight(offset, offset + length, highlightPainter);
	}
	
	public void setDrawsLayeredHighlights(boolean b) {
		super.setDrawsLayeredHighlights(true);
	}

}
