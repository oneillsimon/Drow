package drow.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import drow.document.DrowPage;
import drow.gui.InsertPanel;
import drow.view.DocumentView;

public class TableListener implements ActionListener {

	final static int PAGE_WIDHT = DrowPage.WIDTH;
	
	private InsertPanel panel;
	/**
	 * <h1> </h1>
	 * @param view
	 * @param panel
	 */
	public TableListener(DocumentView view, InsertPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int r = panel.getTxtRows().getValue();
		int c = panel.getTxtColumns().getValue();
		initTable(r, c);
	}
	/**
	 * This method has been stripped down due to integration issues.
	 * Tables are currently not working.
	 * @param rows
	 * @param cols
	 */
	private void initTable(int rows, int cols) {
		// DocxDocument tabledoc = new TableDocument();
        // figure out a the total width of the page then break it up.
        int[] colArray = new int[cols];
        int averageCol = PAGE_WIDHT / cols;
        
        for (int i =0; i< colArray.length; i++) {
        	colArray[i] = averageCol;
        }
        
        // get the caret ??? can we make a static class that tracks the caret array
        
        //tabledoc.insertTable(0, rows, colArray);
        //
    }
}
