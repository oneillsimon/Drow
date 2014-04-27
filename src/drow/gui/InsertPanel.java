package drow.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import drow.tables.TableListener;
import drow.io.DrowIOActionManager;
import drow.view.DocumentView;

/**
 * <h1>InsertPanel</h1>
 * This class contains controls for inserting tables and pictures. 
 */
public class InsertPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int SIZE = 3;
	private static final int DEFAULT_VALUE = 3;
	
	/** JButton for inserting a table. */
	private JButton btnInsert;

	/** JButton for inserting an image. */
	private JButton btnImage;
	
	/** TextField for table rows. */
	private IntTextField txtRows;
	
	/** TextField for table columns. */
	private IntTextField txtColumns;
	
	/** JLabel for table rows. */
	private JLabel lblRows;
	
	/** JLabel for table columns. */
	private JLabel lblCols;
	
	/** Table listener for actions on the table. */
	private TableListener tableListener;
	
	/** DowIOAction manager for IO actions. */
	private DrowIOActionManager actionManager;
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the document.
	 */
	public InsertPanel(DocumentView documentView) {
		 
		btnImage = new JButton(new ImageIcon("res/picture.gif"));
		actionManager = new DrowIOActionManager(documentView);
		
		tableListener = new TableListener(this);
		
		btnInsert = new JButton("Insert");
		btnInsert.setActionCommand("insert");
		lblRows = new JLabel("Number of Rows");
		lblCols = new JLabel("Number of Columns");
		txtRows = new IntTextField(DEFAULT_VALUE, SIZE);
		txtColumns = new IntTextField(DEFAULT_VALUE, SIZE);
		
		btnImage.setAction(actionManager.insertImageAction());
		
		btnInsert.addActionListener(tableListener);
		this.add(lblCols);
		this.add(txtColumns);
		this.add(lblRows);
		this.add(txtRows);
		this.add(btnInsert);
		this.add(btnImage);	
	}

	/**
	 * Gets the txtRows
	 * @return IntTextField - The IntTextField for the rows.
	 */
	public IntTextField getTxtRows() {
		return txtRows;
	}

	/**
	 * Gets the txtColumns
	 * @return IntTextField - The IntTextField for the columns.
	 */
	public IntTextField getTxtColumns() {
		return txtColumns;
	}
}