package drow.highlighter;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class DrowHighlightManager {
	
	private DrowHighlightPainter highlightPainter;
	private DrowHighLighter highlighter;
	
	public DrowHighlightManager(JTextPane textPane, Color color) {
		highlightPainter = new DrowHighlightPainter(color);
		highlighter = new DrowHighLighter(highlightPainter);
		textPane.setHighlighter(highlighter);
	}
	
	//TODO: take in start and end index of word
	public void highlight(int offset, int length) {
		try {
			highlighter.addHighlight(offset, length);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
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
}
