package drow.io;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.text.DefaultEditorKit;

import drow.document.DrowDocumentManager;
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
	
	public Action saveAction() {
		return new AbstractAction("Save", new ImageIcon("res/save.gif")) {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Save a file");
				
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					String[] split = fileChooser.getSelectedFile().getName().split("\\.");
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					String extension = split[split.length  -1];
					
					docManager.saveFile(filePath, Filters.getFilterFromString(extension));
				}
			}
		};
	}

	public Action copyAction() {
		return new AbstractAction("", new ImageIcon("res/copy.gif")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				docView.getDrowDocument().getActionMap().get(DefaultEditorKit.copyAction);
				System.out.println("Copy");
			}
		};
	}
	
	public Action cutAction() {
		return new AbstractAction("", new ImageIcon("res/cut.gif")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				docView.getDrowDocument().getActionMap().get(DefaultEditorKit.cutAction);
				System.out.println("Cut");
			}
		};
	}
	
	public Action pasteAction() {
		return new AbstractAction("", new ImageIcon("res/paste.gif")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				docView.getDrowDocument().getActionMap().get(DefaultEditorKit.pasteAction);
				System.out.println("paste");
			}
		};
	}
}
