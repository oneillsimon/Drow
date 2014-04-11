package drow.manager;

import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import drow.io.Exporter;
import drow.io.Importer;
import drow.view.DocumentView;

public class DrowDocumentManager extends JFrame {

	private static final long serialVersionUID = 1L;

	private Importer importer;
	private Exporter exporter;

	public DrowDocumentManager(DocumentView docView) {
		this.importer = new Importer(docView);
		this.exporter = new Exporter(docView);
	}

	public void saveFile(String fileName, FileFilter filter) {
		exporter.exportFile(fileName, filter);
	}

	public void saveFileAs() {
		// saveFile(textPanel.getCurrentFile(),
		// (DrowFileFilter)fileChooser.getFileFilter());
	}

	public void openFile(String fileName, FileFilter filter) {
		importer.importFile(fileName, filter);
	}
}
