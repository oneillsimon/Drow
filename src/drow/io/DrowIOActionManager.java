package drow.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import drow.manager.DrowDocumentManager;
import drow.view.DocumentView;

public class DrowIOActionManager {
	private static DrowDocumentManager docManager;
	private static JFileChooser fileChooser;
	
	public DrowIOActionManager(DocumentView docView) {
		docManager = docView.getDrowDocumentManager();
		fileChooser = new JFileChooser(System.getProperty("user.dir"));
		Filters.addFiltersToFileChooser(fileChooser);
	}
	
	public ActionListener exportListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					docManager.saveFile(fileChooser.getSelectedFile().getName(),
										fileChooser.getFileFilter());
				}
			}
			
		};
	}
	
	public ActionListener importListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					docManager.openFile(fileChooser.getSelectedFile().getName(),
										fileChooser.getFileFilter());
				}
			}
		};
	}
}
