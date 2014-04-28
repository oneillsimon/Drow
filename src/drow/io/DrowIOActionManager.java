package drow.io;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import drow.document.DrowDocumentManager;
import drow.document.FullScreenDocument;
import drow.gui.DeveloperTabs;
import drow.gui.WordTabs;
import drow.view.DocumentView;

/**
 * <h1>DrowIOActionManager</h1>
 * Handles all the input and output actions.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowIOActionManager {
	
	/** The document manager. */
	private static DrowDocumentManager docManager;
	
	/** The file chooser for browsing the system's directories. */
	private static JFileChooser fileChooser;
	
	/** The view containing the document. */
	private static DocumentView documentView;
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the document.
	 */
	public DrowIOActionManager(DocumentView documentView) {
		docManager = documentView.getDrowDocumentManager();
		fileChooser = new JFileChooser(System.getProperty("user.dir"));
		Filters.addFiltersToFileChooser(fileChooser);
		DrowIOActionManager.documentView = documentView;
	}
	
	/**
	 * Action for opening a file.
	 * @return - An abstract action that will open a file.
	 */
	public Action openAction() {
		return new AbstractAction("Open", new ImageIcon("res/open.gif")) {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Open a file");
				
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					String[] split = fileChooser.getSelectedFile().getName().split("\\.");
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					String extension = split[split.length  -1];
					
					docManager.openFile(filePath, Filters.getFilterFromString(extension));
				}
			}
		};
	}
	
	/**
	 * Action for saving a file.
	 * @return - An abstract action for saving a file.
	 */
	public Action saveAction() {
		return new AbstractAction("Save", new ImageIcon("res/save.gif")) {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Save a file");
				
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					docManager.saveFile(filePath, fileChooser);
				}
			}
		};
	}
	
	/**
	 * Action for creating a new page in the document.
	 * @return - An abstract action for inserting a new page into the document.
	 */
	public Action newAction() {
		return new AbstractAction("New", new ImageIcon("res/new.gif")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				documentView.getDrowDocument().add(documentView.getDrowDocument().newPage());
			}
		};
	}
	
	/**
	 * Action for entering a full screen mode.
	 * @return - An abstract action for entering a full screen mode.
	 */
	public Action fullScreenAction() {
		return new AbstractAction("", new ImageIcon("res/fullscreen.png")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FullScreenDocument(documentView);
			}
		};
	}
	
	/**
	 * Action to switch the GUI to developer mode.
	 * @return - An abstract action for switching to developer mode.
	 */
	public Action devModeAction() {
		return new AbstractAction("", new ImageIcon("res/Developer.png")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
					documentView.getGui().setTabbedPane(new DeveloperTabs(documentView));
					documentView.getDrowDocument().swapToDevListeners();
					DocumentView.IS_IN_DEV_MODE = true;
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					SwingUtilities.updateComponentTreeUI(documentView);
				} catch (Exception e) {
				}
				
				documentView.repaint();
				documentView.revalidate();
			}
		};
	}
	
	/**
	 * Action to switch the GUI to word mode.
	 * @return - An abstract action for switching to word mode.
	 */
	public Action wordModeAction() {
		return new AbstractAction("", new ImageIcon("res/Developer.png")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
					documentView.getGui().setTabbedPane(new WordTabs(documentView));
					documentView.getDrowDocument().swapToWordListeners();
					DocumentView.IS_IN_DEV_MODE = false;
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					SwingUtilities.updateComponentTreeUI(documentView);
				} catch (Exception e) {
				}
				
				documentView.repaint();
				documentView.revalidate();
			}
		};
	}
	
	/**
	 * Action to insert an image into the document.
	 * @return - An abstract action to insert an image into the document.
	 */
	public Action insertImageAction() {
		return new AbstractAction("", new ImageIcon("res/Developer.png")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Insert an image");
				
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					documentView.getDrowDocument().getFocusedPage().insertIcon(new ImageIcon(file.getAbsolutePath()));
				}
			}
		};
	}
}
