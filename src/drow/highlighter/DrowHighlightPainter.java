package drow.highlighter;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.LayeredHighlighter;
import javax.swing.text.Position;
import javax.swing.text.View;

/**
 * <h1>DrowHighlightPainter</h1>
 * Creates and paints the highlight.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowHighlightPainter extends LayeredHighlighter.LayerPainter {

	/** The colour of the highlight. */
	private Color colour;
	
	/**
	 * <h1>Constructor</h1>
	 * @param color - The colour of the highlight.
	 */
	public DrowHighlightPainter(Color colour) {
		this.colour = colour;
	}
	
	@Override
	public void paint(Graphics g, int offset1, int offset2, Shape bounds, JTextComponent component) {
		// This method won't be called.
	}

	/**
	 * Calculates the shape of the highlight and paints it.
	 */
	@Override
	public Shape paintLayer(Graphics g, int offset0, int offset1, Shape bounds, JTextComponent component, View view) {
		g.setColor(colour);
		
		Rectangle rectangle = null;
		
		if(offset0 == view.getStartOffset() && offset1 == view.getEndOffset()) {
			if(bounds instanceof Rectangle) {
				rectangle = (Rectangle)bounds;
			} else {
				rectangle = bounds.getBounds();
			}
		} else {
			try {
				Shape shape = view.modelToView(offset0,
											   Position.Bias.Forward,
											   offset1,
											   Position.Bias.Backward,
											   bounds);
				
				if(shape instanceof Rectangle) {
					rectangle = (Rectangle)shape;
				} else {
					rectangle = shape.getBounds();
				}
			} catch (BadLocationException e) {
				return null;
			}
		}
		
		FontMetrics metrics = component.getFontMetrics(component.getFont());
		int baseLine = rectangle.y + rectangle.height - metrics.getDescent() + 1;
		
		int i = rectangle.x;
		
		for(i = rectangle.x; i < rectangle.x + rectangle.width; i += 2) {
			g.drawLine(i, baseLine, i,  baseLine);
			g.drawLine(i + 1, baseLine + 1, i + 1,  baseLine + 1);
			
		}
		
		return rectangle;
	}

	public Color getColour() {
		return colour;
	}

}