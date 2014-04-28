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

public class DrowIOActionManager {
	private static DrowDocumentManager docManager;
	private static JFileChooser fileChooser;
	private static DocumentView docView;
	
	public DrowIOActionManager(DocumentView docView) {
		docManager = docView.getDrowDocumentManager();
		fileChooser = new JFileChooser(System.getProperty("user.dir"));
		Filters.addFiltersToFileChooser(fileChooser);
		DrowIOActionManager.docView = docView;
	}
	
	public Action openAction() {
		return new AbstractAction("Open", new ImageIcon("res/buttonIcons/open.gif")) {
			
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
	
	public Action saveAction() {
		return new AbstractAction("Save", new ImageIcon("res/buttonIcons/save.gif")) {
			
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
	
	public Action newAction() {
		return new AbstractAction("New", new ImageIcon("res/buttonIcons/new.gif")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				docView.getDrowDocument().add(docView.getDrowDocument().newPage());
			}
		};
	}
	
	public Action fullScreenAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/fullscreen.png")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FullScreenDocument(docView);
			}
		};
	}
	
	public Action devModeAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/Developer.png")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
					docView.getGui().setTabbedPane(new DeveloperTabs(docView));
					docView.getDrowDocument().swapToDevListeners();
					DocumentView.IS_IN_DEV_MODE = true;
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					SwingUtilities.updateComponentTreeUI(docView);
				} catch (Exception e) {
				}
				
				docView.repaint();
				docView.revalidate();
			}
		};
	}
	
	public Action wordModeAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/Developer.png")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
					docView.getGui().setTabbedPane(new WordTabs(docView));
					docView.getDrowDocument().swapToWordListeners();
					DocumentView.IS_IN_DEV_MODE = false;
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					SwingUtilities.updateComponentTreeUI(docView);
				} catch (Exception e) {
				}
				
				docView.repaint();
				docView.revalidate();
			}
		};
	}
	
	public Action insertImageAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/picture.gif")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Insert an image");
				
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					docView.getDrowDocument().getFocusedPage().insertIcon(new ImageIcon(file.getAbsolutePath()));
					System.out.println(file.getAbsolutePath());
				}
			}
		};
	}
}
