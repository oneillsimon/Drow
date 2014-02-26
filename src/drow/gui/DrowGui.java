package drow.gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.text.DefaultEditorKit;

import drow.io.Filters;
import drow.manager.DrowDocumentManager;
import drow.view.DocumentView;

public class DrowGui {
	
	private DocumentView docView;
	private DrowDocumentManager docManager;
	
	private String resDirectory;
	
	private JFileChooser fileChooser;
	private JScrollPane scroll;
	private JMenuBar jmb;
	private JMenu menuFile;
	private JMenu menuEdit;
	private JToolBar toolBar;
	private JButton buttonCut;
	private JButton buttonCopy;
	private JButton buttonPaste;
	
	private ActionMap actionMap;
	private Action actionCut;
	private Action actionCopy;
	private Action actionPaste;
	private Action actionNew;
	private Action actionOpen;
	private Action actionSave;
	private Action actionSaveAs;
	private Action actionQuit;
	
	public DrowGui(DocumentView docView) {
		
		
		this.docView = docView;
		
		Filters.setUpFilters();
		
		resDirectory = "res/";
		
		fileChooser = new JFileChooser(System.getProperty("user.dir"));
		docManager = docView.getDrowDocumentManager();
		
		scroll = new JScrollPane(docView.getDrowDocument().getTextPane(),
								 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
								 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jmb = new JMenuBar();
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		toolBar = new JToolBar();
		
		
		docView.setJMenuBar(jmb);
		docView.add(scroll,BorderLayout.CENTER);
		docView.add(toolBar, BorderLayout.NORTH);
		
		
		
		actionMap = docView.getDrowDocument().getActionMap();
		actionCut = actionMap.get(DefaultEditorKit.cutAction);
		actionCopy = actionMap.get(DefaultEditorKit.copyAction);
		actionPaste = actionMap.get(DefaultEditorKit.pasteAction);
		
		setUpActions();
		
		setUpMenuBar();
		setUpFileMenu();
		setUpEditMenu();
		setUpToolBar();
		setUpToolBarButtons();
		setUpFileFilters();
	}
	
	private void setUpMenuBar() {
		jmb.add(menuFile); 
		jmb.add(menuEdit);
	}
	
	private void setUpFileMenu() {
		menuFile.add(actionNew);
		menuFile.add(actionOpen);
		menuFile.add(actionSave);
		menuFile.add(actionQuit);
		menuFile.add(actionSaveAs);

		for(int i=0; i<4; i++)
			menuFile.getItem(i).setIcon(null);
	}
	
	private void setUpEditMenu() {
		menuEdit.add(actionCut);
		menuEdit.add(actionCopy);
		menuEdit.add(actionPaste);
        
		menuEdit.getItem(0).setText("Cut");
		menuEdit.getItem(1).setText("Copy");
		menuEdit.getItem(2).setText("Paste");
	}
	
	private void setUpToolBar() {
		toolBar.add(actionNew);
		toolBar.add(actionOpen);
		toolBar.add(actionSave);
		toolBar.addSeparator();
		toolBar.setFloatable(false);
	}
	
	private void setUpToolBarButtons() {
		buttonCut = toolBar.add(actionCut);
		buttonCopy = toolBar.add(actionCopy);
		buttonPaste = toolBar.add(actionPaste);

		buttonCut.setText(null);
		buttonCut.setIcon(new ImageIcon(resDirectory + "cut.gif"));
		
		buttonCopy.setText(null); 
		buttonCopy.setIcon(new ImageIcon(resDirectory + "copy.gif"));
		
		buttonPaste.setText(null); 
		buttonPaste.setIcon(new ImageIcon(resDirectory + "paste.gif"));
	}
	
	private void setUpFileFilters()
	{
		for(int i = 0; i < Filters.getExtensions().size(); i++) {
			fileChooser.addChoosableFileFilter(Filters.getExtensions().get(i));
		}
		
		fileChooser.setFileFilter(Filters.TXT);
	}
	
	private void setUpActions() {
		actionNew = new AbstractAction("New", new ImageIcon(resDirectory + "new.gif")) {
			
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if(!docView.getCurrentFileName().equals("Untitled"))
					docManager.saveFile(docView.getCurrentFileName(), fileChooser.getFileFilter());
				else
					docManager.saveFileAs();
				new DocumentView();
			}
		};
		
		
		actionOpen = new AbstractAction("Open", new ImageIcon(resDirectory + "open.gif")) {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Open a file");
				
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					String[] split = fileChooser.getSelectedFile().getName().split("\\.");
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					String extension = split[split.length  -1];
					
					docManager.openFile(filePath, Filters.getFilterFromString(extension));
				}
				actionSaveAs.setEnabled(true);
			}
		};

		actionSave = new AbstractAction("Save", new ImageIcon(resDirectory + "save.gif")) {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				if(docView.getCurrentFileName().equals("Untitled"))
					docManager.saveFileAs();
				else
					docManager.saveFile(docView.getCurrentFileName(), fileChooser.getFileFilter());
			}
		};

		actionSaveAs = new AbstractAction("Save as...") {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					docManager.saveFile(fileChooser.getSelectedFile().getName(), fileChooser.getFileFilter());
				}
			}
		};

		actionQuit = new AbstractAction("Quit") {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				docView.dispose();
			}
		};

	}

	
	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public ActionMap getActionMap() {
		return actionMap;
	}

	public Action getActionCut() {
		return actionCut;
	}

	public Action getActionCopy() {
		return actionCopy;
	}

	public Action getActionPaste() {
		return actionPaste;
	}

	public Action getActionNew() {
		return actionNew;
	}

	public Action getActionOpen() {
		return actionOpen;
	}

	public Action getActionSave() {
		return actionSave;
	}

	public Action getActionSaveAs() {
		return actionSaveAs;
	}

	public Action getActionQuit() {
		return actionQuit;
	}
}
