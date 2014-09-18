package drow.document;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * <h1>DrowCaretListener</h1>
 * <p>
 * This class is used as a wrapper for CaretListener functions
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowCaretListener implements CaretListener {

	private int dot;
	private int mark;
	
	/**
	 * This updates the dot and mark of the caret
	 * and assigns them to local fields.
	 * 
	 * @param e - the caret event object.
	 */
	@Override
	public void caretUpdate(CaretEvent e) {
		this.dot = e.getDot();
		this.mark = e.getMark();
	}
	
	/**
	 * Gets the dot of the caret.
	 * @return int - the caret's dot
	 */
	public int getDot() {
		return dot;
	}
	
	/**
	 * Gets the mark of the caret.
	 * @return int - the other end of the selection,
	 * if there is no selection, it will return
	 * the same value as getDot()
	 */
	public int getMark() {
		return mark;
	}
	
	/**
	 * Gets the lesser of the dot and mark.
	 * @return int - the lesser value of dot and mark
	 */
	public int getLesser() {
		if(dot <= mark)
			return dot;
		else
			return mark;
	}
	
	/**
	 * Gets the absolute value of dot - mark
	 * @return int - the difference between the dot and mark
	 */
	public int getDiff() {
		return Math.abs(mark - dot);
	}
}