package drow.highlighter;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 * <h1>DrowHighLighter</h1>
 * <p>
 * This class takes of adding and drawing the highlight, created by the painter.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowHighLighter extends DefaultHighlighter {
	
	/** The Onject that will create and paint the highlight. */
	private DrowHighlightPainter highlightPainter;
	
	/**
	 * <h1>Constructor</h1>
	 * @param highlightPainter - The component that paints the underline.
	 */
	public DrowHighLighter(DrowHighlightPainter highlightPainter) {
		this.highlightPainter = highlightPainter;
	}
	
	/**
	 * Adds the highlight to the JTextComponent.
	 * @param offset - Where to start the highlight in the JTextComponent.
	 * @param length - The length of the highlight.
	 * @return - an object that can be used as a tag to refer to the highlight.
	 * @throws BadLocationException
	 */
	public Object addHighlight(int offset, int length) throws BadLocationException {
		return super.addHighlight(offset, offset + length, highlightPainter);
	}
	
	/**
	 * Controls if highlights are drawn as the text is typed.
	 * @param b - Should the highlights be drawn as the text is typed.
	 */
	public void setDrawsLayeredHighlights(boolean b) {
		super.setDrawsLayeredHighlights(true);
	}
}
