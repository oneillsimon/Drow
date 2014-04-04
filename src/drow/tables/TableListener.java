package drow.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import drow.gui.InsertPanel;
import drow.view.DocumentView;

public class TableListener implements ActionListener {
	
	DocumentView docView;
	InsertPanel panel;
	final static int PAGE_WIDHT = 500;
	
	public TableListener(DocumentView view, InsertPanel panel) {
		this.docView = view;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int r = panel.txtRows.getValue();
		int c = panel.txtColumns.getValue();
		System.out.println("insert button pressed");
		initTable(r, c);
	}
	
	private void initTable(int rows, int cols) {
        TableDocument doc = (TableDocument) docView.getDrowDocument().getPage().getTextPane().getDocument();
        // figure out a the total width of the page then break it up.
        int[] colArray = new int[cols];
        int averageCol = PAGE_WIDHT / cols;
        
        for (int i =0; i< colArray.length; i++) {
        	colArray[i] = averageCol;
        }
        
        // get the caret ??? can we make a static class that tracks the caret array
        
        doc.insertTable(0, rows, colArray);
    }
}
