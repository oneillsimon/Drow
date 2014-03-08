package drow.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import drow.styles.DrowStyleActions;
import drow.view.DocumentView;

public class TabbedGUI {
	
	private DocumentView docView;
	
	private JTabbedPane tabbedPane;
	private JSplitPane verticalSplitPane;
	
	private JPanel panelHome;
	private JPanel panelInsert;
	private JPanel panelLayout;
	private JPanel panelLanguage;
	private JPanel panelFormat;
	
	public TabbedGUI(DocumentView docView) {
		
		this.docView = docView;

		this.docView.setIconImage(new ImageIcon("res/drow.png").getImage());
		this.docView.setTitle("drow");
		
		tabbedPane = new JTabbedPane();
		verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
										   tabbedPane,
										   docView.getDrowDocument().getTextPane());
		
		//Instantiate panels
		panelHome = createPanelHome();
		panelInsert = createPanelInsert();
		panelLayout = createPanelLayout();
		panelLanguage = createPanelLanguage();
		panelFormat = createPanelFormat();
		
		//tab names
		tabbedPane.addTab("Home", panelHome);
		tabbedPane.addTab("Insert", panelInsert);
		tabbedPane.addTab("Layout", panelLayout);
		tabbedPane.addTab("Language", panelLanguage);
		tabbedPane.addTab("Format", panelFormat);

		docView.getContentPane().add(verticalSplitPane);
		docView.setSize(500,200);
		verticalSplitPane.setDividerLocation(docView.getHeight()/2);
		verticalSplitPane.setDividerSize(0);
	}

	public JPanel createPanelHome()
	{	
		JPanel panel = new JPanel();
		
		JButton buttonBold = new JButton("B");
		JButton buttonItalic = new JButton("I");
		JButton buttonUnderline = new JButton("U");
		
		//Font Selection
		String[] fonts = { "Times New Roman", "Calabri", "Helvetica", "Tahom" };
		JComboBox<String> fontList = new JComboBox<String>(fonts);

		//Font Selection
		String[] sizes = { "8", "10", "11", "12", "16" };
		JComboBox<String> fontSizeList = new JComboBox<String>(sizes);


		//NEEDS ACTION LISTENER
		buttonBold.setAction(DrowStyleActions.bold);

		
		panel.add(fontList);
		panel.add(fontSizeList);
		panel.add(buttonBold);
		panel.add(buttonItalic);
		panel.add(buttonUnderline);

		return panel;
	}

	public JPanel createPanelInsert()
	{
		JPanel panel = new JPanel();
		
		//Insert Table
		String[] tableStrings = { "table  ", "2x2", "2x3", "2x4", "2x5" };
		JComboBox tabelSize = new JComboBox(tableStrings);
		tabelSize.setSelectedIndex(4);
		panel.add( new JComboBox());

		panel.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel.add( new JButton( "btn" ), BorderLayout.EAST );
		panel.add( new JButton( "btn" ), BorderLayout.WEST );
		panel.add( new JButton( "btn" ), BorderLayout.CENTER );
		
		return panel;
	}

	public JPanel createPanelLayout()
	{
		JPanel panel = new JPanel();
		
		panel.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel.add( new JButton( "btn" ), BorderLayout.EAST );
		panel.add( new JButton( "btn" ), BorderLayout.WEST );
		panel.add( new JButton( "btn" ), BorderLayout.CENTER );
		
		return panel;
	}
	public JPanel createPanelLanguage()
	{
		JPanel panel = new JPanel();
		
		panel.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel.add( new JButton( "btn" ), BorderLayout.EAST );
		panel.add( new JButton( "btn" ), BorderLayout.WEST );
		panel.add( new JButton( "btn" ), BorderLayout.CENTER );
		
		return panel;
	}
	public JPanel createPanelFormat()
	{
		JPanel panel = new JPanel();
		
		panel.add( new JButton( "btn" ), BorderLayout.NORTH );
		panel.add( new JButton( "btn" ), BorderLayout.SOUTH );
		panel.add( new JButton( "btn" ), BorderLayout.EAST );
		panel.add( new JButton( "btn" ), BorderLayout.WEST );
		panel.add( new JButton( "btn" ), BorderLayout.CENTER );
		
		return panel;
	}


}
