package drow.highlighter;

import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

public class DrowHighlightManager {
	
	private DrowHighlightPainter highlightPainter;
	private DrowHighLighter highlighter;
	
	public DrowHighlightManager(JTextComponent textComp, Color color) {
		highlightPainter = new DrowHighlightPainter(color);
		highlighter = new DrowHighLighter(highlightPainter);
		textComp.setHighlighter(highlighter);
	}
	
	public void highlight(int offset, int length) {
		try {
			highlighter.addHighlight(offset, length);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public DrowHighlightPainter getHighlightPainter() {
		return highlightPainter;
	}

	public void setHighlightPainter(DrowHighlightPainter highlightPainter) {
		this.highlightPainter = highlightPainter;
	}

	public DrowHighLighter getHighlighter() {
		return highlighter;
	}

	public void setHighlighter(DrowHighLighter highlighter) {
		this.highlighter = highlighter;
	}

	public Color getColor() {
		return highlightPainter.getColor();
	}
}
