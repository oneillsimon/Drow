package drow.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import drow.tables.TableListener;
import drow.io.ImageImport;
import drow.view.DocumentView;

public class InsertPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 3;
	private static final int DEFAULT_VALUE = 3;
	
	private JButton btnInsert;
	private IntTextField txtRows, txtColumns;
	private JLabel lblRows, lblCols;
	private TableListener listener;
	
	private JButton btnImage;	 
	private ImageImport imageImport;
	
	public InsertPanel(DocumentView docView) {
		 
		
		btnImage = new JButton(new ImageIcon("res/picture.gif"));
		imageImport = new ImageImport();
		btnImage.addActionListener(imageImport.insertActionPerformed(docView.getDrowDocument().getFocusedPage()));
		
		listener = new TableListener(docView, this);
		
		btnInsert = new JButton("Insert");
		btnInsert.setActionCommand("insert");
		lblRows = new JLabel("Number of Rows");
		lblCols = new JLabel("Number of Columns");
		txtRows = new IntTextField(DEFAULT_VALUE, SIZE);
		txtColumns = new IntTextField(DEFAULT_VALUE, SIZE);
		
		btnInsert.addActionListener(listener);
		this.add(lblCols);
		this.add(txtColumns);
		this.add(lblRows);
		this.add(txtRows);
		this.add(btnInsert);
		this.add(btnImage);	
	}

	public IntTextField getTxtRows() {
		return txtRows;
	}

	public IntTextField getTxtColumns() {
		return txtColumns;
	}

	public JLabel getLblRows() {
		return lblRows;
	}

	public JLabel getLblCols() {
		return lblCols;
	}
}