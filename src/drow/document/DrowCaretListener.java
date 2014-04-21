package drow.document;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class DrowCaretListener implements CaretListener {

	private int dot;
	private int mark;
	
	@Override
	public void caretUpdate(CaretEvent e) {
		this.dot = e.getDot();
		this.mark = e.getMark();
	}
	
	public int getDot() {
		return dot;
	}
	
	public int getMark() {
		return mark;
	}
	
	public int getLesser() {
		if(dot <= mark)
			return dot;
		else
			return mark;
	}
	
	public int getDiff() {
		return Math.abs(mark - dot);
	}
}
