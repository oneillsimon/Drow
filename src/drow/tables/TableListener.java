package drow.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.EditorKit;

import sl.docx.DocxDocument;
import drow.document.DrowPage;
import drow.gui.InsertPanel;
import drow.view.DocumentView;

public class TableListener implements ActionListener {

	final static int PAGE_WIDHT = DrowPage.WIDTH;
	
	private DocumentView docView;
	private InsertPanel panel;
	
	public TableListener(DocumentView view, InsertPanel panel) {
		this.docView = view;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int r = panel.getTxtRows().getValue();
		int c = panel.getTxtColumns().getValue();
		initTable(r, c);
	}
	
	private void initTable(int rows, int cols) {
		TableDocument doc = (TableDocument) docView.getDrowDocument().getFocusedPage().getDocument();
        
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
