package drow.highlighter;

import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

/**
 * Manages the DrowHighlighter and JTextComponent where the highlights are drawn.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowHighlightManager {

	/** The object that creates and paints the highlight. */
	private DrowHighlightPainter highlightPainter;
	
	/** The object that adds the highlight to the JTextComponent. */
	private DrowHighLighter highlighter;
	
	/**
	 * <h1>Constructor<h1>
	 * @param textComp - The JTextComponent where the highlight will be drawn.
	 * @param color - The color of the highlight.
	 */
	public DrowHighlightManager(JTextComponent textComp, Color color) {
		highlightPainter = new DrowHighlightPainter(color);
		highlighter = new DrowHighLighter(highlightPainter);
		textComp.setHighlighter(highlighter);
	}
	
	/**
	 * Creates a highlight in the DrowHighlighter.
	 * @param offset - The position in the JTextComponenet to start the hightLight.
	 * @param length - The length of the highlight.
	 */
	public void highlight(int offset, int length) {
		try {
			highlighter.addHighlight(offset, length);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the color of the highlight.
	 * @return Color - The color of the highlight.
	 */
	public Color getColor() {
		return highlightPainter.getColor();
	}
}
