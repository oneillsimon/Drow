package drow.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InsertPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JButton btnInsertPicture;
	JButton btnInsertTable;
	JButton btnBulletPoints;
	JButton btnNumberPoints;
	
	GridBagConstraints gbc_btnInsertPicture;
	GridBagConstraints gbc_btnInsertTable;
	GridBagConstraints gbc_btnBulletPoints;
	GridBagConstraints gbc_btnNumberPoints;
	GridBagLayout gridBagLayout;
	
	public InsertPanel() {
		
		gridBagLayout = new GridBagLayout();
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);
		
		btnInsertPicture = new JButton("Insert Picture");
		btnInsertTable = new JButton("Insert Table");
		btnBulletPoints = new JButton("b");
		btnNumberPoints = new JButton("n");
		
		gbc_btnInsertPicture = new GridBagConstraints();
		gbc_btnInsertTable = new GridBagConstraints();
		gbc_btnBulletPoints = new GridBagConstraints();
		gbc_btnNumberPoints = new GridBagConstraints();
		
		gbc_btnInsertPicture.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsertPicture.gridx = 0;
		gbc_btnInsertPicture.gridy = 0;
		
		gbc_btnInsertTable.insets = new Insets(0, 0, 0, 5);
		gbc_btnInsertTable.gridx = 0;
		gbc_btnInsertTable.gridy = 1;
		
		gbc_btnBulletPoints.insets = new Insets(0, 0, 5, 5);
		gbc_btnBulletPoints.gridx = 1;
		gbc_btnBulletPoints.gridy = 0;
		
		gbc_btnNumberPoints.insets = new Insets(0, 0, 0, 5);
		gbc_btnNumberPoints.gridx = 1;
		gbc_btnNumberPoints.gridy = 1;
		
		this.add(btnInsertPicture, gbc_btnInsertPicture);
		this.add(btnInsertTable, gbc_btnInsertTable);
		this.add(btnBulletPoints, gbc_btnBulletPoints);
		this.add(btnNumberPoints, gbc_btnNumberPoints);
	}
}