package drow.highlighter;

import java.awt.Color;

import javax.swing.text.BadLocationException;

public class DrowHighlightManager {
	
	private DrowHighlightPainter highlightPainter;
	private DrowHighLighter highlighter;
	
	public DrowHighlightManager(Color color) {
		highlightPainter = new DrowHighlightPainter(color);
		highlighter = new DrowHighLighter(highlightPainter);
	}
	
	//TODO: take in start and end index of word
	public void highlight(String text) {
		try {
			highlighter.addHighlight(0, text.length());
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
