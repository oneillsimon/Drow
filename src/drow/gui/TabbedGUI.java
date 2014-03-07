package drow.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.plaf.*;  
import javax.swing.plaf.metal.*; 

import drow.view.DocumentView;


public class TabbedGUI {
	
	private JPanel panel1,panel2,panel3,panel4,panel5;
	private DocumentView docView;
	
	public TabbedGUI(DocumentView docView) {
		
		
		
		
		this.docView = docView;

		
		
		this.docView.setIconImage(new ImageIcon("res/drow.png").getImage());
		
		this.docView.setTitle("drow");
		
		
		//Instantiate panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());

		//Create the tab pages
		createPage1(panel1);
		createPage2(panel2);
		createPage3(panel3);
		createPage4(panel4);
		createPage5(panel5);

		//tab names
		tabbedPane.addTab("Home       ", panel1);
		tabbedPane.addTab("Insert     ", panel2);
		tabbedPane.addTab("Layout     ", panel3);
		tabbedPane.addTab("Language   ", panel4);
		tabbedPane.addTab("Format     ", panel5);



		JSplitPane vPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, docView.getDrowDocument().getTextPane());

		docView.getContentPane().add(vPane);
		docView.setSize(500,200);
		vPane.setDividerLocation(docView.getHeight()/2);

		docView.setLocationRelativeTo(null);

		}


	public void createPage1(JPanel panel1)
	{	
		//Font Selection
		String[] fontStrings = { "Select Font", "Times New Roman", "Calabri", "Helvetica", "Tahom" };
		JComboBox FontList = new JComboBox(fontStrings);
		FontList.setSelectedIndex(4);
		panel1.add( new JComboBox());

		//Font Selection
		String[] sizeStrings = { "  ", "10", "11", "12", "15" };
		JComboBox FontSize = new JComboBox(sizeStrings);
		FontSize.setSelectedIndex(4);
		panel1.add( new JComboBox());


		//NEEDS ACTION LISTENER


		panel1.add( new JButton( "B" ));
		panel1.add( new JButton( "I" ));
		panel1.add( new JButton( "U" ));
		panel1.add( new JButton( "btn4" ));
		panel1.add( new JButton( "btn5" ));
		panel1.setLayout(new FlowLayout());


	}

	public void createPage2(JPanel panel2)
	{
		//Insert Table
		String[] tableStrings = { "table  ", "2x2", "2x3", "2x4", "2x5" };
		JComboBox tabelSize = new JComboBox(tableStrings);
		tabelSize.setSelectedIndex(4);
		panel2.add( new JComboBox());



		panel2.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel2.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel2.add( new JButton( "btn" ), BorderLayout.EAST );
		panel2.add( new JButton( "btn" ), BorderLayout.WEST );
		panel2.add( new JButton( "btn" ), BorderLayout.CENTER );
	}

	public void createPage3(JPanel panel3)
	{
		panel3.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel3.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel3.add( new JButton( "btn" ), BorderLayout.EAST );
		panel3.add( new JButton( "btn" ), BorderLayout.WEST );
		panel3.add( new JButton( "btn" ), BorderLayout.CENTER );


	}
	public void createPage4(JPanel panel4)
	{


		panel4.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel4.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel4.add( new JButton( "btn" ), BorderLayout.EAST );
		panel4.add( new JButton( "btn" ), BorderLayout.WEST );
		panel4.add( new JButton( "btn" ), BorderLayout.CENTER );
	}
	public void createPage5(JPanel panel5)
	{


		panel5.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel5.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel5.add( new JButton( "btn" ), BorderLayout.EAST );
		panel5.add( new JButton( "btn" ), BorderLayout.WEST );
		panel5.add( new JButton( "btn" ), BorderLayout.CENTER );
	}


}
