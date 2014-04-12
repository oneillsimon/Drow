package drow.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.EditorKit;

import drow.tables.TableDocument;
import drow.tables.TableEditorKit;
import drow.tables.TableListener;
import drow.io.DrowIOActionManager;
import drow.io.ImageImport;
import drow.styles.DrowStyleActionManager;
import drow.view.DocumentView;

public class InsertPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 3;
	private static final int DEFAULT_VALUE = 3;
	
	private JButton btnInsert;
	private IntTextField txtRows, txtColumns;
	private JLabel lblRows, lblCols;
	private DocumentView docView;
	private TableListener listener;
	
	private DrowStyleActionManager styleActionManager;
	private DrowIOActionManager ioActionManager;
	
	private JButton btnImage;	 
	//private DrowIOActionManager ioActionManager;
	private ImageImport imageImport;
	
	public InsertPanel(DocumentView docView) {
		 
		
		btnImage = new JButton(new ImageIcon("res/picture.gif"));
		imageImport = new ImageImport();
		btnImage.addActionListener(imageImport.insertActionPerformed(docView.getDrowDocument().getFocusedPage()));
		
		this.docView = docView;
		listener = new TableListener(docView, this);
		
		//docView.getDrowDocument().getFocusedPage().setEditorKit(new TableEditorKit());
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