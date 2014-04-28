package drow.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import drow.tables.TableListener;
import drow.io.DrowIOActionManager;
import drow.view.DocumentView;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Label;

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
		btnImage.setText("Picture");
		btnImage.setBounds(323, 11, 33, 31);
		actionManager = new DrowIOActionManager(documentView);
		
		tableListener = new TableListener(this);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(168, 9, 61, 33);
		btnInsert.setActionCommand("insert");
		lblRows = new JLabel("Rows");
		lblRows.setBounds(76, 28, 42, 14);
		lblCols = new JLabel("Columns");
		lblCols.setBounds(76, 9, 57, 14);
		txtRows = new IntTextField(DEFAULT_VALUE, SIZE);
		txtRows.setBounds(128, 25, 30, 20);
		txtColumns = new IntTextField(DEFAULT_VALUE, SIZE);
		txtColumns.setBounds(128, 6, 30, 20);
		
		btnImage.setAction(actionManager.insertImageAction());
		
		btnInsert.addActionListener(tableListener);
		setLayout(null);
		this.add(lblCols);
		this.add(txtColumns);
		this.add(lblRows);
		this.add(txtRows);
		this.add(btnInsert);
		this.add(btnImage);
		
		Label label = new Label("table");
		label.setBounds(27, 0, 33, 14);
		add(label);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		horizontalBox.setBounds(10, 6, 265, 39);
		add(horizontalBox);
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